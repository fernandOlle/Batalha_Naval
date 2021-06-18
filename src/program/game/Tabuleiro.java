package program.game;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class  Tabuleiro extends Parent {

    private VBox rows = new VBox();
    private boolean inimigo = false;
    private int barcos = 4;

    public Tabuleiro(boolean inimigo, EventHandler<? super MouseEvent> handler) {
        this.inimigo = inimigo;
        for (int y = 0; y < 10; y++) {
            HBox row = new HBox();
            for (int x = 0; x < 10; x++) {
                Casa c = new Casa(x, y, this);
                c.setOnMouseClicked(handler);
                row.getChildren().add(c);
            }

            rows.getChildren().add(row);
        }

        getChildren().add(rows);
    }

    public int getBarcos(){
        return barcos;
    }

    public boolean botaBarco(Barco barco, int x, int y) {
        if (podeBotarBarco(barco, x, y)) {
            int length = barco.getTipo();

            if (barco.getVertical()) {
                for (int i = y; i < y + length; i++) {
                    Tabuleiro.Casa casa = getCasa(x, i);
                    casa.barco = barco;
                    if (!inimigo) {
                        casa.setFill(Color.WHITE);
                        casa.setStroke(Color.PURPLE);
                    }
                }
            }
            else {
                for (int i = x; i < x + length; i++) {
                    Tabuleiro.Casa casa = getCasa(i, y);
                    casa.barco = barco;
                    if (!inimigo) {
                        casa.setFill(Color.WHITE);
                        casa.setStroke(Color.PURPLE);
                    }
                }
            }
            return true;
        }
        return false;
    }

    public Tabuleiro.Casa getCasa(int x, int y) {
        return (Tabuleiro.Casa)((HBox)rows.getChildren().get(y)).getChildren().get(x);
    }

    private Casa[] pegaVizinhos(int x, int y) {
        Point2D[] pontos = new Point2D[] {
                new Point2D(x - 1, y),
                new Point2D(x + 1, y),
                new Point2D(x, y - 1),
                new Point2D(x, y + 1)
        };
        List<Tabuleiro.Casa> vizinhos = new ArrayList<Tabuleiro.Casa>();

        for (Point2D p : pontos) {
            if (ehPontoValido(p)) {
                vizinhos.add(getCasa((int)p.getX(), (int)p.getY()));
            }
        }
        return vizinhos.toArray(new Tabuleiro.Casa[0]);
    }

    private boolean podeBotarBarco(Barco barco, int x, int y) {
        int length = barco.getTipo();

        if (barco.getVertical()) {
            for (int i = y; i < y + length; i++) {
                if (!ehPontoValido(x, i))
                    return false;

                Tabuleiro.Casa casa = getCasa(x, i);
                if (casa.barco != null)
                    return false;

                for (Tabuleiro.Casa neighbor : pegaVizinhos(x, i)) {
                    if (!ehPontoValido(x, i))
                        return false;

                    if (neighbor.barco != null)
                        return false;
                }
            }
        }
        else {
            for (int i = x; i < x + length; i++) {
                if (!ehPontoValido(i, y))
                    return false;

                Tabuleiro.Casa cell = getCasa(i, y);
                if (cell.barco != null)
                    return false;

                for (Tabuleiro.Casa vizinho : pegaVizinhos(i, y)) {
                    if (!ehPontoValido(i, y))
                        return false;

                    if (vizinho.barco != null)
                        return false;
                }
            }
        }
        return true;
    }

    private boolean ehPontoValido(Point2D point) {
        return ehPontoValido(point.getX(), point.getY());
    }

    private boolean ehPontoValido(double x, double y) {
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }

    public class Casa extends Rectangle {
        public int x, y;
        public Barco barco = null;
        public boolean foiAtingida = false;

        public Tabuleiro tabuleiro;

        public Casa(int x, int y, Tabuleiro tabuleiro) {
            super(30, 30);
            this.x = x;
            this.y = y;
            this.tabuleiro = tabuleiro;
            setFill(Color.LIGHTGRAY);
            setStroke(Color.BLACK);
        }

        public boolean levouTiro() {
            foiAtingida = true;
            setFill(Color.BLACK);

            if (barco != null) {
                barco.levouTiro();
                setFill(Color.RED);
                if (!barco.estaVivo()) {
                    tabuleiro.barcos--;
                }
                return true;
            }
            return false;
        }
    }
}

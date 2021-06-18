package program.game;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import program.game.Tabuleiro.Casa;

import java.util.Random;

public class Programa extends Application {

    private boolean rodando = false;
    private Tabuleiro tabuleiroInimigo, tabuleiroPlayer;
    private int barcosHaColocar = 4;
    private boolean rodadaInimigo = false;
    private Random random = new Random();
    private int tipo = 4;
    private Stage window;

    private Parent criaTela() {
        BorderPane root = new BorderPane();

        root.setPrefSize(600, 670);

        Button btCaca = new Button("Caça");
        Button btPortaAvioes = new Button("Porta Aviões");
        Button btSubmarino = new Button("Submarino");
        Button btNavioEscolta = new Button("Navio de Escolta");

        VBox vbBtSet1 = new VBox(10, btCaca, btPortaAvioes);
        VBox vbBtSet2 = new VBox(10, btSubmarino, btNavioEscolta);
        VBox vbBts = new VBox(10, vbBtSet1, vbBtSet2);
        vbBts.setAlignment(Pos.BOTTOM_RIGHT);

        root.setRight(vbBts);

        tabuleiroInimigo = new Tabuleiro(true, event -> {

            // vai no update pq roda em tempo real

            if (!rodando)
                return;

            Casa casa = (Casa) event.getSource();
            if (casa.foiAtingida)
                return;

            rodadaInimigo = !casa.levouTiro();

            if (tabuleiroInimigo.getBarcos() == 0) {
                System.out.println("Você Ganhou");
                System.exit(0);
            }

            if (rodadaInimigo)
                movimentoInimigo();
        });

        tabuleiroPlayer = new Tabuleiro(false, event -> {

            // vai no update pq roda em tempo real

            if (rodando)
                return;

            if (barcosHaColocar > 1)
                this.tipo = barcosHaColocar;

            Tabuleiro.Casa casa = (Tabuleiro.Casa) event.getSource();
            if (tabuleiroPlayer.botaBarco(new Barco(this.tipo, event.getButton() == MouseButton.PRIMARY), casa.x, casa.y)) {
                if (--barcosHaColocar == 0) {
                    startGame();
                }
            }
        });

        VBox vbox = new VBox(50, tabuleiroInimigo, tabuleiroPlayer);
        vbox.setAlignment(Pos.TOP_LEFT);

        root.setLeft(vbox);

        return root;
    }

    private void movimentoInimigo() {
        while (rodadaInimigo) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Tabuleiro.Casa casa = tabuleiroPlayer.getCasa(x, y);
            if (casa.foiAtingida)
                continue;

            rodadaInimigo = casa.levouTiro();

            if (tabuleiroPlayer.getBarcos() == 0) {
                System.out.println("Você Perdeu");
                System.exit(0);
            }
        }
    }


    /**
     * Configura o tabuleiro do oponente
     */
    private void startGame() {
        // place enemy ships
        int contador = 4;
        int tipo = contador;

        while (contador > 0) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (contador > 1) {
                tipo = contador;
            }

            if (tabuleiroInimigo.botaBarco(new Barco(tipo, Math.random() < 0.5), x, y)) {
                contador--;
            }
        }
        rodando = true;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        Scene scene = new Scene( criaTela());

        Button bt = new Button("Começar");
        bt.setOnAction(event -> window.setScene(scene));

        BorderPane pane = new BorderPane();
        pane.setPrefSize(600, 670);
        VBox vbox = new VBox(bt);
        vbox.setAlignment(Pos.CENTER);
        pane.setCenter(vbox);
        Scene menu = new Scene(pane);


        window.setTitle("Batalha Naval");
        window.setScene(menu);
        window.setResizable(true);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

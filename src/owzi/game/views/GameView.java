package owzi.game.views;

import javafx.scene.input.MouseButton;
import owzi.engine.*;
import owzi.game.bean.Ship;
import owzi.game.gui.*;
import owzi.game.gui.Button;
import program.game.Barco;
import program.game.Tabuleiro;

import java.awt.*;
import java.util.Random;

public class GameView extends View {

    private int state = 1;

    private String playerName;
    private Boolean isRandom = false;
    private Boolean hasWon = true;
    private Random random = new Random();

    private PlayerBoard playerBoard;
    private EnemyBoard enemyBoard;

    private Button btnExit;

    private Button btnJet;
    private Button btnPlane;
    private Button btnScotter;
    private Button btnSub;

    public static final int PLAYERBOARD_CONFIG = 1;
    public static final int ENEMYBOARD_CONFIG = 2;
    public static final int PLAYERMOVE = 3;
    public static final int ENEMYMOVE = 4;
    public static final int GAMEOVER = 5;

    public GameView(String name, Game game) {
        super(name, game);
        Font font = FontLoader.load(
                "assets/fonts/Inconsolata-Regular.ttf",
                Font.BOLD,
                20f,
                Font.TRUETYPE_FONT
        );

        enemyBoard = new EnemyBoard(this);

        playerBoard = new PlayerBoard(this);

        btnExit = new Button(
                "Sair",
                getGame().getWidth() - 150,
                25,
                100,
                48
        );
        btnExit.setMouseListener(new MouseListener() {
            @Override
            public void whenLeftPressed() {
                getGame().exit(0);
            }
        });
        btnExit.setFont(font);

        btnJet = new Button(
                "Caça",
                getGame().getWidth() - 150,
                (int) (getGame().getHeight() * 0.75),
                100,
                48
        );
        btnJet.setMouseListener(new MouseListener() {
            @Override
            public void whenLeftPressed() {
                getGame().exit(0);
            }
        });
        btnJet.setFont(font);

        btnScotter = new Button(
                "Escolta",
                getGame().getWidth() - 150,
                (int) (getGame().getHeight() * 0.65),
                100,
                48
        );
        btnScotter.setMouseListener(new MouseListener() {
            @Override
            public void whenLeftPressed() {
                getGame().exit(0);
            }
        });
        btnScotter.setFont(font);

        btnPlane = new Button(
                "Porta",
                getGame().getWidth() - 270,
                (int) (getGame().getHeight() * 0.75),
                100,
                48
        );
        btnPlane.setMouseListener(new MouseListener() {
            @Override
            public void whenLeftPressed() {
                getGame().exit(0);
            }
        });
        btnPlane.setFont(font);

        btnSub = new Button(
                "Sub",
                getGame().getWidth() - 270,
                (int) (getGame().getHeight() * 0.65),
                100,
                48
        );
        btnSub.setMouseListener(new MouseListener() {
            @Override
            public void whenLeftPressed() {
                getGame().exit(0);
            }
        });
        btnSub.setFont(font);
    }

    @Override
    public void render(RenderStrategy renderStrategy) {
        Graphics2D g2d = renderStrategy.getStrategy();

        enemyBoard.render(g2d);
        playerBoard.render(g2d);
        btnExit.render(g2d);

        btnSub.render(g2d);
        btnJet.render(g2d);
        btnScotter.render(g2d);
        btnPlane.render(g2d);

        // DEBUG
        g2d.drawString(String.valueOf(state), getGame().getWidth() - 50, 15);
    }

    @Override
    public void update() {
        enemyBoard.update();
        playerBoard.update();
        btnExit.update();
        btnSub.update();
        btnJet.update();
        btnScotter.update();
        btnPlane.update();

        switch (state) {
            case PLAYERBOARD_CONFIG:

                playerConfigState();
                break;

            case ENEMYBOARD_CONFIG:

                enemyConfigState();
                break;

            case PLAYERMOVE:
                // jogada do player
                playerTurnState();
                break;

            case ENEMYMOVE:
                enemyTurnState();
                break;

            case GAMEOVER:
                // chama tela final
                finalState();
                break;

            default:
                System.out.println("Errors muitos Errors !");
                break;
        }

    }

    // qtd: 0 1 2 3 4
    // tam: 4 3 2 2 -
    // typ: 1 2 3 4 -
    private void playerConfigState() {
        if (playerBoard.isClicked() || isRandom) {
            int col, row;

            if (isRandom) {
                col = this.random.nextInt(10);
                row = this.random.nextInt(10);
            } else {
                playerBoard.setClicked(false);
                col = playerBoard.getColumn();
                row = playerBoard.getRow();
            }

            int shipCount = playerBoard.getShipCount();
            int size = 2;

            if (shipCount < 3)
                size = 4 - shipCount;

            boolean isVertical = GameSystem.isLeftButtonPressed();
            Ship newShip = new Ship(size, shipCount + 1, !isVertical); // (?)
            if (playerBoard.placeShip(newShip, col, row)) {
                playerBoard.setShipCount(playerBoard.getShipCount() + 1);
                if (playerBoard.getShipCount() == 4)
                    this.state = ENEMYBOARD_CONFIG;
                //playerBoard.setCellColors();
            }
        }
    }

    private void enemyConfigState() {
        int shipCount = enemyBoard.getShipCount();
        int size = 2;

        if (shipCount < 3)
            size = 4 - shipCount;

        int col = this.random.nextInt(10);
        int row = this.random.nextInt(10);

        Ship newShip = new Ship(size, shipCount + 1, Math.random() < 0.5); // (?)
        if (enemyBoard.placeShip(newShip, col, row)) {
            enemyBoard.setShipCount(enemyBoard.getShipCount() + 1);
            if (enemyBoard.getShipCount() == 4) {
                this.state = PLAYERMOVE;
            }
        }
    }

    private void playerTurnState() {
        try {
            if (enemyBoard.isClicked()) {
                enemyBoard.setClicked(false);
                // a celula que o player atacou
                int col = enemyBoard.getColumn();
                int row = enemyBoard.getRow();

                if (enemyBoard.getCell(col, row).getShip() != null) {
                    System.out.println("tinha barco!");
                }

                if (enemyBoard.getCell(col, row).wasShot(enemyBoard)) {
                    // acertou o bote
                    enemyBoard.getCell(col, row).setBackgroundColorDisable(new Color(0xFF0000));
                    if (enemyBoard.getShipCount() == 0) {
                        this.state = GAMEOVER;
                    }
                    return;
                }
                enemyBoard.getCell(col, row).setBackgroundColorDisable(new Color(0x2222222));
                this.state = ENEMYMOVE;
            }
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    int interval = 0;

    /**
     * - pega um x e y aleatorios para atacar o playerboard
     * - verificar se a celula
     */
    private void enemyTurnState() {
        // jogada do computador
        int col = random.nextInt(10);
        int row = random.nextInt(10);

        Cell cell = playerBoard.getCell(col, row);
        if (cell.wasShot(playerBoard)) {
            playerBoard.getCell(col, row).setBackgroundColorDisable(new Color(0xFF0000));
            if (playerBoard.getShipCount() == 0) {
                System.out.println("Você Perdeu");
                this.hasWon = false;
                this.state = GAMEOVER;
            }
            return;
        }

        playerBoard.getCell(col, row).setBackgroundColorDisable(new Color(0x2222222));
        this.state = PLAYERMOVE;
    }

    private void finalState() {
        FinalMenuView gv = (FinalMenuView) getGame().getViewManager().getView("FinalMenu");
        gv.setHasWon(this.hasWon);
        getGame().setCurrentViewKey("FinalMenu");
        this.state = PLAYERBOARD_CONFIG;
        playerBoard = new PlayerBoard(this);
        enemyBoard = new EnemyBoard(this);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Boolean getRandom() {
        return isRandom;
    }

    public void setRandom(Boolean random) {
        isRandom = random;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}

package owzi.game.gui;

import owzi.engine.Item;
import owzi.engine.View;
import owzi.game.bean.Ship;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Board extends Item {
    private Point point = new Point();
    private boolean isPlayer = true;
    private int shipCount = 0;
    private Cell[][] cells;
    private View view;
    private boolean clicked;

    public Board(int x, int y, int width, int height, View view) {
        super(x, y, width, height);
        this.view = view;
        buildBoard();
    }

    private void buildBoard() {
        this.clicked = false;
        cells = new Cell[10][10];
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                cells[col][row] = new Cell(getX() + (getWidth() * row), getY() + (getHeight() * col), getWidth(), getHeight());
                cells[col][row].setBackgroundColor(new Color(0x888888));
                final int tableColumn = col;
                final int tableRow = row;
                getCell(col, row).setMouseListener(new MouseListener() {
                    @Override
                    public void whenLeftPressed() {
                        setColumn(tableColumn);
                        setRow(tableRow);
                        clicked = true;
                    }

                    @Override
                    public void whenRightPressed() {
                        setColumn(tableColumn);
                        setRow(tableRow);
                        clicked = true;
                    }
                });
            }
        }
    }

    public abstract void setCellColors();

    public void update() {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                cells[i][j].update();
    }

    @Override
    public void render(Graphics2D g2d) {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                cells[i][j].render(g2d);
    }

    public boolean placeShip(Ship ship, int col, int row) {
        if (canPlaceShip(ship, col, row)) {
            int length = ship.getSize();
            boolean vertical = ship.isVertical();

            int k = vertical ? row : col;
            int k2 = vertical ? col : row;

            for (int i = k; i < k + length; i++) {
                Cell cell = vertical ? getCell(k2, i) : getCell(i, k2);
                cell.setShip(ship);
                if (isPlayer) cell.setEnabled(false);
            }

            return true;
        }
        return false;
    }

    public boolean canPlaceShip(Ship ship, int col, int row) {

        int length = ship.getSize();
        boolean vertical = ship.isVertical();
        int k = vertical ? row : col;
        int k2 = vertical ? col : row;

        for (int i = k; i < k + length; i++) {
            if (vertical ? !isValidPoint(k2, i) : !isValidPoint(i, k2))
                return false;

            Cell cell = vertical ? getCell(k2, i) : getCell(i, k2);
            if (cell.getShip() != null)
                return false;

            for (Cell neighbor :
                    (vertical ? getNeighbors(k2, i) : getNeighbors(i, k2))
            ) {
                if (vertical ? !isValidPoint(k2, i) : !isValidPoint(i, k2))
                    return false;

                if (neighbor.getShip() != null)
                    return false;
            }
        }
        return true;
    }

    private Cell[] getNeighbors(int col, int row) {
        Point[] points = new Point[]{
                new Point(col - 1, row),
                new Point(col + 1, row),
                new Point(col, row - 1),
                new Point(col, row + 1)
        };
        List<Cell> neighbors = new ArrayList<Cell>();

        for (Point p : points) {
            if (isValidPoint(p)) {
                neighbors.add(getCell((int) p.getX(), (int) p.getY()));
            }
        }
        return neighbors.toArray(new Cell[0]);
    }

    private boolean isValidPoint(Point point) {
        return isValidPoint((int) point.getX(), (int) point.getY());
    }

    protected boolean isValidPoint(int col, int row) {
        return col >= 0 && col < 10 && row >= 0 && row < 10;
    }

    public Cell getCell(int col, int row) {
        return cells[col][row];
    }

    public int getShipCount() {
        return shipCount;
    }

    public void setShipCount(int shipCount) {
        this.shipCount = shipCount;
    }

    public int getColumn() {
        return point.x;
    }

    public void setColumn(int column) {
        point.x = column;
    }

    public void setRow(int row) {
        this.point.y = row;
    }

    public int getRow() {
        return point.y;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public void setIsPlayer(boolean isPlayer) {
        this.isPlayer = isPlayer;
    }

}

import javafx.scene.layout.Pane;

import java.util.Arrays;
import java.util.HashSet;

class Grid extends Pane {

    private final HashSet<Cell> starts;
    private int ends;
    private final int rows;
    private final int columns;
    private final double width;
    private final double height;
    private final Cell[][] cells;
    private int maxNumberOfStarts;
    private int maxNumberOfEnds;

    public Grid(int rows, int columns, double width, double height) {
        this(rows, columns, width, height, 1, 1);
    }

    //    Ctrl+S = save the grid into a file
//    Ctrl+L = loads a grid from a file


    private Grid(int rows, int columns, double width, double height, int maxNumberOfStarts, int maxNumberOfEnds) {
        this.rows = rows;
        this.columns = columns;
        this.width = width;
        this.height = height;
        this.maxNumberOfStarts = maxNumberOfStarts;
        this.maxNumberOfEnds = maxNumberOfEnds;

        cells = new Cell[rows][columns];

        starts = new HashSet<>();
    }

    public Cell[][] getCells() {
        return cells;
    }

    public HashSet<Cell> getStarts() {
        return starts;
    }

    public int getEnds() {
        return ends;
    }

    public void setEnds(int ends) {
        this.ends = ends;
    }

    public int getMaxNumberOfStarts() {
        return maxNumberOfStarts;
    }

    public int getMaxNumberOfEnds() {
        return maxNumberOfEnds;
    }

    /**
     * Add cell to array and to the UI.
     */
    void add(Cell cell) {

        cells[cell.row][cell.column] = cell;

        double w = width / columns;
        double h = height / rows;
        double x = w * cell.column;
        double y = h * cell.row;

        cell.setLayoutX(x);
        cell.setLayoutY(y);
        cell.setPrefWidth(w);
        cell.setPrefHeight(h);

        getChildren().add(cell);

    }

    public void removePath() {
        for (Cell[] c : cells)
            for (Cell cell : c)
                cell.getStyleClass().removeAll("path-cell", "looking-cell");
    }

    @Override
    public String toString() {
        return "Grid{" +
                "starts=" + starts +
                ", ends=" + ends +
                ", rows=" + rows +
                ", columns=" + columns +
                ", width=" + width +
                ", height=" + height +
                ", cells=" + Arrays.toString(cells) +
                ", maxNumberOfStarts=" + maxNumberOfStarts +
                ", maxNumberOfEnds=" + maxNumberOfEnds +
                '}';
    }
}


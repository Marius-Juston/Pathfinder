import javafx.scene.layout.Pane;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;

class Grid extends Pane {

    private final HashSet<Cell> starts;
    private final int rows;
    private final int columns;
    private final int width;
    private final int height;
    private final Cell[][] cells;
    private final MouseGestures mg;
    private final int maxNumberOfStarts;
    private final int maxNumberOfEnds;
    private int ends;

    private File filePath;

    public Grid() {
        this(Main.rows, Main.columns, Main.width, Main.height);
    }

    public void setFilePath(File filePath) {
        this.filePath = filePath;
    }

    public File getFilePath() {
        return filePath;
    }


    public Grid(HashSet<Cell> starts, int rows, int columns, int width, int height, int ends, int maxNumberOfStarts, int maxNumberOfEnds, MouseGestures mouseGestures, File file) {
        this.starts = starts;
        this.rows = rows;
        this.columns = columns;
        this.width = width;
        this.height = height;
        cells = new Cell[rows][columns];
        this.ends = ends;
        this.maxNumberOfStarts = maxNumberOfStarts;
        this.maxNumberOfEnds = maxNumberOfEnds;

        this.mg = mouseGestures;

        filePath = file;
    }

    public Grid(int rows, int columns, int width, int height) {
        this(rows, columns, width, height, 1, 1);
    }

    private Grid(int rows, int columns, int width, int height, int maxNumberOfStarts, int maxNumberOfEnds) {
        this(new HashSet<>(), rows, columns, width, height, 0, maxNumberOfStarts, maxNumberOfEnds, new MouseGestures(true), null);
    }

    public final MouseGestures getMg() {
        return this.mg;
    }

    public final int getRows() {
        return this.rows;
    }

    public final int getColumns() {
        return this.columns;
    }

    public final int getGridWidth() {
        return this.width;
    }

    //    Ctrl+S = save the grid into a file
//    Ctrl+L = loads a grid from a file

    public final int getGridHeight() {
        return this.height;
    }

    public final Cell[][] getCells() {
        return this.cells;
    }

    public final HashSet<Cell> getStarts() {
        return this.starts;
    }

    public final int getEnds() {
        return this.ends;
    }

    public final void setEnds(int ends) {
        this.ends = ends;
    }

    public final int getMaxNumberOfStarts() {
        return this.maxNumberOfStarts;
    }

    public final int getMaxNumberOfEnds() {
        return this.maxNumberOfEnds;
    }

    /**
     * Add cell to array and to the UI.
     */
    final void add(Cell cell) {

        this.cells[cell.row][cell.column] = cell;

        int w = this.width / this.columns;
        int h = this.height / this.rows;
        int x = w * cell.column;
        int y = h * cell.row;

        cell.setLayoutX(x);
        cell.setLayoutY(y);
        cell.setPrefWidth(w);
        cell.setPrefHeight(h);

        this.getChildren().add(cell);
    }

    public final void removePath() {
        for (Cell[] c : this.cells)
            for (Cell cell : c)
                cell.getStyleClass().removeAll("path-cell", "looking-cell");
    }

    @Override
    public String toString() {
        return "Grid{" +
                "starts=" + starts +
                ", rows=" + rows +
                ", columns=" + columns +
                ", width=" + width +
                ", height=" + height +
                ", cells=" + Arrays.toString(cells) +
                ", mg=" + mg +
                ", ends=" + ends +
                ", maxNumberOfStarts=" + maxNumberOfStarts +
                ", maxNumberOfEnds=" + maxNumberOfEnds +
                '}';
    }
}


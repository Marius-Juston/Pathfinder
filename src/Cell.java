import javafx.scene.layout.StackPane;

class Cell extends StackPane {

    int column;
    int row;
    private Point point = Point.EMPTY;

    Cell(int column, int row) {

        this.column = column;
        this.row = row;

        getStyleClass().add("cell");
        setOpacity(0.9);
    }

    public final Point getPoint() {
        return point;
    }

    final void toggleWall() {
        if (Point.WALL == point) {
            getStyleClass().remove("wall");
            point = Point.EMPTY;
        } else {
            getStyleClass().remove("wall");
            getStyleClass().add("wall");
            point = Point.WALL;
        }

    }

    final void hoverHighlight() {
        // ensure the style is only once in the style list
        getStyleClass().remove("cell-hover-highlight");

        // add style
        getStyleClass().add("cell-hover-highlight");
    }

    final void hoverUnhighlight() {
        getStyleClass().remove("cell-hover-highlight");
    }

    final void toggleStart() {
        if (Point.START == point) {
            getStyleClass().remove("start-cell");
            point = Point.EMPTY;

            Main.getGrid().getStarts().remove(this);
        } else if (Main.getGrid().getStarts().size() < Main.getGrid().getMaxNumberOfStarts()) {
            if (Point.EMPTY == point) {
                getStyleClass().remove("start-cell");
                getStyleClass().add("start-cell");
                point = Point.START;

                Main.getGrid().getStarts().add(this);
            }
        }
    }

    final void toggleEnd() {
        if (Point.END == point) {
            getStyleClass().remove("end-cell");

            point = Point.EMPTY;
            Main.getGrid().setEnds(Main.getGrid().getEnds() - 1);
        } else if (Main.getGrid().getEnds() < Main.getGrid().getMaxNumberOfEnds()) {
            if (Point.EMPTY == point) {
                getStyleClass().remove("end-cell");
                getStyleClass().add("end-cell");
                point = Point.END;

                Main.getGrid().setEnds(Main.getGrid().getEnds() + 1);
            }
        }
    }

    @Override
    public String toString() {
        return "Cell{" +
                "column=" + column +
                ", row=" + row +
                ", point=" + point +
                '}';
    }
}
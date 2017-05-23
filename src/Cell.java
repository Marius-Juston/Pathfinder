import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

class Cell extends StackPane {

    private final Label label;
    int column;
    int row;
    private Point point;

    Cell(int column, int row) {
        this(column, row, Point.EMPTY);
    }

    public Cell(int columns, int rows, Point point) {
        this.column = columns;
        this.row = rows;
        this.point = point;
        this.getStyleClass().add("cell");
        this.setOpacity(0.9);

        label = new Label();

        getChildren().add(label);
        setAlignment(label, Pos.CENTER);

        if (point != Point.EMPTY)
            this.getStyleClass().add(point.name().toLowerCase() + "-cell");
    }

    public Label getLabel() {
        return label;
    }

    public final Point getPoint() {
        return this.point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    final void toggleWall() {
        if (Point.WALL == this.point) {
            this.getStyleClass().remove("wall-cell");
            this.point = Point.EMPTY;
        } else if (Point.EMPTY == this.point) {
            this.getStyleClass().remove("wall-cell");
            this.getStyleClass().add("wall-cell");
            this.point = Point.WALL;
        }

    }

    final void hoverHighlight() {
        // ensure the style is only once in the style list
        this.getStyleClass().remove("cell-hover-highlight");

        // add style
        this.getStyleClass().add("cell-hover-highlight");
    }

    final void hoverUnhighlight() {
        this.getStyleClass().remove("cell-hover-highlight");
    }

    final void toggleStart() {
        if (Point.START == this.point) {
            this.getStyleClass().remove("start-cell");
            this.point = Point.EMPTY;

            Main.getGrid().getStarts().remove(this);
        } else if (Main.getGrid().getStarts().size() < Main.getGrid().getMaxNumberOfStarts()) {
            if (Point.EMPTY == this.point) {
                this.getStyleClass().remove("start-cell");
                this.getStyleClass().add("start-cell");
                this.point = Point.START;

                Main.getGrid().getStarts().add(this);
            }
        }
    }

    final void toggleEnd() {
        if (Point.END == this.point) {
            this.getStyleClass().remove("end-cell");

            this.point = Point.EMPTY;
            Main.getGrid().setEnds(Main.getGrid().getEnds() - 1);
        }
            else if (Point.EMPTY == this.point) {
                this.getStyleClass().remove("end-cell");
                this.getStyleClass().add("end-cell");
                this.point = Point.END;

                Main.getGrid().setEnds(Main.getGrid().getEnds() + 1);
            }
        }


    @Override
    public final String toString() {
        return this.row + "," + this.column + this.point;
    }
}
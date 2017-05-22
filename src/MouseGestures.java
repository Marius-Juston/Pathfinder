import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

class MouseGestures {
    private boolean canInteractWith;
    private final EventHandler<MouseEvent> onMousePressedEventHandler = event -> {

        if (this.canInteractWith) {
            Cell cell = (Cell) event.getSource();

            if (event.isMiddleButtonDown()) {
                cell.toggleWall();
            } else if (event.isPrimaryButtonDown()) {
                cell.toggleStart();
            } else {
                cell.toggleEnd();
            }
        }
    };
    private final EventHandler<MouseEvent> onDragDetectedEventHandler = event -> {
        if (this.canInteractWith) {
            Cell cell = (Cell) event.getSource();

            if (event.isMiddleButtonDown())
                cell.toggleWall();

            cell.startFullDrag();
        }
    };
    private final EventHandler<MouseEvent> onMouseDragEnteredEventHandler = event -> {
        if (this.canInteractWith) {
            Cell cell = (Cell) event.getSource();

            if (event.isMiddleButtonDown())
                cell.toggleWall();
//
        }
    };

    public MouseGestures(boolean canInteractWith) {
        this.canInteractWith = canInteractWith;
    }

    public final boolean isCanInteractWith() {
        return this.canInteractWith;
    }

    public final void setCanInteractWith(boolean canInteractWith) {
        this.canInteractWith = canInteractWith;
    }

    public final void makePaintable(Node node) {


        // that's all there is needed for hovering, the other code is just for painting
        node.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                ((Cell) node).hoverHighlight();
            } else {
                ((Cell) node).hoverUnhighlight();
            }
        });

        node.setOnMousePressed(this.onMousePressedEventHandler);
        node.setOnDragDetected(this.onDragDetectedEventHandler);
        node.setOnMouseDragEntered(this.onMouseDragEnteredEventHandler);

    }

    @Override
    public final String toString() {
        return "MouseGestures{" +
                "canInteractWith=" + this.canInteractWith +
                ", onMousePressedEventHandler=" + this.onMousePressedEventHandler +
                ", onDragDetectedEventHandler=" + this.onDragDetectedEventHandler +
                ", onMouseDragEnteredEventHandler=" + this.onMouseDragEnteredEventHandler +
                '}';
    }
}
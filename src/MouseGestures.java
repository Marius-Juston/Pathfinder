import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

class MouseGestures {
    private boolean canInteractWith = true;
    private final EventHandler<MouseEvent> onMousePressedEventHandler = event -> {

        if (canInteractWith) {
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
        if (canInteractWith) {
            Cell cell = (Cell) event.getSource();

            if (event.isMiddleButtonDown())
                cell.toggleWall();

            cell.startFullDrag();
        }
    };
    private final EventHandler<MouseEvent> onMouseDragEnteredEventHandler = event -> {
        if (canInteractWith) {
            Cell cell = (Cell) event.getSource();

            if (event.isMiddleButtonDown())
                cell.toggleWall();
//
        }
    };

    public void setCanInteractWith(boolean canInteractWith) {
        this.canInteractWith = canInteractWith;
    }

    public void makePaintable(Node node) {


        // that's all there is needed for hovering, the other code is just for painting
        node.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                ((Cell) node).hoverHighlight();
            } else {
                ((Cell) node).hoverUnhighlight();
            }
        });

        node.setOnMousePressed(onMousePressedEventHandler);
        node.setOnDragDetected(onDragDetectedEventHandler);
        node.setOnMouseDragEntered(onMouseDragEnteredEventHandler);

    }

    @Override
    public String toString() {
        return "MouseGestures{" +
                "canInteractWith=" + canInteractWith +
                ", onMousePressedEventHandler=" + onMousePressedEventHandler +
                ", onDragDetectedEventHandler=" + onDragDetectedEventHandler +
                ", onMouseDragEnteredEventHandler=" + onMouseDragEnteredEventHandler +
                '}';
    }
}
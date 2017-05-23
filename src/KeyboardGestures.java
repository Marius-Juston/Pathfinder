import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Window;

import java.util.Arrays;

class KeyboardGestures {
    private final Window main;
    /**
     * Created by Marius Juston on 20/05/2017.
     */
    //    [0] = Ctrl, [1] = S, [2] = L, [3] = N
    private final boolean[] command = {false, false, false, false};
    final EventHandler<? super KeyEvent> onKeyPressedEventHandler = event -> {
        KeyCode keyCode = event.getCode();

        if (KeyCode.CONTROL == keyCode)
            this.command[0] = true;
        else if (KeyCode.S == keyCode)
            this.command[1] = true;
        else if (KeyCode.L == keyCode)
            this.command[2] = true;
        else if (KeyCode.P == keyCode) {
            Main.getGrid().getMg().setCanInteractWith(false);
            Grid grid = Main.getGrid();

            BreathFirstSearch.breathSearch((Cell) grid.getStarts().toArray()[0], Main.getGrid());
        } else if (KeyCode.N == keyCode)
            this.command[3] = true;

        else if (KeyCode.C == keyCode) {
            Main.getGrid().removePath();
            Main.getGrid().getMg().setCanInteractWith(true);
        }


    };
    //    Ctrl+S = save the grid into a file
//    Ctrl+L = loads a grid from a file
//    Ctrl+N = creates new empty tab
    final EventHandler<? super KeyEvent> onKeyReleasedEventHandler = event -> {
        this.interpretCommand();
        KeyCode keyCode = event.getCode();

        if (KeyCode.CONTROL == keyCode)
            this.command[0] = false;
        else if (KeyCode.S == keyCode)
            this.command[1] = false;
        else if (KeyCode.L == keyCode)
            this.command[2] = false;
        else if (KeyCode.N == keyCode)
            this.command[3] = false;
    };

    public KeyboardGestures(Window main) {
        this.main = main;
    }

    private TabPane getTabPane() {
        return (TabPane) main.getScene().getRoot();
    }

    private void interpretCommand() {
        if (this.command[0] && this.command[1])
            GridLoaderSaver.saveGrid(this.main);
        else if (this.command[0] && this.command[2])
            GridLoaderSaver.loadGrid(this.main);
        else if (this.command[0] && this.command[3]) {
            ((TabPane) (main.getScene().getRoot())).getTabs().add(new Tab("Grid " + GridLoaderSaver.getNumberOfUnsavedGrids(), new Grid(87, 63, 800, 800)));
            GridLoaderSaver.setNumberOfUnsavedGrids(GridLoaderSaver.getNumberOfUnsavedGrids() + 1);
        }
    }

    @Override
    public String toString() {
        return "KeyboardGestures{" +
                "main=" + main +
                ", command=" + Arrays.toString(command) +
                ", onKeyPressedEventHandler=" + onKeyPressedEventHandler +
                ", onKeyReleasedEventHandler=" + onKeyReleasedEventHandler +
                '}';
    }
}

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

enum KeyboardGestures {
    ;
    /**
     * Created by Marius Juston on 20/05/2017.
     */
    //    [0] = Ctrl, [1] = S, [2] = L, [3] = P
    private static final boolean[] command = {false, false, false, false};

//    Ctrl+S = save the grid into a file
//    Ctrl+L = loads a grid from a file

    static final EventHandler<? super KeyEvent> onKeyPressedEventHandler = event -> {
        KeyCode keyCode = event.getCode();

        if (KeyCode.CONTROL == keyCode)
            command[0] = true;
        else if (KeyCode.S == keyCode)
            command[1] = true;
        else if (KeyCode.L == keyCode)
            command[2] = true;
        else if (KeyCode.P == keyCode)
            command[3] = true;

        else if (KeyCode.C == keyCode) {
            Main.getGrid().removePath();
            Main.getMg().setCanInteractWith(true);
        }

        interpretCommand();
    };
    static final EventHandler<? super KeyEvent> onKeyReleasedEventHandler = event -> {

        KeyCode keyCode = event.getCode();

        if (KeyCode.CONTROL == keyCode)
            command[0] = false;
        else if (KeyCode.S == keyCode)
            command[1] = false;
        else if (KeyCode.L == keyCode)
            command[2] = false;
        else if (KeyCode.P == keyCode)
            command[3] = false;
    };

    private KeyboardGestures() {
    }

    private static void interpretCommand() {
        if (command[0] && command[1])
            GridLoaderSaver.saveGrid();
        else if (command[0] && command[2])
            GridLoaderSaver.loadGrid();
        else if (command[3]) {
            Main.getMg().setCanInteractWith(false);
            BreathFirstSearch.breathSearch((Cell) Main.getGrid().getStarts().toArray()[0], Main.getGrid());
        }
    }
}

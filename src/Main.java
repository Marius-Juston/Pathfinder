import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class Main extends Application {

    private static final MouseGestures mg = new MouseGestures();
    private static Grid grid;

    public Main() {
    }

    static MouseGestures getMg() {
        return mg;
    }

    static Grid getGrid() {
        return grid;
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public final void start(Stage primaryStage) {
        Tab tab = new Tab("Main Window");
        TabPane root = new TabPane(tab);


        // create grid
        double height = 800;
        double width = 800;
        int columns = 20;
        int rows = 20;
        grid = new Grid(rows, columns, width, height);

        // fill grid
        for (int row = 0; rows > row; row++) {
            for (int column = 0; columns > column; column++) {

                Cell cell = new Cell(column, row);

                mg.makePaintable(cell);
                grid.add(cell);
            }
        }

        tab.setContent(grid);


        // create scene and stage
        Scene scene = new Scene(root, width - 5, height + 25);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        scene.setOnKeyPressed(KeyboardGestures.onKeyPressedEventHandler);
        scene.setOnKeyReleased(KeyboardGestures.onKeyReleasedEventHandler);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public String toString() {
        return "Main{}";
    }
}
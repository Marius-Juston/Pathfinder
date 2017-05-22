import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.stage.Stage;

public class Main extends Application {


    private static final TabPane root = new TabPane();
    private static Grid grid;

    public Main() {
    }

    static Grid getGrid() {
        return (Grid) Main.root.getSelectionModel().getSelectedItem().getContent();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public final void start(Stage primaryStage) {
        Tab tab = new Tab("Main Window");
        Main.root.getTabs().add(tab);
        Main.root.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        Main.root.getTabs().addListener((ListChangeListener<? super Tab>) observable -> {
            if (observable.getList().size() == 1)
                Main.root.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
            else
                Main.root.setTabClosingPolicy(TabClosingPolicy.SELECTED_TAB);
        });

        // create grid
        int height = 800;
        int width = 800;
        int columns = 20;
        int rows = 20;
        Main.grid = new Grid(rows, columns, width, height);

        // fill grid
        for (int row = 0; rows > row; row++) {
            for (int column = 0; columns > column; column++) {

                Cell cell = new Cell(column, row);

                Main.grid.getMg().makePaintable(cell);
                Main.grid.add(cell);
            }
        }

        tab.setContent(Main.grid);

        // create scene and stage
        Scene scene = new Scene(Main.root, width - 5, height + 25);
        scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());

        KeyboardGestures kb = new KeyboardGestures(primaryStage);

        scene.setOnKeyPressed(kb.onKeyPressedEventHandler);
        scene.setOnKeyReleased(kb.onKeyReleasedEventHandler);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public final String toString() {
        return "Main{}";
    }
}
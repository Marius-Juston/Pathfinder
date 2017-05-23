import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.stage.Stage;

public class Main extends Application {


    // create grid
    static final int height = 800;
    static final int width = 800;
    static final int columns = 20;
    static final int rows = 20;
    private static final TabPane root = new TabPane();
    private static Grid grid;

    public Main() {
    }

    static Grid getGrid() {
        return (Grid) Main.root.getSelectionModel().getSelectedItem().getContent();
    }

    static Tab getTab() {
        return Main.root.getSelectionModel().getSelectedItem();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public final void start(Stage primaryStage) {
        Tab tab = new Tab("Main Window");
        Main.root.getTabs().add(tab);
        Main.root.setTabClosingPolicy(TabClosingPolicy.ALL_TABS);

        Main.root.getTabs().addListener((ListChangeListener<? super Tab>) observable -> {
            if (observable.getList().size() == 1)
                Main.root.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
            else
                Main.root.setTabClosingPolicy(TabClosingPolicy.ALL_TABS);
        });

        Main.root.getTabs().addListener((ListChangeListener<? super Tab>) observable -> {
            observable.next();
            if (observable.wasRemoved())
                GridLoaderSaver.setNumberOfUnsavedGrids(GridLoaderSaver.getNumberOfUnsavedGrids() - 1);
        });


        Main.grid = new Grid(rows, columns, width, height);

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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;

/**
 * Created by Marius Juston on 21/05/2017.
 */
final class GridLoaderSaver {

    private static final FileChooser fileChooser = new FileChooser();
    private static int numberOfUnsavedGrids = 1;

    static {
        GridLoaderSaver.fileChooser.getExtensionFilters().add(new ExtensionFilter("Map", "*.mp", "*.txt"));
    }

    private GridLoaderSaver() {
    }

    public static int getNumberOfUnsavedGrids() {
        return numberOfUnsavedGrids;
    }

    public static void setNumberOfUnsavedGrids(int numberOfUnsavedGrids) {
        GridLoaderSaver.numberOfUnsavedGrids = numberOfUnsavedGrids;
    }

    public static void saveGrid(Window owner) {

        File file = null;
        Grid grid = Main.getGrid();

        if (grid.getFilePath() == null) {
            file = fileChooser.showSaveDialog(owner);

            if (file != null)
                numberOfUnsavedGrids--;
        } else
            file = grid.getFilePath();

        if (file != null) {
            try {
                try (BufferedWriter bufferedWriter = Files.newBufferedWriter(file.toPath())) {

                    bufferedWriter.write(grid.getRows() + " " + grid.getColumns() + " " + grid.getGridWidth() + " " + grid.getGridHeight() + " " + grid.getMaxNumberOfStarts() + " " + grid.getMaxNumberOfEnds() + " " + grid.getMg().isCanInteractWith() + " ");

                    StringBuilder stringBuilder = new StringBuilder();

                    for (Cell[] cells : grid.getCells())
                        for (Cell cell : cells)
                            stringBuilder.append(cell.getPoint().ordinal());

                    bufferedWriter.write(stringBuilder.toString());
                }

                grid.setFilePath(file);
                Main.getTab().setText(file.getName());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //    0 == start, 1 == end, + == wall, - == empty
    public static void loadGrid(Window owner) {
        File file = GridLoaderSaver.fileChooser.showOpenDialog(owner);

        if (file != null) {
            String[] game = null;

            try (BufferedReader bufferedReader = Files.newBufferedReader(file.toPath())) {
                game = bufferedReader.readLine().split(" ");
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (game != null) {
                int rows = Integer.parseInt(game[0]);
                int columns = Integer.parseInt(game[1]);
                int width = Integer.parseInt(game[2]);
                int height = Integer.parseInt(game[3]);
                int maxNumberOfStarts = Integer.parseInt(game[4]);
                int maxNumberOfEnds = Integer.parseInt(game[5]);
                int ends = 0;

                MouseGestures mouseGestures = new MouseGestures(game[6].equals("true"));
                HashSet<Cell> starts = new HashSet<>();

                Grid grid = new Grid(starts, rows, columns, width, height, ends, maxNumberOfStarts, maxNumberOfEnds, mouseGestures, file);

                String[] stringCells = game[7].split("");

                for (int i = 0, r = 0; r < rows; r++) {
                    for (int c = 0; c < columns; c++, i++) {
                        Point type = Point.values()[Integer.parseInt(stringCells[i])];

                        grid.getCells()[r][c].setPoint(type);

                        if (type != Point.EMPTY)
                            grid.getCells()[r][c].getStyleClass().add(type.name().toLowerCase() + "-cell");

                        if (type == Point.END)
                            ends++;

                        else if (type == Point.START)
                            starts.add(grid.getCells()[r][c]);
                    }
                }

                grid.setEnds(ends);

                Tab tab = new Tab(file.getName(), grid);

                ((TabPane) owner.getScene().getRoot()).getTabs().add(tab);


            }
        }
    }
}

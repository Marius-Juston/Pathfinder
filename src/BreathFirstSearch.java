import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Marius Juston on 21/05/2017.
 */
final class BreathFirstSearch {
    private BreathFirstSearch() {
    }

    static void breathSearch(Cell start, Grid grid) {
        LinkedList<Cell> frontier = new LinkedList<>();
        frontier.add(start);

        HashMap<Cell, Cell> came_from = new HashMap<>();
        came_from.putIfAbsent(start, null);

        Cell current = null;

        while (!frontier.isEmpty()) {
            current = frontier.pollFirst();
            if (Point.EMPTY == current.getPoint()) {
                current.getStyleClass().remove("looking-cell");
                current.getStyleClass().add("looking-cell");

            }

            if (Point.END == current.getPoint())
                break;

            for (Cell next : neighbors(current, grid)) {
                Cell finalCurrent = current;
                came_from.computeIfAbsent(next, cell -> {
                    frontier.add(next);
                    return finalCurrent;
                });
            }
        }
//        System.out.println(came_from);
        createPath(start, current, came_from);
    }

    private static int createPath(Cell start, Cell goal, Map<Cell, Cell> came_from) {
        Cell current = goal;

        int pathLength = 1;

//        Deque<Cell> path = new LinkedList<>();

        while (!current.equals(start)) {
            current = came_from.get(current);
            if (Point.EMPTY == current.getPoint()) {
                current.getStyleClass().remove("looking-cell");
                current.getStyleClass().remove("path-cell");
                current.getStyleClass().add("path-cell");
            }
            pathLength++;
        }

        return pathLength;
    }

    private static Iterable<Cell> neighbors(Cell cell, Grid grid) {
        LinkedList<Cell> list = new LinkedList<>();

        if ((0 <= (cell.column - 1)) && (Point.WALL != grid.getCells()[cell.row][cell.column - 1].getPoint()))
            list.add(grid.getCells()[cell.row][cell.column - 1]);

        if (((cell.column + 1) < grid.getCells()[cell.row].length) && (Point.WALL != grid.getCells()[cell.row][cell.column + 1].getPoint()))
            list.add(grid.getCells()[cell.row][cell.column + 1]);

        if ((0 <= (cell.row - 1)) && (Point.WALL != grid.getCells()[cell.row - 1][cell.column].getPoint()))
            list.add(grid.getCells()[cell.row - 1][cell.column]);

        if (((cell.row + 1) < grid.getCells().length) && (Point.WALL != grid.getCells()[cell.row + 1][cell.column].getPoint()))
            list.add(grid.getCells()[cell.row + 1][cell.column]);

        return list;
    }
}

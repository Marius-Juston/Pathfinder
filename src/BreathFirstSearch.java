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

//        int i = 1;
        while (!frontier.isEmpty()) {
            current = frontier.pollFirst();

            if (Point.EMPTY == current.getPoint()) {
//                current.getStyleClass().remove("border-cell");
                current.getStyleClass().remove("looking-cell");
                current.getStyleClass().add("looking-cell");

            }

            if (Point.END == current.getPoint())
                break;

            for (Cell next : grid.neighbors(start, current, came_from)) {
                Cell finalCurrent = current;
//                finalCurrent.getLabel().setText(String.valueOf(i));

//                current.getStyleClass().add("border-cell");

                came_from.computeIfAbsent(next, cell -> {
                    frontier.add(next);
                    return finalCurrent;
                });
            }

//            i++;
        }
//        System.out.println(came_from);
        BreathFirstSearch.createPath(start, current, came_from);
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
}

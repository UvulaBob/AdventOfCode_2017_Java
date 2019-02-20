import java.util.ArrayList;
import java.util.HashMap;

public class Part2 {

    public static void main(String[] args) {
        int input = 289326;
        HashMap<String, Integer> grid = new HashMap<>();
        grid.put("0,0", 1);

        int firstLargerValue = 0;

        int x = 0;
        int y = 0;
        int multiplier = 2;
        while (true) {
            int valueToWrite = 0;
            x += 1;
            for (String adjacentPoint : getAdjacentPoints(x, y)) {
                if (grid.keySet().contains(adjacentPoint)) {
                    valueToWrite += grid.get(adjacentPoint);
                }
            }

            if (valueToWrite > input) {
                firstLargerValue = valueToWrite;
                break;
            }
            grid.put((x + "," + y), valueToWrite);

            boolean exceededInput = false;
            for (int i = multiplier - 1; i > 0; i--) {
                valueToWrite = 0;
                y--;
                for (String adjacentPoint : getAdjacentPoints(x, y)) {
                    if (grid.keySet().contains(adjacentPoint)) {
                        valueToWrite += grid.get(adjacentPoint);
                    }
                }

                if (valueToWrite > input) {
                    firstLargerValue = valueToWrite;
                    exceededInput = true;
                    break;
                }
                grid.put((x + "," + y), valueToWrite);

            }

            if (exceededInput) {
                break;
            }

            for (int i = multiplier; i > 0; i--) {
                valueToWrite = 0;
                x--;
                for (String adjacentPoint : getAdjacentPoints(x, y)) {
                    if (grid.keySet().contains(adjacentPoint)) {
                        valueToWrite += grid.get(adjacentPoint);
                    }
                }

                if (valueToWrite > input) {
                    firstLargerValue = valueToWrite;
                    exceededInput = true;
                    break;
                }
                grid.put((x + "," + y), valueToWrite);
            }

            if (exceededInput) {
                break;
            }

            for (int i = multiplier; i > 0; i--) {
                valueToWrite = 0;
                y++;
                for (String adjacentPoint : getAdjacentPoints(x, y)) {
                    if (grid.keySet().contains(adjacentPoint)) {
                        valueToWrite += grid.get(adjacentPoint);
                    }
                }

                if (valueToWrite > input) {
                    firstLargerValue = valueToWrite;
                    exceededInput = true;
                    break;
                }
                grid.put((x + "," + y), valueToWrite);

            }

            if (exceededInput) {
                break;
            }

            for (int i = multiplier; i > 0; i--) {
                valueToWrite = 0;
                x++;
                for (String adjacentPoint : getAdjacentPoints(x, y)) {
                    if (grid.keySet().contains(adjacentPoint)) {
                        valueToWrite += grid.get(adjacentPoint);
                    }
                }

                if (valueToWrite > input) {
                    firstLargerValue = valueToWrite;
                    exceededInput = true;
                    break;
                }
                grid.put((x + "," + y), valueToWrite);

            }

            if (exceededInput) {
                break;
            }

            multiplier += 2;
        }

        System.out.println("First value larger than input: " + firstLargerValue);
        System.out.println("Done!");


    }

    private static ArrayList<String> getAdjacentPoints(int x, int y) {
        ArrayList<String> adjacentPoints = new ArrayList<>();
        adjacentPoints.add(String.format("%s,%s", x - 1, y));
        adjacentPoints.add(String.format("%s,%s", x + 1, y));
        adjacentPoints.add(String.format("%s,%s", x, y - 1));
        adjacentPoints.add(String.format("%s,%s", x, y + 1));
        adjacentPoints.add(String.format("%s,%s", x - 1, y - 1));
        adjacentPoints.add(String.format("%s,%s", x + 1, y - 1));
        adjacentPoints.add(String.format("%s,%s", x - 1, y + 1));
        adjacentPoints.add(String.format("%s,%s", x + 1, y + 1));

        return adjacentPoints;
    }
}

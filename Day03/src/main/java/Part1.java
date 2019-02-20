import java.util.HashMap;

public class Part1 {

    public static void main(String[] args) {
        int input = 289326;
        HashMap<Integer, String> grid = new HashMap<>();
        grid.put(1, "0,0");

        int counter = 2;
        int x = 0;
        int y = 0;
        int multiplier = 2;
        while (counter < input) {
            x += 1;
            grid.put(counter, (x + "," + y));
            counter++;
            for (int i = multiplier - 1; i > 0; i--) {
                y--;
                grid.put(counter, (x + "," + y));
                counter++;
            }
            for (int i = multiplier; i > 0; i--) {
                x--;
                grid.put(counter, (x + "," + y));
                counter++;
            }
            for (int i = multiplier; i > 0; i--) {
                y++;
                grid.put(counter, (x + "," + y));
                counter++;
            }
            for (int i = multiplier; i > 0; i--) {
                x++;
                grid.put(counter, (x + "," + y));
                counter++;
            }
            multiplier += 2;
        }

        String targetCoordinates = grid.get(input);
        int targetX = Integer.parseInt(targetCoordinates.split(",")[0]);
        int targetY = Integer.parseInt(targetCoordinates.split(",")[1]);
        int distanceToTarget = Math.abs(targetX) + Math.abs(targetY);

        System.out.print("Distance to target: " + distanceToTarget);
        System.out.println("Done!");


    }
}

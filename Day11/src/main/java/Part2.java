import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Part2 {

    public static void main(String[] args) throws IOException {
        String input = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\main\\resources\\input.txt")).get(0);
        ArrayList<String> steps = new ArrayList<>(Arrays.asList(input.split(",")));

        int x = 0;
        int y = 0;
        int z = 0;

        int furthestDistance = 0;

        for (String step : steps) {

            switch (step) {
                case "n":
                    y++;
                    z--;
                    break;
                case "s":
                    y--;
                    z++;
                    break;
                case "ne":
                    x++;
                    z--;
                    break;
                case "sw":
                    x--;
                    z++;
                    break;
                case "nw":
                    y++;
                    x--;
                    break;
                case "se":
                    y--;
                    x++;
                    break;
            }

            int[] coordinates = new int[] {Math.abs(x), Math.abs(y), Math.abs(z)};

            int highestCoordinate = coordinates[0];
            for (int coordinate : coordinates) {
                if (coordinate > highestCoordinate) {
                    highestCoordinate = coordinate;
                }
            }

            if (highestCoordinate > furthestDistance) {
                furthestDistance = highestCoordinate;
            }

        }

        System.out.println(furthestDistance);
        System.out.println("Done!");

    }
}

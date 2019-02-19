import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Part1 {

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\main\\resources\\input.txt"));

        int total = 0;
        for (String line : lines) {
            int largestValue = 0;
            int smallestValue = Integer.MAX_VALUE;
            for (String valueAsString : line.split("\t")) {
                int value = Integer.parseInt(valueAsString);
                if (value > largestValue) {
                    largestValue = value;
                }

                if (value < smallestValue) {
                    smallestValue = value;
                }
            }

            total += largestValue - smallestValue;
        }

        System.out.println(total);
        System.out.println("Done!");
        System.out.println("Completed in " + (startTime - System.currentTimeMillis()) + " milliseconds");
    }
}

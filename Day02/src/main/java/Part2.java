import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Part2 {

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\main\\resources\\input.txt"));

        int total = 0;
        for (String line : lines) {
            ArrayList<Integer> values = new ArrayList<>();
            for (String valueAsString : line.split("\t")) {
                values.add(Integer.parseInt(valueAsString));
            }

            for (int firstValueIndex = 0; firstValueIndex < values.size(); firstValueIndex++) {
                int firstValue = values.get(firstValueIndex);
                boolean foundDivisor = false;
                for (int secondValueIndex = 0; secondValueIndex < values.size(); secondValueIndex++) {
                    if (secondValueIndex != firstValueIndex) {
                        int secondValue = values.get(secondValueIndex);
                        if (firstValue % secondValue == 0) {
                            total += firstValue / secondValue;
                            foundDivisor = true;
                            break;
                        }
                    }
                }
                if (foundDivisor) {
                    break;
                }
            }
        }

        System.out.println(total);
        System.out.println("Done!");
        System.out.println("Completed in " + (System.currentTimeMillis() - startTime) + " milliseconds");
    }
}

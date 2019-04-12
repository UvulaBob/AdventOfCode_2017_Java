import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Part2 {

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        String input = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt")).get(0);
        ArrayList<String> splitInput = new ArrayList<>(Arrays.asList(input.split("")));

        int total = 0;

        // Completes in 52 ms
//        for (int firstDigitIndex = 0; firstDigitIndex < splitInput.size(); firstDigitIndex++) {
//            String firstDigit = splitInput.get(0);
//            Collections.rotate(splitInput, -1 * (splitInput.size() / 2));
//            String secondDigit = splitInput.get(0);
//            total += (firstDigit.equals(secondDigit)) ? Integer.parseInt(firstDigit) : 0;
//            Collections.rotate(splitInput, (-1 * (splitInput.size() / 2)) - 1);
//        }

        // Completes in 19 ms
        for (int firstDigitIndex = 0; firstDigitIndex < splitInput.size(); firstDigitIndex++) {
            String firstDigit = splitInput.get(firstDigitIndex);
            int secondDigitIndex = firstDigitIndex + (splitInput.size() / 2);
            if (secondDigitIndex > splitInput.size() - 1) {
                secondDigitIndex -= splitInput.size();
            }
            String secondDigit = splitInput.get(secondDigitIndex);
            total += (firstDigit.equals(secondDigit)) ? Integer.parseInt(firstDigit) : 0;
        }

        System.out.println(total);
        System.out.println("Done!");
        System.out.println("Completed in " + (System.currentTimeMillis() - startTime) + " milliseconds");
    }
}

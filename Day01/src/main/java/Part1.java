import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Part1 {

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        String input = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\main\\resources\\input.txt")).get(0);
        ArrayList<String> splitInput = new ArrayList<>(Arrays.asList(input.split("")));

        int total = 0;

        // Completes in 39 ms
//        for (int i = 0; i < splitInput.size(); i++) {
//            total += (splitInput.get(0).equals(splitInput.get(1))) ? Integer.parseInt(splitInput.get(0)) : 0;
//            Collections.rotate(splitInput, -1);
//        }

        // Completes in 18 ms
        for (int i = 0; i < splitInput.size() - 1; i++) {
            total += (splitInput.get(i).equals(splitInput.get(i + 1))) ? Integer.parseInt(splitInput.get(i)) : 0;
        }

        System.out.println(total);
        System.out.println("Done!");
        System.out.println("Completed in " + (System.currentTimeMillis() - startTime) + " milliseconds");
    }
}

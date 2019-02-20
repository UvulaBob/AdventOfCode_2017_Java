import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Part1 {

    public static void main (String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\main\\resources\\input.txt"));

        int validPassphrases = 0;
        for (String line : lines) {
            boolean validPassphrase = true;
            Queue<String> words = new ArrayDeque<>(Arrays.asList(line.split(" ")));
            while (!words.isEmpty()) {
                String currentWord = words.poll();
                if (words.contains(currentWord)) {
                    validPassphrase = false;
                    break;
                }
            }
            validPassphrases += (validPassphrase) ? 1 : 0;
        }
        System.out.println(validPassphrases);
        System.out.println("Done!");
    }
}

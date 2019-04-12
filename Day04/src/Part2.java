import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Part2 {

    public static void main (String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));

        int validPassphrases = 0;
        for (String line : lines) {
            boolean validPassphrase = true;
            Queue<String> wordsWithSortedLetters = new ArrayDeque<>();
            for (String word : line.split(" ")) {
                wordsWithSortedLetters.add(sortLettersInWord(word));
            }

            while (!wordsWithSortedLetters.isEmpty()) {
                String currentWord = wordsWithSortedLetters.poll();
                if (wordsWithSortedLetters.contains(currentWord)) {
                    validPassphrase = false;
                    break;
                }
            }
            validPassphrases += (validPassphrase) ? 1 : 0;
        }
        System.out.println(validPassphrases);
        System.out.println("Done!");
    }

    private static String sortLettersInWord(String word) {
        ArrayList<String> sortedLetters = new ArrayList<>(Arrays.asList(word.split("")));
        Collections.sort(sortedLetters);
        StringBuilder sb = new StringBuilder();
        for (String letter : sortedLetters) {
            sb.append(letter);
        }

        return sb.toString();
    }
}

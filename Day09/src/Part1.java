import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Part1 {

    public static void main(String[] args) throws IOException {
        String input = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt")).get(0);

        int totalScore = 0;
        int currentGroupScore = 0;
        boolean garbageMode = false;
        boolean cancelMode = false;
        for (String character : input.split("")) {
            if (cancelMode) {
                cancelMode = false;
                continue;
            }

            switch (character) {
                case "!":
                    cancelMode = true;
                    break;
                case "{":
                    if (!garbageMode) {
                        currentGroupScore++;
                    }
                    break;
                case "}":
                    if (!garbageMode) {
                        totalScore += currentGroupScore;
                        currentGroupScore--;
                    }
                    break;
                case "<":
                    if (!garbageMode) {
                        garbageMode = true;
                    }
                    break;
                case ">":
                    garbageMode = false;
                    break;
            }
        }

        System.out.println(totalScore);
        System.out.println("Done!");

    }
}

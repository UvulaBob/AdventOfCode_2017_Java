import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Part2 {

    public static void main(String[] args) throws IOException {
        String input = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt")).get(0);

        int garbageCharacters = 0;
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
                    if (garbageMode) {
                        garbageCharacters++;
                    }
                    break;
                case "}":
                    if (garbageMode) {
                        garbageCharacters++;
                    }
                    break;
                case "<":
                    if (garbageMode) {
                        garbageCharacters++;
                    }
                    garbageMode = true;
                    break;
                case ">":
                    garbageMode = false;
                    break;
                default:
                    if (garbageMode) {
                        garbageCharacters++;
                    }
            }
        }

        System.out.println(garbageCharacters);
        System.out.println("Done!");

    }
}

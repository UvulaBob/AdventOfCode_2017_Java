import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Part1 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\main\\resources\\input.txt"));
        HashMap<String, String> programs = new HashMap<>();

        for (String line : lines) {
            String[] splitLine = line.split("-> ");
            String parentProgram = splitLine[0].split(" ")[0];
            if (splitLine.length > 1) {
                String[] childPrograms = splitLine[1].split(", ");
                for (String childProgram : childPrograms) {
                    programs.put(childProgram, parentProgram);
                }
            }
        }

        String topLevelProgram = "Nothing";
        for (String parentProgram : programs.values()) {
            if (!programs.keySet().contains(parentProgram)) {
                topLevelProgram = parentProgram;
            }
        }

        System.out.println(topLevelProgram);
        System.out.println("Done!");
    }
}

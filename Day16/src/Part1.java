import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Part1 {

    public static void main(String[] args) throws IOException {
        String input = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt")).get(0);
        ArrayList<String> instructions = new ArrayList<>(Arrays.asList(input.split(",")));

        ArrayList<String> programs = new ArrayList<>(Arrays.asList("abcdefghijklmnop".split("")));

        for (String instruction : instructions) {
            String command = instruction.split("")[0];
            switch (command) {
                case "s":
                    int spinAmount = Integer.parseInt(instruction.substring(1));
                    Collections.rotate(programs, spinAmount);
                    break;
                case "x":
                    int firstProgramIndex = Integer.parseInt(instruction.substring(1).split("/")[0]);
                    int secondProgramIndex = Integer.parseInt(instruction.substring(1).split("/")[1]);
                    String temp = programs.get(firstProgramIndex);
                    programs.set(firstProgramIndex, programs.get(secondProgramIndex));
                    programs.set(secondProgramIndex, temp);
                    break;
                case "p":
                    String firstProgramName = instruction.substring(1).split("/")[0];
                    String secondProgramName = instruction.substring(1).split("/")[1];
                    firstProgramIndex = programs.indexOf(firstProgramName);
                    secondProgramIndex = programs.indexOf(secondProgramName);
                    temp = programs.get(firstProgramIndex);
                    programs.set(firstProgramIndex, programs.get(secondProgramIndex));
                    programs.set(secondProgramIndex, temp);
                    break;
            }
        }

        String finalOrder = programs.toString().replace("[", "").replace("]", "").replace(", ", "");
        System.out.println(finalOrder);
        System.out.println("Done!");

    }
}

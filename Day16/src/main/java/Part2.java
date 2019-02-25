import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Part2 {

    public static void main(String[] args) throws IOException {
        int numberOfDances = 1000000000;
        String input = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\main\\resources\\input.txt")).get(0);
        ArrayList<String> instructions = new ArrayList<>(Arrays.asList(input.split(",")));
        ArrayList<String> programs = new ArrayList<>(Arrays.asList("abcdefghijklmnop".split("")));
        ArrayList<String> pastDanceResults = new ArrayList<>();
        String danceResult ="";

        for (int danceNumber = 0; danceNumber < numberOfDances ; danceNumber++) {
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

            danceResult = programs.toString().replace("[", "").replace("]", "").replace(", ", "");
            if (pastDanceResults.contains(danceResult)) {
                int remainingDances = numberOfDances - danceNumber;
                int remainder = remainingDances % danceNumber;
                danceResult = pastDanceResults.get(remainder - 1);
                break;
            } else {
                pastDanceResults.add(danceResult);
            }

        }

        System.out.println(danceResult);
        System.out.println("Done!");

    }
}

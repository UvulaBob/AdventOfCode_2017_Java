import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Part2 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));
        HashMap<String, Program> programs = new HashMap<>();

        for (String line : lines) {
            Program newProgram = new Program();
            String[] splitLine = line.split("-> ");
            String programName = splitLine[0].split(" ")[0];
            int weight = Integer.parseInt(splitLine[0].split(" ")[1].replace("(", "").replace(")", ""));
            newProgram.name = programName;
            newProgram.weight = weight;
            programs.put(programName, newProgram);
        }

        for (String line : lines) {
            String[] splitLine = line.split("-> ");
            String programName = splitLine[0].split(" ")[0];
            Program currentProgram = programs.get(programName);
            if (splitLine.length > 1) {
                for (String childProgramName : splitLine[1].split(", ")) {
                    Program childProgram = programs.get(childProgramName);
                    currentProgram.childPrograms.add(childProgram);
                    childProgram.parentProgram = currentProgram;
                }
            }
            Collections.sort(currentProgram.childPrograms);
        }

        Program topLevelProgram = null;

        for (Program program : programs.values()) {
            if (program.parentProgram == null) {
                topLevelProgram = program;
                break;
            }
        }

        if (topLevelProgram == null) {
            throw new RuntimeException("This shouldn't happen!");
        }

        Program deviantChildProgram = findDeviantChildProgram(topLevelProgram);

        if (deviantChildProgram == null) {
            throw new RuntimeException("This shouldn't happen!");
        }


        int targetWeight = 0;
        for (Program childProgram : topLevelProgram.childPrograms) {
            if (childProgram != deviantChildProgram) {
                targetWeight = childProgram.totalWeight();
            }
        }

        if (targetWeight == 0) {
            throw new RuntimeException("This shouldn't happen!");
        }

        int adjustedWeight = getAdjustedWeightOfDeviantChildProgram(deviantChildProgram, targetWeight);

        System.out.println(adjustedWeight);
        System.out.println("Done!");
    }

    private static int getAdjustedWeightOfDeviantChildProgram(Program deviantChildProgram, int targetWeight) {
        if (deviantChildProgram.childrenHaveEvenWeights()) {
            int weightOfChildren = deviantChildProgram.childPrograms.get(0).totalWeight() * deviantChildProgram.childPrograms.size();
            return targetWeight - weightOfChildren;
        }

        Program newDeviantChildProgram = findDeviantChildProgram(deviantChildProgram);

        if (newDeviantChildProgram == null) {
            throw new RuntimeException("This shouldn't happen!");
        }

        int newTargetWeight = 0;
        for (Program childProgram : deviantChildProgram.childPrograms) {
            if (childProgram != deviantChildProgram) {
                newTargetWeight = childProgram.totalWeight();
                break;
            }
        }

        if (newTargetWeight == 0) {
            throw new RuntimeException("This shouldn't happen!");
        }

        return getAdjustedWeightOfDeviantChildProgram(newDeviantChildProgram, newTargetWeight);
    }

    private static Program findDeviantChildProgram(Program parentProgram) {
        HashMap<Integer, Integer> childProgramWeightCounts = new HashMap<>();
        for (Program childProgram : parentProgram.childPrograms) {
            childProgramWeightCounts.putIfAbsent(childProgram.totalWeight(), 0);
            childProgramWeightCounts.put(childProgram.totalWeight(), childProgramWeightCounts.get(childProgram.totalWeight()) + 1);
        }

        int targetWeight = 0;
        for (Map.Entry<Integer, Integer> entry : childProgramWeightCounts.entrySet()) {
            if (entry.getValue() > 1) {
                targetWeight = entry.getKey();
            }
        }

        Program deviantChildProgram = null;
        for (Program childProgram : parentProgram.childPrograms) {
            if (childProgram.totalWeight() != targetWeight) {
                deviantChildProgram = childProgram;
                break;
            }
        }

        return deviantChildProgram;
    }

}

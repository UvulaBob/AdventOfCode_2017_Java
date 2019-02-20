import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Part2 {

    public static void main (String[] args) throws IOException {
        String inputLine = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\main\\resources\\input.txt")).get(0);
        HashSet<String> configurations = new HashSet<>();
        int cycleCount = 0;

        ArrayList<Integer> blocks = new ArrayList<>();
        for (String valueAsString : inputLine.split("\t")) {
            blocks.add(Integer.parseInt(valueAsString));
        }

        configurations.add(blocks.toString());

        String repeatedConfiguration = "";
        boolean foundRepeatOnce = false;
        while (true) {
            cycleCount++;
            int highestBlockValue = 0;
            int indexOfHighestBlockValue = 0;
            for (int index = 0; index < blocks.size(); index++) {
                int blockValue = blocks.get(index);
                if (blockValue > highestBlockValue) {
                    highestBlockValue = blockValue;
                    indexOfHighestBlockValue = index;
                }
            }

            int blocksToDistribute = highestBlockValue;
            int indexToIncrement = indexOfHighestBlockValue + 1;
            blocks.set(indexOfHighestBlockValue, 0);
            while (blocksToDistribute > 0) {
                if (indexToIncrement == blocks.size()) {
                    indexToIncrement -= blocks.size();
                }
                blocks.set(indexToIncrement, blocks.get(indexToIncrement) + 1);
                blocksToDistribute--;
                indexToIncrement++;
            }

            if (!foundRepeatOnce && configurations.contains(blocks.toString())) {
                foundRepeatOnce = true;
                repeatedConfiguration = blocks.toString();
                cycleCount = 0;
                continue;
            } else {
                configurations.add(blocks.toString());
            }

            if (foundRepeatOnce && blocks.toString().equals(repeatedConfiguration)) {
                break;
            }

        }

        System.out.println(cycleCount);
        System.out.println("Done!");
    }
}

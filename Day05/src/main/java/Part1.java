import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Part1 {

    public static void main (String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\main\\resources\\input.txt"));
        ArrayList<Integer> offsets = new ArrayList<>();

        for (String line : lines) {
            offsets.add(Integer.parseInt(line));
        }

        int offsetNumber = 0;
        int numberOfJumps = 0;
        while (offsetNumber < offsets.size() && offsetNumber >= 0) {
            int currentOffset = offsets.get(offsetNumber);
            offsets.set(offsetNumber, currentOffset + 1);
            offsetNumber += currentOffset;
            numberOfJumps++;
        }

        System.out.println(numberOfJumps);
        System.out.println("Done!");

    }
}

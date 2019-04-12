import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Part1 {
    private static HashSet<String> bridgesToZero = new HashSet<>();
    private static ArrayList<ArrayList<String>> allDestinations = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));

        for (String line : lines) {
            allDestinations.add(new ArrayList<>(Arrays.asList(line.split(" <-> ")[1].split(", "))));
        }

        findBridgesToZero("0", allDestinations.get(0));

        System.out.println(bridgesToZero.size());
        System.out.println("Done!");

    }

    private static void findBridgesToZero(String bridgeID, ArrayList<String> bridgeDestinations) {
        bridgesToZero.add(bridgeID);
        for (String destination : bridgeDestinations) {
            if (!bridgesToZero.contains(destination)) {
                findBridgesToZero(destination, allDestinations.get(Integer.parseInt(destination)));
            }
        }
    }
}

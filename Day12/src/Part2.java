import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Part2 {
    private static HashMap<String, ArrayList<String>> allDestinations = new HashMap<>();

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));

        for (String line : lines) {
            String[] splitLine = line.split(" <-> ");
            allDestinations.put(splitLine[0], new ArrayList<>(Arrays.asList(splitLine[1].split(", "))));
        }

        int groupCount = 0;
        while (allDestinations.size() > 0) {
            Iterator<String> iterator = allDestinations.keySet().iterator();
            String nextGroup = iterator.next();
            findNeighbors(nextGroup);
            groupCount++;
        }

        System.out.println(groupCount);
        System.out.println("Done!");
    }

    private static ArrayList<String> findNeighbors (String member) {
        ArrayList<String> group = new ArrayList<>();
        group.add(member);
        ArrayList<String> destinations = allDestinations.get(member);
        allDestinations.remove(member);
        for (String destination : destinations) {
            if (allDestinations.keySet().contains(destination)) {
                group.addAll(findNeighbors(destination));
            }
        }

        return group;
    }
}


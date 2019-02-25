import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Part1 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\main\\resources\\input.txt"));
        HashMap<Integer, Firewall> firewalls = new HashMap<>();

        int numberOfLevels = 0;
        for (String line : lines) {
            Firewall newFirewall = new Firewall();
            String[] splitLine = line.split(": ");
            newFirewall.range = Integer.parseInt(splitLine[1]);
            int level = Integer.parseInt(splitLine[0]);
            firewalls.put(level, newFirewall);
            numberOfLevels = level;
        }

        System.out.println("Initial State:");
        for (Firewall firewall : firewalls.values()) {
            System.out.println(firewall);
        }
        System.out.println();


        int severityLevel = 0;
        for (int currentLevel = 0; currentLevel <= numberOfLevels; currentLevel++) {
            if (firewalls.keySet().contains(currentLevel)) {
                Firewall currentFirewall = firewalls.get(currentLevel);
                if (currentFirewall.currentScanSpot == 1) {
                    severityLevel += currentFirewall.range * currentLevel;
                }
            }

            System.out.println("After Picosecond " + currentLevel);
            for (Firewall firewall : firewalls.values()) {
                firewall.sweepOneSpot();
                System.out.println(firewall);
            }
            System.out.println();

        }

        System.out.println(severityLevel);
        System.out.println("Done!");
    }
}

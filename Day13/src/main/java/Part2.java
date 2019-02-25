import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\main\\resources\\input.txt"));
        HashMap<Integer, Firewall> firewalls = new HashMap<>();

        for (String line : lines) {
            Firewall newFirewall = new Firewall();
            String[] splitLine = line.split(": ");
            newFirewall.range = Integer.parseInt(splitLine[1]);
            int level = Integer.parseInt(splitLine[0]);
            firewalls.put(level, newFirewall);
        }

        int currentPicoSecond = 1;
        while (true) {
            boolean allFirewallsClear = true;

            for (Map.Entry<Integer, Firewall> entry: firewalls.entrySet()) {
                Firewall firewall = entry.getValue();
                int level = entry.getKey();
                int repeatInterval = (firewall.range * 2) - 2;
                int firstBadPicoSecond = repeatInterval - level;
                if (currentPicoSecond == firstBadPicoSecond) {
                    allFirewallsClear = false;
                    break;
                }

                if ((currentPicoSecond + level) % repeatInterval == 0) {
                    allFirewallsClear = false;
                    break;
                }

            }

            if (allFirewallsClear) {
                break;
            }

            currentPicoSecond++;
        }

        System.out.println(currentPicoSecond);
        System.out.println("Done!");
    }
}

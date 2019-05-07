import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Part2 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));
        ArrayList<String> instructions = new ArrayList<>(lines);

        Processor p0 = new Processor(instructions, 0L);
        Processor p1 = new Processor(instructions, 1L);
        
        
        p0.process(p1);
        while ((!p0.isHalted() && !p1.isHalted()) && (!p0.isWaiting() || !p1.isWaiting())) {
            p1.process(p0);
            p0.process(p1);
        }

        System.out.println(p1.getSendInstructionCount());
        System.out.println("Done!");
    }
}

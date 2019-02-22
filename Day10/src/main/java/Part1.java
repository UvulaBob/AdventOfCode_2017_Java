import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class Part1 {

    public static void main(String[] args) throws IOException {
        String input = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\main\\resources\\input.txt")).get(0);
        ArrayList<String> rope = new ArrayList<>();

        for (int i = 0; i < 256; i++) {
            rope.add(String.valueOf(i));
        }

        ArrayList<Integer> lengths = new ArrayList<>();
        for (String length : input.split(",")) {
            lengths.add(Integer.parseInt(length));
        }

        int skipSize = 0;
        int currentPosition = 0;
        for (int length : lengths) {
            ArrayList<Integer> indexesToChange = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                indexesToChange.add(currentPosition);
                currentPosition++;
                if (currentPosition == rope.size()) {
                    currentPosition = 0;
                }
            }

            ArrayList<String> markersToReverse = new ArrayList<>();
            for (int indexToChange : indexesToChange) {
                markersToReverse.add(rope.get(indexToChange));
            }

            Collections.reverse(markersToReverse);

            for (int indexToChange : indexesToChange) {
                rope.set(indexToChange, markersToReverse.get(0));
                markersToReverse.remove(0);
            }

            currentPosition += skipSize;
            if (currentPosition >= rope.size()) {
                currentPosition -= rope.size();
            }
            skipSize++;
         }

        System.out.println(rope);
        System.out.println(Integer.parseInt(rope.get(0)) * Integer.parseInt(rope.get(1)));
        System.out.println("Done!");


    }
}

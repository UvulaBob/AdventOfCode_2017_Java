import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Part2 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\main\\resources\\input.txt"));
        int rowTotal = lines.size();
        int columnTotal = 0;

        for (String line : lines) {
            if (line.length() > columnTotal) {
                columnTotal = line.length();
            }
        }

        String[][] worldMap = new String[rowTotal][];

        for (int row = 0; row < rowTotal; row++) {
            String[] splitRow = lines.get(row).split("");
            worldMap[row] = splitRow;
        }

        Direction direction = Direction.DOWN;
        int x = 0;
        int y = 0;
        for (String column : worldMap[0]) {
            if (column.equals("|")) {
                break;
            } else {
                x++;
            }
        }

        int numberOfSteps = 0;
        boolean deadEnd = false;
        ArrayList<String> visitedLetters = new ArrayList<>();

        while (!deadEnd) {
            numberOfSteps++;
            String currentCharacter = worldMap[y][x];

            if (currentCharacter.matches("\\w")) {
                visitedLetters.add(currentCharacter);
            }

            if (!currentCharacter.equals("+")) {
                if (direction == Direction.DOWN && !worldMap[y + 1][x].equals(" ")) {
                    y++;
                } else if (direction == Direction.UP && !worldMap[y - 1][x].equals(" ")) {
                    y--;
                } else if (direction == Direction.LEFT && !worldMap[y][x - 1].equals(" ")) {
                    x--;
                } else if (direction == Direction.RIGHT && !worldMap[y][x + 1].equals(" ")) {
                    x++;
                } else {
                    deadEnd= true;
                }
            } else {
                if (x > 0 && !worldMap[y][x - 1].equals(" ") && direction != Direction.RIGHT) {
                    direction = Direction.LEFT;
                    x--;
                } else if (y > 1 && !worldMap[y - 1][x].equals(" ") && direction != Direction.DOWN) {
                    direction = Direction.UP;
                    y--;
                } else if (y < rowTotal - 1 && !worldMap[y + 1][x].equals(" ") && direction != Direction.UP) {
                    direction = Direction.DOWN;
                    y++;
                } else if (x < columnTotal - 1 && !worldMap[y][x + 1].equals(" ") & direction != Direction.LEFT) {
                    direction = Direction.RIGHT;
                    x++;
                } else {
                    deadEnd = true;
                }
            }

        }

        System.out.println(numberOfSteps);
        System.out.println("Done!");
    }
}

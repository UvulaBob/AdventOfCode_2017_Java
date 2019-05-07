import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Part2 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));
		HashMap<String, String> map = new LinkedHashMap<>();
		int mapHeight = lines.size();
		int mapWidth = lines.get(0).length();

		for (int rowNumber = 0; rowNumber < mapHeight; rowNumber++) {
			String[] inputRow = lines.get(rowNumber).split("");
			for (int columnNumber = 0; columnNumber < mapWidth; columnNumber++) {
				map.put(key(rowNumber, columnNumber), inputRow[columnNumber]);
			}
		}

		Facing currentFacing = Facing.UP;
		int currentColumn = mapWidth / 2;
		int currentRow = mapHeight / 2;

		int infectionCount = 0;
		int burstsLeft = 10000000;
		while (burstsLeft > 0) {
			switch (map.get(key(currentRow, currentColumn))) {
				case ".": 
					map.put(key(currentRow, currentColumn), "W");
	
					switch (currentFacing) {
						case UP:
							currentFacing = Facing.LEFT;
							break;
						case LEFT:
							currentFacing = Facing.DOWN;
							break;
						case DOWN:
							currentFacing = Facing.RIGHT;
							break;
						case RIGHT:
							currentFacing = Facing.UP;
					}
					break;
				case "W":
					map.put(key(currentRow, currentColumn), "#");
					infectionCount++;
					break;
				case "#":
					map.put(key(currentRow, currentColumn), "F");
					switch (currentFacing) {
						case UP:
							currentFacing = Facing.RIGHT;
							break;
						case LEFT:
							currentFacing = Facing.UP;
							break;
						case DOWN:
							currentFacing = Facing.LEFT;
							break;
						case RIGHT:
							currentFacing = Facing.DOWN;
					}
					break;
				case "F": 
					map.put(key(currentRow, currentColumn), ".");
					switch (currentFacing) {
						case UP:
							currentFacing = Facing.DOWN;
							break;
						case LEFT:
							currentFacing = Facing.RIGHT;
							break;
						case DOWN:
							currentFacing = Facing.UP;
							break;
						case RIGHT:
							currentFacing = Facing.LEFT;
					}
					break;
				}

			switch (currentFacing) {
				case UP:
					currentRow--;
					break;
				case LEFT:
					currentColumn--;
					break;
				case DOWN:
					currentRow++;
					break;
				case RIGHT:
					currentColumn++;
			}

			map.putIfAbsent(key(currentRow, currentColumn), ".");
			burstsLeft--;
		}

		System.out.println(infectionCount);
		System.out.println("Done!");
	}

	private static String key(int row, int column) {
		return row + "," + column;
	}

}



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Part2 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));
		HashMap<String, String> rules = new HashMap<>();

		for (String line : lines) {
			String[] splitLine = line.split(" => ");
			rules.put(splitLine[0], splitLine[1]);

		}

		String grid = ".#./..#/###";

		int iterations = 18;
		while (iterations > 0) {
			ArrayList<ArrayList<String>> squares = breakGridIntoSquares(grid);
			ArrayList<ArrayList<String>> newSquares = new ArrayList<>();

			for (ArrayList<String> row : squares) {
				ArrayList<String> rowOfNewSquares = new ArrayList<>();
				for (String square : row) {
					if (rules.containsKey(square)){
						rowOfNewSquares.add(rules.get(square));
						continue;
					}

					if (rules.containsKey(flipSquare(square))){
						rowOfNewSquares.add(rules.get(flipSquare(square)));
						continue;
					}


					String rotatedSquare = square;
					int rotations = 3;
					while (rotations > 0) {
						rotatedSquare = rotateSquare(rotatedSquare);
						if (rules.containsKey(rotatedSquare)) {
							rowOfNewSquares.add(rules.get(rotatedSquare));
							break;
						} else if (rules.containsKey(flipSquare(rotatedSquare))){
							rowOfNewSquares.add(rules.get(flipSquare(rotatedSquare)));
							break;
						} else {
							rotations--;
						}
					}

					if (rotations == 0) {
						throw new RuntimeException("No match found!");
					}
				}
				newSquares.add(rowOfNewSquares);
			}

			grid = reassembleGridFromSquares(newSquares);
			iterations--;
		}


		int onCount = 0;
		for (String character : grid.split("")) {
			if (character.equals("#")) {
				onCount++;
			}
		}

		System.out.println(onCount);
		System.out.println("Done!");
	}

	private static String reassembleGridFromSquares(ArrayList<ArrayList<String>> squares) {
		StringBuilder newGrid = new StringBuilder();
		int squareSize = squares.get(0).get(0).split("/")[0].length();

		for (ArrayList<String> rowOfSquares : squares) {
			for (int gridRowNumber = 0; gridRowNumber < squareSize; gridRowNumber++) {
				for (String square : rowOfSquares) {
					newGrid.append(square.split("/")[gridRowNumber]);
				}
				newGrid.append("/");
			}
		}


		newGrid.deleteCharAt(newGrid.lastIndexOf("/"));

		return newGrid.toString();
	}

	private static ArrayList<ArrayList<String>> breakGridIntoSquares(String grid) {
		ArrayList<String> gridAsArrayList = new ArrayList<>(Arrays.asList(grid.split("/")));
		ArrayList<ArrayList<String>> squares = new ArrayList<>();
		int gridSize = gridAsArrayList.get(0).length();
		if (gridSize % 2 == 0) {
			for (int rowNumber = 0; rowNumber < gridSize - 1; rowNumber += 2) {
				String[] upperRow = gridAsArrayList.get(rowNumber).split("");
				String[] lowerRow = gridAsArrayList.get(rowNumber + 1).split("");
				ArrayList<String> newRowOfSquares = new ArrayList<>();
				for (int columnNumber = 0; columnNumber < upperRow.length - 1; columnNumber += 2) {
					newRowOfSquares.add(upperRow[columnNumber] + upperRow[columnNumber + 1] + "/" + lowerRow[columnNumber] + lowerRow[columnNumber + 1]);
				}
				squares.add(newRowOfSquares);
			}
		} else {
			for (int rowNumber = 0; rowNumber < gridSize - 2; rowNumber += 3) {
				String[] upperRow = gridAsArrayList.get(rowNumber).split("");
				String[] middleRow = gridAsArrayList.get(rowNumber + 1).split("");
				String[] lowerRow = gridAsArrayList.get(rowNumber + 2).split("");
				ArrayList<String> newRowOfSquares = new ArrayList<>();
				for (int columnNumber = 0; columnNumber < upperRow.length - 2; columnNumber += 3) {
					newRowOfSquares.add(
							upperRow[columnNumber] + upperRow[columnNumber + 1] + upperRow[columnNumber + 2] + "/" +
									middleRow[columnNumber] + middleRow[columnNumber + 1] + middleRow[columnNumber + 2] + "/" +
									lowerRow[columnNumber] + lowerRow[columnNumber + 1] + lowerRow[columnNumber + 2]
					);
				}
				squares.add(newRowOfSquares);
			}
		}

		return squares;
	}

	private static String rotateSquare (String square) {
		ArrayList<String> squareToRotate = new ArrayList<>(Arrays.asList(square.split("/")));
		ArrayList<String> rotatedSquare= new ArrayList<>();

		for (int columnNumber = 0; columnNumber < squareToRotate.size(); columnNumber++) {
			StringBuilder newRow = new StringBuilder();
			for (String row : squareToRotate) {
				newRow.append(row, columnNumber, columnNumber + 1);
			}
			rotatedSquare.add(newRow.reverse().toString());
		}

		StringBuilder sb = new StringBuilder();
		for (String row : rotatedSquare) {
			sb.append(row);
			sb.append("/");
		}

		sb.deleteCharAt(sb.lastIndexOf("/"));

		return sb.toString();
	}

	private static String flipSquare(String square) {
		ArrayList<String> squareToFlip = new ArrayList<>(Arrays.asList(square.split("/")));
		StringBuilder sb = new StringBuilder();
		for (String row : squareToFlip) {
			sb.append(new StringBuilder(row).reverse().toString());
			sb.append("/");
		}

		sb.deleteCharAt(sb.lastIndexOf("/"));

		return sb.toString();
	}

}

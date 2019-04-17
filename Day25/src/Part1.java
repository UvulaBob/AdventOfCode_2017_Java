import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Part1 {
	
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));
		HashMap<String, State> states = new HashMap<>();

		String beginningStateLetter = lines.get(0).replace("Begin in state ", "").replace(".", "");
		lines.remove(0);
		
		int stepsUntilChecksum = Integer.parseInt(lines.get(0).replace("Perform a diagnostic checksum after ", "").replace(" steps.", ""));

		lines.remove(0);
		lines.remove(0);
		
		while (lines.size() > 0) {
			String newStateLetter = lines.get(0).replace("In state ", "").replace(":", "");
			lines.remove(0);
			lines.remove(0);

			State newState = new State();
			newState.valuesToWrite.put(0, Integer.parseInt(lines.get(0).replace("    - Write the value ", "").replace(".", "")));

			lines.remove(0);
			int directionToMove = (lines.get(0).replace("    - Move one slot to the ", "").replace(".", "").equals("right")) ? 1 : -1;
			newState.directionsToMove.put(0, directionToMove);

			lines.remove(0);
			newState.statesToMoveInto.put(0, lines.get(0).replace("    - Continue with state ", "").replace(".", ""));

			lines.remove(0);
			lines.remove(0);

			newState.valuesToWrite.put(1, Integer.parseInt(lines.get(0).replace("    - Write the value ", "").replace(".", "")));

			lines.remove(0);
			directionToMove = (lines.get(0).replace("    - Move one slot to the ", "").replace(".", "").equals("right")) ? 1 : -1;
			newState.directionsToMove.put(1, directionToMove);

			lines.remove(0);
			newState.statesToMoveInto.put(1, lines.get(0).replace("    - Continue with state ", "").replace(".", ""));

			lines.remove(0);
			if (lines.size() > 0) {
				lines.remove(0);
			}
			
			states.put(newStateLetter, newState);
		}

		ArrayList<Integer> tape = new ArrayList<>();
		tape.add(0);
		
		State currentState = states.get(beginningStateLetter);
		int currentPosition = 0;

		while (stepsUntilChecksum > 0) {
			Pair<String, Integer> results = currentState.process(tape, currentPosition);
			currentState = states.get(results.getKey());
			currentPosition = results.getValue();
			stepsUntilChecksum--;
		}
		
		int total = 0;
		for (Integer spot : tape) {
			total += spot;
		}

		System.out.println(total);
		System.out.println("Done!");
				
	}
}

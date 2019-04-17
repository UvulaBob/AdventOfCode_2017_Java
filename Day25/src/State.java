import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class State {
	HashMap<Integer, Integer> valuesToWrite = new HashMap<>();
	HashMap<Integer, Integer> directionsToMove = new HashMap<>();
	HashMap<Integer, String> statesToMoveInto = new HashMap<>();
	
	public Pair<String, Integer> process(ArrayList<Integer> tape, int currentPosition) {
		int value = tape.get(currentPosition);
		
		tape.set(currentPosition, valuesToWrite.get(value));
		currentPosition += directionsToMove.get(value);
		
		if (currentPosition < 0) {
			tape.add(0, 0);
			currentPosition++;
		} else if (currentPosition == tape.size()) {
			tape.add(0);
		}
		
		return new Pair<>(statesToMoveInto.get(value), currentPosition);
	}
}

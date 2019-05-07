import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class Processor {

	private boolean isHalted = false;
	
	private ArrayDeque<Long> queue = new ArrayDeque<>();
	private HashMap<String, Long> registers = new HashMap<>();
	private ArrayList<String> instructions;
	private int sndInstructionsExecuted = 0;
	private int instructionNumber = 0;
	
	public Processor(ArrayList<String> instructions, long id) {
		this.instructions = instructions;
		for (String instruction : instructions) {
			String register = instruction.split(" ")[1];
			boolean numeric = register.matches("-?\\d+(\\.\\d+)?");
			if (!numeric) {
				registers.putIfAbsent(register, 0L);
			}
		}

		registers.put("p", id);
	}

	void process(Processor otherProcessor) {

		while (instructionNumber >= 0 && instructionNumber < instructions.size()) {
			String instruction = instructions.get(instructionNumber);
			String operation = instruction.split(" ")[0];
			String x = instruction.split(" ")[1];
			boolean numericX = x.matches("-?\\d+(\\.\\d+)?");
			switch (operation) {
				case "snd":
					otherProcessor.addToQueue(numericX ? Long.parseLong(x) : registers.get(x));
					sndInstructionsExecuted++;
					break;
				case "set":
					String y = instruction.split(" ")[2];
					boolean numericY = y.matches("-?\\d+(\\.\\d+)?");
					registers.put(x, numericY ? Long.parseLong(y) : registers.get(y));
					break;
				case "add":
					y = instruction.split(" ")[2];
					numericY = y.matches("-?\\d+(\\.\\d+)?");
					registers.put(x, registers.get(x) + (numericY ? Long.parseLong(y) : registers.get(y)));
					break;
				case "mul":
					y = instruction.split(" ")[2];
					numericY = y.matches("-?\\d+(\\.\\d+)?");
						registers.put(x, registers.get(x) * (numericY ? Long.parseLong(y) : registers.get(y)));
					break;
				case "mod":
					y = instruction.split(" ")[2];
					numericY = y.matches("-?\\d+(\\.\\d+)?");
					registers.put(x, registers.get(x) % (numericY ? Long.parseLong(y) : registers.get(y)));
					break;
				case "rcv":
					if (queue.isEmpty()) {
						return;
					} else {
						registers.put(x, queue.pop());
					}
					break;
				case "jgz":
					boolean timeToJump = false;
					if (numericX) {
						if (Integer.parseInt(x) > 0) {
							timeToJump = true;
						}
					} else {
						if (registers.get(x) > 0) {
							timeToJump = true;
						}
					}
					
					if (timeToJump) {
						y = instruction.split(" ")[2];
						numericY = y.matches("-?\\d+(\\.\\d+)?");
						instructionNumber += numericY ? Integer.parseInt(y) : registers.get(y);
						instructionNumber--;
					}
					
					break;
			}
			instructionNumber++;
		}

		isHalted = true;
	}
	
	private void addToQueue(long value) {
		queue.add(value);
	}
	
	int getSendInstructionCount() {
		return sndInstructionsExecuted;
	}
	
	boolean isWaiting() {
		return queue.isEmpty();
	}

	boolean isHalted() {
		return isHalted;
	}

}

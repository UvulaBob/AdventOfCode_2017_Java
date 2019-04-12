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
		HashMap<String, Long> registers = new HashMap<>();

		for (String register : "abcdefgh".split("")) {
			registers.put(register,  0L);
		}

		registers.put("a", 1L);

		ArrayList<String> instructions = new ArrayList<>(lines);
		int instructionNumber = 0;

		System.out.println("Start");
		System.out.println(registers);

		int lineCount = 0;

		while (instructionNumber >= 0 && instructionNumber < instructions.size()) {
			String instruction = instructions.get(instructionNumber);
			String operation = instruction.split(" ")[0];
			String register = instruction.split(" ")[1];
			System.out.println((instructionNumber + 1) + ": " + instruction);
			switch (operation) {
				case "set":
					String value = instruction.split(" ")[2];
					boolean numeric = value.matches("-?\\d+(\\.\\d+)?");
					if (numeric) {
						registers.put(register, Long.parseLong(value));
					} else {
						registers.put(register, registers.get(value));
					}
					System.out.println(registers);
					break;
				case "sub":
					value = instruction.split(" ")[2];
					numeric = value.matches("-?\\d+(\\.\\d+)?");
					if (numeric) {
						registers.put(register, registers.get(register) - Long.parseLong(value));
					} else {
						registers.put(register, registers.get(register) - registers.get(value));
					}
					System.out.println(registers);
					break;
				case "mul":
					value = instruction.split(" ")[2];
					numeric = value.matches("-?\\d+(\\.\\d+)?");
					if (numeric) {
						registers.put(register, registers.get(register) * Long.parseLong(value));
					} else {
						registers.put(register, registers.get(register) * registers.get(value));
					}
					System.out.println(registers);
					break;
				case "jnz":
					value = instruction.split(" ")[1];
					numeric = value.matches("-?\\d+(\\.\\d+)?");
					if (numeric) {
						if (Long.parseLong(value) != 0) {
							instructionNumber += Integer.parseInt(instruction.split(" ")[2]);
							instructionNumber--;
						}
					} else {
						if (registers.get(value) != 0) {
							instructionNumber += Integer.parseInt(instruction.split(" ")[2]);
							instructionNumber--;
						}
					}
					break;

			}
			instructionNumber++;
			lineCount++;
			if (lineCount > 10000) {
				break;
			}
		}

		System.out.println("Register h: " + registers.get("h"));
		System.out.println("Done!");
	}
}

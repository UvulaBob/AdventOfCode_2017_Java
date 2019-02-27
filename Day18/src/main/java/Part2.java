import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Part2 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\main\\resources\\input.txt"));
        HashMap<String, Long> registers = new HashMap<>();

        for (String line: lines) {
            String register = line.split(" ")[1];
            registers.putIfAbsent(register, 0L);
        }

        ArrayList<String> instructions = new ArrayList<>(lines);
        long frequency = 0;
        long recoveredFrequency = 0;
        int instructionNumber = 0;
        int instructionsExecuted = 0;

        while (instructionNumber >= 0 && instructionNumber < instructions.size()) {
            instructionsExecuted++;
            String instruction = instructions.get(instructionNumber);
            String operation = instruction.split(" ")[0];
            String register = instruction.split(" ")[1];
            switch (operation) {
                case "snd":
                    frequency = registers.get(register);
                    break;
                case "set":
                    String value = instruction.split(" ")[2];
                    boolean numeric = value.matches("-?\\d+(\\.\\d+)?");
                    if (numeric) {
                        registers.put(register, Long.parseLong(value));
                    } else {
                        registers.put(register, registers.get(value));
                    }
                    break;
                case "add":
                    value = instruction.split(" ")[2];
                    numeric = value.matches("-?\\d+(\\.\\d+)?");
                    if (numeric) {
                        registers.put(register, registers.get(register) + Long.parseLong(value));
                    } else {
                        registers.put(register, registers.get(register) + registers.get(value));
                    }
                    break;
                case "mul":
                    value = instruction.split(" ")[2];
                    numeric = value.matches("-?\\d+(\\.\\d+)?");
                    if (numeric) {
                        registers.put(register, registers.get(register) * Long.parseLong(value));
                    } else {
                        registers.put(register, registers.get(register) * registers.get(value));
                    }
                    break;
                case "mod":
                    value = instruction.split(" ")[2];
                    numeric = value.matches("-?\\d+(\\.\\d+)?");
                    if (numeric) {
                        registers.put(register, registers.get(register) % Long.parseLong(value));
                    } else {
                        registers.put(register, registers.get(register) % registers.get(value));
                    }
                    break;
                case "rcv":
                    if (registers.get(register) != 0) {
                        recoveredFrequency = frequency;
                        instructionNumber = -2;
                    }
                    break;
                case "jgz":
                    if (registers.get(register) > 0) {
                        instructionNumber += Integer.parseInt(instruction.split(" ")[2]);
                        instructionNumber--;
                    }
                    break;
            }
            instructionNumber++;
        }

        System.out.println(recoveredFrequency);
        System.out.println("Done!");
    }
}

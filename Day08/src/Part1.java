import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));
        HashMap<String, Integer> registers = new HashMap<>();

        for (String line : lines) {
            registers.putIfAbsent(line.split(" ")[0], 0);
        }

        Pattern pattern = Pattern.compile("(\\w+) (inc|dec) (-?\\d+) if (\\w+) (.*) (-?\\d+)");
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);

            if (matcher.find()) {
                String register = matcher.group(1);
                String operation = matcher.group(2);
                Integer value = Integer.parseInt(matcher.group(3));
                String conditionalRegister = matcher.group(4);
                String condition = matcher.group(5);
                Integer conditionalValue = Integer.parseInt(matcher.group(6));

                switch (condition) {
                    case "<":
                        if (registers.get(conditionalRegister) < conditionalValue) {
                            if (operation.equals("inc")) {
                                registers.put(register, registers.get(register) + value);
                            } else {
                                registers.put(register, registers.get(register) - value);
                            }
                        }
                        break;
                    case ">":
                        if (registers.get(conditionalRegister) > conditionalValue) {
                            if (operation.equals("inc")) {
                                registers.put(register, registers.get(register) + value);
                            } else {
                                registers.put(register, registers.get(register) - value);
                            }
                        }
                        break;
                    case ">=":
                        if (registers.get(conditionalRegister) >= conditionalValue) {
                            if (operation.equals("inc")) {
                                registers.put(register, registers.get(register) + value);
                            } else {
                                registers.put(register, registers.get(register) - value);
                            }
                        }
                        break;
                    case "<=":
                        if (registers.get(conditionalRegister) <= conditionalValue) {
                            if (operation.equals("inc")) {
                                registers.put(register, registers.get(register) + value);
                            } else {
                                registers.put(register, registers.get(register) - value);
                            }
                        }
                        break;
                    case "==":
                        if (registers.get(conditionalRegister).equals(conditionalValue)) {
                            if (operation.equals("inc")) {
                                registers.put(register, registers.get(register) + value);
                            } else {
                                registers.put(register, registers.get(register) - value);
                            }
                        }
                        break;
                    case "!=":
                        if (!registers.get(conditionalRegister).equals(conditionalValue)) {
                            if (operation.equals("inc")) {
                                registers.put(register, registers.get(register) + value);
                            } else {
                                registers.put(register, registers.get(register) - value);
                            }
                        }
                        break;
                }
            }

        }

        ArrayList<Integer> values = new ArrayList<>(registers.values());
        Collections.sort(values);
        Collections.reverse(values);

        System.out.println(values.get(0));
        System.out.println("Done!");

    }
}

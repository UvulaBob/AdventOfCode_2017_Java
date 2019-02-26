import java.util.ArrayList;

public class Part1 {

    public static void main(String[] args) {
        int input = 349;
        int cycles = 2017;

        ArrayList<Integer> buffer = new ArrayList<>();
        buffer.add(0);

        int currentPosition = 0;
        for (int currentCycle = 1; currentCycle <= cycles; currentCycle++) {
            currentPosition += input;
            while (currentPosition >= buffer.size()) {
                currentPosition -= buffer.size();
            }
            buffer.add(currentPosition + 1, currentCycle);
            currentPosition++;
        }

        System.out.println(buffer.get(buffer.indexOf(cycles) + 1));
        System.out.println("Done!");

    }
}

import java.util.ArrayList;

public class Part2 {

    public static void main(String[] args) {
        int input = 349;
        int cycles = 50000000;
        int valueOfKeyPosition = 1;
        int bufferSize = 2;

        int currentPosition = 1;
        for (int currentCycle = 2; currentCycle <= cycles; currentCycle++) {
            currentPosition += input;
            while (currentPosition >= bufferSize) {
                currentPosition -= bufferSize;
            }

            if (currentPosition == 0) {
                valueOfKeyPosition = currentCycle;
            }

            bufferSize++;
            currentPosition++;
        }

        System.out.println(valueOfKeyPosition);
        System.out.println("Done!");

    }
}

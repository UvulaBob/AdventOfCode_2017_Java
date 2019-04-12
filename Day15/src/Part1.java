public class Part1 {

    public static void main(String[] args) {
        int numberOfCycles = 40000000;
        long prevValueGenA = 512L;
        long prevValueGenB = 191L;

        int divisor = 2147483647;
        long factorGenA = 16807L;
        long factorGenB = 48271L;


        int pairCount = 0;
        for (int currentCycle = 0; currentCycle < numberOfCycles; currentCycle++) {
            prevValueGenA = (factorGenA * prevValueGenA) % divisor;
            prevValueGenB = (factorGenB * prevValueGenB) % divisor;
            StringBuilder binaryA = new StringBuilder(Long.toBinaryString(prevValueGenA));
            StringBuilder binaryB = new StringBuilder(Long.toBinaryString(prevValueGenB));
            while (binaryA.length() < 16) {
                binaryA.insert(0, "0");
            }

            while (binaryB.length() < 16) {
                binaryB.insert(0, "0");
            }

            binaryA.reverse();
            binaryB.reverse();

            if (binaryA.substring(0, 16).equals(binaryB.substring(0, 16))) {
                pairCount++;
            }

        }

        System.out.println(pairCount);
        System.out.println("Done!");

    }
}

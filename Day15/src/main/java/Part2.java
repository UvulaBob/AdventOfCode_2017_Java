public class Part2 {

    public static void main(String[] args) {
        int numberOfPairs = 5000000;
        long prevValueGenA = 512L;
        long prevValueGenB = 191L;

        int divisor = 2147483647;
        long factorGenA = 16807L;
        long factorGenB = 48271L;

        boolean lockedGenA = false;
        boolean lockedGenB = false;

        int pairCount = 0;
        int goodPairs = 0;
        while (pairCount < numberOfPairs) {
            if (!lockedGenA) {
                prevValueGenA = (factorGenA * prevValueGenA) % divisor;
                lockedGenA = (prevValueGenA % 4L == 0L);
            }

            if (!lockedGenB) {
                prevValueGenB = (factorGenB * prevValueGenB) % divisor;
                lockedGenB = (prevValueGenB % 8L == 0L);
            }


            if (lockedGenA && lockedGenB) {
                StringBuilder binaryA = new StringBuilder(Long.toBinaryString(prevValueGenA));
                while (binaryA.length() < 16) {
                    binaryA.insert(0, "0");
                }
                binaryA.reverse();

                StringBuilder binaryB = new StringBuilder(Long.toBinaryString(prevValueGenB));
                while (binaryB.length() < 16) {
                    binaryB.insert(0, "0");
                }
                binaryB.reverse();

                if (binaryA.substring(0, 16).equals(binaryB.substring(0, 16))) {
                    goodPairs++;
                }

                lockedGenA = false;
                lockedGenB = false;
                pairCount++;
            }

        }

        System.out.println(goodPairs);
        System.out.println("Done!");

    }
}

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

public class Part1 {

    public static void main(String[] args) {
        String input = "amgozmfv";

        StringBuilder grid = new StringBuilder();

        for (int row = 0; row < 128; row++) {
            String knotHash = generateKnotHash(input + "-" + row);
            grid.append(hexToBin(knotHash));
        }


        int occupiedSquares = grid.toString().replace("0", "").length();
        System.out.println(occupiedSquares);
        System.out.println("Done!");

    }

    private static String generateKnotHash(String input) {
        ArrayList<Byte> lengths = new ArrayList<>();

        for (String character : input.split("")) {
            for (byte characterByte : character.getBytes(StandardCharsets.US_ASCII)) {
                lengths.add(characterByte);
            }
        }

        lengths.add((byte) 17);
        lengths.add((byte) 31);
        lengths.add((byte) 73);
        lengths.add((byte) 47);
        lengths.add((byte) 23);


        ArrayList<String> rope = new ArrayList<>();

        for (int i = 0; i < 256; i++) {
            rope.add(String.valueOf(i));
        }

        int skipSize = 0;
        int currentPosition = 0;
        for (int round = 0; round < 64; round++) {
            for (int length : lengths) {
                ArrayList<Integer> indexesToChange = new ArrayList<>();
                for (int i = 0; i < length; i++) {
                    indexesToChange.add(currentPosition);
                    currentPosition++;
                    if (currentPosition == rope.size()) {
                        currentPosition = 0;
                    }
                }

                ArrayList<String> markersToReverse = new ArrayList<>();
                for (int indexToChange : indexesToChange) {
                    markersToReverse.add(rope.get(indexToChange));
                }

                Collections.reverse(markersToReverse);

                for (int indexToChange : indexesToChange) {
                    rope.set(indexToChange, markersToReverse.get(0));
                    markersToReverse.remove(0);
                }

                currentPosition += skipSize;
                while (currentPosition >= rope.size()) {
                    currentPosition -= rope.size();
                }
                skipSize++;
            }

        }

        ArrayList<Integer> denseHash = new ArrayList<>();
        for (int block = 0; block < 16; block++) {
            int blockValue = Integer.parseInt(rope.get(0));
            rope.remove(0);
            for (int value = 0; value < 15; value++) {
                blockValue ^= Integer.parseInt(rope.get(0));
                rope.remove(0);
            }
            denseHash.add(blockValue);
        }

        StringBuilder hexHash = new StringBuilder();
        for (Integer block : denseHash) {
            if (block < 16) {
                hexHash.append("0");
            }
            hexHash.append(Integer.toHexString(block));
        }

        return hexHash.toString();
    }

    static String hexToBin(String s) {
        StringBuilder output = new StringBuilder(new BigInteger(s, 16).toString(2));
        while (output.length() < 128) {
            output.insert(0, "0");
        }

        return output.toString();
    }
}

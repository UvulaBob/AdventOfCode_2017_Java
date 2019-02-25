import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Part2 {

    public static void main(String[] args) {
        String input = "amgozmfv";

        HashSet<String> occupiedSquares = new HashSet<>();

        for (int row = 0; row < 128; row++) {
            String knotHash = generateKnotHash(input + "-" + row);
            String newGridLine = hexToBin(knotHash);
            String[] splitGridLine = newGridLine.split("");
            for (int column = 0; column < 128; column ++) {
                if (splitGridLine[column].equals("1")) {
                    occupiedSquares.add(row + "," + column);
                }
            }
        }

        HashSet<HashSet<String>> regions = new HashSet<>();

        for (String occupiedSquare : occupiedSquares) {
            if (regions.size() == 0) {
                regions.add(mapRegion(occupiedSquare, occupiedSquares));
            } else {
                boolean occupiedSquareAlreadyIncludedInRegion = false;
                for (HashSet<String> region : regions) {
                    if (region.contains(occupiedSquare)) {
                        occupiedSquareAlreadyIncludedInRegion = true;
                        break;
                    }
                }

                if (!occupiedSquareAlreadyIncludedInRegion) {
                    regions.add(mapRegion(occupiedSquare, occupiedSquares));
                }
            }
        }

        System.out.println(regions.size());
        System.out.println("Done!");

    }

    private static HashSet<String> mapRegion(String occupiedSquare, HashSet<String> occupiedSquares) {
        HashSet<String> newRegion = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.add(occupiedSquare);

        while (!queue.isEmpty()) {
            String currentSquare = queue.poll();
            if (!newRegion.contains(currentSquare) && occupiedSquares.contains(currentSquare)) {
                newRegion.add(currentSquare);
                int x = Integer.parseInt(currentSquare.split(",")[0]);
                int y = Integer.parseInt(currentSquare.split(",")[1]);
                if (x > 0) {
                    queue.add((x - 1) + "," + y);
                }
                if (y > 0) {
                    queue.add(x + "," + (y - 1));
                }
                if (x < 127) {
                    queue.add((x + 1) + "," + y);
                }
                if (y < 127) {
                    queue.add(x + "," + (y + 1));
                }
            }
        }

        return newRegion;
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

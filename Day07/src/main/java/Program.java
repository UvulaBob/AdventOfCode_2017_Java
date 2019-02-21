import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Program implements Comparable<Program>{

    String name;
    int weight;
    ArrayList<Program> childPrograms = new ArrayList<>();
    Program parentProgram;

    int totalWeight() {
        int totalWeight = weight;
        for (Program childProgram : childPrograms) {
            totalWeight += childProgram.totalWeight();
        }

        return totalWeight;
    }

    boolean childrenHaveEvenWeights() {
        HashMap<String, Integer> childProgramWeights = new HashMap<>();
        for (Program childProgram : childPrograms) {
            childProgramWeights.put(childProgram.name, childProgram.totalWeight());
        }

        HashSet<Integer> weights = new HashSet<>(childProgramWeights.values());
        return weights.size() == 1;
    }

    public String toString() {
        return this.name + ": " + this.totalWeight();
    }

    public int compareTo(Program p) {
        return Integer.compare(this.totalWeight(), p.totalWeight());
    }

}

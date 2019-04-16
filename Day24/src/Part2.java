import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Part2 {

	private static ArrayList<ArrayList<Component>> bridges = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));
		ArrayList<Component> componentList = new ArrayList<>();

		for (String line : lines) {
			Component newComponent = new Component();
			String[] splitLine = line.split("/");
			newComponent.ports.add(Integer.parseInt(splitLine[0]));
			newComponent.ports.add(Integer.parseInt(splitLine[1]));
			componentList.add(newComponent);
		}

		for (Component component : componentList) {
			if (component.ports.contains(0)) {
				ArrayList<Component> newBridge = new ArrayList<>();
				newBridge.add(component);
				ArrayList<Component> tempComponentList = new ArrayList<>(componentList);
				tempComponentList.remove(component);
				if (component.ports.get(0) != 0) {
					buildBridge(newBridge, component.ports.get(0), tempComponentList);
				} else {
					buildBridge(newBridge, component.ports.get(1), tempComponentList);
				}
			}
		}
		
		int longestBridge = 0;
		for (ArrayList<Component> bridge : bridges) {
			longestBridge = (bridge.size() > longestBridge) ? bridge.size() : longestBridge;

		}

		int highestStrength = 0;
		for (ArrayList<Component> bridge : bridges) {
			if (bridge.size() == longestBridge) {
				int currentStrength = 0;
				for (Component component : bridge) {
					currentStrength += component.ports.get(0) + component.ports.get(1);
				}

				highestStrength = (currentStrength > highestStrength) ? currentStrength : highestStrength;
			}
		}

		System.out.println(highestStrength);
		System.out.println("Done!");
	}

	private static ArrayList<Component> buildBridge(ArrayList<Component> unfinishedBridge, int openPort, ArrayList<Component> availableComponents) {
		bridges.add(unfinishedBridge);
		ArrayList<Component> finishedBridge = new ArrayList<>(unfinishedBridge);

		for (Component availableComponent : availableComponents) {
			if (availableComponent.ports.contains(openPort)) {
				ArrayList<Component> newBridge = new ArrayList<>(unfinishedBridge);
				newBridge.add(availableComponent);
				ArrayList<Component> tempComponentList = new ArrayList<>(availableComponents);
				tempComponentList.remove(availableComponent);
				if (availableComponent.ports.get(0) != openPort) {
					finishedBridge = buildBridge(newBridge, availableComponent.ports.get(0), tempComponentList);
				} else {
					finishedBridge = buildBridge(newBridge, availableComponent.ports.get(1), tempComponentList);
				}
			}
		}
		return finishedBridge;
	}
}


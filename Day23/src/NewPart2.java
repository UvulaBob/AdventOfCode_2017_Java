public class NewPart2 {

	public static void main(String[] args) {
		int currentValue = 105700;
		int endValue = 122700;

		int divisors = 0;

		while (currentValue <= endValue) {
			for (int valueToTest = 2; valueToTest < currentValue; valueToTest++) {
				if (currentValue % valueToTest == 0) {
					divisors++;
					break;
				}
			}
			currentValue += 17;
		}

		System.out.println(divisors);
		System.out.println("Done!");
	}
}

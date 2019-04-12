import java.io.IOException;

public class NewPart2 {

	public static void main(String[] args) throws IOException {
		long a = 1;
		long b = 0;
		long c = 0;
		long d = 0;
		long e = 0;
		long f = 0;
		long g = 0;
		long h = 0;

		//System.out.println(String.format("a: %s, b: %s, c: %s, d: %s, e: %s, f:%s, g:%s, h:%s", a, b, c, d, e, f, g, h));
		b = 57;
		b *= 100;
		b += 100000L;
		c = b;
		c += 17000L;

		while (true) {
			f = 1;
			d = b / 2;
			e = 2;
			g = (d * e) - b;
			if (g == 0) {
				f = 0;
			}
			e += 1;
			g = e - b;
			while (g != 0) {
				g = (d * e) - b;
				if (g == 0) {
					f = 0;
				}
				e += 1;
				g = e - b;
			}
			d += 1;
			g = d - b;
			while (g != 0) {
				e = 2;
				g = (d * e) - b;
				if (g == 0) {
					f = 0;
				}
				e += 1;
				g = e - b;
				while (g != 0) {
					g = (d * e) - b;
					if (g == 0) {
						f = 0;
					}
					e += 1;
					g = e - b;
				}
				d+= 1;
				g = d - b;
			}
			if (f == 0) {
				h += 1;
			}
			g = b - c;
			if (g == 0) {
				System.out.println("Done!");
				System.out.println("h: " + h);
				return;
			}
			b += 17;
		}
	}
}

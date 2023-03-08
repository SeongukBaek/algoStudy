import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_2527_직사각형 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int x3 = Integer.parseInt(st.nextToken());
			int y3 = Integer.parseInt(st.nextToken());
			int x4 = Integer.parseInt(st.nextToken());
			int y4 = Integer.parseInt(st.nextToken());
			findOverlapedShape(x1, y1, x2, y2, x3, y3, x4, y4);
		}
	}

	private static void findOverlapedShape(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
		if (x2 < x3 || y2 < y3 || x4 < x1 || y4 < y1) {
			System.out.println("d");
			return;
		}
		if ((x2 == x3 && y2 == y3) || (x2 == x3 && y1 == y4) || (x1 == x4 && y2 == y3) || (x1 == x4 && y1 == y4)) {
			System.out.println("c");
			return;
		}
		if (x2 == x3 || y2 == y3 || x1 == x4 || y1 == y4) {
			System.out.println("b");
			return;
		}
		System.out.println("a");

	}
}

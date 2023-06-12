import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static int[] stone;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		int N = Integer.parseInt(info[0]);
		int H = Integer.parseInt(info[1]);

		int[] ceil = new int[H];
		int[] floor = new int[H];

		for (int index = 0; index < N / 2 ; index++) {
			floor[Integer.parseInt(br.readLine()) - 1]++;
			ceil[Integer.parseInt(br.readLine()) - 1]++;
		}

		// 누적합
		for (int index = H - 2; index >= 0 ; index--) {
			ceil[index] += ceil[index + 1];
			floor[index] += floor[index + 1];
		}

		stone = new int[H];
		int min = N + 1;

		for (int index = 0; index < H; index++) {
			stone[index] = floor[index] + ceil[H - 1 - index];
			min = Math.min(min, stone[index]);
		}

		System.out.println(min + " " + countMin(min));
	}

	private static int countMin(int min) {
		return (int) Arrays.stream(stone).filter(number -> number == min).count();
	}
}
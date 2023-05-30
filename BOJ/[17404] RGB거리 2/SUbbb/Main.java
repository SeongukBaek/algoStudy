import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static final int MAX = 1000 * 1000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] colorCosts = new int[N][3];
		int[][] dp = new int[N][3];

		for (int index = 0; index < N; index++) {
			colorCosts[index] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		int answer = MAX;
		// 첫 집을 하나의 색으로 고정한 후 모든 집을 색칠해보고 마지막에 answer 갱신
		for (int color = 0; color < 3; color++) {
			//RED, GREEN, BLUE로 칠하는 경우 각 색을 제외한 나머지는 최댓값으로 초기화해서 사용하지 않도록 한다.
			for (int currentColor = 0; currentColor < 3; currentColor++) {
				if (currentColor == color) {
					dp[0][currentColor] = colorCosts[0][currentColor];
				} else {
					dp[0][currentColor] = MAX;
				}
			}

			// 첫 집 이후의 집들을 색칠하는 최소 비용 계산
			for (int house = 1; house < N; house++) {
				int prevHouse = house - 1;
				dp[house][0] = Math.min(dp[prevHouse][1], dp[prevHouse][2]) + colorCosts[house][0];
				dp[house][1] = Math.min(dp[prevHouse][0], dp[prevHouse][2]) + colorCosts[house][1];
				dp[house][2] = Math.min(dp[prevHouse][0], dp[prevHouse][1]) + colorCosts[house][2];
			}

			// answer 갱신
			for (int currentColor = 0; currentColor < 3; currentColor++) {
				if (currentColor != color) {
					answer = Math.min(answer, dp[N - 1][currentColor]);
				}
			}
		}

		System.out.println(answer);
	}
}
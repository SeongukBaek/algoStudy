import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] houses;
	static int[][][] dp; //집 번호, 색, 1번집 색
	static int N;

	public static void main(String[] args) throws IOException {
		init();
		calCost();
		System.out.println(findMinCost());
	}

	private static int findMinCost() {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				min = Math.min(min, dp[N-1][i][j]);
			}
		}
		return min;
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		houses = new int[N][3];
		dp = new int[N][3][3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			houses[i][0] = Integer.parseInt(st.nextToken());
			houses[i][1] = Integer.parseInt(st.nextToken());
			houses[i][2] = Integer.parseInt(st.nextToken());
		}
	}

	private static void calCost() {
		for (int color = 0; color < 3; color++) {
			dp[0][color][color] = houses[0][color];
			dp[0][(color - 1 + 3) % 3][color] = 1000000;
			dp[0][(color + 1 + 3) % 3][color] = 1000000;

			for (int i = 1; i < N - 1; i++) {
				for (int j = 0; j < 3; j++) {
					dp[i][j][color] = Math.min(dp[i - 1][(j - 1 + 3) % 3][color], dp[i - 1][(j + 1 + 3) % 3][color])
							+ houses[i][j];
				}
			}

			for (int j = 0; j < 3; j++) {
                //첫 번째 집과 마지막 집의 색이 같은 경우
				if (j == color) {
					dp[N - 1][j][color] = Integer.MAX_VALUE;
					continue;
				}

				dp[N - 1][j][color] = Math.min(dp[N - 2][(j - 1 + 3) % 3][color], dp[N - 2][(j + 1 + 3) % 3][color])
						+ houses[N - 1][j];
			}
		}
	}
}
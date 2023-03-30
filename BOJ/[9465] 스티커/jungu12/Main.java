import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());

			int[][] dp = new int[2][N];
			int[][] score = new int[2][N];

			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					score[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			dp[0][0] = score[0][0];
			dp[1][0] = score[1][0];
			
			if (N > 1) {
				dp[0][1] = dp[1][0] + score[0][1];
				dp[1][1] = dp[0][0] + score[1][1];
			}

			for (int i = 2; i < N; i++) {
				dp[0][i] = Math.max(dp[1][i - 2], dp[1][i - 1]) + score[0][i];
				dp[1][i] = Math.max(dp[0][i - 2], dp[0][i - 1]) + score[1][i];
			}

			System.out.println(Math.max(dp[0][N - 1], dp[1][N - 1]));
		}
	}
}
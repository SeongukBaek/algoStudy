import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] wines = new int[N];

		for (int index = 0; index < N; index++) {
			wines[index] = Integer.parseInt(br.readLine());
		}

		int[][] dp = new int[N][3];
		dp[0][0] = dp[0][1] = wines[0];

		for (int index = 1; index < N; index++) {
			dp[index][0] = wines[index] + dp[index - 1][1];
			dp[index][1] = wines[index] + dp[index - 1][2];
			dp[index][2] = Math.max(dp[index - 1][0], Math.max(dp[index - 1][1], dp[index - 1][2]));
		}

		System.out.println(Math.max(dp[N - 1][0], Math.max(dp[N - 1][1], dp[N - 1][2])));
	}
}
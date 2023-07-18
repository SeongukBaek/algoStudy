import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[][][] dp = new int[N + 1][2][3]; // 날짜, 지각 횟수, 연속으로 결석한 횟수
		dp[1][0][0] = 1;
		dp[1][0][1] = 1;
		dp[1][1][0] = 1;

		for (int i = 2; i <= N; i++) {
			dp[i][0][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % 1000000;
			dp[i][0][1] = dp[i - 1][0][0];
			dp[i][0][2] = dp[i - 1][0][1];
			dp[i][1][0] = (Arrays.stream(dp[i - 1][0]).sum() + Arrays.stream(dp[i - 1][1]).sum()) % 1000000;
			dp[i][1][1] = dp[i - 1][1][0];
			dp[i][1][2] = dp[i - 1][1][1];
		}
		
		System.out.println((Arrays.stream(dp[N][0]).sum() + Arrays.stream(dp[N][1]).sum()) % 1000000);
	}
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] table = new int[N + 1];
		int[] dp = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			table[i] = Integer.parseInt(br.readLine());
		}

		dp[1] = table[1];
		
		if (N > 1) {
			dp[2] = table[1] + table[2];
		}

		for (int i = 3; i <= N; i++) {
			dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + table[i], dp[i - 3] + table[i - 1] + table[i]));
		}

		System.out.println(dp[N]);
	}

}
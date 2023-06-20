import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] wine = new int[n+1];
		
		for (int i = 1; i < n+1; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[n+1];
		
		// 초기값 설정
		dp[1] = wine[1];
		
		// n = 1일 경우 예외처리
		if (n > 1) {
			dp[2] = wine[1] + wine[2];
		}
		
		for (int i = 3; i <= n; i++) {
			dp[i] = Math.max(dp[i-1], Math.max(dp[i-2], dp[i-3] + wine[i-1]) + wine[i]);
		}
		System.out.println(dp[n]);
	}
	
}
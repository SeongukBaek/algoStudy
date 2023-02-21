import java.io.*;
import java.util.*;

public class Main {
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		int[][][] dp = new int[n][n][3];
		int[][] map = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 최상단 초기화
		for (int i = 1; i < n; i++) {
			if (map[0][i] == 1) {
				break;
			}
			dp[0][i][0] = 1;
		}
		
		for (int i = 1; i < n; i++) {
			for (int j = 2; j < n; j++) {
				// 벽인 경우
				if (map[i][j] == 1) {
					continue;
				}
				// 가로 초기화
				dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][1];
				// 대각선 초기화
				if (map[i - 1][j] != 1 && map[i][j - 1] != 1) {
					dp[i][j][1] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
				}
				// 세로 초기화
				dp[i][j][2] = dp[i-1][j][1] + dp[i-1][j][2];
			}
		}
		
		System.out.println(dp[n - 1][n - 1][0] + dp[n - 1][n - 1][1] + dp[n - 1][n - 1][2]);
	}
}

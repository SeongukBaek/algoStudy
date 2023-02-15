package BOJ;

import java.io.*;

// [11660] 구간 합 구하기 5 - DP
public class Main_11660{
	
	static int[][] matrix;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		
		int N = Integer.parseInt(str[0]); // 표의 크기 
		int M = Integer.parseInt(str[1]); // 횟수 
		
		matrix = new int[N+1][N+1];
		dp = new int[N+1][N+1];
		
		// 입력 
		for(int i = 1; i <= N; i++) {
			str = br.readLine().split(" ");
			for(int j = 1; j <= N; j++) {
				matrix[i][j] = Integer.parseInt(str[j-1]);
			}
		}
		
		// 구간합을 구한 dp 매트릭스 
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + matrix[i][j];
			}
		}
		
		int x1, y1, x2, y2; // 좌표 
		int sum;
		
		for(int i = 0; i < M; i++) {
			sum = 0;
			
			str = br.readLine().split(" ");
			x1 = Integer.parseInt(str[0]);
			y1 = Integer.parseInt(str[1]);
			x2 = Integer.parseInt(str[2]);
			y2 = Integer.parseInt(str[3]);
			
			sum = dp[x2][y2] - dp[x2][y1-1] - dp[x1-1][y2] + dp[x1-1][y1-1];
			
			System.out.println(sum);
		}
	}
}

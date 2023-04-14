import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] triangle;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		/* 입력 및 변수 초기화 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		triangle = new int[n][n];
		
		StringTokenizer st;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<i+1; j++) {
				triangle[i][j] = Integer.parseInt(st.nextToken());
			}
		}	
		
		/* 출력 */
		System.out.println(searchMaxPath());
	}

	/* Dynamic Programming : 최대값을 구할 수 있는 경로 찾기 */
	private static int searchMaxPath() {
		int[][] dp = new int[n][n];
		// 초기값 설정
		dp[n-1] = triangle[n-1].clone();
		
		for(int i=n-2; i>=0; i--) {
			for(int j=0; j<i+1; j++) {
				// dp[i][j]  = max(왼쪽 위 대각선, 오른쪽 위 대각선)
				dp[i][j] += triangle[i][j] + Math.max(dp[i+1][j], dp[i+1][j+1]);
			}	
		}
		
		return dp[0][0];
	}
}
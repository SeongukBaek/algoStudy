import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N];
		int[][] triangle = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int j = 0;
			while(st.hasMoreTokens()) {
				triangle[i][j] = Integer.parseInt(st.nextToken());
				j++;
			}
		}
		
		// 가장 마지막 레벨의 수로 dp 배열 초기화 
		for(int i = 0; i < N; i++) {
			dp[i] = triangle[N-1][i];
		}
		
		// 맨 밑단부터 위로 올라오기 
		for(int i = N-2; i >= 0; i--) {
			for(int j = 0; j <= i; j++) {
				dp[j] = Math.max(dp[j] + triangle[i][j], dp[j+1] + triangle[i][j]);
			}
		}
		
		System.out.println(dp[0]);
	}
}

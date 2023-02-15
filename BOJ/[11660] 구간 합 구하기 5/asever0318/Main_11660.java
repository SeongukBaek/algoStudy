package BOJ;

import java.io.*;

// [11660] ���� �� ���ϱ� 5 - DP
public class Main_11660{
	
	static int[][] matrix;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		
		int N = Integer.parseInt(str[0]); // ǥ�� ũ�� 
		int M = Integer.parseInt(str[1]); // Ƚ�� 
		
		matrix = new int[N+1][N+1];
		dp = new int[N+1][N+1];
		
		// �Է� 
		for(int i = 1; i <= N; i++) {
			str = br.readLine().split(" ");
			for(int j = 1; j <= N; j++) {
				matrix[i][j] = Integer.parseInt(str[j-1]);
			}
		}
		
		// �������� ���� dp ��Ʈ���� 
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + matrix[i][j];
			}
		}
		
		int x1, y1, x2, y2; // ��ǥ 
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

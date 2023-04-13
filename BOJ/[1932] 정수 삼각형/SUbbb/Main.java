import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int[][] triangle;
	private static int[][] dp;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	N = Integer.parseInt(br.readLine());
    	triangle = new int[N][N];
    	dp = new int[N][N];
    	
    	for (int x = 0; x < N; x++) {
			st = new StringTokenizer(br.readLine());
    		for (int y = 0; y <= x; y++) {
    			triangle[x][y] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	dp[0][0] = triangle[0][0];
    	
    	for (int row = 1; row < N; row++) {
    		// col == 0
    		dp[row][0] = triangle[row][0] + dp[row - 1][0];
    		
    		for (int col = 1; col < row; col++) {
    			dp[row][col] = triangle[row][col] + Math.max(dp[row - 1][col - 1], dp[row - 1][col]);
    		}
    		
    		// col == row
    		dp[row][row] = triangle[row][row] + dp[row - 1][row - 1];
    	}
    	
    	System.out.println(Arrays.stream(dp[N - 1]).max().getAsInt());
    }
}
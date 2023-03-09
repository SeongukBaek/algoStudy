import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] board;

	public static void main(String[] args) throws IOException {
		input();
		System.out.println(countRoute());
		
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static long countRoute() {
		long[][] dp = new long[N][N];
		dp[0][0] = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
                //board[i][j]로 갈 수 있으며, board[i][j]에서 다른 곳으로 이동 가능한 경우
				if (dp[i][j] >= 1 && board[i][j] != 0) {
                    //아래로 이동하여 경로 업데이트
					if (i + board[i][j] < N) {
						dp[i + board[i][j]][j] += dp[i][j];
					}
                    //오른쪽으로 이동하여 경로 업데이트
					if (j + board[i][j] < N) {
						dp[i][j + board[i][j]] += dp[i][j];
					}
				}
			}
		}
		return dp[N-1][N-1];
	}
}
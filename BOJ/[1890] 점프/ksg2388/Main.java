import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        long[][] dp = new long[N][N];
        dp[0][0] = 1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 이 위치로 온 경우가 있는 경우
                if (dp[i][j] > 0) {
                    if (i == N - 1 && j == N - 1) {
                        break;
                    }
                    int num = map[i][j];
                    // 오른쪽으로 이동하는 경우
                    if (j + num < N) {
                        dp[i][j + num] += dp[i][j];
                    }
                    // 아래로 이동하는 경우
                    if (i + num < N) {
                        dp[i + num][j] += dp[i][j];
                    }
                }
            }
        }

        System.out.println(dp[N - 1][N - 1]);
    }
}
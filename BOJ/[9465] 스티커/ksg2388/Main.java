import java.io.*;
import java.util.*;

public class Main {
  static int t, n;
  static int[][] stickers;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = null;
    t = Integer.parseInt(br.readLine());

    for (int tc = 0; tc < t; tc++) {
      n = Integer.parseInt(br.readLine());
      stickers = new int[2][n];
      int[][] dp = new int[n][3];

      for (int i = 0; i < 2; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < n; j++) {
          stickers[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      // 위쪽 값이 들어간 경우
      dp[0][0] = stickers[0][0];
      // 아래쪽 값이 들어간 경우
      dp[0][1] = stickers[1][0];
      // 위아래 둘다 안들어간 경우
      dp[0][2] = 0;

      for (int i = 1; i < n; i++) {
        dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][2]) + stickers[0][i];
        dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][2]) + stickers[1][i];
        dp[i][2] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2]));
      }

      System.out.println(Math.max(dp[n - 1][0], Math.max(dp[n - 1][1], dp[n - 1][2])));
    }
  }
}
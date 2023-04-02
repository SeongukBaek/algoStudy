import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] stickers, dp;

    public static void main(String[] args) throws IOException {
        /* 입력 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for(int t=0; t<tc; t++){
            n = Integer.parseInt(br.readLine());
            stickers = new int[2][n];
            dp = new int[2][n];
            StringTokenizer st;
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    stickers[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            /* 출력 */
            System.out.println(searchSticker());
        }
    }

    /* dp : 스티커 탐색 */
    static int searchSticker() {
        // 초기값 설정
        dp[0][0] = stickers[0][0];
        dp[1][0] = stickers[1][0];

        for(int i=1; i<n; i++) {
            dp[0][i] = Math.max(dp[0][i-1], dp[1][i-1]+stickers[0][i]);
            dp[1][i] = Math.max(dp[1][i-1], dp[0][i-1]+stickers[1][i]);
        }

        return Math.max(dp[0][n-1], dp[1][n-1]);
    }
}

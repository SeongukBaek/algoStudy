import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {

        /* 입력 및 변수 초기화 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][][] dp = new int[3][3][n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] firstCost = new int[3];
        for (int i = 0; i < 3; i++) {
            firstCost[i] = Integer.parseInt(st.nextToken());
        }


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Arrays.fill(dp[i][j], 2_000_000);
            }
        }

        int seq = 1; // 집 순서
        for (int color = 0; color < 3; color++) {
            dp[color][color][seq] = firstCost[color];
        }
        seq++;

        for (;seq <= n; seq++) {
            st = new StringTokenizer(br.readLine());
            int[] cost = new int[3];
            for (int i = 0; i < 3; i++) {
                cost[i] = Integer.parseInt(st.nextToken());
            }

            
            /* DP : 집 색칠할 수 있는 모든 비용 구하기 */
            for (int start = 0; start < 3; start++) {
                for (int end = 0; end < 3; end++) {
                    dp[start][end][seq] = Math.min(dp[start][(end+1)%3][seq - 1], dp[start][(end+2)%3][seq - 1]) + cost[end];
                }
            }
        }

        /* 최소 비용 구하기 */
        int minCost = Integer.MAX_VALUE;
        for (int start = 0; start < 3; start++) {
            for (int end = 0; end < 3; end++) {
                //시작 색, 끝 색 같을 경우
                if (start == end) {
                    continue;
                }

                minCost = Math.min(dp[start][end][n], minCost);
            }
        }

        System.out.println(minCost);
    }

}
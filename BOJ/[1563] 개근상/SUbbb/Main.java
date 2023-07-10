import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int MOD = 1000000;

    // O는 제한 없음
    // L은 0 ~ 1개 가능
    // A는 0 ~ 3개 가능, 연속 2개까지만 가능
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // [날짜][지각 횟수][연속 결석 횟수]
        int[][][] dp = new int[N][2][3];
        dp[0][0][0] = dp[0][0][1] = dp[0][1][0] = 1;

        for (int index = 1; index < N; index++) {
            // 지각도 한 적 없고, 연속된 결석도 없으려면,
            // 앞에서 지각이 없는 경우와, 연속된 결석이 1번인 경우, 2번인 경우의 합!
            dp[index][0][0] = (dp[index - 1][0][0] + dp[index - 1][0][1] + dp[index - 1][0][2]) % MOD;

            // 지각한 적 없고, 연속된 결석이 1개이려면,
            // 지각이 없고, 연속된 결석이 없는 경우에 결석 1개가 붙어야 함!
            dp[index][0][1] = dp[index - 1][0][0];

            // 지각한 적 없고, 연속된 결석이 2번이려면
            // 앞에서 연속된 결석이 1번인 경우에 결석 1개가 붙어야 함!
            dp[index][0][2] = dp[index - 1][0][1];

            // 지각을 1번 하고, 연속된 결석이 없으려면
            // 앞에서는 지각을 하지 않고, 결석이 0 ~ 2번이거나,
            // 앞에서 지각을 1번 하고, 결석이 0 ~ 2번했어야 함!
            dp[index][1][0] = (dp[index - 1][0][0] + dp[index - 1][0][1] + dp[index - 1][0][2] + dp[index - 1][1][0]
                    + dp[index - 1][1][1] + dp[index - 1][1][2]) % MOD;

            // 위와 동일!
            dp[index][1][1] = dp[index - 1][1][0];
            dp[index][1][2] = dp[index - 1][1][1];
        }

        System.out.println((Arrays.stream(dp[N - 1][0]).sum() + Arrays.stream(dp[N - 1][1]).sum()) % MOD);
    }
}
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        /* 입력 및 변수 초기화 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+2];
        
        /* dp : 2xn의 사각형 방법 구하기 */
        dp[1] = 1;
        dp[2] = 2;
        for(int i=3; i<=n; i++) {
            dp[i] = (dp[i-2] + dp[i-1]) % 10007;
        }
        
        /* 출력 */
        System.out.println(dp[n]);
    }

}
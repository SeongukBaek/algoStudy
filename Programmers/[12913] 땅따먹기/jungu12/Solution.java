import java.util.*;
class Solution {
    int solution(int[][] land) {
        int[][] dp = new int[land.length][4];

        dp[0] = land[0].clone();
        
        for(int i = 1; i < land.length; i++) {
            for(int j = 0; j < 4; j++) {
                int max = 0;
                for(int k = 0; k < 4; k++) {
                    if(j == k) {
                        continue;
                    }
                    
                    max = Math.max(max, dp[i - 1][k]);
                }
                dp[i][j] = max + land[i][j];
            }
        }
        
        return Arrays.stream(dp[land.length - 1]).max().getAsInt();
    }
}
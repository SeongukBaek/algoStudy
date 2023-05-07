class Solution {
    static int[][] dp;
    int solution(int[][] land) {
        int n = land.length;
        dp = new int[n][4];
        // 초기값 설정
        dp[0] = land[0].clone();
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                dp[i][j] = land[i][j] + getMaxValue(i, j);
            }  
        }
        
        int maxScore = 0;
        
        for (int i = 0; i < 4; i++) {
            maxScore = Math.max(maxScore, dp[n-1][i]); 
        }
        
        return maxScore;
    }
    
    /* 같은 열에서 최대값 구하기 */
    public int getMaxValue(int i, int j) {
        int max = 0;
        for (int k = 0; k < 4; k++) {
            if (j == k) continue;
            max = Math.max(max, dp[i-1][k]);
        }      
        return max;
    }
}
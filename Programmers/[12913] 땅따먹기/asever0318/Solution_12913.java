class Solution {
    static int[][] dp;
    
    static int solution(int[][] land) {
		int size = land.length;
		dp = new int[size][4];
		
		for(int i =0; i < 4; i++) {
			dp[0][i] = land[0][i];
		}
		
		for(int i = 0; i < size-1; i++) {
			for(int j = 0; j < 4; j++) {
				for(int k = 0; k < 4; k++) {
					if(j == k) {
						continue;
					}
					
					if(dp[i+1][k] < dp[i][j] + land[i+1][k]) {
						dp[i+1][k] = dp[i][j] + land[i+1][k];
					}
				}
			}
		}
		
		return getMax(size);
	}
	
	static int getMax(int size) {
		int max = 0;
		
		for(int i = 0; i < 4; i++) {
			max = Math.max(max, dp[size-1][i]);
		}
		
		return max;
	}
}
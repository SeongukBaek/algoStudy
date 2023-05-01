import java.util.Arrays;

class Solution {
    int solution(int[][] land) {
    	int[] scores = land[0].clone();
    	
        for (int row = 1; row < land.length; row++) {
            // 현재 행의 얻을 수 있는 최댓값 갱신을 저장할 배열
            int[] tempScore = new int[4];
            
            for (int col = 0; col < 4; col++) {
                int current = scores[col];
                
                for (int scoreIndex = 0; scoreIndex < 4; scoreIndex++) {
                    // 현재 열과 같은 열은 패스
                    if (scoreIndex == col) {
                        continue;
                    }
                    
                    if (tempScore[scoreIndex] < land[row][scoreIndex] + current) {
                    	tempScore[scoreIndex] = land[row][scoreIndex] + current;
                    }
                }
            }
            
            // 현재 행에 대한 최댓값 갱신 이후, 점수 배열 갱신
            scores = tempScore.clone();
        }
        
        return Arrays.stream(scores).max().getAsInt();
    }
}
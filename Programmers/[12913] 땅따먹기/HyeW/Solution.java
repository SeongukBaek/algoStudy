class Solution {
    final int COL = 4;
    int solution(int[][] land) {
        int[][] maxValue = new int[land.length][COL]; //0행 : pre, 1행 : cur
        maxValue[0] = land[0].clone();
        
        for(int cur = 1; cur < land.length; cur++){
            for(int i = 0; i < COL; i++){ //현재
                for(int j = 0; j < COL; j++){ //이전 값
                    // 같은 열을 연속해서 밟을 수 없다.
                    if(i==j){
                        continue;
                    }
                    maxValue[cur][i] = Math.max(maxValue[cur][i], land[cur][i]+maxValue[cur-1][j]);
                }
            }
        }
        
        int answer = 0;
        for(int i = 0; i < COL; i++){
            answer = Math.max(answer, maxValue[land.length-1][i]);
        }
        
        return answer;
    }
}
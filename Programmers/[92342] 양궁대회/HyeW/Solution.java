class Solution {
    int[] answer;
    int maxScore = -1;
    
    public int[] solution(int n, int[] info) {
        answer = new int[11];    
        
        lionshoot(0, 0, n, info, new int[11]);
        if(maxScore == -1){
            return new int[]{-1};
        }
        return answer;
    }
    
    // 중복조합을 사용해서 라이언과 어피치의 점수차 찾기
    private void lionshoot(int d, int s, int n, int[] info, int[] arrow){
        if(d == n){
            countScore(info, arrow);
            return;
        }
        
        for(int i = s; i < 11; i++){
            arrow[i]++;
            lionshoot(d+1, i, n, info, arrow);
            arrow[i]--;
        }
    }
    
    //점수 계산
    private void countScore(int[] info, int[] arrow){
        int scoreLion = 0;
        int scoreApeach = 0;
        
        for(int i = 0; i < 11; i++){
            // info : 어피치 화살 위치, arrow : 라이언 화살 위치가 있음
            // 어피치는 0부터 10 index에 10~0점까지 담겨있고 라이언은 0~10점으로 담겨있음
            if(info[10-i] < arrow[i]){
                scoreLion += i;
                continue;
            }
            if(info[10-i] == 0){
                continue;
            }
            scoreApeach += i;
        }
        
        // 어피치가 점수가 더 높으면 리턴
        if(scoreApeach >= scoreLion){
            return;
        }
        
        if(scoreLion-scoreApeach > maxScore){
            maxScore = scoreLion-scoreApeach;
            // 과녁 답 복사
            for(int i = 0; i < 11; i++){
                answer[10-i] = arrow[i];
            }
        }
    }
}
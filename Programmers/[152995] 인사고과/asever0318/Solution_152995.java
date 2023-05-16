import java.util.*;

class Solution {
    static int wanHo;
    static int[] whScore;
    
    public int solution(int[][] scores) {
        wanHo = scores[0][0] + scores[0][1];
        whScore = new int[] {scores[0][0], scores[0][1]};
        
        // 근무태도 점수로 내림차순 정렬 -> 동석차에 대해서는 동료 평가 점수로 오름차순 정렬
        Arrays.sort(scores, (o1, o2) -> {
            return  o1[0] != o2[0] ? o2[0] - o1[0] : o1[1] - o2[1];
        });
        
        return getWanHoRank(scores);
    }
    
    static int getWanHoRank(int[][] scores){
        
        int max = 0; // 동료 평가 점수 max 값
        int cnt = 1; // 완호보다 점수 높은 사람 
        for(int i = 0; i < scores.length; i++){
            
            if(max > scores[i][1]){ // 인센티브 받을 수 없는 직원 패스
                if(whScore[0] == scores[i][0] && whScore[1] == scores[i][1]){ // 인센티브 받을 수 없는 직원이 완호라면 -1 반환 
                    return -1;
                }
                continue;
            }
            
            max = Math.max(max, scores[i][1]);
            if(wanHo < scores[i][0] + scores[i][1]){ // 완호보다 점수가 높은 사람이 있으면 cnt++
                cnt++;
            }
        }
        
        return cnt;
    }
}
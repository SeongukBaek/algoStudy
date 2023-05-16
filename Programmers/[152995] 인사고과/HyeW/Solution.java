import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int rank = 1;
        int[] target = scores[0];

        // 근무 태도 점수 desc, 동료 평가 점수 desc
        Arrays.sort(scores, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            return o2[0] - o1[0];
        });

        // 이전(근무 평가 점수가 작은) 동료 평가 점수 max값
        int preMax = 0;
        // 현재 동료 평가 점수 max값
        int curMax = scores[0][1];

        for(int i = 0; i < scores.length; i++){
            // 근무 평가 점수가 달라지면 동료 평가 점수 max값 갱신
            if(i != 0 && scores[i][0] != scores[i-1][0]){
                preMax = curMax;
                curMax = Math.max(curMax, scores[i][1]);
            }
            // 현재 동료 평가 점수가 max값보다 작다면 인센티브를 받지 못한다.
            if(scores[i][1] < preMax){
                // 인센티브를 받지 못하는 사원이 완호인지 확인한다.
                if(target[0] == scores[i][0] && target[1] == scores[i][1]){
                    return -1;
                }
                continue;
            }
            // 완호 점수 합보다 점수합이 높으면 완호의 등수를 내린다.
            if(target[0] + target[1] < scores[i][0]+scores[i][1]){
                rank++;
            }
        }
        return rank;
    }
}
import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        Arrays.sort(targets, (o1, o2) -> o1[0] - o2[0]);
        
        int end = targets[0][1];
        
        for (int i = 1; i < targets.length; i++) {
            if (end <= targets[i][0]) {
                end = targets[i][1];
                answer++;
                continue;
            }
            
            if (end > targets[i][1]) {
                end = targets[i][1];
            }
        }

        //마지막 요격 미사일 발사는 위 조건에서 걸리지 않으니 answer + 1 return 
        return answer + 1;
    }
}
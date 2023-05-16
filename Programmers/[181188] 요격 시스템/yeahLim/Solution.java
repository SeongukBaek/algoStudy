import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        
        int n = targets.length;
        PriorityQueue<int[]> missile = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]); 
        for (int i = 0; i < n; i++) {
            missile.offer(targets[i]);
        }
        
        int count = 0;
        int[] baseMs = new int[2]; // 기준 미사일
        int[] comparedMs = new int[2]; // 비교할 미사일
        while (!missile.isEmpty()) {
            baseMs = missile.poll();
            count++;
            
            // 기준 미사일의 범위와 겹치는지 확인
            while(!missile.isEmpty()) {
                comparedMs = missile.peek();
                // 겹치는 경우
                if (comparedMs[0] < baseMs[1]) {
                    missile.poll();
                }    
                // 아닌 경우
                else {
                    break;
                }
            }
        }
        
        return count;
    }
}
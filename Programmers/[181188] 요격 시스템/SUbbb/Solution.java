import java.util.*;

class Solution {
    // end를 기준으로 오름차순 정렬
    private Queue<int[]> targetLocations = new PriorityQueue<>(new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            return Integer.compare(o1[1], o2[1]);
        }
    });
    
    public int solution(int[][] targets) {
        for (int[] target : targets) {
            targetLocations.add(target);
        }
        
        int answer = 0;
        
        while (!targetLocations.isEmpty()) {
            int[] current = targetLocations.poll();
            
            // 큐의 peek의 start가 current의 end보다 작은 경우 계속 poll
            while (!targetLocations.isEmpty() && targetLocations.peek()[0] < current[1]) {
                targetLocations.poll();
            }
            
            answer++;
        }
        
        return answer;
    }
}
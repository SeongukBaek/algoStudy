import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < enemy.length; i++) {
            pq.offer(enemy[i]);
            
            // 무적권 사용 개수보다 사이즈가 커질 경우
            if (pq.size() > k) {
                n -= pq.poll();
            }
            
            // n의 개수
            if (n < 0) {
                return i;
            }
        }
        return enemy.length;
    }
}
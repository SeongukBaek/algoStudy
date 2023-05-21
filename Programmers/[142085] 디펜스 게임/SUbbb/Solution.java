import java.util.Queue;
import java.util.PriorityQueue;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        Queue<Integer> enemies = new PriorityQueue<>();

        for (int index = 0; index < enemy.length; index++) {
            enemies.add(enemy[index]);

            // 막아야 하는 적 라운드 수가 무적권을 사용해서 막을 수 있는 라운드 수보다 많아지면, 그 중 가장 작은 적이 있는 라운드를 병사로 디펜스!
            if (enemies.size() > k) {
                n -= enemies.poll();
            }

            // 만약 병사 수가 음수가 된다면, 더 이상 막을 수 없음!
            if (n < 0) {
                return index;
            }
        }

        return enemy.length;
    }
}
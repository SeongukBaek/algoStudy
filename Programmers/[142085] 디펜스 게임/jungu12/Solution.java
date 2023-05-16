import java.util.*;
class Solution {
    static int n;
    static int k;
    static int[] enemy;
    static PriorityQueue<Integer> usedSoldiers;
    public int solution(int n, int k, int[] enemy) {
        this.n = n;
        this.k = k;
        this.enemy = enemy;
        usedSoldiers = new PriorityQueue<>(Collections.reverseOrder());
         
        defense();
        
        return Math.min(usedSoldiers.size() + k, enemy.length);
    }
    
    void defense() {
        for (int i = 0; i < enemy.length; i++) {
            //남은 무적권이 없고, 남은 병사가 현재 적보다 적은 경우
            if(k == 0 && n < enemy[i]) {
                return;
            }
            
            //남은 무적권이 있고, 남은 병사가 현재 적보다 적은 경우
            if (n < enemy[i]) {
                usedSoldiers.add(enemy[i]);
                n += usedSoldiers.poll();
                k--;
                n -= enemy[i];
                continue;
            }
            
            //남은 병사로 현재 적을 막을 수 있는 경우
            usedSoldiers.add(enemy[i]);
            n -= enemy[i];
        }
    }
}
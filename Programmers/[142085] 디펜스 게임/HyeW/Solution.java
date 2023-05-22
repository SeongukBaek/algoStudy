import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int round = 0;
        // 적의 수가 많은 순으로 넣는다
        Queue<Integer> enemiesCnt = new PriorityQueue<>((o1,o2)-> o2-o1);

        for(int e : enemy) {
            // 라운드의 적의 수를 넣는다.
            enemiesCnt.add(e);
            // 병사의 수를 갱신한다.
            n -= e;
            // 무적권이 남아있고 병사의 수가 없다면 무적권을 사용할 라운드를 찾는다.
            while(k > 0 && n < 0){
                // 적의 수가 많은 라운드를 가져온다.
                int max = enemiesCnt.poll();
                // 병사 보충
                n += max;
                // 무적권 소모
                k--;
            }
            // 병사가 적을 막을 수 없으면 종료한다.
            if(n < 0){
                break;
            }
            round++;
        }
        return round;
    }
}
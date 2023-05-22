import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        Queue<Missile> missiles = new PriorityQueue<>((o1,o2)-> o1.s - o2.s);
        // 스택으로 사용
        Deque<Integer> shoots = new ArrayDeque<>();

        // 미사일을 시작 좌표 기준으로 정렬한다.
        for(int[] target : targets){
            missiles.add(new Missile(target[0], target[1]));
        }

        shoots.push(missiles.poll().e);
        while(!missiles.isEmpty()){
            Missile cur = missiles.poll();
            // 이전 미사일의 끝나는 좌표보다 현재 시작 좌표가 더 크다면 새로운 요격 미사일을 사용한다.
            if(cur.s >= shoots.peek()){
                shoots.push(cur.e);
                continue;
            }
            // 기존의 요격 미사일을 사용할 수 있다면 끝 좌표를 더 적은 좌표로 갱신한다.
            int preEnd = shoots.pop();
            preEnd = Math.min(preEnd, cur.e);
            shoots.push(preEnd);
        }
        return shoots.size();
    }

    class Missile{
        int s;
        int e;
        public Missile(int s, int e){
            this.s = s;
            this.e = e;
        }
    }
}
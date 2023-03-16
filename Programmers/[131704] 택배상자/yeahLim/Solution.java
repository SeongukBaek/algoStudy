import java.util.*;

class Solution {

    public static int solution(int[] order) {
        int answer = 0;
        Queue<Integer> belt = new ArrayDeque<>(); // 컨테이너 벨트
        Stack<Integer> container = new Stack<>(); // 보조 컨테이너

        for(int i=1; i<=order.length; i++) {
            belt.offer(i);
        }
        int loadedCourier = 0; // 가장 최근에 실은 택배

        for(int od : order) {
            while(true) {

                // 최근에 실은 택배의 순서보다 작을 경우
                if (od < loadedCourier) {
                    if(container.peek() == od) { 
                        loadedCourier = container.pop(); // 보조 컨테이너에서 택배를 싣는다 
                        answer++;
                        break;
                    } 
                    else return answer;
                } 

                // 최근에 실은 택배의 순서보다 큰 경우
                else if(!belt.isEmpty()) {
                    if(belt.peek() == od) {
                        loadedCourier = belt.poll(); // 벨트 컨테이너에서 택배를 싣는다
                        answer++;
                        break;
                    } 
                    else container.add(belt.poll()); // 벨트 컨테이너 -> 보조 컨테이너로 옮긴다
                }
            }
        }


        return answer;
    }
}
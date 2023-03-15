import java.util.*;

class Solution {

    public static int solution(int[] order) {
        int answer = 0;
        Queue<Integer> belt = new ArrayDeque<>();
        Stack<Integer> container = new Stack<>();

        for(int i=1; i<=order.length; i++) {
            belt.offer(i);
        }
        int n = 0;

        for(int od : order) {
            while(true) {
                if (od < n) {
                    if (container.peek() == od) {
                        n = container.pop();
                        answer++;
                        break;
                    } else return answer;
                } else if (!belt.isEmpty()) {
                    if (belt.peek() == od) {
                        n = belt.poll();
                        answer++;
                        break;
                    } else container.add(belt.poll());
                }
            }
        }


        return answer;
    }
}
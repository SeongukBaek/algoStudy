import java.util.*;

public class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        Stack<Integer> dStack = new Stack<>();
        Stack<Integer> pStack = new Stack<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < deliveries[i]; j++) {
                dStack.push(i + 1);
            }
            
            for (int j = 0; j < pickups[i]; j++) {
                pStack.push(i + 1);
            }
        }

        while (!dStack.isEmpty() && !pStack.isEmpty()) {
            int lastD = dStack.peek();
            int lastP = pStack.peek();

            for (int i = 0; i < cap; i++) {
                if (!dStack.isEmpty()) dStack.pop();
                if (!pStack.isEmpty()) pStack.pop();
            }

            answer += Math.max(lastD, lastP) * 2;
        }

        while (!dStack.isEmpty()) {
            int last = dStack.peek();

            for (int i = 0; i < cap; i++) {
                if (!dStack.isEmpty()) {
                    dStack.pop();
                }
            }

            answer += last * 2;
        }

        while (!pStack.isEmpty()) {
            int last = pStack.peek();

            for (int i = 0; i < cap; i++) {
                if (!pStack.isEmpty()) {
                    pStack.pop();
                }
            }

            answer += last * 2;
        }

        return answer;
    }
}
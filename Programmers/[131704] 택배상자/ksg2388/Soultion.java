import java.util.*;

class Solution {
    public int solution(int[] order) {
        int count = 0;
        Deque<Integer> container = new ArrayDeque<>();
        Stack<Integer> subContainer = new Stack<>();

        // 컨테이너에 순서대로 상자 추가
        for (int i = 1; i <= order.length; i++) {
            container.offer(i);
        }

        for (int i = 0; i < order.length; i++) {
            int nextNum = order[i];

            if (!loadBox(nextNum, container, subContainer)) {
                break;
            }
            count++;
        }
        return count;
    }

    public static boolean loadBox(int num, Deque<Integer> container, Stack<Integer> subContainer) {
        // 주문받은 번호가 컨테이너 가장 앞의 숫자보다 큰 경우
        while (!container.isEmpty() && container.peek() < num) {
            subContainer.add(container.poll());
        }

        // 컨테이너 가장 앞의 숫자와 주문받은 숫자를 확인
        if (!container.isEmpty() && num == container.peek()) {
            container.poll();
            return true;
        }

        // 보조 컨테이너 가장 앞의 숫자와 주문받은 숫자를 확인
        if (!subContainer.isEmpty() && num == subContainer.peek()) {
            subContainer.pop();
            return true;
        }
        return false;
    }
}
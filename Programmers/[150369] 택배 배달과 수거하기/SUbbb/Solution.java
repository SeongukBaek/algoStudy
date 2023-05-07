import java.util.Stack;

class Solution {
    private long answer;
    private int cap;

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        Stack<Integer> deliver = new Stack<>();
        Stack<Integer> pickup = new Stack<>();
        this.cap = cap;

        for (int index = 0; index < n; index++) {
            // 배달해야 하는 박스의 개수만큼 해당 지점까지의 거리를 스택에 저장
            for (int count = 0; count < deliveries[index]; count++) {
                deliver.add(index + 1);
            }
            // 수거해야 하는 박스의 개수만큼 해당 지점까지의 거리를 스택에 저장
            for (int count = 0; count < pickups[index]; count++) {
                pickup.add(index + 1);
            }
        }

        processDeliverAndPickup(deliver, pickup);
        processRemaining(deliver);
        processRemaining(pickup);

        return answer;
    }

    private void processDeliverAndPickup(Stack<Integer> deliver, Stack<Integer> pickup) {
        // 배달과 수거 중 하나라도 끝날 때까지 수행
        while (!deliver.isEmpty() && !pickup.isEmpty()) {
            // 배달과 수거 각각의 가장 멀리 떨어진 거리
            int lastDeliver = deliver.peek();
            int lastPickup = pickup.peek();

            // cap만큼 배달과 수거를 수행
            for (int count = 0; count < cap; count++) {
                if (!deliver.isEmpty()) {
                    deliver.pop();
                }
                if (!pickup.isEmpty()) {
                    pickup.pop();
                }
            }

            // 배달과 수거 중 더 멀리 떨어진 거리까지 진행!
            answer += Math.max(lastDeliver, lastPickup) * 2L;
        }
    }

    /**
     * 남아있는 박스를 처리
     * */
    private void processRemaining(Stack<Integer> works) {
        while (!works.isEmpty()) {
            int last = works.peek();

            for (int count = 0; count < cap; count++) {
                if (!works.isEmpty()) {
                    works.pop();
                }
            }

            answer += last * 2L;
        }
    }
}
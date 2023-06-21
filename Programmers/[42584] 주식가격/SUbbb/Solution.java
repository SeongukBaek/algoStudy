import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int N = prices.length;
        int[] answer = new int[N];

        for (int current = 0; current < N; current++) {
            answer[current] = 0;

            for (int next = current + 1; next < N; next++) {
                // 다음 초가 있으면 일단 최소 1초동안은 가격이 떨어지지 않은 것
                answer[current]++;

                // 가격이 떨어지면 종료
                if (prices[next] < prices[current]) {
                    break;
                }
            }
        }

        return answer;
    }
}
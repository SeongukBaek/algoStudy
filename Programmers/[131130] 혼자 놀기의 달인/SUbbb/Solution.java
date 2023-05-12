import java.util.*;

class Solution {
    // 1번째부터 DFS 수행
    // Depth 저장하고 최대값 2개의 곱 출력
    private boolean[] isVisited;
    private List<Integer> depths;
    private int[] cards;

    public int solution(int[] cards) {
        isVisited = new boolean[101];
        depths = new ArrayList<>();
        this.cards = cards;

        for (int index = 0; index < cards.length; index++) {
            if (isVisited[cards[index]]) {
                continue;
            }

            depths.add(openBoxes(cards[index]));
        }

        Collections.sort(depths, Collections.reverseOrder());

        // 구한 depth의 개수가 2보다 크면 제일 앞 2개의 곱 반환
        if (depths.size() >= 2) {
            return depths.get(0) * depths.get(1);
        }

        // 1개면, 무조건 2번 상자 그룹의 상자 수는 0개이므로 0 반환
        return 0;
    }

    private int openBoxes(int start) {
        isVisited[start] = true;
        int next = cards[start - 1];
        int count = 1;

        while (!isVisited[next]) {
            count++;
            isVisited[next] = true;
            next = cards[next - 1];
        }

        return count;
    }
}
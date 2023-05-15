import java.util.*;

class Solution {
    private final List<int[]> scoreOrder = new ArrayList<>();

    public int solution(int[][] scores) {
        int sum = sum(scores[0]);

        // 완호보다 점수 합이 높은 사원들을 리스트에 추가
        for (int index = 1; index < scores.length; index++) {
            if (sum(scores[index]) > sum) {
                scoreOrder.add(scores[index]);
            }
        }

        // 완호보다 점수가 높은 사람이 없다면 1등
        if (scoreOrder.isEmpty()) {
            return 1;
        }

        // 점수가 같으면, 근무 태도 점수를 기준으로 내림차순 정렬
        // 그렇지 않으면 점수 총합 기준으로 정렬
        scoreOrder.sort((o1, o2) -> {
            if (sum(o2) == sum(o1)) {
                return Integer.compare(o2[0], o1[0]);
            }
            return Integer.compare(sum(o2), sum(o1));
        });

        int answer = 1;
        for (int index = 0; index < scoreOrder.size() - 1; index++) {
            // 현재 다음의 사원이 인센티브를 받을 수 없는 경우는 패스
            if (!canGetInsentive(index + 1)) {
                continue;
            }

            answer++;
        }

        // 완호가 인센티브를 받을 수 없다면 -1 반환
        for (int[] score : scoreOrder) {
            if (!canGetInsentive(score, scores[0])) {
                return -1;
            }
        }

        return answer + 1;
    }

    private int sum(int[] score) {
        return score[0] + score[1];
    }

    /**
     * next의 두 점수가 current의 두 점수보다 작다면 인센티브를 받을 수 없음
     */
    private boolean canGetInsentive(int[] current, int[] next) {
        return current[0] <= next[0] || current[1] <= next[1];
    }

    /**
     * person이 인센티브를 받을 수 있는지 반환
     */
    private boolean canGetInsentive(int person) {
        for (int prev = 0; prev <= person - 1; prev++) {
            if (!canGetInsentive(scoreOrder.get(prev), scoreOrder.get(person))) {
                return false;
            }
        }
        return true;
    }
}
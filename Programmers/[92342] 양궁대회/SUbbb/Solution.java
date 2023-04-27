class Solution {
    private int maxDifference;
    private int[] apeach;
    private int[] answer = { -1 };

    public int[] solution(int n, int[] info) {
        apeach = info;

        shootArrow(new int[11], 0, n);

        return answer;
    }

    private void shootArrow(int[] ryan, int now, int remainArrows) {
        // 만약 현재 쏘려는 점수가 0점이고, 쏠 수 있는 화살이 남은 경우
        if (now == 10 && remainArrows > 0) {
            // 0점에 다 쏘기
            ryan[now] = remainArrows;
            remainArrows = 0;
        }

        if (remainArrows == 0) {
            // 점수 계산
            int[] scores = computeSum(ryan);
            int difference = scores[1] - scores[0];

            // 어피치보다 점수가 낮거나 같으면 이길 수 없음
            if (difference <= 0 || maxDifference > difference) {
                // 0점에 화살을 다 쏜 경우를 다시 돌려놓기
                ryan[10] = 0;
                return;
            }

            // 최대 점수 차와 같다면 갱신 가능한지 비교 후 갱신
            if (maxDifference == difference) {
                if (canUpdate(ryan)) {
                    answer = ryan.clone();
                }
                ryan[10] = 0;
            }

            // 점수 차가 최대인 경우 갱신
            if (maxDifference < difference) {
                maxDifference = difference;
                answer = ryan.clone();
                ryan[10] = 0;
            }

            return;
        }

        // 현재 점수는 안 쏨 !
        shootArrow(ryan, now + 1, remainArrows);

        // 현재 점수를 쏴봄 !
        // 남은 화살로 쏠 수 있는 경우
        if (remainArrows >= apeach[now] + 1) {
            ryan[now] = apeach[now] + 1;
            shootArrow(ryan, now + 1, remainArrows - (apeach[now] + 1));
            ryan[now] = 0;
        }
    }

    /**
     * 화살 정보에 따른 어피치와 라이언의 점수 반환
     */
    private int[] computeSum(int[] ryan) {
        int apeachSum = 0;
        int ryanSum = 0;

        for (int index = 0; index <= 10; index++) {
            if (apeach[index] == 0 && ryan[index] == 0) {
                continue;
            }

            if (apeach[index] >= ryan[index]) {
                apeachSum += 10 - index;
            } else {
                ryanSum += 10 - index;
            }
        }

        return new int[] { apeachSum, ryanSum };
    }

    /**
     * 전달받은 라이언의 화살 정보로 정답을 갱신할 수 있는지 반환
     */
    private boolean canUpdate(int[] ryan) {
        for (int index = 10; index >= 0; index--) {
            if (ryan[index] == answer[index]) {
                continue;
            }

            return answer[index] < ryan[index];
        }
        return false;
    }
}
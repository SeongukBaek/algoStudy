class Solution {
    public int solution(int storey) {
        int answer = 0;
        char[] level = String.valueOf(storey).toCharArray();
        boolean isRound = false;

        for (int index = level.length - 1; index >= 0; index--) {
            int current = level[index] - '0';

            // 이전 숫자를 반올림한 경우
            if (isRound) {
                current++;
                isRound = false;
            }

            // 앞에 숫자가 없는 경우, 현재 숫자만으로 판단
            if (index - 1 < 0) {
                if (current <= 5) {
                    answer += current;
                    continue;
                }
                answer += (10 - current);
                isRound = true;
                continue;
            }

            // 앞의 숫자를 보고 판단해야 하는 경우들
            int preNumber = level[index - 1] - '0';

            // 앞의 숫자를 반올림할 수 있는 경우와 없는 경우
            if (preNumber < 5) {
                // 현재 숫자를 반올림하지 않았을때 사용하는 마법의 돌 개수와 현재 숫자를 반올림했을 때 사용하는 마법의 돌 개수 비교
                if (preNumber + current > 10 - current + preNumber + 1) {
                    answer += (10 - current);
                    isRound = true;
                } else {
                    answer += current;
                }
            } else {
                // 앞의 숫자만 반올림하는 경우와 앞의 숫자랑 현재 숫자 모두 반올림하는 경우 비교
                if (10 - preNumber + current > 10 - current + (10 - (preNumber + 1))) {
                    answer += (10 - current);
                    isRound = true;
                } else {
                    answer += current;
                }
            }
        }

        // 제일 앞 자리 숫자를 반올림한 경우
        if (isRound) {
            answer++;
        }

        return answer;
    }
}
class Solution {
    static int answer = 0;

    public int solution(int storey) {
        int length = Integer.toString(storey).length();
        for(int i=0; i<length+1; i++) {
            storey = roundNumber(storey);
        }
        return answer;
    }

    /* 낮은 자리의 숫자부터 차례로 반올림 */
    int roundNumber(int storey) {
        int mod = storey % 10;
        boolean lessThen5 = false;

        // mod가 5인 경우
        if(mod == 5) {
            if(storey % 100 < 50) lessThen5 = true;
        }

        // mod가 0~4일 경우
        if(mod < 5 || lessThen5 == true) {
            answer += mod;
            storey /= 10;
        }

        // mod가 6~9일 경우
        else {
            answer += 10 - mod;
            storey /= 10;
            storey += 1;
        }

        return storey;
    }
}
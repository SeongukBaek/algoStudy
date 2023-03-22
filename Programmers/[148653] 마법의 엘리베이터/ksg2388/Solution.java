class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while (storey > 0) {
            int division = storey / 10;
            int reminder = storey % 10;
            int checkNum = division % 10;   // 뒤에서 2번째 자리 수
            
            // 5보다 작은 경우는 그 수만큼 -버튼을 누른다.
            if (reminder < 5) {
                answer += reminder;
            }
            // 5인 경우
            else if (reminder == 5) {
                // 앞자리 숫자가 5보다 작은 경우
                if (checkNum < 5) {
                    answer += reminder;
                }
                // 앞자리 숫자가 5보다 큰 경우
                else {
                    answer += reminder;
                    // 1을 올림해줌.
                    division++;
                }
            }
            // 5보다 큰 경우
            else {
                answer += 10 - reminder;
                division++;
            }
            
            storey = division;
        }
        
        return answer;
    }
}
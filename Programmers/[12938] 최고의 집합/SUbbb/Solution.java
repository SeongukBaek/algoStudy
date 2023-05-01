import java.util.Arrays;

class Solution {
    // n = 4, s = 9
    // start = 2;
    // remain = 1;
    // answer = 2(start), 2(start), 2(start), 3(start + 1);
    public int[] solution(int n, int s) {
        int start = s / n;
        int remain = s % n;
        
        if (start == 0) { 
            return new int[] {-1};
        }
        
        int[] answer = new int[n];
        Arrays.fill(answer, start);
        
        for (int count = 0; count < remain; count++) {
            answer[n - 1 - count]++;
        }
        
        return answer;
    }
}
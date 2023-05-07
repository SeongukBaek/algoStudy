import java.util.*;
class Solution {
    public int[] solution(int n, int s) {
        int[] answer = {-1};
        
        //집합이 생길 수 없는 경우 return
        if(s < n) {
            return answer;
        }
        
        answer = new int[n];
        Arrays.fill(answer, s / n);
        
        //나머지 만큼 순차적으로 배열의 맨 뒤에서부터 1씩 더해줌
        for(int i = 0; i < s % n; i++){
            answer[n - i - 1]++;
        }
        
        return answer;
    }
}
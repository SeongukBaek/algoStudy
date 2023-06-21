import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        // 마지막은 무조건 0이니까 prices 길이 -1까지만 탐색하기
        for(int i = 0; i < prices.length-1; i++){
            for(int j = i+1; j < prices.length; j++){
                answer[i]++; // 가격이 떨어지는데 무조건 1초이상 걸리기 때문에 먼저 ++해줌
                
                if(prices[i] > prices[j]){ // 만약 주가가 내리면 멈추기 
                    break;
                }
            }
        }
        
        return answer;
    }
}

// 가격이 떨어지지 않은 시간을 저장
import java.util.*;

class Solution {
    class priceData {
        int price;
        int index;
        
        priceData(int price, int index) {
            this.price = price;
            this.index = index;
        }
    }
    
    public int[] solution(int[] prices) {
        int length = prices.length;
        Stack<priceData> stack = new Stack<>();
        int[] answer = new int[length];
        
        for (int i = 0; i < length; i++) {
            // 주식의 가격이 떨어진지 확인
            while (!stack.isEmpty() && stack.peek().price > prices[i]) {
                priceData temp = stack.pop();
                answer[temp.index] = i - temp.index;
            }
            stack.push(new priceData(prices[i], i));
        }
        
        // 스텍에 남아있는 값 저장
        for (priceData data: stack) {
            answer[data.index] = length - 1 - data.index;
        }
        
        return answer;
    }
}
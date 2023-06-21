import java.util.*;

class Solution {
    
    public int[] solution(int[] prices) {
        int[] result = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            for (int j = i+1; j < prices.length; j++) {
                result[i]++;
                if (prices[i] > prices[j]) {
                    break;
                }
            }
        }        
        
        return result;
    }
}
import java.util.*;

class Solution {    
    public int[] solution(int n, int s) {
        
        /* n > s일때 */
        if (n > s) {
            return new int[] {-1};
        }
        
        /* n <= s일때 */
        int [] bestSet = new int[n];
        
        for(int i = 0; i < n; i++) {
            bestSet[i] = s/n;
        }
        
        for(int i = 0; i < s % n; i++) {
            bestSet[i]++;
        }
        
        Arrays.sort(bestSet);
        
        return bestSet;
    }
}
import java.util.*;

class Solution {
    public long solution(long w, long h) {
        long answer = 1;
    
        // 사용할 수 있는 부분 = 전체 - 겹치는 부분(가로 + 세로 - 최대공약수)
        answer = (w * h) - (w + h - gdc(w, h));
        
        return answer;
    }
    
    // 최대공약수 
    static long gdc(long w, long h){
        long gdc = 0;
        
        for(long i = 1; i <= w && i <= h; i++){
            if(w % i == 0 && h % i == 0){
                gdc = i;
            }
        }
        return gdc;
    }
}
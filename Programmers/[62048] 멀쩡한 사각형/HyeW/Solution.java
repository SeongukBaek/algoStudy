import java.util.*;

class Solution {
    public long solution(int w, int h) {
        long answer = 1;
        
        long gcb = (long)getGcb(w, h);
        answer = ((long)w*h) - (gcb * (w/gcb + h/gcb -1));
        
        return answer;
    }
    
    // 유클리드 호제 최대공약수 구하기
    int getGcb(int a, int b){
        int n;
        
        while(b!=0){
            n = a%b;
            a = b;
            b = n;
        }
        
        return a;
    }
}
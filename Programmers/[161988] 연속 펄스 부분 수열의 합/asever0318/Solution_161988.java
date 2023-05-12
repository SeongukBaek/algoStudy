import java.util.*;

class Solution {
    static long[] newSequence, sum;
    
    public long solution(int[] sequence) {
        int size = sequence.length;
        
        if(size == 1){
            return Math.abs(sequence[0]);
        }
        
        newSequence = new long[size];
        sum = new long[size+1]; // 누적합이 모두 양수이거나 모두 음수일 경우를 위해서 sum[0] = 0 준비 
        // min, max가 둘 다 양수 혹은 음수일 경우는 max에서 min을 빼버리면 오히려 max값에서 min값만큼 더 값이 작아진다
        // 예를들어 max = 7, min = -2라면 max - min = 9가 되어 더 큰 max 값을 구할 수 있는데,
        // 만약 둘 다 양수인 max = 7, min = 2일 경우 max - min = 5로 원래 max값보다 더 작아짐!
        // 따라서 sum[0] = 0으로 두어서 min = 0이 되면 max - min = 7로 max값을 크게 유지할 수 있음!
        
        getPulseSequence(sequence); // 펄스 수열 구하기
        getSumSequence(); // 누적합 구하기 
        
        return getMaxSum(size);
    }
    
    static long getMaxSum(int size){
        
        Arrays.sort(sum);
        
        long min = sum[0];
        long max = sum[size];
        
        return max - min; // 누적최대값에서 누적최소값 빼기
    }
    
    // 펄스 수열 구하기 
    static void getPulseSequence(int[] sequence){
        for(int i = 0; i < sequence.length; i++){
            if(i%2 == 0){ // 짝수일 때 -1 곱하기
                newSequence[i] = sequence[i] * (-1);
                continue;
            }
            
            newSequence[i] = sequence[i];
        }
    }
    
    // 누적합 구하기 
    static void getSumSequence(){
        for(int i = 1; i < sum.length; i++){
            sum[i] = sum[i-1] + newSequence[i-1];
        }
    }
}
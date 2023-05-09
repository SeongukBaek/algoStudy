import java.util.*;
class Solution {
    static int[] seq1, seq2;
    static long[] dp1, dp2;
    static int[] sequence;
    public long solution(int[] sequence) {
        long answer = 0;
        seq1 = new int[sequence.length];
        seq2 = new int[sequence.length];
        dp1 = new long[sequence.length];
        dp2 = new long[sequence.length];
        this.sequence = sequence;
        
        makeArr();
        
        return makeDp();
    }

    /**
     * 두 가지의 펄스 수열을 sequence에 곱해 두개의 새로운 배열을 만든다.
     */
    void makeArr() {
        for (int i = 0; i < sequence.length; i++) {
            if (i % 2 == 0) {
                seq1[i] = sequence[i];
                seq2[i] = sequence[i] * -1;
            }
            
            if (i % 2 == 1) {
                seq1[i] = sequence[i] * -1;
                seq2[i] = sequence[i];
            }
        }
    }
    
    long makeDp() {
        dp1[0] = seq1[0];
        dp2[0] = seq2[0];
        for (int i = 1; i < sequence.length; i++) {
          //DP[K] = max(DP[K-1] + K번째 원소, K번째 원소)
            dp1[i] = Math.max(dp1[i - 1] + seq1[i], seq1[i]);
            dp2[i] = Math.max(dp2[i - 1] + seq2[i], seq2[i]);
        }
        
        return Math.max(Arrays.stream(dp1).max().getAsLong(), Arrays.stream(dp2).max().getAsLong());
    }
}
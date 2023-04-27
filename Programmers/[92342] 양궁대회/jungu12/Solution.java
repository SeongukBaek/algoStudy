import java.util.Arrays;
class Solution {
    static int maxSub;
    static int[] apeachInfo;
    static int N;
    static int apeachSum, ryanSum;
    static int[] answer;
    public int[] solution(int n, int[] info) {
        answer = new int[11];
        N = n;
        apeachInfo = info;
        int[] ryanInfo = new int[11];
        getOrGiveUpScore(0, ryanInfo, N);
        if(maxSub == 0) return new int[] {-1};
        return answer;
    }
    
    public void getOrGiveUpScore(int index, int[] ryanInfo, int left) {
        //10점 ~ 1점 과녁에 화살을 다 쐈는데 화살이 남은 경우, 남은 화살을 다 0점에 쏨
        if(index == 10) {
            ryanInfo[index] = left;
            left = 0;
        }
        
        //남은 화살이 없는 경우
        if(left == 0) {
            calSum(ryanInfo);
            
            if(maxSub == ryanSum - apeachSum) {
                for(int i = 10 ;i >= 0; i--) {
                    if(answer[i] > ryanInfo[i]) {
                        ryanInfo[10] = 0;
                        return;
                    }
                    if(answer[i] < ryanInfo[i]) {
                        answer = ryanInfo.clone();
                        ryanInfo[10] = 0;
                        return;
                    }
                }
            }
            
            if(maxSub < ryanSum - apeachSum) {
                maxSub = ryanSum - apeachSum;
                answer = ryanInfo.clone();
            }
            ryanInfo[10] = 0;
            return;
        }
        
        //현재 점수에 화살을 쏨!
        if(apeachInfo[index] + 1 <= left) {
            ryanInfo[index] = apeachInfo[index] + 1;
            getOrGiveUpScore(index + 1, ryanInfo, left - ryanInfo[index]);
            ryanInfo[index] = 0;
        }
        
        //현재 점수에 화살 안쏘고 점수 포기!
        getOrGiveUpScore(index + 1, ryanInfo, left);
    }
    
    public void calSum(int[] ryanInfo) {
        ryanSum = 0;
        apeachSum = 0;
        for(int i = 0; i <= 10; i++) {
            if(apeachInfo[i] < ryanInfo[i]) {
                ryanSum += 10 - i;
            }
            
            if(apeachInfo[i] > ryanInfo[i]) {
                apeachSum += 10 - i;
            }
        }
    }
}
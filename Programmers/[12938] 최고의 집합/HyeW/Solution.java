class Solution {

    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        
        int avg = s/n;
        
        if(avg == 0){
            return new int[]{-1};
        }
  
        int remainCnt = s%n;
        int index = 0;
        for(int i = 0; i < n-remainCnt; i++){
            answer[index++] = avg;
        }
        for(int i = 0; i < remainCnt; i++){
            answer[index++] = avg+1;
        }
        
        return answer;
    }
}
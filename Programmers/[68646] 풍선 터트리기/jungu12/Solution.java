class Solution {
    public int solution(int[] a) {
        if (a.length == 1) {
            return 1;
        }
        int answer = 2;
        int[] leftMin = new int[a.length];
        int[] rightMin = new int[a.length];
        
        leftMin[0] = a[0];
        rightMin[a.length - 1] = a[a.length - 1];
        
        for (int j = 1; j < a.length - 1; j++) {
            leftMin[j] = Math.min(leftMin[j - 1], a[j - 1]);
        }

        for (int j = a.length - 2; j > 0; j--) {
            rightMin[j] = Math.min(rightMin[j + 1], a[j + 1]);
        }
        
        for (int i = 1; i < a.length  - 1; i++) {
            if(leftMin[i] < a[i] && rightMin[i] < a[i]) {
                continue;     
            }
            answer++;
        }
        return answer;
    }
}
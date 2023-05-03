class Solution {
    int nameLength ;
    int minDistance = 100;
    public int solution(String name) {
        int answer = 0;   // 이동 횟수 저장
        int doneChar = 0; // 완성한 문자 개수를 저장
        nameLength = name.length();
        boolean[] upDown = new boolean[nameLength];
        
        for(int i = 0; i < nameLength; i++){
            char cur = name.charAt(i);
            // A->B에서 확인하는 것과 A->Z로 이동하는 것 중 이동수가 더 적은 것을 선택한다.
            int move = Math.min(cur - 'A', 'Z'- cur+1);
            if(move == 0){
                doneChar++;
                upDown[i] = true;
            }
            answer += move;
        }
        
        getShortestDistance(upDown, 0, doneChar, 0);
        answer+= minDistance;
        return answer;
    }
    // DFS를 사용해 좌우이동의 최소값 구하기
    private void getShortestDistance(boolean[] visited, int cur, int done, int distance){
        if(done >= nameLength){
            minDistance = Math.min(distance, minDistance);
            return;
        }
        
        //오른쪽으로 가기
        int right = cur;
        int rightMove = 0;
        while(visited[right]){
            right = (right+1)%nameLength;
            rightMove++;
        }
        visited[right] = true;
        getShortestDistance(visited, right, done+1, distance+rightMove);
        visited[right] = false;
        
        //왼쪽으로 가기
        int left = cur;
        int leftMove = 0;
        while(visited[left]){
            left = left-1 == -1?nameLength-1:left-1;
            leftMove++;
        }
        visited[left] = true;
        getShortestDistance(visited, left, done+1, distance+leftMove);
        visited[left] = false;
    }

}
import java.util.*;

class Solution {
    static int n;
    static int moveCnt;
    static boolean[] isA;
    
    public int solution(String name) {
        
        n = name.length();
        moveCnt = n;
        int alphCnt = changeAlphabets(name);
        
        // A는 방문 처리
        int[] visited = new int[n];
        for(int i = 0; i < n; i++) {
            if (name.charAt(i) == 'A') {
                visited[i] = 1;
            }
        }
        visited[0] = 1;
        move(0, 0, visited);
        
        return alphCnt + moveCnt;
    }
    
    /* 알파벳 변환 */
    public int changeAlphabets(String name) {
        int count = 0;
        for (char na : name.toCharArray()) {
            count += Math.min(na - 'A', 'Z' - na + 1);
        }
        return count;
    }
    
    /* Backtracking : A가 아닌 모든 알파벳 방문 횟수 구하기 */
    public void move(int current, int count, int[] visited) {

        // 최대로 방문할 수 있는 길이보다 클 경우 
        if (moveCnt <= count) {
            return;
        }

        // 종료 조건
        if (checkAllVisited(visited)) { 
            moveCnt = Math.min(moveCnt, count);
            return;
        }
        
        // 왼쪽으로 이동 
        int left = current - 1 >=0 ? current - 1 : n-1;
        visited[left] += 1;
        move(left, count + 1, visited);
        visited[left] -= 1;


        // 오른쪽으로 이동
        int right = current + 1 < n ? current + 1 : 0;
        visited[right] += 1;
        move(right, count + 1, visited);
        visited[right] -= 1;

    }
    
    /* 모든 곳 방문했는지 */
    public boolean checkAllVisited(int[] visited) {
        for (int v : visited) {
            if (v == 0) {
                return false;
            }
        }
        return true;
    }
}
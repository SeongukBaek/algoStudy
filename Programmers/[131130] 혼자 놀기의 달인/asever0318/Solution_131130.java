import java.util.*;

class Solution {
    static int max, first, second, answer;
    static boolean[] visited;
    
    public int solution(int[] cards) {
        
        // 원소 전체를 각각 시작 박스로 탐색
        for(int i = 0; i < cards.length; i++){ // 첫 번째 상자 
            visited = new boolean[cards.length];
            
            answer = 0;
            openBox(i, 0, cards);
            first = answer;

            for(int j = 0; j < cards.length; j++){ // 두 번째 상자
                if(visited[j]){ // 첫 번째에서 방문한 상자면 패스
                    continue;
                }
                
                answer = 0;
                openBox(j, 0, cards);
                second = answer;
                
                max = Math.max(first*second, max);
            }
        }
        return max;
    }
    
    static void openBox(int box, int cnt, int[] cards){
        
        if(visited[box]){ // 이미 열어본 박스이면 멈춤
            answer = Math.max(answer, cnt);
            return;
        }
        
        visited[box] = true;
        openBox(cards[box]-1, cnt+1, cards);
    }
}
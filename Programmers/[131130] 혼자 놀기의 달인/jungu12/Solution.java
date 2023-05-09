import java.util.*;
class Solution {
    static int[] cards;
    static boolean[] visited;
    static List<List<Integer>> boxGroups;
    public int solution(int[] cards) {
        int answer = 0;
        this.cards = cards;
        visited = new boolean[cards.length];
        boxGroups = new ArrayList<>();
        
        makeBoxGroups();
        
        return findMaxScore();
    }
    
    void makeBoxGroups() {
        //방문하지 않은 박스를 찾는다.
        for (int i = 0; i < cards.length; i++) {
            //이미 방문한 박스라면 continue
            if(visited[i]) {
                continue;
            }

            //해당 박스를 시작으로 DFS로 새로운 박스 그룹을 만든다.
            boxGroups.add(new ArrayList<>());
            boxGroups.get(boxGroups.size() - 1).add(i);
            visited[i] = true;
            openBox(cards[i] - 1);
        }
    }

    void openBox(int idx) {
        //이미 방문한 박스라면 continue
        if(visited[idx]) {
            return;
        }

        visited[idx] = true;
        //박스 그룹에 추가
        boxGroups.get(boxGroups.size() - 1).add(idx);
        //다음 박스 방문
        openBox(cards[idx] - 1);
    }
    
    int findMaxScore() {
        int max = 0;
        //박스 그룹 중 2개를 골라 가장 곱이 큰 값을 return
        for (int i = 0; i < boxGroups.size() - 1; i++) {
            for (int j = i + 1; j < boxGroups.size(); j++) {
                max = Math.max(max, (boxGroups.get(i).size()) * (boxGroups.get(j).size()));
            }
        }
        return max;
    }
}
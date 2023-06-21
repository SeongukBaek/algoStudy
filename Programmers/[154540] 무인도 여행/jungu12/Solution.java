import java.util.*;

class Solution {
    boolean[][] visited;
    int[][] map;
    String[] maps;
    List<Integer> answer;
    public int[] solution(String[] maps) {
        this.maps = maps;
        map = new int[maps.length][maps[0].length()];
        visited = new boolean[maps.length][maps[0].length()];
        answer = new ArrayList<>();
        int[] ans;
        init();
        calcCanStayDate();
        if(answer.size() == 0) {
            return new int[] {-1};
        }
        Collections.sort(answer);
        ans = new int[answer.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = answer.get(i);
        }
        return ans;
    }
    
    void init() {
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                char cur = maps[i].charAt(j);
                if(cur == 'X') {
                    map[i][j] = 0;
                    continue;
                }
                map[i][j] = maps[i].charAt(j) - '0';
            }
        }
    }
    
    void calcCanStayDate() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j] != 0 && !visited[i][j]) {
                    bfs(i, j);
                }
            }
        }
    }
    
    void bfs(int i, int j) {
        Queue<int[]> que = new LinkedList<>();
        int countFood = map[i][j];
        visited[i][j] = true;
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, -1, 0, 1 };
        
        que.add(new int[] {i, j});
        
        while(!que.isEmpty()) {
            int[] cur = que.poll();
            
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];
                
                if(!isIn(nx, ny)) {
                    continue;
                }
                
                if(map[nx][ny] != 0 && !visited[nx][ny]) {
                    countFood += map[nx][ny];
                    visited[nx][ny] = true;
                    que.add(new int[] {nx, ny});
                }
            }
        }
        answer.add(countFood);
    }
    
    boolean isIn(int nx, int ny) {
        return nx >=0 && nx < map.length && ny >= 0 && ny < map[0].length;
    }
}
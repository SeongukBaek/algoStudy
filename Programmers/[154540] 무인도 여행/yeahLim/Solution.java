import java.util.*;

class Solution {
    int n, m;
    char[][] map;
    boolean[][] visited;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public int[] solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        map = new char[n][m];
        visited = new boolean[n][m];
        
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                map[i][j] = maps[i].charAt(j);
            }
        }
        
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && map[i][j] != 'X') {
                    list.add(searchIsland(i, j));
                }
            }
        }
        
        if (list.isEmpty()) {
            return new int[] {-1};
        }
        
        
        Collections.sort(list);
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    public int searchIsland(int x, int y) {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {x, y});
        visited[x][y] = true;
        int count = 0;
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            
            count += map[curr[0]][curr[1]] - '0';
            
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == 'X') continue;
                visited[nx][ny] = true;
                q.offer(new int[] {nx, ny});
            }        
        }
    
        return count;
    }
}
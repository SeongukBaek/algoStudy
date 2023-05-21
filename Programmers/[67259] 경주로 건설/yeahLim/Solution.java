import java.util.*;

class Solution {
    int n;
    int[][] board;
    class Current {
        int x, y, dir;
        
        public Current(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    
    public int solution(int[][] b) {
        n = b.length;
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            board = b.clone();
        }
        return estimateTrack();
    }
    
    /* BFS : 경주로 견적내기 */
    public int estimateTrack() {
        Deque<Current> q = new ArrayDeque<>();
        q.offer(new Current(0, 0, 1));
        q.offer(new Current(0, 0, 2));
        int[][][] cost = new int[n][n][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(cost[i][j], Integer.MAX_VALUE);
            }
        }
        cost[0][0][1] = 0;
        cost[0][0][2] = 0;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        int minCost = Integer.MAX_VALUE;
        
        while (!q.isEmpty()) {
            Current cur = q.poll();
            
            if (cur.x == n - 1 && cur.y == n - 1) {
                minCost = Math.min(minCost, cost[n - 1][n - 1][cur.dir]);
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (board[nx][ny] == 1) continue;
                
                // 직선으로 가는 경우
                if (i % 2 == cur.dir % 2) {
                    if (cost[nx][ny][i] > cost[cur.x][cur.y][cur.dir] + 100) {
                        cost[nx][ny][i] = cost[cur.x][cur.y][cur.dir] + 100;
                        q.offer(new Current(nx, ny, i));
                        
                    }
                }
                // 코너로 도는 경우
                else {
                    if (cost[nx][ny][i] > cost[cur.x][cur.y][cur.dir] + 600) {
                        cost[nx][ny][i] = cost[cur.x][cur.y][cur.dir] + 600;
                        q.offer(new Current(nx, ny, i));
                        
                    }
                }
                
            }
        }    
        
        return minCost;
    }
}
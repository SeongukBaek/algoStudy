import java.util.*;

class Solution {
    int n, m;
    char[][] board;
    int goalX, goalY;
    class Point {
        int x, y, count;
        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    
    public int solution(String[] b) {
        
        /* 변수 초기화 */
        n = b.length;
        m = b[0].length();
        board = new char[n][m];
        int robotX = 0, robotY = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = b[i].charAt(j);
                if (board[i][j] == 'R') {
                    robotX = i;
                    robotY = j;
                    board[i][j] = '.';
                }
                if (board[i][j] == 'G') {
                    goalX = i;
                    goalY = j;
                    board[i][j] = '.';
                }
            }         
        }
        
        return moveRobot(robotX, robotY);
    }
    
    /* BFS : 리코쳇 로봇의 최소 움직임 구하기 */
    public int moveRobot(int robotX, int robotY) {
        Deque<Point> q = new ArrayDeque<>();
        q.offer(new Point(robotX, robotY, 0));
        boolean[][] visited = new boolean[n][m];
        visited[robotX][robotY] = true;
        
        while (!q.isEmpty()) {
            Point point = q.poll();
            
            if (point.x == goalX && point.y == goalY) {
                return point.count;
            }
            
            // 상
            int i = point.x;
            while (i >= 0 && board[i][point.y] == '.') {
                i--;
            }
            if (!visited[++i][point.y]) {
                visited[i][point.y] = true;
                q.offer(new Point(i, point.y, point.count+1));
            }
            
            // 하
            i = point.x;
            while (i < n && board[i][point.y] == '.') {
                i++;
            }
            if (!visited[--i][point.y]) {
                visited[i][point.y] = true;
                q.offer(new Point(i, point.y, point.count+1));
            }
            
            // 좌
            i = point.y;
            while (i >= 0 && board[point.x][i] == '.') {
                i--;
            }
            if (!visited[point.x][++i]) {
                visited[point.x][i] = true;
                q.offer(new Point(point.x, i, point.count+1));
            }
            
            // 우
            i = point.y;
            while (i < m && board[point.x][i] == '.') {
                i++;
            }
            if (!visited[point.x][--i]) {
                visited[point.x][i] = true;
                q.offer(new Point(point.x, i, point.count+1));
            }
            
        }
        
        return -1;
    }
    
}
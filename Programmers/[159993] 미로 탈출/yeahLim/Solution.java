import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    static char[][] map;
    static int n, m;
    static class Current {
        int x, y, time;
        public Current(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public int solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        map = new char[n][m];
        int[] start = new int[2], lever = new int[2], end = new int[2];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                map[i][j] = maps[i].charAt(j);

                if(map[i][j] == 'S') {
                    start[0] = i;
                    start[1] = j;
                }

                if(map[i][j] == 'L') {
                    lever[0] = i;
                    lever[1] = j;
                }

                if(map[i][j] == 'E') {
                    end[0] = i;
                    end[1] = j;
                }
            }
        }


        int timeSearchLever = escapeMaze(start, lever); // 레버 찾는 시간 구하기
        if(timeSearchLever == -1) return timeSearchLever;
        int timeSearchExit = escapeMaze(lever, end); // 출구 찾는 시간 구하기
        if(timeSearchExit == -1) return timeSearchExit;
        else return timeSearchLever + timeSearchExit;
    }

    /* BFS로 미로 탈출하기 : 레버 찾고, 출구 찾기 */
    int escapeMaze(int[] start, int[] end) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        boolean[][] visited = new boolean[n][m];
        Queue<Current> queue = new ArrayDeque();
        queue.add(new Current(start[0], start[1], 0));

        while(!queue.isEmpty()) {
            Current cur = queue.poll();

            if(cur.x == end[0] && cur.y == end[1]) return cur.time;

            for(int i=0; i<4; i++) {
                int x = cur.x + dx[i];
                int y = cur.y + dy[i];

                if(x<0 || x>=n || y<0 || y>=m) continue;
                if(map[x][y] == 'X') continue;
                if(visited[x][y]) continue;

                visited[x][y] = true;
                queue.add(new Current(x, y, cur.time+1));
            }
        }

        return -1; // 못찾으면 -1 반환
    }

}
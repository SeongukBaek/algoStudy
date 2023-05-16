import java.util.*;

class Solution {
    String[] board;
    Position goal;
    int R, C;
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, -1, 0, 1};

    public int solution(String[] board) {
        this.board = board;
        R = board.length;
        C = board[0].length();

        // 로봇 시작 위치 저장 변수
        Position start = null;
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                char cur = board[i].charAt(j);
                // 로봇의 시작위치 찾기
                if(cur == 'R'){
                    start = new Position(i, j);
                }
                // 로봇 목표위치 찾기
                if(cur == 'G'){
                    goal = new Position(i,j);
                }
            }
        }
        return getMinMove(start);
    }

    /* 목표지점까지의 최단거리를 반환한다. */
    public int getMinMove(Position start){
        int move = 0;
        boolean[][] visited = new boolean[R][C];
        Deque<Position> path = new ArrayDeque<>();
        path.add(start);
        visited[start.x][start.y] = true;

        while(!path.isEmpty()){
            int parentSize = path.size();
            move++;
            while(parentSize-- > 0){
                Position cur = path.poll();

                for(int i = 0; i < 4; i++){
                    Position next = slideRobot(cur, i);
                    // 목적 좌표에 도달했을 경우
                    if(next.x == goal.x && next.y == goal.y){
                        return move;
                    }
                    // 이미 방문한 경우
                    if(visited[next.x][next.y]){
                        continue;
                    }
                    path.add(next);
                    visited[next.x][next.y] = true;
                }
            }
        }
        return -1;
    }

    /* 로봇이 미끄러져서 도착한 위치좌표를 반환한다. */
    public Position slideRobot(Position cur, int dir){
        int dx = cur.x;
        int dy = cur.y;

        while(!isMapOut(dx, dy) && board[dx].charAt(dy) != 'D'){
            dx += dr[dir];
            dy += dc[dir];
        }
        return new Position(dx - dr[dir], dy - dc[dir]);
    }

    public boolean isMapOut(int x, int y){
        return x < 0 || y < 0 || x >= R || y >= C;
    }

    class Position{
        int x;
        int y;
        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
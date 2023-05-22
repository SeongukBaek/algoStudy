import java.util.*;

class Solution {
    int[] dr = {-1, 1, 0, 0}; // 상 하 좌 우
    int[] dc = {0, 0, -1, 1};
    int R, C;
    public int solution(int[][] board) {
        R = board.length;
        C = board[0].length;
        // 방향에 따른 최소 비용값을 저장한다.
        int[][][] minCost = new int[R][C][4];
        Deque<Road> path = new ArrayDeque<>();
        for(int i = 0; i < 4; i++){
            // 0을 계산하지 않은 값으로 취급하기 위해 (0,0)은 -1로 저장하여 방문처리를 한다.
            minCost[0][0][i] = -1;
        }
        // 시작 지점에선 오른쪽, 아래쪽으로 갈 수 있다.
        path.add(new Road(0,0,1));
        path.add(new Road(0,0,3));

        while(!path.isEmpty()){
            Road cur = path.poll();
            for(int i = 0; i < 4; i++){
                int dx = dr[i]+cur.x;
                int dy = dc[i]+cur.y;

                if(isMapOut(dx, dy) || board[dx][dy] == 1){
                    continue;
                }
                int fee = minCost[cur.x][cur.y][cur.dir] + 100;
                // 다음 좌표 값이 일직선으로 도로를 놓을 수 없다면 500원을 추가한다.
                if(cur.dir != i && oppositeDir(cur.dir) != i){
                    fee += 500;
                }
                // 방문한 좌표이고 현재 비용이 저장된 최소 비용보다 크다면 다음으로 넘어간다.
                if(minCost[dx][dy][i] != 0 && minCost[dx][dy][i] <= fee){
                    continue;
                }
                minCost[dx][dy][i] = fee;
                path.add(new Road(dx, dy, i));
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < 4; i++){
            // 방문하지 않은 값은 넘어간다.
            if(minCost[R-1][C-1][i] == 0){
                continue;
            }
            min = Math.min(min, minCost[R-1][C-1][i]);
        }
        // 시작 비용을 -1로 하여 마지막 값을 반환할땐 +1을 해준다.
        return min+1;
    }
    /* 주어진 범위를 넘었는지 확인한다. */
    boolean isMapOut(int x, int y){
        return x < 0 || y < 0 || x >= R || y >= C;
    }
    /* 파라미터에 주어진 방향의 반대 방향을 반환한다. */
    int oppositeDir(int dir){
        if(dir%2 == 0){
            return dir+1;
        }
        return dir-1;
    }

    class Road {
        int x;
        int y;
        int dir;
        Road(int x, int y, int dir){
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}
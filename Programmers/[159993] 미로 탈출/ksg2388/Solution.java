import java.util.*;

class Solution {
    class Node {
        int x;
        int y;
        boolean onLever;
        int d;  // 이동한 거리
        
        Node(int x, int y, int d, boolean onLever) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.onLever = onLever;
        }
    }
    int visited[][];    // 0인경우 미방문, 1인경우 아직한 레버를 작동시키지 않고 방문한 경우
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    int h, w;
    public int solution(String[] maps) {
        Node start = null;
        h = maps.length;
        w = maps[0].length();
        
        // 시작지점 찾기
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (maps[i].charAt(j) == 'S') {
                    start = new Node(i, j, 0, false);
                    break;
                }
            }
        }
        
        // visited 배열 초기화
        visited = new int[h][w];
        
        
        return findOptimalPath(maps, start);
    }
    
    int findOptimalPath(String[] maps, Node start) {
        Deque<Node> nextPaths = new LinkedList<>();
        nextPaths.offer(start);
        visited[start.x][start.y] = 1;
        
        while(!nextPaths.isEmpty()) {
            Node cur = nextPaths.poll();
            
            // 다음 이동할 위치 탐색
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                // 맵 밖으로 나가거나 벽이거나 방문한 경우 무시
                if (isMapOut(nx, ny) || maps[nx].charAt(ny) == 'X' || isVisited(nx, ny, cur.onLever)) {
                    continue;
                }
                
                // 통로에 도착했고 레버를 작동 시킨 경우
                if (maps[nx].charAt(ny) == 'E' && cur.onLever) {
                    return cur.d + 1;
                }
                
                // 이동 위치가 레버인 경우
                if (maps[nx].charAt(ny) == 'L') {
                    nextPaths.offer(new Node(nx, ny, cur.d + 1, true));
                    visited[nx][ny] = 2;
                    continue;
                }
                nextPaths.offer(new Node(nx, ny, cur.d + 1, cur.onLever));
                if (cur.onLever) {
                    visited[nx][ny] = 2;
                }
                else {
                    visited[nx][ny] = 1;
                }
            }
        }
        return -1;
    }
    
    boolean isMapOut(int x, int y) {
        return x < 0 || y < 0 || x >= h || y >= w;
    }
    
    boolean isVisited(int x, int y, boolean onLever) {
        // 레버를 작동시킨 경우
        if (onLever) {
            return visited[x][y] > 1;
        }
        return visited[x][y] > 0;
    }
}
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] map;
    static int sharkX = 0, sharkY = 0;
    static class Current {
        int x, y, dist;
        public Current(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.dist = distance;
        }
    }

    public static void main(String[] args) throws IOException {

        /* 입력 및 변수 초기화 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        StringTokenizer st;
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0; // 0으로 처리 안해줄 경우, 상어보다 큰 물고기로 여겨짐
                }
            }
        }

        System.out.println(searchFish());
    }

    /* BFS : 먹을 수 있는 물고기 찾기 */
    private static int searchFish() {
        PriorityQueue<Current> pq;
        boolean[][] visited;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        boolean isEaten = true; // 물고기를 먹었는지
        int sharkSize = 2; // 상어의 크기
        int eatenFish = 0; // 잡아먹힌 물고기의 수
        int time = 0; // 총 상어가 이동한 시간

        // 상어가 물고기를 먹을 수 있을 때까지
        while(isEaten) {
            isEaten = false;
            visited = new boolean[n][n];
            // 우선순위 1. 거리순 2. 가장 위쪽 3. 가장 왼쪽
            pq = new PriorityQueue<>((o1, o2) ->
                    (o1.dist != o2.dist) ? Integer.compare(o1.dist, o2.dist) :
                    (o1.x != o2.x) ? Integer.compare(o1.x, o2.x) : Integer.compare(o1.y, o2.y));
            pq.offer(new Current(sharkX, sharkY, 0)); // 상어의 x좌표, y좌표, 이동한 거리
            visited[sharkX][sharkY] = true;

            while(!pq.isEmpty()) {
                Current cur = pq.poll();

                // 상어보다 작은 크기의 물고기일 경우
                if(map[cur.x][cur.y] != 0 && map[cur.x][cur.y] < sharkSize) {
                    map[cur.x][cur.y] = 0;
                    sharkX = cur.x;
                    sharkY = cur.y;
                    time += cur.dist;
                    // 상어 크기 만큼의 물고기를 먹었을 때
                    if(++eatenFish == sharkSize) {
                        eatenFish = 0;
                        sharkSize++;
                    }
                    isEaten = true;
                    break;
                }

                for(int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                    if (visited[nx][ny]) continue;
                    if (map[nx][ny] > sharkSize) continue; // 상어보다 클 경우

                    visited[nx][ny] = true;
                    pq.offer(new Current(nx, ny, cur.dist + 1));
                }
            }
        }

        return time;
    }
}
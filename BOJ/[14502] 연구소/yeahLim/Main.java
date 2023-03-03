import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] map;
    static List<int[]> virus = new LinkedList<>(); // 최초 바이러스의 위치
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int maxCnt = 0;

    public static void main(String[] args) throws IOException {
        /* 입력 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    virus.add(new int[] {i, j});
                }
            }
        }

        build3Walls(0, 0, 0);

        /* 출력 */
        System.out.println(maxCnt);
    }

    /* 조합으로 벽 3개 세우기 */
    static void build3Walls(int startX, int startY, int depth) {
        if(depth == 3) {
            spreadVirus();
            return;
        }

        for(int i=startX; i<n; i++) {
            for(int j=startY; j<m; j++) {
                if(map[i][j] == 0) {
                    map[i][j] = 1;
                    if(j != m-1) {
                        build3Walls(i, j+1, depth+1);
                    } else {
                        build3Walls(i+1, 0, depth+1); // j가 마지막 열일때 0으로 초기화
                        startY = 0;  // j가 마지막 열일때 0으로 초기화
                    }
                    map[i][j] = 0;
                } else {
                    if(j == m-1) { // j가 마지막 열일때 0으로 초기화
                        startY = 0;
                    }
                }
            }
        }
    }

    /* bfs로 바이러스 전염시키기 */
    static void spreadVirus() {
        Queue<int[]> q = new LinkedList<>();
        q.addAll(virus);

        while(!q.isEmpty()) {

            int[] curr = q.poll();

            for(int i=0; i<4; i++) {
                int x = dx[i] + curr[0];
                int y = dy[i] + curr[1];

                // 범위 넘어갈 때 스킵
                if(x<0 || x>=n || y<0 || y>=m) continue;

                // 벽일 때 스킵
                if(map[x][y] == 1) continue;

                // 현 위치에 바이러스가 존재하면, 사방에 전염
                if(map[curr[0]][curr[1]] == 2) {
                    if(map[x][y] != 2) {
                        map[x][y] = 2;
                        q.add(new int[] {x, y});
                    }
                }
            }
        }

        countSafetyArea();
    }

    /* 안전한 지역 개수 세기 */
    static void countSafetyArea() {
        int count = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(map[i][j] == 0) {
                    count++;
                }
            }
        }

        maxCnt = maxCnt < count ? count : maxCnt;

        restoreArray();
    }

    /* 바이러스에 전염되기 전의 배열로 복원 */
    static void restoreArray() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(map[i][j] == 2) {
                    map[i][j] = 0;
                }
            }
        }
        for(int[] vr : virus) {
            map[vr[0]][vr[1]] = 2;
        }
    }
}
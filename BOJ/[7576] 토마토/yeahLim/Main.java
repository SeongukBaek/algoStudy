import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] tomato;
    static Queue<int[]> queue = new LinkedList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        /* 입력 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        tomato = new int[n][m];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++) {
                tomato[i][j] = Integer.parseInt(st.nextToken());
                if(tomato[i][j] == 1) {
                    queue.add(new int[] {i, j});
                }
            }
        }

        /* 토마토 익는데 걸리는 날 수 구하기 */
        ripenTomatoes();

        /* 출력 */
        System.out.println(print());
    }

    static void ripenTomatoes() {
        while(!queue.isEmpty()) {
            int[] d = queue.poll();
            // 익은 토마토의 좌표 (기준점)
            int i = d[0];
            int j = d[1];

            for(int k=0; k<4; k++) {
                // 익은 토마토로부터 상,하,좌,우의 토마토의 좌표
                int x = i + dx[k];
                int y = j + dy[k];

                if(0<=x && x<n && 0<=y && y<m && tomato[x][y] == 0) {
                    queue.add(new int[] {x, y}); // 익은 토마토에 추가
                    tomato[x][y] = tomato[i][j] + 1; // 토마토가 익는 데까지 걸린 날 수
                }
            }
        }
    }

    static int print() {
        int max = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {

                // 안익은 토마토가 있으면 -1 반환
                if(tomato[i][j] == 0) {
                    return -1;
                }

                // 익는데 가장 오래걸린 날 수 찾기
                if(max < tomato[i][j]) {
                    max = tomato[i][j];
                }
            }
        }
        return max - 1;
    }

}
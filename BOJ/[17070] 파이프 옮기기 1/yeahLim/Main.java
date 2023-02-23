import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] house;
    static int count; // 파이프 이동 방법의 개수
    static class Current{
        int x;
        int y;
        int direction;
        public Current(int x, int y, int direction){
            this.x = x; // x좌표
            this.y = y; // y좌표
            this.direction = direction; // 방향 (가로:0, 세로: 1, 대각선: 2)
        }
    }

    public static void main(String[] args) throws IOException {
        /* 입력 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        house = new int[n][n];
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /* BFS로 파이프 (1,2) -> (n,n)로 이동시키기 */
        movePipe();

        /* 출력 */
        System.out.println(count);

    }

    static void movePipe(){
        Queue<Current> q = new LinkedList<>();
        // 방향 : 가로, 세로, 대각선
        int[] dx = {0, 1, 1};
        int[] dy = {1, 0, 1};
        q.add(new Current(0, 1, 0));
        
        if(house[n-1][n-1] == 1) {
            return ;
        }
        
        while(!q.isEmpty()) {
            Current curr  = q.poll();
            if(curr.x == n-1 && curr.y == n-1) {
                count += 1;
                continue;
            }

            // 방향이 가로라면
            if(curr.direction == 0) {

                // 가로, 대각선만 진행
                for(int k=0; k<3; k++) {
                    if(k == 1) continue; // 세로 제외

                    int x = dx[k] + curr.x;
                    int y = dy[k] + curr.y;

                    if(!checkWall(x, y, k)) {
                        q.add(new Current(x, y, k));
                    }
                }

            // 방향이 세로라면
            } else if(curr.direction == 1) {

                // 세로, 대각선만 진행
                for(int k=1; k<3; k++) {

                    int x = dx[k] + curr.x;
                    int y = dy[k] + curr.y;

                    if(!checkWall(x, y, k)) {
                        q.add(new Current(x, y, k));
                    }
                }

            // 방향이 대각선이라면
            } else {

                // 가로, 세로, 대각선일때 모두 진행
                for(int k=0; k<3; k++) {
                    int x = dx[k] + curr.x;
                    int y = dy[k] + curr.y;

                    if(!checkWall(x, y, k)) {
                        q.add(new Current(x, y, k));
                    }
                }
            }
        }
    }

    /* 벽인지 아닌지 확인 */
    static boolean checkWall(int x, int y, int direction) {

        if(direction == 0) { // 가로일때
            return (y >= n || house[x][y] == 1);
        } 
        if(direction == 1) { // 세로일때
            return (x >= n || house[x][y] == 1);
        } 
        if(direction == 2) { // 대각선일때
            return (x >= n || y >= n || house[x][y-1] == 1 || house[x-1][y] == 1 || house[x][y] == 1); // 이동할때 3칸 차지
        }
    }
}

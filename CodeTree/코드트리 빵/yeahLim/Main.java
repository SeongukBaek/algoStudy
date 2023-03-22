import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
    static int n, m;
    static int[][] map;
    static int[][] storeLocation;
    static int[][] campOwner;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int count;

    public static void main(String[] args) throws IOException {

        /* 입력 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        campOwner = new int[m][2];
        storeLocation = new int[m][2];
        count = m;
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            storeLocation[i][0] = Integer.parseInt(st.nextToken());
            storeLocation[i][1] = Integer.parseInt(st.nextToken());
            searchBasecamp(i, storeLocation[i][0], storeLocation[i][1]);
        }
        
        int time = 0;
        while(count > 0) {
        	moveToStore();
        	time++;
        }
        
        System.out.println(time);

    }

    /* 편의점에서 가장 가까운 스타트 캠프 찾기 */
    static void searchBasecamp(int person, int storeX, int storeY) {
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {storeX, storeY});

        while(!queue.isEmpty()) {
            int[] location = queue.poll();
            int x = location[0];
            int y = location[1];

            if(map[x][y] == 1) {
                campOwner[person][0] = x; // 베이스캠프를 차지한 사람 저장
                campOwner[person][1] = y;
                break;
            }

            visited[x][y] = true; // 방문 처리

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx<0 || nx >=n || ny<0 || ny >=n) continue;
                if(!visited[nx][ny]) continue;

                queue.add(new int[]{nx, ny});
            }
        }
    }

    /* 편의점으로 이동하기 */
    static void moveToStore() {
        int time = 0;

        // while 문안에 모든 사람 한번씩 이동시키고 time+1
        
        
    }
}
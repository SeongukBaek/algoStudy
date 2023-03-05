import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m, k;
    static Queue<Integer>[][] map;

    static class Player {
        int idx;
        int x, y, d, s;
        int gun = 0; // 총의 공격력
        public Player(int i, int x, int y, int d, int s) {
            this.idx = i; // 인덱스
            this.x = x;
            this.y = y;
            this.d = d; // 방향 (0:상, 1:우, 2:하, 3:좌)
            this.s = s; // 초기 능력
        }
    }
    static Player[] player;
    static int[] point;

    public static void main(String[] args) throws IOException {

        /* 입력 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new PriorityQueue[n][n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = new PriorityQueue<>((o1, o2) -> o2 - o1);
                map[i][j].add(Integer.parseInt(st.nextToken()));
            }
        }
        point = new int[m];
        player = new Player[m];
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            player[i] = new Player(i, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        }

        /* 게임 k회 돌리기 */
        for(int i=0; i<k; i++) {
            runRound();
        }

        /* 출력 */
        for(int i=0; i<m; i++) {
            System.out.print(point[i]+" ");
        }

    }

    /* 한 개 라운드 실행 */
    static void runRound() {
        for(int i=0; i<m; i++) {
            movePlayer(player[i]);
            play(i);
        }
    }

    static void movePlayer(Player plyr) {

        /* 방향 : 위 */
        if(plyr.d == 0) {

            // 한 칸 이동
            if(checkBounds(plyr.x - 1, plyr.y)) {
                plyr.x -= 1;
            }
            // 반대 방향으로 이동
            else {
                plyr.x += 1;
                plyr.d = 2;
            }
        }

        /* 방향 : 우 */
        else if(plyr.d == 1) {

            // 한 칸 이동
            if(checkBounds(plyr.x, plyr.y + 1)) {
                plyr.y += 1;
            }
            // 반대 방향으로 이동
            else {
                plyr.y -= 1;
                plyr.d = 3;
            }
        }

        /* 방향 : 하 */
        else if(plyr.d == 2) {

            // 한 칸 이동
            if(checkBounds(plyr.x + 1, plyr.y)) {
                plyr.x += 1;
            }
            // 반대 방향으로 이동
            else {
                plyr.x -= 1;
                plyr.d = 0;
            }
        }

        /* 방향 : 좌 */
        else if(plyr.d == 3) {

            // 한 칸 이동
            if(checkBounds(plyr.x, plyr.y - 1)) {
                plyr.y -= 1;
            }
            // 반대 방향으로 이동
            else {
                plyr.y += 1;
                plyr.d = 1;
            }
        }

    }

    /* 좌표가 범위 안인지 확인*/
    static boolean checkBounds(int x, int y) {
        return  0<=x && x<n && 0<=y && y<n;
    }

    /* 현재 플레이어 이동시켜서  */
    static void play(int cur) {

        int op = meetPlayer(cur);
        // 다른 플레이어를 만났다면
        if(op >= 0) {
            fight(player[cur], player[op]);
        }

        // 아니라면
        else {
            checkGun(player[cur]);
        }
    }

    /* 다른 플레이어를 만났는지 확인 */
    static int meetPlayer(int cur) {
        for(int i=0; i<m; i++) {

            if(i == cur) continue;

            if(player[i].x == player[cur].x && player[i].y == player[cur].y) {
                return i; // 다른 플레이어 인덱스 반환
            }
        }
        return -1;
    }

    /* 싸운다 */
    static void fight(Player curPlyr, Player opPlyr) {
        int curPoint = curPlyr.s + curPlyr.gun;
        int opPoint = opPlyr.s + opPlyr.gun;

        if(curPoint > opPoint ) {
            point[curPlyr.idx] += curPoint - opPoint; // 이긴 플레이어가 포인트 획득
            loose(opPlyr);
            win(curPlyr);
        }

        // 상대 플레이어가 이겼을 때
        else if(curPoint < opPoint ) {
            point[opPlyr.idx] += opPoint - curPoint; // 이긴 플레이어가 포인트 획득
            loose(curPlyr);
            win(opPlyr);
        }

        // 비겼을 때
        else {
            if(curPlyr.s > opPlyr.s) {
                loose(opPlyr);
                win(curPlyr);
            }
            else {
                loose(curPlyr);
                win(opPlyr);
            }
        }
    }

    /* 총 있는지 확인 */
    static void checkGun(Player plyr) {
        Queue<Integer> pq = map[plyr.x][plyr.y];

        // 총을 있을 경우 
        if(!pq.isEmpty()) {
            // 갖고 있는 총보다 공격력이 높을 경우
            if(plyr.gun < pq.peek()) {
                pq.add(plyr.gun);
                plyr.gun = pq.poll();

            }
        }
    }

    /* 졌을 경우 */
    static void loose(Player plyr) {
        map[plyr.x][plyr.y].add(plyr.gun);
        plyr.gun = 0;
        move90d(plyr); // 오른쪽으로 90도씩 회전하여 빈 칸으로 이동

        // 총 있으면 갖고 오기
        if(!map[plyr.x][plyr.y].isEmpty()) {
            plyr.gun = map[plyr.x][plyr.y].poll();
        }
    }

    /* 이겼을 경우 */
    static void win(Player plyr) {
        map[plyr.x][plyr.y].add(plyr.gun);
        plyr.gun = map[plyr.x][plyr.y].poll();
    }

    /* 90도로 이동 */
    static void move90d(Player plyr) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int i = plyr.d;
        while(true) {
            int x = dx[i] + plyr.x;
            int y = dy[i] + plyr.y;

            if(checkBounds(x, y) && !meetPlayer(x, y)) {
                plyr.x = x;
                plyr.y = y;
                plyr.d = i;
                break;
            }

            if(++i >= 4) {
                i = 0;
            }
        }
    }

    /* 다른 플레이어 있는지 확인 */
    static boolean meetPlayer(int x, int y) {
        for(int i=0; i<m; i++) {
            if(player[i].x == x && player[i].y == y) {
                return true;
            }
        }
        return false;
    }
}
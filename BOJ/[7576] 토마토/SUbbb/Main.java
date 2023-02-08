import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Tomato {
        int x;
        int y;
        int day;
        Tomato(int x, int y, int day) {
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }
    private static Queue<Tomato> ripeTomatoes;
    private static int[][] map;
    private static int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private static int N;
    private static int M;
    // 0인 영역 개수 -> 0에 대해서만 익는 날짜 정보를 갱신하기에 이를 변수로 두고 카운트하는 것이 효율적일 것 같음.
    private static int zeroCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        ripeTomatoes = new LinkedList<>();

        for (int x = 0; x < M; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < N; y++) {
                int tomatoInfo = Integer.parseInt(st.nextToken());
                map[x][y] = tomatoInfo;

                if (tomatoInfo == 1) {
                    ripeTomatoes.add(new Tomato(x, y, 0));
                } else if (tomatoInfo == 0) {
                    zeroCount++;
                }
            }
        }

        System.out.println(getMinDay());
    }

    /**
     * 토마토 숙성 최소 날짜 또는 불가능한 경우의 출력 반환
     * @return int
     * */
    private static int getMinDay() {
        // 이미 모든 토마토가 익었다면 0을 반환
        if (zeroCount == 0) {
            return 0;
        }

        int day = proceedRiping();
        // 숙성 이후 익지 않은 토마토가 있다면 -1을 반환
        if (zeroCount > 0) {
            return -1;
        }
        return day;
    }

    /**
     * 익은 토마토로부터 주변의 토마토를 익게 한다.
     *
     * @return int 가능한 모든 토마토를 익게 한 후의 날짜
     * */
    private static int proceedRiping() {
        int day = 0;
        // 토마토 숙성 시작
        while (!ripeTomatoes.isEmpty()) {
            day = searchAllDirection(ripeTomatoes.poll());
        }
        return day - 1;
    }

    /**
     * 주어진 토마토의 4방향을 탐색하면서
     * 아직 익지 않은 토마토라면 익게 되는 날짜를 저장하고, 큐에 삽입한다.
     *
     * @param current 주어지는 토마토의 정보
     * @return int
     * */
    private static int searchAllDirection(Tomato current) {
        int day = current.day + 1;
        for (int dir = 0; dir < 4; dir++) {
            int nx = current.x + directions[dir][0];
            int ny = current.y + directions[dir][1];

            // 범위 밖이거나, 토마토가 없거나, 이미 익은 토마토라면 패스
            if (!isIn(nx, ny) || map[nx][ny] == -1 || map[nx][ny] > 0) {
                continue;
            }

            // 토마토가 익게 되는 날짜 갱신하고 큐에 삽입
            ripeTomatoes.add(new Tomato(nx, ny, day));
            map[nx][ny] = day;
            zeroCount--;
        }
        return day;
    }

    /**
     * 주어진 좌표가 지도 범위 내인지 반환
     *
     * @return boolean
     * */
    private static boolean isIn(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }
}
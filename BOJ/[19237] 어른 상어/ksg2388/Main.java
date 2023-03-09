import java.io.*;
import java.util.*;

public class Main {
    static class Board {
        int sharkNum;
        int time; // 향기가 사라지기까지 남은 시간

        public Board(int sharkNum, int time) {
            this.sharkNum = sharkNum;
            this.time = time;
        }
    }

    static int N, M, k, count;
    static Board[][] map;
    static int[] sharkDirection; // 상어들이 현재 보고있는 방향 저장
    static int[][][] sharkPriorityDirection; // 상어들의 방향 우선순위 저장
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        int result = 0;
        init();

        for (int i = 0; i < 1001; i++) {
            if (count == 1) {
                break;
            }
            sharksMove();
            result++;
        }

        System.out.println(result > 1000 ? -1 : result);

    }

    /**
     * 입력값을 받고 초기화 한다.
     * 
     * @throws IOException
     */
    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 격자의 크기
        M = Integer.parseInt(st.nextToken()); // 상어의 수
        k = Integer.parseInt(st.nextToken()); // 냄새의 지속시간
        count = M;

        map = new Board[N][N];
        sharkDirection = new int[M];
        sharkPriorityDirection = new int[M][4][4];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                // 상어인 경우
                if (num > 0) {
                    map[i][j] = new Board(num - 1, k);
                }
            }
        }

        // 상어의 방향 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            sharkDirection[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        // 상어들의 방향 우선순위 입력
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    sharkPriorityDirection[i][j][k] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }
    }

    public static void sharksMove() {
        Board[][] newMap = new Board[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != null && map[i][j].time == k) {
                    sharkMove(i, j, map[i][j], newMap);
                }
            }
        }

        // 맵 업데이트
        mapUpdate(newMap);
    }

    public static void sharkMove(int x, int y, Board shark, Board[][] newMap) {
        int nx = x;
        int ny = y;

        // 주변에 빈 공간이 있는 경우
        if (checkEmptyPlace(x, y, shark.sharkNum) > -1) {
            int d = checkEmptyPlace(x, y, shark.sharkNum);
            sharkDirection[shark.sharkNum] = d;
            nx += dx[d];
            ny += dy[d];

            // 다른 상어가 있는 경우
            if (newMap[nx][ny] != null && newMap[nx][ny].time == k) {
                newMap[nx][ny] = fight(shark, newMap[nx][ny]);
                count--;
            } else {
                newMap[nx][ny] = shark;
            }
        }
        // 주변에 빈 공간이 없는 경우
        else {
            if (checkMyPlace(x, y, shark.sharkNum) > -1) {
                int d = checkMyPlace(x, y, shark.sharkNum);
                sharkDirection[shark.sharkNum] = d;
                nx = x + dx[d];
                ny = y + dy[d];

                // 다른 상어가 있는 경우
                if (newMap[nx][ny] != null && newMap[nx][ny].time == k) {
                    newMap[nx][ny] = fight(shark, newMap[nx][ny]);
                    count--;
                } else {
                    newMap[nx][ny] = shark;
                }
            }
        }

    }

    public static Board fight(Board shark1, Board shark2) {
        return shark1.sharkNum < shark2.sharkNum ? shark1 : shark2;
    }

    public static void mapUpdate(Board[][] newMap) {
        // 냄새 남기기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 냄새가 존재하는 경우
                if (map[i][j] != null) {
                    map[i][j] = new Board(map[i][j].sharkNum, map[i][j].time - 1);
                    if (map[i][j].time == 0) {
                        map[i][j] = null;
                    }
                }
            }
        }

        // 상어들을 추가
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 상어인 경우
                if (newMap[i][j] != null && newMap[i][j].time == k) {
                    map[i][j] = newMap[i][j];
                }
            }
        }
    }

    public static int checkEmptyPlace(int x, int y, int sharkNum) {
        // 상어의 방향 우선순위 순서에 따라 돌면서 빈 공간 확인
        for (int i = 0; i < 4; i++) {
            int d = sharkPriorityDirection[sharkNum][sharkDirection[sharkNum]][i];
            int nx = x + dx[d];
            int ny = y + dy[d];
            // 상어가 격자 밖으로 나가는 경우
            if (sharkOutMap(nx, ny)) {
                continue;
            }

            if (map[nx][ny] == null) {
                return d;
            }
        }

        return -1;
    }

    public static int checkMyPlace(int x, int y, int sharkNum) {
        // 상어의 방향 우선순위 순서에 따라 돌면서 빈 공간 확인
        for (int i = 0; i < 4; i++) {
            int d = sharkPriorityDirection[sharkNum][sharkDirection[sharkNum]][i];
            int nx = x + dx[d];
            int ny = y + dy[d];
            // 상어가 격자 밖으로 나가는 경우
            if (sharkOutMap(nx, ny)) {
                continue;
            }

            if (map[nx][ny] != null && map[nx][ny].sharkNum == sharkNum) {
                return d;
            }
        }

        return -1;
    }

    public static boolean sharkOutMap(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= N;
    }
}
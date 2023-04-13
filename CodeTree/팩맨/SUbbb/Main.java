import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static final int SIZE = 4;
    // 몬스터들이 한 위치에 겹쳐져 위치할 수 있음.
    private static int[][][] monsters;
    private static int[][][] eggs;
    // 시체 : -3
    private static int[][] deads;
    // 팩맨은 지도에 표시하지 않기
    private static int packManX;
    private static int packManY;
    private static List<int[]> packManRoutes;
    private static final int[][] moves = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        monsters = new int[SIZE][SIZE][8];
        deads = new int[SIZE][SIZE];

        int m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        packManX = Integer.parseInt(st.nextToken()) - 1;
        packManY = Integer.parseInt(st.nextToken()) - 1;

        for (int index = 0; index < m; index++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;

            monsters[r][c][d]++;
        }

        // 우선순위를 이용해 미리 팩맨의 이동 선택지 만들기
        packManRoutes = new ArrayList<>();
        makePackManRoutes(new int[3], 0);
        for (int turn = 1; turn <= t; turn++) {
            // 몬스터 복제 시도
            // 현재 자기 위치에 그대로 하나 생성
            // eggs에만 추가
            copyMonsters();

            // 몬스터 이동
            // 이동하지 못해, 방향을 -1하는 경우 : 몬스터 시체가 있거나, 팩맨, 혹은 격자 밖인 경우 반복
            // -> 모든 방향 탐색 후 이동하지 못하면 이동 X
            moveMonsters();

            // 팩맨 이동
            // 조합을 사용해서 미리 이동 선택지를 만들어두고, 매번 해당 조합에서 최대값 찾아 이동하기
            // 먹은 후 해당 자리에는 시체 두기
            // 현재 자리에 있는 몬스터는 먹지 않고, 알도 먹지 않음 -> 따라서 최대 3개의 몬스터만 먹을 수 있음
            movePackMan();

            // 몬스터 시체 소멸
            // 2턴동안만 유지됨.
            removeDeadMonsters();

            // 몬스터 복제 완성
            hatchEggs();
        }

        // map 탐색하면서 몬스터 수 출력
        System.out.println(countMonsters());
    }

    /**
     * 중복순열을 이용해 팩맨이 이동할 수 있는 경로를 우선순위에 따라 저장
     */
    private static void makePackManRoutes(int[] route, int count) {
        if (count == 3) {
            packManRoutes.add(route.clone());
            return;
        }

        for (int move = 0; move < 8; move += 2) {
            route[count] = move;
            makePackManRoutes(route, count + 1);
        }
    }

    /**
     * 현재 몬스터들이 알을 낳는다. 복사해 저장
     * */
    private static void copyMonsters() {
        eggs = new int[SIZE][SIZE][8];
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                System.arraycopy(monsters[x][y], 0, eggs[x][y], 0, 8);
            }
        }
    }

    /**
     * 리스트에 몬스터들의 이동 정보를 저장해둔다.
     * 이후, 모든 몬스터의 이동을 마친 후, 해당 정보로 몬스터 정보를 갱신한다.
     * */
    private static void moveMonsters() {
        List<int[]> moveInfo = new ArrayList<>();

        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                for (int d = 0; d < 8; d++) {
                    if (monsters[x][y][d] == 0) {
                        continue;
                    }

                    for (int count = 0; count < 8; count++) {
                        int td = (d + count) % 8;
                        int nx = x + moves[td][0];
                        int ny = y + moves[td][1];

                        if (!isIn(nx, ny) || isDead(nx, ny) || isPackMan(nx, ny)) {
                            continue;
                        }
                        moveInfo.add(new int[] {
                                x, y, d, nx, ny, td, monsters[x][y][d]
                        });
                        break;
                    }
                }
            }
        }

        // 모든 몬스터가 이동한 후, 이동 정보를 갱신
        for (int[] info : moveInfo) {
            monsters[info[0]][info[1]][info[2]] -= info[6];
            monsters[info[3]][info[4]][info[5]] += info[6];
        }
    }

    private static boolean isDead(int x, int y) {
        return deads[x][y] < 0;
    }

    private static boolean isPackMan(int x, int y) {
        return packManX == x && packManY == y;
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }

    /**
     * 팩맨의 최대 몬스터 섭취 경로를 찾아, 이동시킨다.
     * 어차피 몬스터가 있는 위치에 팩맨이 이동한다면 시체는 1개만 둔다고 생각해도 무방하다. 시체가 있는 곳으로 팩맨이 가지 않기에, 시체 정보가 -2인데 다시 -3으로 덮어씌워질 걱정할 핊요가 없다.
     * */
    private static void movePackMan() {
        // 몬스터를 최대로 많이 먹는 move를 찾기
        int[] route = packManRoutes.get(findMaxRoute());

        for (int move = 0; move < 3; move++) {
            int currentD = route[move];

            packManX += moves[currentD][0];
            packManY += moves[currentD][1];

            // 몬스터 먹기 -> 시체 남기기 / 몬스터 리스트 제거
            if (computeCount(packManX, packManY) > 0) {
                deads[packManX][packManY] = -3;
                clearMonsters(packManX, packManY);
            }
        }
    }

    /**
     * 미리 구한 팩맨의 경로들 중 가장 몬스터를 많이 먹을 수 있는 경로의 인덱스를 반환
     * */
    private static int findMaxRoute() {
        int x = packManX;
        int y = packManY;

        int max = -1;
        int maxId = -1;

        for (int index = 0; index < 64; index++) {
            int eat = 0;
            boolean[][] used = new boolean[SIZE][SIZE];

            int nx = x;
            int ny = y;

            int[] route = packManRoutes.get(index);

            for (int d = 0; d < 3; d++) {
                nx = nx + moves[route[d]][0];
                ny = ny + moves[route[d]][1];

                if (!isIn(nx, ny)) {
                    eat = -1;
                    break;
                }

                if (!used[nx][ny]) {
                    eat += computeCount(nx, ny);
                    used[nx][ny] = true;
                }
            }

            if (eat > max) {
                max = eat;
                maxId = index;
            }
        }

        return maxId;
    }

    /**
     * 시체는 2턴 동안 유지되므로 매 턴마다 +1씩 증가
     * */
    private static void removeDeadMonsters() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (deads[x][y] < 0) {
                    deads[x][y]++;
                }
            }
        }
    }

    private static int computeCount(int x, int y) {
        int count = 0;
        for (int d = 0; d < 8; d++) {
            count += monsters[x][y][d];
        }
        return count;
    }

    private static void clearMonsters(int x, int y) {
        for (int d = 0; d < 8; d++) {
            monsters[x][y][d] = 0;
        }
    }

    /**
     * 알들이 부화한다. 부화를 위해서는 몬스터 정보에 이를 추가
     * */
    private static void hatchEggs() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                for (int d = 0; d < 8; d++) {
                    monsters[x][y][d] += eggs[x][y][d];
                }
            }
        }
    }

    private static int countMonsters() {
        int count = 0;
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                count += computeCount(x, y);
            }
        }
        return count;
    }
}
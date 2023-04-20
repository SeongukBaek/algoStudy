import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] map;

    private static int minMove;
    private static int[] minFish;

    // 처음 상어 크기
    private static int sharkSize = 2;
    // 상어가 물고기를 먹은 횟수
    private static int eatCount = 0;

    private static int sharkX;
    private static int sharkY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        // 입력
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < N; y++) {
                int number = Integer.parseInt(st.nextToken());

                if (number == 9) {
                    sharkX = x;
                    sharkY = y;
                    number = 0;
                }

                map[x][y] = number;
            }
        }

        int seconds = 0;
        // 물고기 탐색 -> 자신보다 작은 물고기를 찾으면, 거리 계산
        // 거리 계산과 동시에 거리가 가장 가까운 물고기를 찾기
        while (true) {
            findFish();

            if (minFish == null) {
                break;
            }

            // 상어 이동
            map[minFish[0]][minFish[1]] = 0;
            sharkX = minFish[0];
            sharkY = minFish[1];

            // 시간 증가
            seconds += minMove;

            // 상어 먹은 횟수 비교해서, 상어 크기 증가
            if (++eatCount == sharkSize) {
                sharkSize++;
                eatCount = 0;
            }
        }

        System.out.println(seconds);
    }

    private static void findFish() {
        // 가장 가까이 있는 먹을 수 있는 물고기
        minMove = N * N;
        minFish = null;

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                // 상어가 먹을 수 있는 물고기로부터 상어까지의 거리 계산
                if (map[x][y] == 0 || map[x][y] >= sharkSize) {
                    continue;
                }

                int move = computePath(x, y);

                // 이동하지 못하는 경우
                if (move == -1) {
                    continue;
                }

                if (move < minMove) {
                    minMove = move;
                    minFish = new int[] {x, y};
                }
            }
        }
    }

    private static final int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    /**
     * 상어 위치에서 해당 좌표까지 가는 거리 반환
     * */
    private static int computePath(int x, int y) {
        int cost = 1;
        Queue<int[]> areas = new ArrayDeque<>();
        int[][] costs = new int[N][N];
        for (int index = 0; index < N; index++) {
            Arrays.fill(costs[index],N * N);
        }

        areas.add(new int[] {x, y});
        costs[x][y] = cost;

        while (!areas.isEmpty()) {
            int size = areas.size();

            for (int c = 0; c < size; c++) {
                int[] now = areas.poll();

                for (int[] move : moves) {
                    int nx = now[0] + move[0];
                    int ny = now[1] + move[1];

                    if (nx == sharkX && ny == sharkY) {
                        return cost;
                    }

                    if (!isIn(nx, ny) || costs[nx][ny] <= cost || map[nx][ny] > sharkSize) {
                        continue;
                    }

                    costs[nx][ny] = cost;
                    areas.add(new int[] {nx, ny});
                }
            }

            cost++;
        }

        return -1;
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Location {
        int x;
        int y;
        Location (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int N;
    private static int M;
    private static int[][] map;
    private static int[][] temp;
    private static final int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static int max;
    private static List<Location> wallCandidates;
    private static Queue<Location> virus;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        virus = new LinkedList<>();
        wallCandidates = new ArrayList<>();

        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < M; y++) {
                int number = Integer.parseInt(st.nextToken());
                map[x][y] = number;

                if (number == 2) {
                    virus.add(new Location(x, y));
                }

                if (number == 0) {
                    wallCandidates.add(new Location(x, y));
                }
            }
        }

        buildWall();

        System.out.println(max);
    }

    // 모든 경우의 벽을 3개 세워보고, 바이러스를 퍼뜨리기
    private static void buildWall() {
        // 첫 번째 벽 선택
        for (int first = 0; first < wallCandidates.size() - 2; first++) {
            Location firstWall = wallCandidates.get(first);
            // 두 번째 벽 선택
            for (int second = first + 1; second < wallCandidates.size() - 1 && second != first; second++) {
                Location secondWall = wallCandidates.get(second);
                // 세 번째 벽 선택
                for (int third = second + 1; third < wallCandidates.size() && third != second; third++) {
                    temp = copyMap();
                    Location thirdWall = wallCandidates.get(third);
                    temp[firstWall.x][firstWall.y] = 1;
                    temp[secondWall.x][secondWall.y] = 1;
                    temp[thirdWall.x][thirdWall.y] = 1;

                    spreadVirus();
                    max = Math.max(max, countSafeArea());
                }
            }
        }
    }

    private static void spreadVirus() {
        Queue<Location> copyVirus = new LinkedList<>(virus);
        while (!copyVirus.isEmpty()) {
            Location currentVirus = copyVirus.poll();
            int x = currentVirus.x;
            int y = currentVirus.y;

            for (int[] direction : directions) {
                int nx = x + direction[0];
                int ny = y + direction[1];

                if (!isInBoundary(nx, ny) || temp[nx][ny] >= 1) {
                    continue;
                }

                copyVirus.add(new Location(nx, ny));
                temp[nx][ny] = 2;
            }
        }
    }

    private static int[][] copyMap() {
        int[][] temp = new int[N][M];
        for (int x = 0; x < N; x++) {
            System.arraycopy(map[x], 0, temp[x], 0, M);
        }
        return temp;
    }

    private static int countSafeArea() {
        int area = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (temp[x][y] == 0) {
                    area++;
                }
            }
        }
        return area;
    }

    private static boolean isInBoundary(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Location {
        int x;
        int y;
        int cost;
        boolean isBoomed;
        Location(int x, int y, int cost, boolean isBoomed) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.isBoomed = isBoomed;
        }
    }
    private static int[][] map;
    private static int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int x = 0; x < N; x++) {
            String[] info = br.readLine().split("");
            for (int y = 0; y < M; y++) {
                map[x][y] = Integer.parseInt(info[y]);
            }
        }

        System.out.println(traverseMap());
    }

    private static int traverseMap() {
        Queue<Location> path = new LinkedList<>();
        boolean[][][] isVisited = new boolean[N][M][2];
        path.add(new Location(0, 0, 1, false));

        while (!path.isEmpty()) {
            Location current = path.poll();
            int x = current.x;
            int y = current.y;
            int cost = current.cost;
            boolean isBoomed = current.isBoomed;

            // 목적지인 경우 바로 종료 -> 최단 경로를 구하는 것이므로
            if (x == N - 1 && y == M - 1) {
                return cost;
            }

            for (int[] direction : directions) {
                int nx = x + direction[0];
                int ny = y + direction[1];

                // 범위 밖인 경우 더이상 진행 불가
                if (!isIn(nx, ny) ) {
                    continue;
                }

                int nCost = cost + 1;

                // 벽이고, 이전에 벽을 부순 적이 없는 경우
                if (map[nx][ny] == 1 && !isBoomed) {
                    isVisited[nx][ny][1] = true;
                    path.add(new Location(nx, ny, nCost, true));
                }
                // 벽이 아닌 경우
                else if (map[nx][ny] == 0){
                    // 이전에 벽을 부순 적이 있고, 방문한 적이 없다면
                    if (isBoomed && !isVisited[nx][ny][1]) {
                        isVisited[nx][ny][1] = true;
                        path.add(new Location(nx, ny, nCost, isBoomed));
                    }
                    // 이전에 벽을 부순 적이 없고, 방문한 적이 없다면
                    else if (!isBoomed && !isVisited[nx][ny][0]){
                        isVisited[nx][ny][0] = true;
                        path.add(new Location(nx, ny, nCost, isBoomed));
                    }
                }
            }
        }
        return -1;
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
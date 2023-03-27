import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Location {
        int x;
        int y;
        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static List<Location> stores;
    private static int n;
    private static int m;
    private static int baseCamps;
    private static int[][] map;
    private static final int[][] moves = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        stores = new ArrayList<>();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for (int x = 0; x < n; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < n; y++) {
                int number = Integer.parseInt(st.nextToken());
                map[x][y] = number;
                if (number == 1) {
                    baseCamps++;
                }
            }
        }

        for (int index = 0; index < m; index++) {
            st = new StringTokenizer(br.readLine());
            stores.add(new Location(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1));
        }

        int max = 0;
        // 각 편의점에서부터 출발!
        for (int index = 0; index < stores.size(); index++) {
            // 최소 index + 1분부터 각 편의점에서 출발가능
            max = Math.max(max, bfs(stores.get(index)) + index + 1);
        }

        System.out.println(max);
    }

    /**
     * 각 편의점에서부터, 모든 지점까지의 최소 거리를 갱신
     * 각 베이스캠프까지의 거리 중, 최소 거리를 반환
     */
    private static int bfs(Location location) {
        Queue<Location> locations = new ArrayDeque<>();
        locations.add(location);
        int baseCampCount = baseCamps;

        // 각 좌표까지의 최소 이동 거리 갱신
        int[][] distance = new int[n][n];
        int minDistance = n * n;

        while (!locations.isEmpty()) {
            Location currentLocation = locations.poll();

            for (int[] move : moves) {
                int nx = currentLocation.x + move[0];
                int ny = currentLocation.y + move[1];

                // 격자 밖이거나, 이미 최소 거리가 갱신된 경우 패스
                if (!isInMap(nx, ny) || distance[nx][ny] != 0) {
                    continue;
                }

                // 이전 좌표까지의 거리 + 1로 현재 좌표까지의 최소 이동 거리 갱신
                distance[nx][ny] = distance[currentLocation.x][currentLocation.y] + 1;

                // 현재 좌표가 방문가능한 베이스캠프이면서, 최소 이동 거리라면
                if (map[nx][ny] == 1 && minDistance > distance[nx][ny]) {
                    // 갱신!
                    minDistance = distance[nx][ny];
                    // 방문처리, 더이상 해당 좌표로 이동 불가
                    map[nx][ny] = 2;
                    baseCampCount--;
                } else {
                    // 그렇지 않은 경우는 큐에 넣고 추가 이동
                    locations.add(new Location(nx, ny));
                }
            }

            // 모든 베이스캠프에 대한 최소 거리 갱신이 끝났다면 종료!
            if (baseCampCount == 0) {
                break;
            }
        }

        return minDistance;
    }

    /**
     * 격자 안에 있는지 여부 반환
     */
    private static boolean isInMap(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
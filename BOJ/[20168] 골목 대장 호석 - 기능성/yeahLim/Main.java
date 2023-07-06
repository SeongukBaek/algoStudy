import java.io.*;
import java.util.*;

class Main {

    static int n, a, b, c;
    static int[][] roads;
    static int minCost = 1001;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        /* 입력 및 변수 초기화 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken()); // 출발지
        b = Integer.parseInt(st.nextToken()); // 도착지
        c = Integer.parseInt(st.nextToken()); // 비용
        visited = new boolean[n+1];
        roads = new int[n+1][n+1];
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int road1 = Integer.parseInt(st.nextToken());
            int road2 = Integer.parseInt(st.nextToken());
            roads[road1][road2] = Integer.parseInt(st.nextToken());
            roads[road2][road1] = roads[road1][road2];
        }

        searchRoute(a, 0, 0);
        System.out.println(minCost == 1001 ? -1 : minCost);
    }

    private static void searchRoute(int current, int cost, int max) {
        if (visited[current]) {
            return;
        }
        if (cost > c) {
            return ;
        }
        if (current == b) {
            minCost = Math.min(minCost, max);
            return;
        }
        visited[current] = true;
        for (int i = 1; i <= n; i++) {
            if (roads[current][i] == 0) continue;
            searchRoute(i, cost + roads[current][i], Math.max(max, roads[current][i]));
        }
        visited[current] = false;
    }
}
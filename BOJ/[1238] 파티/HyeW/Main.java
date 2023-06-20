import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1000000;
    static int N, M, X;
    static List<Road>[] map, mapReverse;
    static int[] dist, distReverse;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        map = new ArrayList[N + 1];
        mapReverse = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            map[i] = new ArrayList<>();
            mapReverse[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            map[v1].add(new Road(v2, w));
            mapReverse[v2].add(new Road(v1, w));
        }

        dist = getShortestPath(map);
        distReverse = getShortestPath(mapReverse);

        int maxDist = 0;
        for (int i = 1; i <= N; i++) {
            maxDist = Math.max(maxDist, dist[i] + distReverse[i]);
        }

        System.out.println(maxDist);
    }

    private static int[] getShortestPath(List<Road>[] map) {
        PriorityQueue<Road> roads = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
        boolean[] checked = new boolean[N + 1];
        int[] distance = new int[N + 1];

        roads.add(new Road(X, 0));
        Arrays.fill(distance, INF);
        distance[X] = 0;

        while (!roads.isEmpty()) {
            Road cur = roads.poll();

            if (checked[cur.v]) {
                continue;
            }

            checked[cur.v] = true;
            for (Road next : map[cur.v]) {
                if (!checked[next.v] && distance[next.v] > distance[cur.v] + next.w) {
                    distance[next.v] = distance[cur.v] + next.w;
                    roads.add(new Road(next.v, distance[next.v]));
                }
            }
        }
        return distance;
    }

    static class Road {
        int v;
        int w;

        Road(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}

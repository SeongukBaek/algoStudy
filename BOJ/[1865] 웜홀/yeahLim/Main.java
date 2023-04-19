import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 500*10_000;
    static int n;
    static int[] dist;
    static List<Road>[] roads;
    static class Road {
        int dest;
        int time;
        public Road(int destination, int time) {
            this.dest = destination;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {

        /* 입력 및 변수 초기화 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int roadNum, wormholeNum;
        while(t-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            roadNum = Integer.parseInt(st.nextToken());
            wormholeNum = Integer.parseInt(st.nextToken());
            dist = new int[n+1];
            roads = new List[n+1];
            for(int i=0; i<=n; i++) {
                roads[i] = new ArrayList<>();
            }
            while(roadNum-- > 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                roads[a].add(new Road(b, time));
                roads[b].add(new Road(a, time));
            }
            while(wormholeNum-- > 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                roads[a].add(new Road(b, -time));
            }
            System.out.println(searchRoad());
        }


    }

    /* Bellman-Ford : 시간을 되돌아가면서, 출발지로 돌아올 수 있는 길 찾기 */
    private static String searchRoad() {
        boolean isMinusCycle = false;
        Arrays.fill(dist, INF);
        dist[1] = 0;

        // 노드의 개수 - 1 만큼 반복
        for(int i=1; i<n; i++) {
            for(int j=1; j<roads.length; j++) {
                for(Road road : roads[j]) {
                    dist[road.dest] = Math.min(dist[road.dest], dist[j] + road.time);
                }
            } 
        }

        // 값이 갱신되면 음수 사이클 존재
        for(int j=1; j<roads.length; j++) {
            for(Road road : roads[j]) {
                if(dist[road.dest] > dist[j] + road.time) {
                    isMinusCycle = true;
                }
            }
        }

        return (isMinusCycle) ? "YES" : "NO";
    }
}
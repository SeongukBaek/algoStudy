import java.io.*;
import java.util.*;

class Main {

    static List<Node>[] graph;
    static int[] dist;
    static class Node {
        int n, weight; // 노드, 가중치
        public Node(int n, int weight) {
            this.n = n;
            this.weight = weight;
        }
    }
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        /* 입력 및 변수 초기화 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while (tc-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 노드 개수
            int d = Integer.parseInt(st.nextToken()); // 간선 개수
            int c = Integer.parseInt(st.nextToken()); // 시작점

            graph = new List[n+1];
            dist = new int[n+1];

            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
                dist[i] = INF;
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                graph[b].add(new Node(a, s));
            }

            hackComputers(c);

            /* 출력 */
            int count = 0;
            int time = 0;
            for (int i = 1; i <= n; i++) {
                if (dist[i] != INF) {
                    count++;
                    time = Math.max(time, dist[i]);
                }
            }
            System.out.println(count + " " + time);
        }
    }

    /* Dijkstra : 마지막 컴퓨터가 감염되기까지 걸리는 시간 구하기 */
    private static void hackComputers(int start) {
        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        q.offer(new Node(start, 0));
        dist[start] = 0;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (dist[cur.n] < cur.weight) {
                continue;
            }

            for (Node next : graph[cur.n]) {
                if (dist[next.n] > cur.weight + next.weight) {
                    dist[next.n] = cur.weight + next.weight;
                    q.offer(new Node(next.n, dist[next.n]));
                }
            }
        }
    }
}
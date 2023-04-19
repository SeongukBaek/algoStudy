import java.util.*;
import java.io.*;

public class Main {
	static final int INF = 500 * 10000;
	static int N, M, W; // 지점수, 도로개수, 웜홀개수
	static List<Node>[] graph;
	static int[] distance;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());

		for (int t = 0; t < TC; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			graph = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}

			// 도로 받기
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());

				graph[s].add(new Node(e, w));
				graph[e].add(new Node(s, w));
			}

			// 웜홀 받기
			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				graph[Integer.parseInt(st.nextToken())]
						.add(new Node(Integer.parseInt(st.nextToken()), -Integer.parseInt(st.nextToken())));
			}

			distance = new int[N + 1];
			Arrays.fill(distance, INF);

			if (getShortestPath(1)) {
				sb.append("YES").append("\n");
			}
			else {
				sb.append("NO").append("\n");
			}
		}
		System.out.println(sb);
	}

	private static boolean getShortestPath(int start) {
        // 벨만포드 알고리즘
		// 전체 간선 E개를 하나씩 확인한다.
		// 각 간선을 거쳐 다른 노드로 가는 비용을 계산하여 최단 거리 테이블을 갱신한다.
		distance[start] = 0;
		for (int i = 1; i < N; i++) {
			for (int j = 1; j <= N; j++) {
				for (Node nxt : graph[j]) {
					if (distance[nxt.v] > distance[j] + nxt.w) {
						distance[nxt.v] = distance[j] + nxt.w;
					}
				}
			}
		}

        // 음의 사이클이 생기는지 확인한다.
		for (int j = 1; j <= N; j++) {
			for (Node nxt : graph[j]) {
				if (distance[nxt.v] > distance[j] + nxt.w) {
					distance[nxt.v] = distance[j] + nxt.w;
					return true;
				}
			}
		}
		return false;
	}

	static class Node {
		int v;
		int w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

}

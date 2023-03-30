import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer>[] graph;
	static boolean[] visited;
	static int N, M, count, vertexNum, edgeNum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 0;

		while (true) {
			T++;
			StringBuilder sb = new StringBuilder();
			sb.append("Case ").append(T).append(": ");
			count = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			if (N == 0 && M == 0) {
				break;
			}

			visited = new boolean[N + 1];
			graph = new List[N + 1];

			for (int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				graph[x].add(y);
				graph[y].add(x);
			}

			countTree();

			if (count == 0) {
				sb.append("No trees.");
				System.out.println(sb);
				continue;
			}
			if (count == 1) {
				sb.append("There is one tree.");
				System.out.println(sb);
				continue;
			}
			if (count > 1) {
				sb.append("A forest of ").append(count).append(" trees.");
				System.out.println(sb);
			}
		}
	}

	private static void countTree() {
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				vertexNum = 0;
				edgeNum = 0;
				makeEdge(i);
				if (edgeNum == (vertexNum - 1) * 2) {
					count++;
				}
			}
		}
	}

	private static void makeEdge(int n) {
		visited[n] = true;
		vertexNum++;
		int connectedEdgeSize = graph[n].size();
		edgeNum += connectedEdgeSize;
		for (int i = 0; i < connectedEdgeSize; i++) {
			int current = graph[n].get(i);
			if (visited[current]) {
				continue;
			}
			makeEdge(current);
		}
	}
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 음수 사이클이 존재하는지를 확인하는 문제 !!!
// 벨만 포드 알고리즘 사용
public class Main {
	private static List<int[]>[] adjList;
	private static int N;
	private static final int INF = 5000001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		StringBuilder answer = new StringBuilder();

		for (int test = 0; test < T; test++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			adjList = new ArrayList[N];
			for (int index = 0; index < N; index++) {
				adjList[index] = new ArrayList<>();
			}

			for (int index = 0; index < M; index++) {
				st = new StringTokenizer(br.readLine());
				int edge1 = Integer.parseInt(st.nextToken()) - 1;
				int edge2 = Integer.parseInt(st.nextToken()) - 1;
				int cost = Integer.parseInt(st.nextToken());

				adjList[edge1].add(new int[] {edge2, cost});
				adjList[edge2].add(new int[] {edge1, cost});
			}

			for (int index = 0; index < W; index++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;
				int cost = Integer.parseInt(st.nextToken());

				adjList[from].add(new int[] {to, cost * -1});
			}

			answer.append(canTravelBackward() ? "YES" : "NO").append("\n");
		}

		System.out.println(answer);
	}

	private static boolean canTravelBackward() {
		int[] costs = new int[N];
		Arrays.fill(costs, INF);

		costs[0] = 0;

		// 0번 정점부터, N - 1개의 정점만을 시작 정점으로,
		// 어차피 모든 정점을 방문하기 위해서는 N - 1번의 탐색만 하면 됨
		for (int start = 0; start < N - 1; start++) {
			for (int to = 0; to < N; to++) {
				for (int[] nextInfo : adjList[to]) {
					int next = nextInfo[0];
					int cost = nextInfo[1];

					// next까지 가는 비용이 기존 next의 비용보다 작다면 갱신
					if (costs[to] + cost < costs[next]) {
						costs[next] = costs[to] + cost;
					}
				}
			}
		}

		for (int start = 0; start < N; start++) {
			for (int[] nextInfo : adjList[start]) {
				int to = nextInfo[0];
				int cost = nextInfo[1];

				// 출발점부터 to까지 가는 비용이 기존 to의 비용보다 작다면 음수 사이클 존재 !
				if (costs[start] + cost < costs[to]) {
					return true;
				}
			}
		}

		return false;
	}
}
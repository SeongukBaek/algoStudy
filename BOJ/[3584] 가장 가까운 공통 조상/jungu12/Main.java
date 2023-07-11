import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int A, B;
	static int[] graph;
	static Set<Integer> ancestors;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());

			graph = new int[N + 1];
			ancestors = new HashSet<>();

			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine());
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());
				graph[child] = parent;
			}

			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			ancestors.add(A);
			makeAncestorSet(A);
			if(ancestors.contains(B)) {
				System.out.println(B);
				continue;
			}
			findCommonAncester(B);
		}
	}

	private static void findCommonAncester(int cur) {
		if (ancestors.contains(graph[cur])) {
			System.out.println(graph[cur]);
			return;
		}
		
		findCommonAncester(graph[cur]);
	}

	private static void makeAncestorSet(int cur) {
		if (graph[cur] == 0) {
			return;
		}
		
		ancestors.add(graph[cur]);
		makeAncestorSet(graph[cur]);
	}
}
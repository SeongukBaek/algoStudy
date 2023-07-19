import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	private static List<Integer>[] treeInfo;
	private static int N;
	private static boolean[] isRoot;
	// 각 노드의 높이를 저장
	private static int[] heights;
	// 부모 노드를 저장
	private static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder answer = new StringBuilder();
		for (int test = 0; test < T; test++) {
			N = Integer.parseInt(br.readLine());

			treeInfo = new ArrayList[N + 1];
			for (int index = 1; index <= N; index++) {
				treeInfo[index] = new ArrayList<>();
			}
			isRoot = new boolean[N + 1];
			Arrays.fill(isRoot, true);

			for (int index = 0; index < N - 1; index++) {
				int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				int a = info[0];
				int b = info[1];

				treeInfo[a].add(b);
				isRoot[b] = false;
			}

			heights = new int[N + 1];
			parents = new int[N + 1];

			int root = getRoot();

			// 루트부터 부모 노드 정보 저장
			fillParent(root, 1, 0);

			int[] nodes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			answer.append(lca(nodes[0], nodes[1])).append("\n");
		}

		System.out.print(answer);
	}

	private static int getRoot() {
		int root = 0;
		for (int index = 1; index <= N; index++) {
			if (isRoot[index]) {
				root = index;
				break;
			}
		}
		return root;
	}

	private static void fillParent(int currentNode, int currentHeight, int parent) {
		heights[currentNode] = currentHeight;
		parents[currentNode] = parent;

		for (int nextNode : treeInfo[currentNode]) {
			// 부모 노드인 경우는 패스
			if (nextNode == parent) {
				continue;
			}

			fillParent(nextNode, currentHeight + 1, currentNode);
		}
	}

	private static int lca(int nodeA, int nodeB) {
		int aHeight = heights[nodeA];
		int bHeight = heights[nodeB];

		// 두 노드의 높이가 같아질 때까지, 높이가 낮은 노드 기준으로 맞춘다.
		while (aHeight > bHeight) {
			nodeA = parents[nodeA];
			aHeight--;
		}

		while (bHeight > aHeight) {
			nodeB = parents[nodeB];
			bHeight--;
		}

		// 두 노드의 높이를 같이 낮추면서, LCA를 찾는다.
		while (nodeA != nodeB) {
			nodeA = parents[nodeA];
			nodeB = parents[nodeB];
		}

		return nodeA;
	}
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
	private static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int cases = 1;
		StringBuilder answer = new StringBuilder();

		while (n != 0) {
			answer.append("Case ").append(cases++).append(": ");
			parents = new int[n + 1];
			for (int index = 0; index < n; index++) {
				parents[index] = index;
			}

			for (int index = 0; index < m; index++) {
				st = new StringTokenizer(br.readLine());

				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());

				int leftParent = findParent(left);
				int rightParent = findParent(right);
				// 이미 둘 중 한 노드가 싸이클에 속한다면 다른 노드 또한 싸이클 마킹
				if (leftParent == -1) {
					parents[right] = -1;
					markCycle(rightParent);
					continue;
				}
				if (rightParent == -1) {
					parents[left] = -1;
					markCycle(leftParent);
					continue;
				}
				// 이미 부모가 같다면 해당 부모 노드는 싸이클이 존재함
				if (leftParent == rightParent) {
					// 해당 부모 노드를 부모로 가지는 모든 노드의 parents 값을 -1로 갱신
					markCycle(leftParent);
					continue;
				}

				// 나머지는 연결
				union(left, right);
			}

			int treeCount = computeTree();

			if (treeCount == 0) {
				answer.append("No trees");
			}
			if (treeCount == 1) {
				answer.append("There is one tree");
			}
			if (treeCount > 1) {
				answer.append("A forest of ").append(treeCount).append(" trees");
			}

			answer.append(".\n");
			// 다시 입력받기
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
		}

		System.out.print(answer);
	}

	/**
	 * 인자로 주어지는 노드 값을 부모로 가지는 노드들에 싸이클임을 마킹
	 * */
	private static void markCycle(int parent) {
		for (int index = 0; index < parents.length; index++) {
			if (parents[index] == parent) {
				parents[index] = -1;
			}
		}
	}

	private static int findParent(int node) {
		if (node == -1 || parents[node] == -1) {
			return -1;
		}
		if (node == parents[node]) {
			return node;
		}

		return parents[node] = findParent(parents[node]);
	}

	private static void union(int left, int right) {
		int leftParent = parents[left];
		int rightParent = parents[right];

		if (leftParent < rightParent) {
			parents[right] = leftParent;
			// rightParent를 부모로 가지는 노드를 모두 찾아 leftParent를 부모로 가지도록 수정
			findAll(rightParent, leftParent);
		} else {
			parents[left] = rightParent;
			// leftParent를 부모로 가지는 노드를 모두 찾아 rightParent를 부모로 가지도록 수정
			findAll(leftParent, rightParent);
		}
	}

	/**
	 * findNode값을 부모 노드로 가지는 노드를 찾아 targetNode로 갱신
	 * 부모 노드가 갱신된 경우, 이를 부모로 가졌던 노드들 또한 부모 노드의 값이 갱신되어야 함
	 * */
	private static void findAll(int findNode, int targetNode) {
		for (int index = 1; index < parents.length; index++) {
			if (parents[index] == findNode) {
				parents[index] = targetNode;
			}
		}
	}

	/**
	 * 싸이클이 있는 노드들은 부모 노드값을 -1로 표시
	 * -1이 아닌 부모 노드 값들을 집합으로 저장하고 개수 카운트
	 * */
	private static int computeTree() {
		Set<Integer> parentNodes = Arrays.stream(parents).boxed().filter(parent -> parent != -1).collect(Collectors.toSet());
		return parentNodes.size();
	}
}
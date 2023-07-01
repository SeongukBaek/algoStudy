import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	private static List<List<Node>> roadInfo;
	private static int end;
	private static int money;
	private static int minMoney = 1001;

	static class Node {
		int to;
		int cost;

		Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int N = line[0];
		int M = line[1];

		int start = line[2] - 1;
		end = line[3] - 1;
		money = line[4];

		roadInfo = new ArrayList<>();
		for (int index = 0; index < N; index++) {
			roadInfo.add(new ArrayList<>());
		}

		for (int index = 0; index < M; index++) {
			line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			int from = line[0] - 1;
			int to = line[1] - 1;
			int cost = line[2];

			roadInfo.get(from).add(new Node(to, cost));
			roadInfo.get(to).add(new Node(from, cost));
		}

		for (Node next : roadInfo.get(start)) {
			boolean[] isVisited = new boolean[N];
			isVisited[start] = true;
			findPath(next, next.cost, 0, isVisited);
		}

		if (minMoney == 1001) {
			minMoney = -1;
		}
		System.out.println(minMoney);
	}

	private static void findPath(Node current, int allCost, int maxCost, boolean[] isVisited) {
		maxCost = Math.max(maxCost, current.cost);

		if (current.to == end) {
			if (allCost <= money) {
				minMoney = Math.min(minMoney, maxCost);
			}
			return;
		}

		isVisited[current.to] = true;
		for (Node next : roadInfo.get(current.to)) {
			if (isVisited[next.to]) {
				continue;
			}
			findPath(next, allCost + next.cost, maxCost, isVisited);
		}
		isVisited[current.to] = false;
	}
}
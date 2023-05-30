import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static final int MAX = 10000*1000;
	private static int N;
	private static int[] times;
	private static List<List<Node>> dependencyInfo;
	private static class Node {
		int index;
		int time;
		Node(int index, int time) {
			this.index = index;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder answer = new StringBuilder();
		for (int test = 0; test < T; test++) {
			String[] line = br.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			int D = Integer.parseInt(line[1]);
			int C = Integer.parseInt(line[2]);

			init();

			for (int index = 0; index < D; index++) {
				line = br.readLine().split(" ");
				int a = Integer.parseInt(line[0]) - 1;
				int b = Integer.parseInt(line[1]) - 1;
				int s = Integer.parseInt(line[2]);

				dependencyInfo.get(b).add(new Node(a, s));
			}

			hacking(C - 1);
			answer.append(countInfectedComputers()).append(" ").append(countTime()).append("\n");
		}

		System.out.print(answer);
	}

	private static void init() {
		dependencyInfo = new ArrayList<>();
		for (int index = 0; index < N; index++) {
			dependencyInfo.add(new ArrayList<>());
		}

		times = new int[N];
		Arrays.fill(times, MAX);
	}

	private static void hacking(int start) {
		Queue<Node> nodes = new PriorityQueue<>(Comparator.comparingInt(value -> value.time));
		nodes.add(new Node(start, 0));
		// start -> start = 0
		times[start] = 0;

		while (!nodes.isEmpty()) {
			Node current = nodes.poll();

			// 현재 번호
			int now = current.index;
			// 현재 번호까지 오는 시간
			int time = current.time;

			// 현재 번호로 다시 방문했는데, 이미 더 빠르게 왔다면 패스
			if (times[now] < time) {
				continue;
			}

			for (Node next : dependencyInfo.get(now)) {
				int nextTime = times[now] + next.time;

				if (times[next.index] <= nextTime) {
					continue;
				}

				times[next.index] = nextTime;
				nodes.add(new Node(next.index, nextTime));
			}
		}
	}

	private static int countInfectedComputers() {
		return (int) Arrays.stream(times).filter(value -> value != MAX).count();
	}

	private static int countTime() {
		return Arrays.stream(times).filter(value -> value != MAX).max().getAsInt();
	}
}
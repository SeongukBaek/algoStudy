import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static final int MAX = 1000*1000;
	private static int N;
	private static int X;
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
		String[] info = br.readLine().split(" ");
		N = Integer.parseInt(info[0]);
		int M = Integer.parseInt(info[1]);
		X = Integer.parseInt(info[2]) - 1;

		List<List<Node>> roadInfo = new ArrayList<>();
		List<List<Node>> reverseRoadInfo = new ArrayList<>();

		for (int index = 0; index < N; index++) {
			roadInfo.add(new ArrayList<>());
			reverseRoadInfo.add(new ArrayList<>());
		}

		for (int index = 0; index < M; index++) {
			info = br.readLine().split(" ");

			int a = Integer.parseInt(info[0]) - 1;
			int b = Integer.parseInt(info[1]) - 1;
			int s = Integer.parseInt(info[2]);

			roadInfo.get(a).add(new Node(b, s));
			reverseRoadInfo.get(b).add(new Node(a, s));
		}

		System.out.println(computeMaxTime(goParty(roadInfo), goParty(reverseRoadInfo)));
	}

	private static int[] goParty(List<List<Node>> roadInfo) {
		int[] times = new int[N];

		Arrays.fill(times, MAX);
		times[X] = 0;

		Queue<Node> nodes = new PriorityQueue<>(Comparator.comparingInt(value -> value.time));
		nodes.add(new Node(X, 0));

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

			for (Node next : roadInfo.get(now)) {
				int nextTime = times[now] + next.time;

				if (times[next.index] <= nextTime) {
					continue;
				}

				times[next.index] = nextTime;
				nodes.add(new Node(next.index, nextTime));
			}
		}

		return times;
	}

	private static int computeMaxTime(int[] fromParty, int[] toParty) {
		int maxTime = 0;
		for (int start = 0; start < N; start++) {
			maxTime = Math.max(maxTime, toParty[start] + fromParty[start]);
		}
		return maxTime;
	}
}
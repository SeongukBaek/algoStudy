import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int[] costs;
	private static int[] indegrees;
	private static List<List<Integer>> info;
	private static final Queue<Integer> todo = new ArrayDeque<>();

	// 3개의 무리가 있다면, 3개의 무리를 완료하는 시간 중 최대 시간을 출력
	public static void main(String[] args) throws IOException {
		init(new BufferedReader(new InputStreamReader(System.in)));

		System.out.print(computeMinTime());
	}

	private static void init(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
		costs = new int[N + 1];
		indegrees = new int[N + 1];

		info = new ArrayList<>();
		for (int index = 0; index <= N; index++) {
			info.add(new ArrayList<>());
		}

		for (int index = 1; index <= N; index++) {
			String[] line = br.readLine().split(" ");

			costs[index] = Integer.parseInt(line[0]);
			int count = Integer.parseInt(line[1]);

			indegrees[index] = count;
			// 해당 작업이 진행된 후 진행할 수 있는 작업을 연결
			for (int c = 0; c < count; c++) {
				info.get(Integer.parseInt(line[2 + c])).add(index);
			}
		}
	}

	private static int computeMinTime() {
		int[] result = costs.clone();

		for (int index = 1; index <= N; index++) {
			// indegree가 0인 작업을 큐에 넣음.
			if (indegrees[index] == 0) {
				todo.add(index);
			}
		}

		while (!todo.isEmpty()) {
			int now = todo.poll();

			for (int next : info.get(now)) {
				indegrees[next]--;

				result[next] = Math.max(result[next], result[now] + costs[next]);

				// indegree가 0이 된 작업을 큐에 넣음.
				if (indegrees[next] == 0) {
					todo.add(next);
				}
			}
		}

		return Arrays.stream(result).max().getAsInt();
	}
}
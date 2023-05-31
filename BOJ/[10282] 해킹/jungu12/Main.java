import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {
	static int N, D, C;
	static List<int[]>[] dependency;
	static int[] hackedTime;
	static int time, count;

	public static void main(String[] args) throws IOException {
		init();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken()) - 1;

			dependency = new List[N];
			hackedTime = new int[N];

			for (int i = 0; i < N; i++) {
				dependency[i] = new ArrayList<>();
			}

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken()) - 1;
				int B = Integer.parseInt(st.nextToken()) - 1;
				int S = Integer.parseInt(st.nextToken());

				dependency[B].add(new int[] { A, S });
			}

			for (int i = 0; i < N; i++) {
				Arrays.fill(hackedTime, Integer.MAX_VALUE);
			}
			hackedTime[C] = 0;

			startHacking();
			findAnswer();
			System.out.println(count + " " + time);
		}
	}

	private static void startHacking() {
		Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
			return o1[1] - o2[1];
		});
		pq.add(new int[] { C, 0 });
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			for (int i = 0; i < dependency[cur[0]].size(); i++) {
				int[] destination = dependency[cur[0]].get(i);
				if (cur[1] + destination[1] < hackedTime[destination[0]]) {
					hackedTime[destination[0]] = cur[1] + destination[1];
					pq.add(new int[] { destination[0], hackedTime[destination[0]] });
				}
			}
		}
	}

	private static void findAnswer() {
		time = 0;
		count = 0;
		for (int i = 0; i < N; i++) {
			if (hackedTime[i] != Integer.MAX_VALUE) {
				count++;
			}

			if (hackedTime[i] > time && hackedTime[i] != Integer.MAX_VALUE) {
				time = hackedTime[i];
			}
		}
	}
}
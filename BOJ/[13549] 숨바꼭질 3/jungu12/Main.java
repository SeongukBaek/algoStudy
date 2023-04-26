import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int[] dot = new int[100001];
	static int N, K;

	static class Subin implements Comparable<Subin> {
		int pos;
		int time;

		Subin(int pos, int time) {
			this.pos = pos;
			this.time = time;
		}

		@Override
		public int compareTo(Subin o) {
			if (this.time != o.time) {
				return this.time - o.time;
			}
			return this.pos - o.pos;
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(findBrother());
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Arrays.fill(dot, Integer.MAX_VALUE);
	}

	private static int findBrother() {
		PriorityQueue<Subin> queue = new PriorityQueue<>();
		queue.add(new Subin(N, 0));
		dot[N] = 0;

        //수빈이의 초기 위치와 동생의 초기 위치가 같은 경우
		if (N == K) {
			return 0;
		}

		while (!queue.isEmpty()) {
			Subin cur = queue.poll();

            //순간이동하여 동생을 찾을 수 있는 경우
			if (cur.pos * 2 == K) {
				return cur.time;
			}

            //앞 or 뒤로 이동하여 동생을 찾을 수 있는 경우
			if (cur.pos + 1 == K || cur.pos - 1 == K) {
				return cur.time + 1;
			}

            //순간이동하여 이동 한 곳을 queue에 저장
			if (cur.pos * 2 <= 100000 && dot[cur.pos * 2] > cur.time) {
				queue.add(new Subin(cur.pos * 2, cur.time));
				dot[cur.pos * 2] = cur.time;
			}

            //앞으로 이동 한 곳을 queue에 저장
			if (cur.pos + 1 <= 100000 && dot[cur.pos + 1] > cur.time + 1) {
				queue.add(new Subin(cur.pos + 1, cur.time + 1));
				dot[cur.pos + 1] = cur.time + 1;
			}

            //뒤로 이동 한 곳을 queue에 저장
			if (cur.pos - 1 >= 0 && dot[cur.pos - 1] > cur.time + 1) {
				queue.add(new Subin(cur.pos - 1, cur.time + 1));
				dot[cur.pos - 1] = cur.time + 1;
			}
		}
		return -1;
	}
}
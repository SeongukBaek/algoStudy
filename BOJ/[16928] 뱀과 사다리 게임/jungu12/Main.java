import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] board;
	static boolean[] visited;
	static int N, M;

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(findMinRollCnt());
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[100];
		visited = new boolean[100];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			board[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			board[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
	}

	private static int findMinRollCnt() {
		Queue<Integer> queue = new LinkedList<>();
		// 시작점은 0이 아닌 1이다.
		queue.add(1);
		// 주사위를 굴린 횟수
		int rollCnt = -1;

		while (!queue.isEmpty()) {
			int queueSize = queue.size();
			rollCnt++;

			for (int i = 0; i < queueSize; i++) {
				int cur = queue.poll();
				int nextBlank = 0;

				for (int j = 1; j <= 6; j++) {
					if (cur + j == 100) {
						return rollCnt + 1;
					}

					if (visited[cur + j]) {
						continue;
					}

                    //뱀과 사다리가 있는 block인 경우
					if (board[cur + j] != 0) {
						queue.add(board[cur + j]);
						visited[cur + j] = true;
						continue;
					}
					nextBlank = j;
				}

                //주사위를 굴려서 갈 수 있는 빈칸이 있는 경우
				if (nextBlank != 0) {
					queue.add(cur + nextBlank);
					visited[nextBlank] = true;
				}
			}
		}
		return rollCnt;
	}
}
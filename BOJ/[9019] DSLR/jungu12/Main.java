import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int A, B;
	static Queue<Integer> queue;
	static boolean[] visited;
	static String[] command;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			queue = new LinkedList<>();
			visited = new boolean[10000];
			command = new String[10000];

			findAnswer(A, B);
			System.out.println(command[B]);
		}
	}

	private static void findAnswer(int A, int B) {
		queue.add(A);
		visited[A] = true;
		command[A] = "";
		
		while (!visited[B]) {
			int cur = queue.poll();

			int D = cur * 2 % 10000;
			int S = cur == 0 ? 9999 : cur - 1;
			int L = cur % 1000 * 10 + cur / 1000;
			int R = cur % 10 * 1000 + cur / 10;

			if (!visited[D]) {
				queue.add(D);
				visited[D] = true;
				command[D] = command[cur] + "D";
			}

			if (!visited[S]) {
				queue.add(S);
				visited[S] = true;
				command[S] = command[cur] + "S";
			}

			if (!visited[L]) {
				queue.add(L);
				visited[L] = true;
				command[L] = command[cur] + "L";
			}

			if (!visited[R]) {
				queue.add(R);
				visited[R] = true;
				command[R] = command[cur] + "R";
			}
		}
	}
}
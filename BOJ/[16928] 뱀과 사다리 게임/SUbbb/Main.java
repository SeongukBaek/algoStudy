import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	private static int[] cuts;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		cuts = new int[101];

		for (int index = 0; index < N + M; index++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			cuts[start] = end;
		}

		System.out.println(move());
	}

	/**
	 * 주사위를 한 번씩 던지면서 100까지 도달하는 최소 주사위 개수를 반환
	 * */
	private static int move() {
		int diceCount = 0;
		int current = 1;
		Deque<Integer> cannes = new ArrayDeque<>();
		// 100번째 칸에 대한 방문 여부는 저장할 필요 없음
		boolean[] isVisited = new boolean[100];

		cannes.add(current);
		isVisited[current] = true;

		while (!cannes.isEmpty()) {
			int size = cannes.size();

			for (int count = 0; count < size; count++) {
				current = cannes.poll();

				for (int dice = 1; dice <= 6; dice++) {
					// 이동할 칸
					int next = current + dice;

					if (next == 100) {
						return diceCount + 1;
					}

					if (next > 100 || isVisited[next]) {
						continue;
					}

					isVisited[next] = true;

					// 해당 칸의 뱀이나 사다리 확인
					int nextCan = cuts[next];
					if (nextCan == 100) {
						return diceCount + 1;
					}

					if (isVisited[nextCan]) {
						continue;
					}

					// 사다리나 뱀이 없다면 주사위로 이동할 칸으로 할당
					if (nextCan == 0) {
						nextCan = next;
					}

					isVisited[nextCan] = true;
					cannes.add(nextCan);
				}
			}

			diceCount++;
		}

		return diceCount;
	}
}
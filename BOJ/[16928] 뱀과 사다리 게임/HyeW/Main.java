import java.io.*;
import java.util.*;

public class Main {
	static final int MAP_SIZE = 100;
	static final int DICE = 6;
	static boolean[] visited;
	static int[] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		map = new int[MAP_SIZE];
		visited = new boolean[MAP_SIZE];

		// 사다리 정보 얻기
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken()) - 1] = Integer.parseInt(st.nextToken()) - 1;

		}
		// 뱀 정보 얻기
		for (int j = 0; j < m; j++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken()) - 1] = Integer.parseInt(st.nextToken()) - 1;
		}

		System.out.println(getMinCount());
	}

	private static int getMinCount() {
		Deque<Integer> blocks = new ArrayDeque<>();
		int diceCount = 0;
		visited[0] = true;
		blocks.add(0);

		while (!blocks.isEmpty()) {
			diceCount++;
			int parent = blocks.size();

			while (parent-- > 0) {
				int cur = blocks.poll();
				for (int i = 1; i <= DICE; i++) {
					int nxt = cur + i;
					// map밖으로 나갔을 때 패스
					if (nxt > MAP_SIZE - 1) {
						continue;
					}
					// 이미 방문한 곳이면 패스
					if (visited[nxt]) {
						continue;
					}
					// 목적지에 도착하면 주사위 던진 수 반환
					if (nxt == MAP_SIZE - 1) {
						return diceCount;
					}

					visited[nxt] = true;
					// 뱀이나 사다리를 가는 경우
					if (map[nxt] != 0) {
						if (visited[map[nxt]]) {
							continue;
						}
						blocks.add(map[nxt]);
						visited[map[nxt]] = true;
					}else { // 뱀이나 사다리가 없는 경우
						blocks.add(nxt);
					}
				}
			}
		}
		return diceCount;
	}

}

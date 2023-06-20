import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int map[][];
	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { -1, 1, 0, 0 };
	static int group[][];
	static HashMap<Integer, Integer> groupInfo = new HashMap<>();

	public static void main(String[] args) throws IOException {
		init();
		findGroup();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(mapCount(i, j));
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		group = new int[N][M];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
	}

	private static void findGroup() {
		int index = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0 && group[i][j] == 0) {
					groupInfo.put(index, makeGroup(i, j, index));
					index++;
				}
			}
		}
	}

	private static int mapCount(int i, int j) {
		int sum = 1;
		HashSet<Integer> adjacentGroup = new HashSet<>();

		if (map[i][j] == 0) {
			return 0;
		}

		for (int dir = 0; dir < 4; dir++) {
			int nx = i + dx[dir];
			int ny = j + dy[dir];

			if (!isIn(nx, ny)) {
				continue;
			}

			if (map[nx][ny] == 0) {
				adjacentGroup.add(group[nx][ny]);
			}
		}

		for (int groupIdx : adjacentGroup) {
			sum += groupInfo.get(groupIdx);
		}

		return sum % 10;
	}

	private static int makeGroup(int i, int j, int groupIdx) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { i, j });
		int count = 1;
		group[i][j] = groupIdx;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int dir = 0; dir < 4; dir++) {
				int nx = cur[0] + dx[dir];
				int ny = cur[1] + dy[dir];

				if (!isIn(nx, ny)) {
					continue;
				}

				if (map[nx][ny] == 0 && group[nx][ny] == 0) {
					queue.add(new int[] { nx, ny });
					group[nx][ny] = groupIdx;
					count++;
				}
			}
		}
		return count;
	}
	
	private static boolean isIn(int nx, int ny) {
		return nx >= 0 && nx < N && ny >= 0 && ny < M;
	}
}

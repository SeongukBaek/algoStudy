import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K, maxDepth;
	static HashMap<String, Integer> map = new HashMap<>();
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 }; // 시계방향
	static char[][] world;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		String[] likeStrings = new String[K];
		world = new char[N][M];
		map = new HashMap<>();
		maxDepth = 0;

		for (int i = 0; i < N; i++) {
			world[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < K; i++) {
			String likeString = br.readLine();
			maxDepth = Math.max(maxDepth, likeString.length());
			map.put(likeString, 0);
			likeStrings[i] = likeString;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				findNumberOfCases(i, j, 1, Character.toString(world[i][j]));
			}
		}

		for (String key : likeStrings) {
			System.out.println(map.get(key));
		}
	}
	
	public static void findNumberOfCases(int x, int y, int depth, String result) {
		if (map.containsKey(result)) {
			map.put(result, map.get(result) + 1);
		}

		if (depth == maxDepth) {
			return;
		}

		for (int dir = 0; dir < dx.length; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			if (nx < 0) {
				nx = N - 1;
			}
			if (nx >= N) {
				nx = 0;
			}
			if (ny < 0) {
				ny = M - 1;
			}
			if (ny >= M) {
				ny = 0;
			}

			findNumberOfCases(nx, ny, depth + 1, result + world[nx][ny]);
		}
	}
}
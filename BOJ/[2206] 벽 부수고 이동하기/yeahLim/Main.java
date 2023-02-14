import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[][] map;
	static int[][] visited;

	static class Point {
		int x;
		int y;
		int step; // 이동 개수
		int smash; // 벽을 부신 횟수

		public Point(int x, int y, int step, int smash) {
			this.x = x;
			this.y = y;
			this.step = step;
			this.smash = smash;
		}
	}

	public static void main(String[] args) throws IOException {

		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new int[n][m];
		for (int i = 0; i < n; i++) {
			char[] chars = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				map[i][j] = chars[j] - '0';
				visited[i][j] = Integer.MAX_VALUE;
			}
		}

		/* BFS로 최단 경로 찾고 출력 */		
		System.out.println(searchRoute());

	}

	static int searchRoute() {
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(0, 0, 1, 0));
		visited[0][0] = 0;
		
		while (!queue.isEmpty()) {
			Point point = queue.poll();

			/* 도착하면 이동 개수 반환 */
			if (point.x == n - 1 && point.y == m - 1) {
				return point.step;
			}

			for (int k = 0; k < 4; k++) {
				int i = point.x + dx[k];
				int j = point.y + dy[k];

				/* i, j가 범위 내이고, 방문 안 한 경우 */
				if (0 <= i && i < n && 0 <= j && j < m && visited[i][j] > point.smash) {

					/* 벽 아닐 때 */
					if (map[i][j] == 0) {
						queue.add(new Point(i, j, point.step + 1, point.smash));
						visited[i][j] = point.smash; // 벽 한번 부셨으면 1, 아니면 0 넣어주기	

					/* 벽이고, 벽을 부순 횟수가 0일 때 */
					} else if (point.smash == 0) {
						queue.add(new Point(i, j, point.step + 1, point.smash + 1));
						visited[i][j] = point.smash; // 부셨으니까 1 넣어주기
					}
				}
			}
		}
		
		/* 도착 못하면 -1 반환 */
		return -1;
	}
}
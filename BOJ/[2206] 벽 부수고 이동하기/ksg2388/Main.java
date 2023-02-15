import java.util.*;
import java.io.*;

class Place {
	int x;
	int y;
	int dis; // 이동 거리
	int breakCnt = 0; // 벽을 부순 횟수

	Place(int x, int y, int dis, int breakCnt) {
		this.x = x;
		this.y = y;
		this.dis = dis;
		this.breakCnt = breakCnt;
	}
}

public class Main {
	private static int n, m;
	private static int[] dx = { 1, -1, 0, 0 };
	private static int[] dy = { 0, 0, 1, -1 };
	private static final int MAX_VALUE = Integer.MAX_VALUE;
	private static int minDist = MAX_VALUE;
	private static int[][] map, visited;

	private static Queue<Place> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new int[n][m];

		// 맵 초기화
		for (int i = 0; i < n; ++i) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j) - '0';
				visited[i][j] = Integer.MAX_VALUE;
			}
		}

		move(0, 0);

		if (minDist == MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(minDist);
		}
	}

	// bfs
	private static void move(int x, int y) {
		// (0, 0)에서 시작
		queue.add(new Place(x, y, 1, 0));
		visited[x][y] = 0;

		while (!queue.isEmpty()) {
			Place p = queue.poll();

			if (p.x == n - 1 && p.y == m - 1) {
				if (minDist > p.dis) {
					minDist = p.dis;
				}
			}

			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
					continue;
				}
				// 벽이 아닌 경우
				if (map[nx][ny] == 0 && visited[nx][ny] > p.breakCnt) {
					queue.add(new Place(nx, ny, p.dis + 1, p.breakCnt));
					visited[nx][ny] = p.breakCnt;
					continue;
				}
				// 벽인 경우 중 벽을 부술 수 있는 횟수가 남아있는 경우
				if (p.breakCnt < 1 && visited[nx][ny] > p.breakCnt) {
					queue.add(new Place(nx, ny, p.dis + 1, p.breakCnt + 1));
					visited[nx][ny] = 1;
				}

			}
		}
	}
}


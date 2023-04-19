import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] sea;
	static Position shark;
	static int eatenFish = 0;
	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		sea = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int cur = Integer.parseInt(st.nextToken());
				if (cur == 9) {
					shark = new Position(i, j, 2);
					cur = 0;
				}
				sea[i][j] = cur;
			}
		}

		int time = 0;
		int totalTime = 0;
		while (time != -1) {
			totalTime += time;
			time = eatFish();
		}

		System.out.println(totalTime);
	}

	private static int eatFish() {
		Queue<Position> path = new PriorityQueue<>();
		boolean[][] visited = new boolean[N][N];
		// 상어 위치에서 시작
		visited[shark.x][shark.y] = true;
		path.add(new Position(shark.x, shark.y, 0));

		while (!path.isEmpty()) {
			Position cur = path.poll();
			// 물고기를 잡아 먹을 수 있을 경우
			if (sea[cur.x][cur.y] < shark.w && sea[cur.x][cur.y] > 0) {
				eatenFish++;
				// 먹은 물고기 수가 자신의 크기와 같으면 상어 크기 커진다.
				if (shark.w == eatenFish) {
					shark.w++;
					eatenFish = 0;
				}
				shark.x = cur.x;
				shark.y = cur.y;
				sea[cur.x][cur.y] = 0;
				return cur.w;
			}

			for (int i = 0; i < 4; i++) {
				int dx = cur.x + dr[i];
				int dy = cur.y + dc[i];

				if (isMapOut(dx, dy)) {
					continue;
				}
				// 방문을 했거나 물고기가 상어보다 클때 
				if (visited[dx][dy] || sea[dx][dy] > shark.w) {
					continue;
				}

				// 상어가 해당 좌표로 이동할 수 있는 경우
				path.add(new Position(dx, dy, cur.w + 1));
				visited[dx][dy] = true;

			}
		}
		return -1;
	}

	private static boolean isMapOut(int x, int y) {
		return x >= N || y >= N || x < 0 || y < 0;
	}

	static class Position implements Comparable<Position> {
		int x;
		int y;
		int w; // 상어는 크기, 좌표는 거리

		Position(int x, int y, int w) {
			this.x = x;
			this.y = y;
			this.w = w;
		}

		@Override
		public int compareTo(Position o) {
			if (this.w == o.w) {
				if (this.x == o.x) {
					return this.y - o.y;
				}
				return this.x - o.x;
			}
			return this.w - o.w;
		}
	}
}

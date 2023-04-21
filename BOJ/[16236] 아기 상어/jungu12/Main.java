import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] map;
	static int answer;

	static boolean[][] visited;
	static Queue<Position> queue = new LinkedList<>();
	static PriorityQueue<Position> pq = new PriorityQueue<>();
	static int sharkX, sharkY;
	static int sharkSize = 2; 
	static int eatCnt;

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	static class Position implements Comparable<Position> {
		int x;
		int y;
		int distance;

		Position(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}

		public int compareTo(Position pos) {
			if (this.distance != pos.distance)
				return this.distance - pos.distance;

			if (this.x != pos.x)
				return this.x - pos.x;

			return this.y - pos.y;
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		moveShark();
		System.out.println(answer);
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					sharkX = i;
					sharkY = j;
				}
			}
		}
	}
	
	private static void moveShark() {
		while (true) {
			visited = new boolean[N][N];
			queue.clear();
			pq.clear();

			visited[sharkX][sharkY] = true;
			queue.add(new Position(sharkX, sharkY, 0));
			findFish();

			if (pq.isEmpty()) {
				return;
			}

			Position fish = pq.poll();
			
			map[sharkX][sharkY] = 0;
			map[fish.x][fish.y] = 9;
			
			sharkX = fish.x;
			sharkY = fish.y;
			eatCnt++;
			

			if (eatCnt == sharkSize) {
				sharkSize++;
				eatCnt = 0;
			}

			answer += fish.distance;
		}
	}

	private static void findFish() {
		while (!queue.isEmpty()) {
			Position current = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];

				if (!isIn(nx, ny)) {
					continue;
				}

				if (!visited[nx][ny] && sharkSize >= map[nx][ny]) {
					Position nextPos = new Position(nx, ny, current.distance + 1);
					visited[nx][ny] = true;
					queue.add(nextPos);

					if (map[nx][ny] != 0 && sharkSize > map[nx][ny]) {
						pq.add(nextPos);
					}
				}
			}
		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
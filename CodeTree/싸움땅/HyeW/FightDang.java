import java.io.*;
import java.util.*;

public class FightDang {
	static int n, m, k; // 격자크기, 플레이어 수, 라운드 수
	static PriorityQueue<Integer>[][] ground;
	static List<Player> players;
	static int[][] visited;
	static int[] dr = { -1, 0, 1, 0 }; // 상우하좌
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		init();
		
		for (int i = 0; i < k; i++) {
			runGame();
		}

		for (Player p : players) {
			System.out.print(p.score + " ");
		}
	}

	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		visited = new int[n][n];

		ground = new PriorityQueue[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ground[i][j] = new PriorityQueue<>(Comparator.reverseOrder());
			}
		}

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				ground[i][j].add(Integer.parseInt(st.nextToken()));
			}
		}

		players = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			players.add(new Player(x, y, d, s));
			players.get(i).gun = ground[x][y].poll();
			ground[x][y].add(0);
			visited[x][y] = i + 1;
		}
	}

	static void runGame() {

		for (int i = 0; i < m; i++) {
			Player cur = players.get(i);
			int dx = cur.x + dr[cur.d];
			int dy = cur.y + dc[cur.d];

			// 이전 좌표 지움
			visited[cur.x][cur.y] = 0;

			// 벽만났을때
			if (!arrayBoundsValidation(dx, dy)) {
				cur.d = turn180(cur.d);
				dx = cur.x + dr[cur.d];
				dy = cur.y + dc[cur.d];
			}

			// 플레이어 만났을때
			if (visited[dx][dy] != 0) {
				// 1. 싸운다.
				int winner = getWinner(visited[dx][dy] - 1, i);
				int loser = getLoser(winner, visited[dx][dy] - 1, i);
				
				// 2-1. 이긴 사람 제자리
				visited[dx][dy] = winner + 1;
				players.get(winner).x = dx;
				players.get(winner).y = dy;
				
				// 2-2. 진 사람 이동 (진 사람은 총 떨어트림)
				players.get(loser).x = dx;
				players.get(loser).y = dy;
				dropGun(players.get(loser));
				players.get(loser).gun = 0;
				moveLoser(players.get(loser), loser);
				
				// 3. 각자 총 줍기
				getGun(players.get(loser));
				
				dropGun(players.get(winner));
				getGun(players.get(winner));

			} else {// 플레이어를 만나지 않았을 때 총 그냥 주움
				visited[dx][dy] = i + 1;
				players.get(i).x = dx;
				players.get(i).y = dy;
				dropGun(players.get(i));
				getGun(players.get(i));
			}

		}
	}
    
	static void dropGun(Player player) {
		
		if (player.gun == 0 && ground[player.x][player.y].size() > 0) {
			return;
		}
		
		ground[player.x][player.y].add(player.gun);
		player.gun = 0;
	}

	static void getGun(Player player) {

		if (player.gun >= ground[player.x][player.y].peek()) {
			return;
		}

		player.gun = ground[player.x][player.y].poll();
		if(ground[player.x][player.y].size() == 0) {
			ground[player.x][player.y].add(0);
		}
	}

	static void moveLoser(Player loser, int num) {
		int turnCnt = 0;
		while (turnCnt++ < 4) {
			int dx = loser.x + dr[loser.d];
			int dy = loser.y + dc[loser.d];

			if (!arrayBoundsValidation(dx, dy)) {
				loser.d = turnRight90(loser.d);
				continue;
			}
			if (visited[dx][dy] != 0) {
				loser.d = turnRight90(loser.d);
				continue;
			}

			visited[dx][dy] = num + 1;
			players.get(num).x = dx;
			players.get(num).y = dy;
			players.get(num).d = loser.d;
			return;
		}
	}

	//위너를 찾고 위너 점수까지 계산
	static int getWinner(int p1, int p2) {
		int winner = -1;
		int loser = -1;
		int p1Force = players.get(p1).gun + players.get(p1).s;
		int p2Force = players.get(p2).gun + players.get(p2).s;

		if (p1Force == p2Force) {
			winner = (players.get(p1).s > players.get(p2).s) ? p1 : p2;
		} else if (p1Force > p2Force) {
			winner = p1;
		} else {
			winner = p2;
		}
		loser = getLoser(winner, p1, p2);

		players.get(winner).score += players.get(winner).s + players.get(winner).gun
				- (players.get(loser).s + players.get(loser).gun);

		return winner;
	}

	static boolean arrayBoundsValidation(int x, int y) {
		return (x >= 0 && y >= 0 && x < n && y < n);
	}

	static int turn180(int d) {
		if (d < 2) {
			return d += 2;
		} else {
			return d -= 2;
		}
	}

	static int turnRight90(int d) {
		if (d == 3) {
			return 0;
		}
		return d + 1;
	}

	static int getLoser(int winner, int p1, int p2) {
		if (p1 == winner) {
			return p2;
		}
		return p1;
	}

	static class Player {
		int x;
		int y;
		int d; // 방향
		int s; // 초기능력치
		int gun = 0;
		int score = 0;

		public Player(int x, int y, int d, int s) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.s = s;
		}
	}
}
import java.io.*;
import java.util.*;

public class Main {
	static class Player {
		int x;
		int y;
		int d; // 플레이어가 보고 있는 방향
		int s; // 플레이어의 초기 능력치
		int weaponPower; // 무기 공격력
		int point;

		Player(int x, int y, int d, int s) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.s = s;
		}
	}

	static int n, m, k; // 격자의 크기, 플레이어의 수, 라운드 수
	static int[] dx = { -1, 0, 1, 0 };	// 상 우 하 좌
	static int[] dy = { 0, 1, 0, -1 };
	static Queue<Integer>[][] map; // 무기를 공격력이 쎈 순서대로 정렬해두기위해 우선순위 큐 사용
	static List<Player> players = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new PriorityQueue[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int wp = Integer.parseInt(st.nextToken());
				map[i][j] = new PriorityQueue<Integer>(Collections.reverseOrder());
				if (wp > 0) {
					map[i][j].add(wp);
				}
			}
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			players.add(new Player(x, y, d, s));
		}

		gameStart(k);
	}

	// 라운드의 수만큼 게임 진행
	public static void gameStart(int k) {
//		for (Player p: players) {
//			System.out.println("초기 위치 : (" + p.x + ", " + p.y + ")  방향 : " + p.d + "  무기 : " + p.weaponPower);
//		}
//		System.out.println();
		for (int i = 0; i < k; i++) {
			roundRun();
//			for (Player p: players) {
//				System.out.println("현재 위치 : (" + p.x + ", " + p.y + ")  방향 : " + p.d + "  무기 : " + p.weaponPower);
//			}
//			pirntPoint();
//			System.out.println();
		}
		pirntPoint();
	}
	
	public static void pirntPoint() {
		for (Player p: players) {
			System.out.print(p.point + " ");
		}
	}

	// 한 라운드 진행
	public static void roundRun() {
		for (int i = 0; i < m; i++) {
			Player curPlayer = players.get(i);
			// 현재 플레이어가 벽을 보고 있는 경우
			if (playerSeeWall(curPlayer.x, curPlayer.y, curPlayer.d)) {
				curPlayer.d = changeDirection(curPlayer.d);
			}
			// 보고 있는 방향에 적이 있나 확인한 이동
			int nx = curPlayer.x + dx[curPlayer.d];
			int ny = curPlayer.y + dy[curPlayer.d];
			int enemy = checkPlayer(nx, ny);
			
			curPlayer.x = nx;
			curPlayer.y = ny;

			// 이동한 방향에 플레이어가 없는 경우
			if (enemy == -1) {
				// 이동한 방향에 총이 있는 경우
				if (!map[curPlayer.x][curPlayer.y].isEmpty()) {
					curPlayer.weaponPower = compare(curPlayer);
				}
			}
			// 이동한 방향에 플레이어가 있는 경우
			else {
				fight(i, enemy);
			} 
		}
	}
	
	/**
	 * 자신이 들고 있는 무기와 현재 위치에 떨어진 무기를 비교하여 공격력이 더 쌘 무기를 가짐
	 * @param p
	 * @return 
	 */
	public static int compare(Player p) {
		// 무기를 들고 있지 않은 경우
		if (p.weaponPower == 0) {
			return map[p.x][p.y].poll();
		}
		map[p.x][p.y].add(p.weaponPower);
		return map[p.x][p.y].poll();
	}

	public static void fight(int p, int enemy) {
		Player player = players.get(p);
		Player enemyPlayer = players.get(enemy);
		int p1Power = player.s + player.weaponPower;
		int p2Power = enemyPlayer.s + enemyPlayer.weaponPower;
//		System.out.println(p + " vs " + enemy + "승자는 : " + (p1Power > p2Power ? "p1" : "p2"));

		if (p1Power > p2Power) {
			macthEnd(p, enemy);
		}
		else if (p1Power < p2Power) {
			macthEnd(enemy, p);
		}
		// 공격력이 같은 경우 원래 플레이어의 스텟으로 비교
		else if (p1Power == p2Power) {
			if (player.s > enemyPlayer.s) {
				macthEnd(p, enemy);
			}
			else if (player.s < enemyPlayer.s) {
				macthEnd(enemy, p);
			}
		}
	}
	
	/**
	 * 싸우고 난 뒤의 결과 처리
	 **/
	public static void macthEnd(int winner, int loser) {
		Player winnerPlayer = players.get(winner);
		Player loserPlayer = players.get(loser);
		// 1. 이긴 사람은 점수 획득
		winnerPlayer.point += win(winnerPlayer, loserPlayer);
		// 2. 진 플레이어는 총을 내려놓고 이동한 후 그 위치의 총을 비교하여 높은 공격력의 총을 획득한다.
		loserPlayer = lose(loserPlayer);
		// 3. 이긴 플레이어는 승리한 칸에 총이 있는 경우 총들과 자신의 총을 비교해 높은 공격력의 총을 가진다.
		if (!map[winnerPlayer.x][winnerPlayer.y].isEmpty()) {
			winnerPlayer.weaponPower = compare(winnerPlayer);
		}
		
		players.set(winner, winnerPlayer);
		players.set(loser, loserPlayer);
	}
	/**
	 * 포인트를 얻는다.
	 * 얻을 포인트를 반환한다.
	 **/
	public static int win(Player winner, Player loser) {
		int point = (winner.s + winner.weaponPower) - (loser.s + loser.weaponPower);

		return point;
	}

	/**
	 * 본인이 가지고 있는 총을 해당 위치에 내려놓고, 이동한다
	 **/
	public static Player lose(Player loser) {
		// 총을 가지고 있는 경우
		if (loser.weaponPower != 0) {
			map[loser.x][loser.y].add(loser.weaponPower);
			loser.weaponPower = 0;
		}
		
		while (true) {
			int nx = loser.x + dx[loser.d];
			int ny = loser.y + dy[loser.d];
			// 이동하려는 칸이 맵 안이고 플레이어가 없는 경우
			if (playerInMap(nx, ny) && checkPlayer(nx, ny) == -1) {
				loser.x = nx;
				loser.y = ny;
				break;
			}
			
			loser.d += 1;
			if (loser.d == 4) {
				loser.d = 0;
			}
		}
		// 이동한 방향에 총이 있는 경우
		if (!map[loser.x][loser.y].isEmpty()) {
			loser.weaponPower = compare(loser);
		}
		
		return loser;
	}

	/**
	 * 적이 있는 경우 적의 인덱스 반환, 아닌 경우 -1 반환
	 **/
	public static int checkPlayer(int x, int y) {
		for (int i = 0; i < m; i++) {
			Player p = players.get(i);
			if (p.x == x && p.y == y) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 플레이어가 맵 안에 있는지 확인하고 있는 경우 true, 없는 경우 false 반환
	 **/
	public static boolean playerInMap(int x, int y) {
		return x >= 0 && y >= 0 && x < n && y < n;
	}
	
	/**
	 * 플레이어가 벽을 보고 있는 경우 true, 아닌 경우 false 반환
	 **/
	public static boolean playerSeeWall(int x, int y, int d) {
		int nx = x + dx[d];
		int ny = y + dy[d];

		if (!playerInMap(nx, ny)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 현재 보고 있는 방향의 반대편 방향으로 돌아서기
	 **/
	public static int changeDirection(int d) {
		if (d < 2) {
			return d + 2;
		}
		return d - 2;
	}
}
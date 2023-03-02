import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, k;
	static List<Player> players = new ArrayList<>();
	static PriorityQueue[][] map;
	static int[][] mapForPlayer;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static class Player {
		int num;
		int x;
		int y;
		int dir;
		int initAbility;
		int gunAbility;
		int score;

		Player(int num, int x, int y, int dir, int initAbility) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.initAbility = initAbility;
		}
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new PriorityQueue[n][n];
		//player의 위치를 저장하는 배열
		mapForPlayer = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = new PriorityQueue<>(Collections.reverseOrder());
				int ability = Integer.parseInt(st.nextToken());
				if (ability != 0)
					map[i][j].add(ability);
			}
		}
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			mapForPlayer[x][y] = i;
			players.add(new Player(i, x, y, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
	}

	static void move(Player player) {
		mapForPlayer[player.x][player.y] = 0;
		int nx = player.x + dx[player.dir];
		int ny = player.y + dy[player.dir];
		// 움직이려는 방향이 map 밖이면 진행방향 정반대로 변경
		if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
			player.dir = (player.dir + 2) % 4;
			nx = player.x + dx[player.dir];
			ny = player.y + dy[player.dir];
		}
		// 이동
		player.x = nx;
		player.y = ny;
		// 이동하려는 곳에 다른 player가 있는 경우
		if (mapForPlayer[nx][ny] != 0) {
			Player vsPlayer = players.get(mapForPlayer[nx][ny] - 1);
			Player loser = null;
			Player winner = null;
			if (player.gunAbility + player.initAbility > vsPlayer.gunAbility + vsPlayer.initAbility) {
				winner = player;
				loser = vsPlayer;
			}
			if (player.gunAbility + player.initAbility < vsPlayer.gunAbility + vsPlayer.initAbility) {
				winner = vsPlayer;
				loser = player;
			}
			if (player.gunAbility + player.initAbility == vsPlayer.gunAbility + vsPlayer.initAbility) {
				if (player.initAbility > vsPlayer.initAbility) {
					winner = player;
					loser = vsPlayer;
				}
				if (player.initAbility < vsPlayer.initAbility) {
					winner = vsPlayer;
					loser = player;
				}
			}
			doTasksAfterFight(winner, loser);
		}
		// 이동하려는 곳에 다른 player가 없는 경우
		else {
			mapForPlayer[nx][ny] = player.num;
			if (!map[nx][ny].isEmpty() && (int) map[nx][ny].peek() > player.gunAbility) {
				if (player.gunAbility != 0) {
					map[nx][ny].add(player.gunAbility);
				}
				player.gunAbility = (int) map[nx][ny].poll();
			}
		}
	}

	static void doTasksAfterFight(Player winner, Player loser) {
		// 이긴 player의 점수 갱신
		winner.score += winner.gunAbility + winner.initAbility - loser.gunAbility - loser.initAbility;
		//진 player는 총을 해당 격자에 내려 놓음
		if (loser.gunAbility != 0) {
			map[loser.x][loser.y].add(loser.gunAbility);
			loser.gunAbility = 0;
		}
		//진 player가 이동할 곳을 찾음
		int loserX = loser.x + dx[loser.dir];
		int loserY = loser.y + dy[loser.dir];
		while (loserX < 0 || loserX >= n || loserY < 0 || loserY >= n || mapForPlayer[loserX][loserY] != 0) {
			loser.dir = (loser.dir + 1) % 4;
			loserX = loser.x + dx[loser.dir];
			loserY = loser.y + dy[loser.dir];
		}
		//진 player 이동 및 위치 갱신
		loser.x = loserX;
		loser.y = loserY;
		mapForPlayer[loserX][loserY] = loser.num;
		//이동한 곳에 총이 있다면 주움
		if (!map[loserX][loserY].isEmpty()) {
			loser.gunAbility = (int) map[loserX][loserY].poll();
		}
		//이긴 player는 승리한 칸에서 가장 쎈 총을 획득
		if (!map[winner.x][winner.y].isEmpty() && (int) map[winner.x][winner.y].peek() > winner.gunAbility) {
			map[winner.x][winner.y].add(winner.gunAbility);
			winner.gunAbility = (int) map[winner.x][winner.y].poll();
		}
		//이긴 player 위치 갱신
		mapForPlayer[winner.x][winner.y] = winner.num;
	}

	public static void main(String[] args) throws IOException {
		input();
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < players.size(); j++) {
                move(players.get(j));
            }
        }
        for (Player player : players) {
            System.out.print(player.score + " ");
        }
	}
}

import java.io.*;
import java.util.*;

public class Main {
	static final int MAP_SIZE = 4;
	static int[][] monsterMove = { { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 },
			{ -1, 1 } };
	static int[][] pacmanMove = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	static List<Integer>[][] monsters;
	static int[][] deadMonsters;
	static int[][] visited;
	static List<Position> eggs;
	static Position pacman;
	static int[] pacManOrder;
	static int maxMonster = -1;
	static int remainMonsters = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 몬스터 마리 수와 턴의 수 입력받기
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		remainMonsters += m;
		
		// 팩맨 위치 받기
		st = new StringTokenizer(br.readLine());
		pacman = new Position(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);

		// 맵 초기화 하기
		monsters = new ArrayList[MAP_SIZE][MAP_SIZE];
		deadMonsters = new int[MAP_SIZE][MAP_SIZE];
		for (int i = 0; i < MAP_SIZE; i++) {
			for (int j = 0; j < MAP_SIZE; j++) {
				monsters[i][j] = new ArrayList<Integer>();
			}
		}

		// 몬스터 입력받기
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			monsters[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1]
					.add(Integer.parseInt(st.nextToken()) - 1);
		}
		// 알집 초기화
		eggs = new ArrayList<>();

		gameStart(t);
		System.out.println(remainMonsters);
	}

	private static void gameStart(int t) {
		for (int time = 0; time < t; time++) {
			// 몬스터 복제
			createMonsterEggs();
			// 몬스터 이동
			moveMonsters();
			// 팩맨 이동
			visited = new int[MAP_SIZE][MAP_SIZE];
			movePacman();
			// 시체 소멸
			removeMonsters();
			// 알에서 몬스터 생성
			createMonsters();
		}
	}
    /* 몬스터 알 만들기 */
	private static void createMonsterEggs() {
		for (int r = 0; r < MAP_SIZE; r++) {
			for (int c = 0; c < MAP_SIZE; c++) {
				if (monsters[r][c].size() == 0) {
					continue;
				}
				// 몬스터가 있으면 알 생성하기
				for (int dir : monsters[r][c]) {
					eggs.add(new Position(r, c, dir));
				}
			}
		}
	}
    /* 몬스터 이동하기 */
	private static void moveMonsters() {
		List<Integer>[][] tempMap = new ArrayList[MAP_SIZE][MAP_SIZE];
		for (int i = 0; i < MAP_SIZE; i++) {
			for (int j = 0; j < MAP_SIZE; j++) {
				tempMap[i][j] = new ArrayList<Integer>();
			}
		}

		for (int r = 0; r < MAP_SIZE; r++) {
			for (int c = 0; c < MAP_SIZE; c++) {
				if (monsters[r][c].size() == 0) {
					continue;
				}
				// 몬스터가 있으면 이동하기
				for (int dir : monsters[r][c]) {
					int nxtDir = getNextStep(r, c, dir);
					if (nxtDir == -1) {
						tempMap[r][c].add(dir);
						continue;
					}

					tempMap[r + monsterMove[nxtDir][0]][c + monsterMove[nxtDir][1]].add(nxtDir);
				}
			}
		}
		monsters = tempMap;
	}
    /* 몬스터 다음 경로 구하기 */
	private static int getNextStep(int r, int c, int dir) {
		int nextDir = dir;
		for (int i = 0; i < monsterMove.length; i++) {
			nextDir = (dir + i) % monsterMove.length;
			int dx = r + monsterMove[nextDir][0];
			int dy = c + monsterMove[nextDir][1];
			// 격자밖으로 나가고 팩맨이 있는 경우 못감
			if (isMapOut(dx, dy) || dx == pacman.x && dy == pacman.y) {
				continue;
			}
			// 몬스터 시체가 있는 경우 못감
			if (deadMonsters[dx][dy] > 0) {
				continue;
			}
			return nextDir;
		}
		// 움직이지 못하면 -1 반환
		return -1;
	}

	private static boolean isMapOut(int x, int y) {
		return x < 0 || y < 0 || x >= MAP_SIZE || y >= MAP_SIZE;
	}
    /* 팩맨 이동하기 */
	private static void movePacman() {
		maxMonster = -1;
		int[] order = new int[3];
		getPacmanStep(0, pacman.x, pacman.y, 0, order);
		catchMonster();
		remainMonsters -= maxMonster;
	}
    /* 몬스터 잡기 */
	private static void catchMonster() {
		for (int dir : pacManOrder) {
			pacman.x += pacmanMove[dir][0];
			pacman.y += pacmanMove[dir][1];

			// 몬스터 잡기
			int size = monsters[pacman.x][pacman.y].size();
			monsters[pacman.x][pacman.y].clear();

			// 시체 처리하기
			if(size > 0) {
				deadMonsters[pacman.x][pacman.y] = 3;
			}
		}
	}
    /* 팩맨이 이동할 경로 구하기 */
	private static void getPacmanStep(int d, int r, int c, int monstersCnt, int[] order) {
		if (d == 3) {
			if (maxMonster >= monstersCnt) {
				return;
			}

			// 잡은 몬스터수가 크다면
			pacManOrder = order.clone();
			maxMonster = monstersCnt;
			return;
		}

		for (int i = 0; i < pacmanMove.length; i++) {
			int dx = r + pacmanMove[i][0];
			int dy = c + pacmanMove[i][1];

			if (isMapOut(dx, dy)) {
				continue;
			}
			
			int catchMonsterCnt = 0;
            // 방문하지 않은 칸이면
			if(visited[dx][dy] == 0) {
                // 몬스터를 잡는다
				catchMonsterCnt = monsters[dx][dy].size();
			}
			
			order[d] = i;
			visited[dx][dy] += 1;
			getPacmanStep(d + 1, dx, dy, monstersCnt + catchMonsterCnt, order);
			visited[dx][dy] -= 1;
		}
	}
    /* 시체 처리하기 */
	private static void removeMonsters() {
		for (int r = 0; r < MAP_SIZE; r++) {
			for (int c = 0; c < MAP_SIZE; c++) {
				if(deadMonsters[r][c] > 0) {
					deadMonsters[r][c]--;
				}
			}
		}
	}
    /* 몬스터 알에서 깨우기 */
	private static void createMonsters() {
		for(Position egg : eggs) {
			monsters[egg.x][egg.y].add(egg.dir);
		}
		remainMonsters += eggs.size();
		eggs.clear();
	}

	static class Position {
		int x;
		int y;
		int dir;

		public Position(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
}
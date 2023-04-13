import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int M, T, R, C, max, turn;
	static List<Integer>[][][] board;
	static int[][][] deadBoard;
	static List<int[]> pacmanMovedPos;
	static boolean[][] visited;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int[] monsterDx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] monsterDy = { 0, -1, -1, -1, 0, 1, 1, 1 };

	public static void main(String[] args) throws IOException {
		init();
		for (turn = 0; turn < T; turn++) {
			pacmanMovedPos = new ArrayList<>();
			visited = new boolean[4][4];
			max = -1;
			progress();
		}
		System.out.println(countLiveMonster());
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		board = new List[4][4][2];
		deadBoard = new int[4][4][3];

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 2; k++) {
					board[i][j][k] = new ArrayList<>();
				}
			}
		}

		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()) - 1;
		C = Integer.parseInt(st.nextToken()) - 1;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			board[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1][0]
					.add(Integer.parseInt(st.nextToken()) - 1);
		}
	}

    /**
     * 한 Turn에 수행 되는 작업들
     */
	private static void progress() {
		makeEgg();
		moveMonster();
		findBestMovingCase(R, C, 0, 0);
		movePacman();
		removeDead();
		copyMonster();
	}

    /**
     * 몬스터 복제 시도 (알 낳기)
     */
	private static void makeEgg() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j][0].isEmpty()) {
					continue;
				}

				board[i][j][1].addAll(board[i][j][0]);
			}
		}
	}

    /**
     * 몬스터 이동
     */
	private static void moveMonster() {
		List<Integer>[][] tmpBoard = new List[4][4];

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				tmpBoard[i][j] = new ArrayList<>();
			}
		}
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j][0].isEmpty()) {
					continue;
				}

				int size = board[i][j][0].size();
				for (int k = 0; k < size; k++) {
					int monsterDir = board[i][j][0].get(k);
					for (int dir = 0; dir <= 8; dir++) {
						if(dir == 8) {
							tmpBoard[i][j].add(monsterDir);
							break;
						}
						int nx = i + monsterDx[(monsterDir + dir) % 8];
						int ny = j + monsterDy[(monsterDir + dir) % 8];

						if (!isIn(nx, ny)) {
							continue;
						}

						if (isPacman(nx, ny)) {
							continue;
						}

						if (deadBoard[nx][ny][(turn % 3 + 1) % 3] != 0 || deadBoard[nx][ny][(turn % 3 + 2) % 3] != 0) {
							continue;
						}

						// 조건 만족하면 이동
						tmpBoard[nx][ny].add((monsterDir + dir) % 8);
						break;
					}
				}
			}
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				board[i][j][0] = tmpBoard[i][j];
			}
		}
	}

	private static boolean isIn(int nx, int ny) {
		return nx >= 0 && nx < 4 && ny >= 0 && ny < 4;
	}

	private static boolean isPacman(int nx, int ny) {
		return nx == R && ny == C;
	}

    /**
     * 팩맨이 이동하여 가장 많이 몬스터를 잡을 수 있는 경우를 찾음
     */
	private static void findBestMovingCase(int x, int y, int count, int depth) {
		if (depth == 3) {
			if (count <= max) {
				return;
			}

			R = x;
			C = y;
			max = count;

			pacmanMovedPos.clear();
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (visited[i][j]) {
						pacmanMovedPos.add(new int[] { i, j });
					}
				}
			}
			return;
		}

		for (int dir = 0; dir < 4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			if (!isIn(nx, ny)) {
				continue;
			}

			if (visited[nx][ny]) {
				findBestMovingCase(nx, ny, count, depth + 1);
				continue;
			}

			int curCount = count;
			visited[nx][ny] = true;

			count += board[nx][ny][0].size();

			findBestMovingCase(nx, ny, count, depth + 1);
			visited[nx][ny] = false;
			count = curCount;
		}
	}

    /**
     * findBestMovingCase()에서 찾은 최적의 경우의 수에 따라 팩맨을 이동시켜줌
     */
	private static void movePacman() { 
		for (int[] pos : pacmanMovedPos) {
			int size = board[pos[0]][pos[1]][0].size();

			board[pos[0]][pos[1]][0].clear();
			deadBoard[pos[0]][pos[1]][turn % 3] = size;
		}
	}

    /**
     * 2턴이 지난 몬스터 시체를 지워줌
     */
	private static void removeDead() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				deadBoard[i][j][(turn % 3 + 1) % 3] = 0;
			}
		}
	}

    /**
     * 몬스터를 복제 시켜줌(알 까고 나오기)
     */
	private static void copyMonster() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j][1].isEmpty()) {
					continue;
				}

				board[i][j][0].addAll(board[i][j][1]);
				board[i][j][1].clear();
			}
		}
	}

	private static int countLiveMonster() {
		int count = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j][0].isEmpty()) {
					continue;
				}

				count += board[i][j][0].size();
			}
		}
		return count;
	}
}
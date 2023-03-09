import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static Shark[][] map;
	static int[][][] priorDir;
	static int count, outSharkNum;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static class Shark {
		int num;
		int x;
		int y;
		int trace;
		int dir;
		boolean blanked = true;
		
		//입력받을 때, 초기 dir은 나중에 나옴으로 일단 상어의 좌표값을 저장하기 위한 생성자
		Shark(int num, int x, int y) {
			this.num = num;
			this.x = x;
			this.y = y;
		}

		Shark(int num, int x, int y, int dir) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.trace = K;
		}
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new Shark[N][N];
		priorDir = new int[M + 1][4][4];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (tmp != 0) {
					map[i][j] = (new Shark(tmp, i, j));
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int num = 1; num <= M; num++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != null && map[i][j].num == num) {
						map[i][j].dir = Integer.parseInt(st.nextToken()) - 1;
					}
				}
			}
		}

		for (int num = 1; num <= M; num++) {
			for (int dir = 0; dir < 4; dir++) {
				st = new StringTokenizer(br.readLine());
				for (int order = 0; order < 4; order++) {
					priorDir[num][dir][order] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		moveUntilShark1Left();
		System.out.println(count);
	}
	/**
	 * reduceTraces() -> checkAllMap() -> removeTraces()의 한 번의 루틴을 수행하면 count를 증가시켜준다.
	 * count가 1000을 초과한다면, count를 -1로 바꾸고 return해준다.
	 */

	private static void moveUntilShark1Left() {
		moveStart();
		count++;
		while (outSharkNum != M - 1) {
			reduceTraces();
			checkAllMap();
			removeTraces();
			count++;
			if (count > 1000) {
				count = -1;
				return;
			}
		}
	}
	
	/**
	 * 아무 흔적이 없는 초기 상태에서 map전체를 돌며 상어를 움직인다.
	 * 상어가 다른 상어랑 만나 쫒겨나면 outShark를 증가 시켜준다.
	 */
	private static void moveStart() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != null && map[i][j].trace == 0) {
					for (int dir = 0; dir < 4; dir++) {
						int selectedDir = priorDir[map[i][j].num][map[i][j].dir][dir];
						int nx = i + dx[selectedDir];
						int ny = j + dy[selectedDir];
						if (isIn(nx, ny)) {
							// 다른 상어 만난 경우
							if (map[nx][ny] != null && map[nx][ny].trace == K) {
								Shark winShark = fightAndFindWinner(i, j, nx, ny, selectedDir);
								map[nx][ny] = new Shark(winShark.num, nx, ny, winShark.dir);
								map[i][j].trace = K - 1;
								outSharkNum++;
								break;
							}
							map[nx][ny] = new Shark(map[i][j].num, nx, ny, selectedDir);
							map[i][j].trace = K - 1;
							break;
						}
					}
				}
			}
		}
	}

	private static boolean isIn(int nx, int ny) {
		if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
			return false;
		}
		return true;
	}
	
	/**
	 * 상어가 만나면 번호가 낮은 상어를 반환한다.
	 */
	private static Shark fightAndFindWinner(int i, int j, int nx, int ny, int dir) {
		Shark winShark = null;
		if (map[i][j].num > map[nx][ny].num) {
			winShark = map[nx][ny];
		}
		if (map[i][j].num < map[nx][ny].num) {
			winShark = map[i][j];
			winShark.dir = dir;
		}
		return winShark;
	}
	
	/**
	 * 상어들이 이동하기 전, 흔적 값들을 -1해준다.
	 */
	private static void reduceTraces() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != null) {
					map[i][j].trace--;
				}
			}
		}
	}

	/**
	 * 전체 배열을 다 돌면서, 상어가 있는 좌표라면 그 상어가 이동할 곳을 찾아준다.
	 */
	private static void checkAllMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != null && map[i][j].trace == K - 1) {
					findNextPos(i, j);
				}
			}
		}
	}

	/**
	 * map에서 사라질 흔적들을 지워준다.
	 */
	private static void removeTraces() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != null && map[i][j].trace <= 0) {
					map[i][j] = null;
				}
			}
		}
	}

	/**
	 * 상어가 이동할 좌표를 결정한다.
	 * 이동 가능한 좌표가 여러 곳이라면, 우선 순위에 따라 결정한다.
	 */
	private static void findNextPos(int i, int j) {
		List<Integer> blanks = new ArrayList<>();
		List<Integer> traces = new ArrayList<>();
		for (int dir = 0; dir < 4; dir++) {
			int nx = i + dx[dir];
			int ny = j + dy[dir];
			if (!isIn(nx, ny)) {
				continue;
			}
			if (map[nx][ny] == null || map[nx][ny].trace == K && map[nx][ny].blanked) {
				blanks.add(dir);
				continue;
			}
			if (map[nx][ny].num == map[i][j].num) {
				traces.add(dir);
			}
		}
		//사방에 빈칸이 있는 경우
		if (!blanks.isEmpty()) {
			if (blanks.size() == 1) {
				moveShark(i, j, blanks.get(0), true);
				return;
			}
			if (blanks.size() > 1) {
				for (int dirNum = 0; dirNum < 4; dirNum++) {
					for (int num = 0; num < blanks.size(); num++) {
						int dir = blanks.get(num);
						if (priorDir[map[i][j].num][map[i][j].dir][dirNum] == dir) {
							moveShark(i, j, dir, true);
							return;
						}
					}
				}
			}
		}
		//사방에 빈칸이 없는 경우
		if (blanks.isEmpty()) {
			if (traces.size() == 1) {
				moveShark(i, j, traces.get(0), false);
				return;
			}
			if (traces.size() > 1) {
				for (int dirNum = 0; dirNum < 4; dirNum++) {
					for (int num = 0; num < traces.size(); num++) {
						int dir = traces.get(num);
						if (priorDir[map[i][j].num][map[i][j].dir][dirNum] == dir) {
							moveShark(i, j, dir, false);
							return;
						}
					}
				}
			}
		}
	}
	
	/**
	 * 상어가 이동한 후 결과들을 map에 갱신해준다.
	 */
	private static void moveShark(int i, int j, int dir, boolean isBlank) {
		int nx = i + dx[dir];
		int ny = j + dy[dir];
		// 다른 상어 만난 경우
		if (map[nx][ny] != null && map[nx][ny].trace == K) {
			Shark winShark = fightAndFindWinner(i, j, nx, ny, dir);
			map[nx][ny] = new Shark(winShark.num, nx, ny, winShark.dir);
			//흔적이 아닌 빈칸이로 이동했다는 의미의 변수
			map[nx][ny].blanked = isBlank;
			outSharkNum++;
			return;
		}
		map[nx][ny] = new Shark(map[i][j].num, nx, ny, dir);
		//흔적이 아닌 빈칸이로 이동했다는 의미의 변수
		map[nx][ny].blanked = isBlank;
	}
}

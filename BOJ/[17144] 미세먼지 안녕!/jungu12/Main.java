import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C, T;
	static int[][] house;
	static int[] aircleaner;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		init();
		for (int i = 0; i < T; i++) {
			spreadDust();
			turnOnAircleaner();
		}
		System.out.println(countDust());
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		house = new int[R][C];
		aircleaner = new int[2];
		int idx = 0;

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				house[i][j] = Integer.parseInt(st.nextToken());
				if (house[i][j] == -1) {
					aircleaner[idx++] = i;
				}
			}
		}
	}

	private static void spreadDust() {
		int[][] tmp = new int[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (house[i][j] <= 0) {
					continue;
				}

				int canSpread = 0;
				for (int dir = 0; dir < 4; dir++) {
					int nx = i + dx[dir];
					int ny = j + dy[dir];

					if (!isIn(nx, ny)) {
						continue;
					}

					if (house[nx][ny] == -1) {
						continue;
					}

					canSpread++;
				}

				for (int dir = 0; dir < 4; dir++) {
					int nx = i + dx[dir];
					int ny = j + dy[dir];

					if (!isIn(nx, ny)) {
						continue;
					}

					if (house[nx][ny] == -1) {
						continue;
					}

					tmp[nx][ny] += house[i][j] / 5;
				}

				house[i][j] -= house[i][j] / 5 * canSpread;
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				house[i][j] += tmp[i][j];
			}
		}
	}

	private static boolean isIn(int nx, int ny) {
		return nx >= 0 && nx < R && ny >= 0 && ny < C;
	}

	private static void turnOnAircleaner() {
		for (int i = aircleaner[0] - 1; i > 0; i--) {
			house[i][0] = house[i - 1][0];
		}

		for (int i = 0; i < C - 1; i++) {
			house[0][i] = house[0][i + 1];
		}

		for (int i = 0; i < aircleaner[0]; i++) {
			house[i][C - 1] = house[i + 1][C - 1];
		}

		for (int i = C - 1; i > 1; i--) {
			house[aircleaner[0]][i] = house[aircleaner[0]][i - 1];
		}
		house[aircleaner[0]][1] = 0;

		for (int i = aircleaner[1] + 1; i < R - 1; i++) {
			house[i][0] = house[i + 1][0];
		}

		for (int i = 0; i < C - 1; i++) {
			house[R - 1][i] = house[R - 1][i + 1];
		}

		for (int i = R - 1; i > aircleaner[1]; i--) {
			house[i][C - 1] = house[i - 1][C - 1];
		}

		for (int i = C - 1; i > 1; i--) {
			house[aircleaner[1]][i] = house[aircleaner[1]][i - 1];
		}
		house[aircleaner[1]][1] = 0;
	}

	private static int countDust() {
		int count = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(house[i][j] <= 0) {
					continue;
				}
				count += house[i][j];
			}
		}
		return count;
	}
}
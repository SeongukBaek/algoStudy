import java.io.*;
import java.util.*;

public class Main {
	static int[][] ground;
	static boolean[][] visited;
	static int N, L, R;
	static List<List<int[]>> unions;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		int count = 0;

		ground = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				ground[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			unions = new ArrayList<>();
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i][j]) {
						continue;
					}

					visited[i][j] = true;
					List<int[]> union = new ArrayList<>();
					union.add(new int[] { i, j });
					makeUnion(i, j, union);
					if (union.size() != 1) {
						unions.add(union);
					}
				}
			}

			if (unions.isEmpty()) {
				System.out.println(count);
				break;
			}

			count++;
			movePopulation();
		}
	}

	private static void makeUnion(int i, int j, List<int[]> union) {
		for (int dir = 0; dir < 4; dir++) {
			int nx = i + dx[dir];
			int ny = j + dy[dir];

			if (!isIn(nx, ny)) {
				continue;
			}

			if (visited[nx][ny]) {
				continue;
			}

			int sub = Math.abs(ground[i][j] - ground[nx][ny]);
			if (sub >= L && sub <= R) {
				union.add(new int[] { nx, ny });
				visited[nx][ny] = true;
				makeUnion(nx, ny, union);
			}
		}
	}

	private static void movePopulation() {
		int countryNum = unions.size();

		for (int i = 0; i < countryNum; i++) {
			List<int[]> currentUnion = unions.get(i);
			int population = 0;

			for (int[] currentCountry : currentUnion) {
				int x = currentCountry[0];
				int y = currentCountry[1];
				population += ground[x][y];
			}

			for (int[] currentCountry : currentUnion) {
				int x = currentCountry[0];
				int y = currentCountry[1];
				ground[x][y] = population / currentUnion.size();
			}
		}
	}

	private static boolean isIn(int nx, int ny) {
		return (nx >= 0 && nx < N && ny >= 0 && ny < N);
	}
}
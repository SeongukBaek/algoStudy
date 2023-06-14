import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int M;
	private static int area;
	private static int[][] map;
	// 몇번째 지역인지 저장
	private static int[][] areas;
	// 지역별 이동할 수 있는 칸의 개수 저장
	private static List<Integer> areaSize;
	private static boolean[][] isVisited;
	private static final int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);

		map = new int[N][M];
		areas = new int[N][M];
		areaSize = new ArrayList<>();
		isVisited = new boolean[N][M];

		for (int x = 0; x < N; x++) {
			String info = br.readLine();
			for (int y = 0; y < M; y++) {
				map[x][y] = info.charAt(y) - '0';
			}
		}

		// 우선 0인 좌표들로부터 이동할 수 있는 칸의 개수 저장
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < M; y++) {
				if (map[x][y] == 1 || isVisited[x][y]) {
					continue;
				}
				computeMovableCount(x, y);
				area++;
			}
		}

		// 벽에서 이동할 수 있는 칸의 개수 계산 후 정답 생성
		// 굳이 배열에 저장할 필요 없이 바로 반환해줘도 될듯?
		StringBuilder answer = new StringBuilder();
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < M; y++) {
				int result = 0;

				if (map[x][y] == 1) {
					result = countEWSN(x, y);
				}

				answer.append(result);
			}
			answer.append("\n");
		}

		System.out.print(answer);
	}

	/**
	 * 0인 x, y 좌표로부터 이동할 수 있는 0의 개수를 찾고, 저장
	 * */
	private static void computeMovableCount(int x, int y) {
		Stack<int[]> locations = new Stack<>();
		List<int[]> zeros = new ArrayList<>();

		locations.add(new int[] {x, y});
		zeros.add(new int[] {x, y});

		isVisited[x][y] = true;

		while (!locations.isEmpty()) {
			int[] current = locations.pop();

			for (int[] direction : directions) {
				int nx = current[0] + direction[0];
				int ny = current[1] + direction[1];

				// 다음 좌표가 맵 밖이거나, 벽이거나, 이미 방문한 좌표라면 패스
				if (!isIn(nx, ny) || map[nx][ny] == 1 || isVisited[nx][ny]) {
					continue;
				}

				zeros.add(new int[] {nx, ny});
				locations.add(new int[] {nx, ny});
				isVisited[nx][ny] = true;
			}
		}

		// 지역별 이동할 수 있는 칸의 개수 저장 및 지역 정보 저장
		areaSize.add(zeros.size());
		for (int[] zero : zeros) {
			areas[zero[0]][zero[1]] = area;
		}
	}

	/**
	 * 해당 좌표로부터 동서남북을 확인해서 이동할 수 있는 칸의 개수 계산
	 * */
	private static int countEWSN(int x, int y) {
		int count = 1;
		boolean[] isVisitedArea = new boolean[area];

		for (int[] direction : directions) {
			int nx = x + direction[0];
			int ny = y + direction[1];

			// 다음 좌표가 맵 밖이거나, 벽이거나, 이미 방문한 지역이라면 패스
			if (!isIn(nx, ny) || map[nx][ny] == 1 || isVisitedArea[areas[nx][ny]]) {
				continue;
			}

			count += areaSize.get(areas[nx][ny]);
			isVisitedArea[areas[nx][ny]] = true;
		}
		return count % 10;
	}

	private static boolean isIn(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static int M;
	private static char[][] map;
	// 상 우 하 좌
	private static final int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	// 해당 좌표의 부모 좌표 표현값 저장
	private static int[][] parents;
	private static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		int N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);

		map = new char[N][M];
		parents = new int[N][M];

		for (int x = 0; x < N; x++) {
			String line = br.readLine();
			for (int y = 0; y < M; y++) {
				map[x][y] = line.charAt(y);
				parents[x][y] = getNumber(x, y);
			}
		}

		for (int x = 0; x < N; x++) {
			for (int y = 0; y < M; y++) {
				if (getNumber(x, y) == parents[x][y]) {
					traverseMap(x, y);
				}
			}
		}

		System.out.println(count);
	}

	/**
	 * 시작 좌표로부터 탐색할 수 있는 만큼 탐색
	 * */
	private static void traverseMap(int x, int y) {
		int number = getNumber(x, y);
		Deque<int[]> locations = new ArrayDeque<>();
		locations.add(new int[] {x, y});

		while (!locations.isEmpty()) {
			int[] current = locations.poll();

			int direction = getDirection(map[current[0]][current[1]]);

			int nx = current[0] + directions[direction][0];
			int ny = current[1] + directions[direction][1];

			// 새로운 좌표의 부모 좌표 표현값이 탐색 시작 좌표(x, y)의 부모 좌표 표현값과 같다면 사이클이 발생한 것이고, 안전 구역 증가
			if (parents[nx][ny] == number) {
				count++;
				return;
			}

			// 새로운 좌표의 부모 좌표 표현값이 탐색 시작 좌표(x, y)의 부모 좌표 표현값보다 작다면, 안전 구역을 합칠 수 있으므로 그대로 사용
			if (parents[nx][ny] < number) {
				return;
			}

			// 새로운 좌표의 부모 좌표 표현값이 더 크다면, 더 작은 부모 좌표를 표현하는 number를 가르키도록 갱신
			if (parents[nx][ny] > number) {
				parents[nx][ny] = number;
				locations.add(new int[] {nx, ny});
			}
		}
	}

	private static int getDirection(char input) {
		if ('U' == input) {
			return 0;
		}
		if ('R' == input) {
			return 1;
		}
		if ('D' == input) {
			return 2;
		}
		return 3;
	}

	private static int getNumber(int x, int y) {
		return x * M + y;
	}
}
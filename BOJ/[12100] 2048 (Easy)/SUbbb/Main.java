import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static int[][] map;
	private static int N;
	private static int maxBlock;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		for (int x = 0; x < N; x++) {
			st = new StringTokenizer(br.readLine());
			for (int y = 0; y < N; y++) {
				int number = Integer.parseInt(st.nextToken());
				map[x][y] = number;
				maxBlock = Math.max(maxBlock, number);
			}
		}

		int[][] copyMap = copy();
		move(copyMap, 0);

		System.out.println(maxBlock);
	}

	private static int[][] copy() {
		int[][] clone = new int[N][N];
		for (int x = 0; x < N; x++) {
			System.arraycopy(map[x], 0, clone[x], 0, N);
		}
		return clone;
	}

	private static void move(int[][] movedMap, int count) {
		if (count == 5) {
			return;
		}

		for (int m = 0; m < 4; m++) {
			move(moveMap(movedMap, m), count + 1);
		}
	}

	/**
	 * 주어진 맵을 주어진 이동방향 정보에 따라 이동시키고 반환
	 * @param target 이동시킬 맵
	 * @param move 이동시킬 방향
	 * @return int[][]
	 * */
	private static int[][] moveMap(int[][] target, int move) {
		Stack<Integer> numbers;
		int[][] movedMap = new int[N][N];
		boolean isPlus;
		int number;

		// 위로 이동시키는 경우
		if (move == 0) {
			for (int y = 0; y < N; y++) {
				isPlus = false;
				numbers = new Stack<>();
				for (int x = 0; x < N; x++) {
					number = target[x][y];
					if (number == 0) {
						continue;
					}
					if (!isPlus && !numbers.empty() && numbers.peek() == number) {
						numbers.pop();
						numbers.push(number * 2);
						maxBlock = Math.max(maxBlock, number * 2);
						isPlus = true;
						continue;
					}

					isPlus = false;
					numbers.push(number);
				}
				for (int x = 0; x < numbers.size(); x++) {
					movedMap[x][y] = numbers.get(x);
				}
			}
		}

		// 오른쪽으로 이동시키는 경우
		if (move == 1) {
			for (int x = 0; x < N; x++) {
				isPlus = false;
				numbers = new Stack<>();
				for (int y = N - 1; y >= 0; y--) {
					number = target[x][y];
					if (number == 0) {
						continue;
					}
					if (!isPlus && !numbers.empty() && numbers.peek() == number) {
						numbers.pop();
						numbers.push(number * 2);
						maxBlock = Math.max(maxBlock, number * 2);
						isPlus = true;
						continue;
					}

					isPlus = false;
					numbers.push(number);
				}
				for (int index = 0; index < numbers.size(); index++) {
					movedMap[x][N - 1 - index] = numbers.get(index);
				}
			}
		}

		// 아래로 이동시키는 경우
		if (move == 2) {
			for (int y = 0; y < N; y++) {
				isPlus = false;
				numbers = new Stack<>();
				for (int x = N - 1; x >= 0; x--) {
					number = target[x][y];
					if (number == 0) {
						continue;
					}
					if (!isPlus && !numbers.empty() && numbers.peek() == number) {
						numbers.pop();
						numbers.push(number * 2);
						maxBlock = Math.max(maxBlock, number * 2);
						isPlus = true;
						continue;
					}

					isPlus = false;
					numbers.push(number);
				}
				for (int index = 0; index < numbers.size(); index++) {
					movedMap[N - 1 - index][y] = numbers.get(index);
				}
			}
		}

		// 왼쪽으로 이동시키는 경우
		if (move == 3) {
			for (int x = 0; x < N; x++) {
				isPlus = false;
				numbers = new Stack<>();
				for (int y = 0; y < N; y++) {
					number = target[x][y];
					if (number == 0) {
						continue;
					}
					if (!isPlus && !numbers.empty() && numbers.peek() == number) {
						numbers.pop();
						numbers.push(number * 2);
						maxBlock = Math.max(maxBlock, number * 2);
						isPlus = true;
						continue;
					}

					isPlus = false;
					numbers.push(number);
				}
				for (int index = 0; index < numbers.size(); index++) {
					movedMap[x][index] = numbers.get(index);
				}
			}
		}

		return movedMap;
	}
}
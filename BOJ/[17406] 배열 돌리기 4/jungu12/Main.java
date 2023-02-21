import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] A;
	static int[][] tmpA;
	static int[][] rotate;
	static int[] order;
	static int result = Integer.MAX_VALUE;
	static boolean[] isSelected;

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new int[N + 1][M + 1];
		tmpA = new int[N + 1][M + 1];
		rotate = new int[K][3];
		order = new int[K];
		isSelected = new boolean[K];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			rotate[i][0] = Integer.parseInt(st.nextToken());
			rotate[i][1] = Integer.parseInt(st.nextToken());
			rotate[i][2] = Integer.parseInt(st.nextToken());
		}
	}

	static void chooseOrder(int cnt) {
		if (cnt == K) {
			rotateArr(order);
			return;
		}
		for (int i = 0; i < K; i++) {
			if (!isSelected[i]) {
				order[cnt] = i;
				isSelected[i] = true;
				chooseOrder(cnt + 1);
				isSelected[i] = false;
			}
		}
	}

	static void rotateArr(int[] order) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				tmpA[i][j] = A[i][j];
			}
		}

		for (int idx = 0; idx < K; idx++) {
			int r = rotate[order[idx]][0];
			int c = rotate[order[idx]][1];
			int s = rotate[order[idx]][2];

			for (int i = 1; i <= s; i++) {
				int limit = i * 2;
				int curX = r - i;
				int curY = c - i;
				int tmpNow = tmpA[curX][curY];
				int tmpNext;
				for (int j = 0; j < limit * 4; j++) {
					if (j < limit) {
						tmpNext = tmpA[curX][curY + 1];
						tmpA[curX][curY + 1] = tmpNow;
						curY += 1;
						tmpNow = tmpNext;
					} else if (j < limit * 2) {
						tmpNext = tmpA[curX + 1][curY];
						tmpA[curX + 1][curY] = tmpNow;
						curX += 1;
						tmpNow = tmpNext;
					} else if (j < limit * 3) {
						tmpNext = tmpA[curX][curY - 1];
						tmpA[curX][curY - 1] = tmpNow;
						curY -= 1;
						tmpNow = tmpNext;

					} else if (j < limit * 4) {
						tmpNext = tmpA[curX - 1][curY];
						tmpA[curX - 1][curY] = tmpNow;
						curX -= 1;
						tmpNow = tmpNext;
					}
				}
			}
		}result = Math.min(result, calMin());
	}

	static int calMin() {
		int min = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 1; j <= M; j++) {
				sum += tmpA[i][j];
			}
			min = Math.min(min, sum);
		}
		return min;
	}

	public static void main(String[] args) throws IOException {
		input();
		chooseOrder(0);
		System.out.println(result);
	}
}

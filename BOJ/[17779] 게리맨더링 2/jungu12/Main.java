import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, totalPopulation, minGap = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][] isBorder;

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		isBorder = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				totalPopulation += map[i][j];
			}
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		divideElectionDistrict();
		System.out.println(minGap);
	}
	
	/**
	 * 조건에 맞는 시작점과 d1, d2를 구하면, 경계선을 긋고 차이를 구한다.
	 */
	private static void divideElectionDistrict() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int d1 = 1; d1 < N; d1++) {
					for (int d2 = 1; d2 < N; d2++) {
						if (i + d1 + d2 >= N) {
							continue;
						}
						if (j - d1 < 0 || j + d2 >= N) {
							continue;
						}
						isBorder = new boolean[N][N];
						makeBorder(i, j, d1, d2);
						minGap = Math.min(minGap, calMaxGap(i, j, d1, d2));
					}
				}
			}
		}
	}

	/**
	 * 경계선을 그어 준다.
	 */
	private static void makeBorder(int i, int j, int d1, int d2) {
		for (int index = 0; index <= d1; index++) {
			isBorder[i + index][j - index] = true;
			isBorder[i + d2 + index][j + d2 - index] = true;
		}

		for (int index = 0; index <= d2; index++) {
			isBorder[i + index][j + index] = true;
			isBorder[i + d1 + index][j - d1 + index] = true;
		}
	}

	/**
	 * 가장 인구가 많은 선거구와 적은 선거구의 인구수 차이를 구한다.
	 */
	private static int calMaxGap(int i, int j, int d1, int d2) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int sum = 0;
		int population = 0;

		population = calDistrict1(i, j, d1, d2);
		min = Math.min(min, population);
		max = Math.max(max, population);
		sum += population;
		
		population = calDistrict2(i, j, d1, d2);
		min = Math.min(min, population);
		max = Math.max(max, population);
		sum += population;
		
		population = calDistrict3(i, j, d1, d2);
		min = Math.min(min, population);
		max = Math.max(max, population);
		sum += population;
		
		population = calDistrict4(i, j, d1, d2);
		min = Math.min(min, population);
		max = Math.max(max, population);
		sum += population;
		
		//총 인구수에서 다른 네곳의 인구수를 빼주어 다섯번째 선거구의 인구수를 구한다.
		population = totalPopulation - sum;
		min = Math.min(min, population);
		max = Math.max(max, population);

		return max - min;
	}

	/**
	 * 선거구 1의 인구수 합을 구한다.
	 */
	private static int calDistrict1(int i, int j, int d1, int d2) {
		int sum = 0;
		for (int x = 0; x < i + d1; x++) {
			for (int y = 0; y <= j; y++) {
				if (isBorder[x][y]) {
					break;
				}
				sum += map[x][y];
			}
		}
		return sum;
	}

	/**
	 * 선거구 2의 인구수 합을 구한다.
	 */
	private static int calDistrict2(int i, int j, int d1, int d2) {
		int sum = 0;
		for (int x = 0; x <= i + d2; x++) {
			for (int y = N - 1; y > j; y--) {
				if (isBorder[x][y]) {
					break;
				}
				sum += map[x][y];
			}
		}
		return sum;
	}
	
	/**
	 * 선거구 3의 인구수 합을 구한다.
	 */
	private static int calDistrict3(int i, int j, int d1, int d2) {
		int sum = 0;
		for (int x = i + d1; x < N; x++) {
			for (int y = 0; y < j - d1 + d2; y++) {
				if (isBorder[x][y]) {
					break;
				}
				sum += map[x][y];
			}
		}
		return sum;
	}
	
	/**
	 * 선거구 4의 인구수 합을 구한다.
	 */
	private static int calDistrict4(int i, int j, int d1, int d2) {
		int sum = 0;
		for (int x = i + d2 + 1; x < N; x++) {
			for (int y = N - 1; y >= j - d1 + d2; y--) {
				if (isBorder[x][y]) {
					break;
				}
				sum += map[x][y];
			}
		}
		return sum;
	}
}
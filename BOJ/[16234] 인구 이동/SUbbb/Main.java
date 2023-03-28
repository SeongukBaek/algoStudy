import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int[][] country;
	private static int[][] opened;
	private static int N;
	private static int L;
	private static int R;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		country = new int[N][N];

		for (int x = 0; x < N; x++) {
			st = new StringTokenizer(br.readLine());
			for (int y = 0; y < N; y++) {
				country[x][y] = Integer.parseInt(st.nextToken());
			}
		}

		int day = 0;
		while (true) {
			// 하루가 시작될 때 방문 처리 배열 초기화
			opened = new int[N][N];

			// 각 나라에서 인구 이동이 가능한지 확인해야 함
			// 이때 이미 방문하지 않은 나라에 대해서 이를 판단
			List<Integer> populations = new ArrayList<>();
			int countNumber = 1;
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < N; y++) {
					if (opened[x][y] == 0) {
						// 방문하지 않은 나라라면 해당 나라에서 인구 이동이 가능한 나라 확인
						int dividedPopulation = markMove(x, y, countNumber);
						if (dividedPopulation != 0) {
							populations.add(dividedPopulation);
							countNumber++;
						}
					}
				}
			}

			// 이동할 인구가 없다면 종료
			if (populations.size() == 0) {
				break;
			}

			// 실제 인구 이동 실시
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < N; y++) {
					if (opened[x][y] != 0) {
						country[x][y] = populations.get(opened[x][y] - 1);
					}
				}
			}

			day++;
		}

		System.out.print(day);
	}

	private static int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	private static int markMove(int startX, int startY, int countNumber) {
		Queue<int[]> countries = new LinkedList<>();
		countries.add(new int[] {startX, startY});
		int sum = country[startX][startY];
		int openedCountry = 1;
		opened[startX][startY] = countNumber;

		while(!countries.isEmpty()) {
			int[] current = countries.poll();
			int currentPopulation = country[current[0]][current[1]];

			for (int[] move : moves) {
				int nx = current[0] + move[0];
				int ny = current[1] + move[1];

				if (!isIn(nx, ny) || opened[nx][ny] != 0) {
					continue;
				}

				int newPopulation = country[nx][ny];
				int sub = Math.abs(currentPopulation - newPopulation);
				if (L <= sub && sub <= R) {
					sum += newPopulation;
					openedCountry++;
					opened[nx][ny] = countNumber;
					countries.add(new int[] {nx,ny});
				}
			}
		}

		// 시작 나라만 연합이 가능한 경우는 연합 불가
		if (openedCountry == 1) {
			opened[startX][startY] = 0;
			return 0;
		}
		return sum / openedCountry;
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}

import java.io.*;
import java.util.*;

public class Gerrymandering2 {
	static int N;
	static int[][] city;
	static int minPopulation = Integer.MAX_VALUE;
	static int totalPopulation;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		city = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
				totalPopulation += city[i][j];
			}
		}

		getMinPopulationGap();
		System.out.println(minPopulation);
	}

	private static void getMinPopulationGap() {
		Queue<Border> borders = new ArrayDeque<>();

		// x,y좌표 정하기 -> 1,1부터 N-2,N-2까지
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				borders.clear();
				getBorders(borders, i, j);
				while (!borders.isEmpty()) {
					Border cur = borders.poll();

					// 선거구 인구 구하기
					int gap = getPopulationGap(cur, i, j);
					minPopulation = Math.min(gap, minPopulation);
				}
			}
		}
	}

	private static void getBorders(Queue<Border> borders, int x, int y) {

		for (int d1 = 1; d1 < N - 1; d1++) {
			for (int d2 = 1; d2 < N - 1; d2++) {
				if (x + d1 + d2 < N && y - d1 >= 0 && y + d2 < N) {
					borders.add(new Border(d1, d2));
				}
			}
		}
	}

	private static int getPopulationGap(Border cur, int x, int y) {
		int max = 0;
		int min = Integer.MAX_VALUE;
		
		// 1번 선거구 구역
		int population1 = 0;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j <= y; j++) {
				population1 += city[i][j];
			}
		}

		for (int i = 0; i < cur.d1; i++) {
			for (int j = 0; j < y - i; j++) {
				population1 += city[x+i][j];
			}
		}

		// 2번 선거구 구역
		int population2 = 0;
		for (int i = 0; i < x; i++) {
			for (int j = y + 1; j < N; j++) {
				population2 += city[i][j];
			}
		}

		for (int i = 0; i <= cur.d2; i++) {
			for (int j = y + i+1; j < N; j++) {
				population2 += city[x+i][j];
			}
		}
		
		max = Math.max(population1, population2);
		min = Math.min(population1, population2);
		
		//3번 선거구 구역
		int population3 = 0;
		for(int i = 0; i < cur.d2; i++) {
			for(int j = 0; j < y-cur.d1+i; j++) {
				population3 += city[x+cur.d1+i][j];
			}
		}
		
		for(int i = x+cur.d1+cur.d2; i < N; i++) {
			for(int j = 0; j < y - cur.d1+cur.d2; j++) {
				population3 += city[i][j];
			}
		}
		
		max = Math.max(max, population3);
		min = Math.min(min, population3);
		
		//4번 선거구 구역
		int population4 = 0;
		for(int i = 0; i < cur.d1; i++) {
			for(int j = y+cur.d2-i; j < N; j++) {
				population4 += city[x+cur.d2+1+i][j];
			}
		}
		
		for(int i = x+cur.d1+cur.d2+1; i < N; i++) {
			for(int j = y+cur.d2 - cur.d1; j < N; j++) {
				population4 += city[i][j];
			}
		}
		
		max = Math.max(max, population4);
		min = Math.min(min, population4);
		
		int population5 = totalPopulation - (population1+population2+population3+population4);
		
		max = Math.max(max, population5);
		min = Math.min(min, population5);
		
		return max - min;
	}

	private static class Border {
		int d1;
		int d2;

		public Border(int d1, int d2) {
			this.d1 = d1;
			this.d2 = d2;
		}
	}
}
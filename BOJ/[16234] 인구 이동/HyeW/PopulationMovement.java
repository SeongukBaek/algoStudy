import java.io.*;
import java.util.*;

public class PopulationMovement {
	static int N, L, R;
	static int[][] population;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		population = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				population[i][j] = Integer.parseInt(st.nextToken());
			}
		}

        //국경을 인구이동을 몇번했는지 체크하는 변수
		int cnt = 1;
		int day = -1;

		while (cnt > 0) {
			cnt = 0;
			day++;
			cnt = getMovementCount();
		}
		
		System.out.println(day);
	}

	private static int getMovementCount() {
		int cnt = 0;
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j]) {
					continue;
				}
				// 국경을 열 나라 찾기
				int peopleAvg = getPopulation(new Node(i, j));
				// 인구 이동하기
				cnt += move(new Node(i, j), peopleAvg);
			}
		}

		return cnt;
	}

	private static int getPopulation(Node start) {
		Deque<Node> countries = new ArrayDeque<>();
		countries.add(start);
		visited[start.x][start.y] = true;

		int totalPopulation = population[start.x][start.y];
		int countryCount = 1;
		
		while (!countries.isEmpty()) {
			Node cur = countries.poll();
			int curPopulation =  population[cur.x][cur.y];
            // 인구 이동할 나라 표시
			population[cur.x][cur.y] = -1;
			
			for (int i = 0; i < 4; i++) {
				int dx = cur.x + dr[i];
				int dy = cur.y + dc[i];

				if (!arrayBoundsValidation(dx, dy)) {
					continue;
				}
				//방문했고 두 나라의 인구 차이가 L과 R사이가 아니라면 패스
				if(visited[dx][dy] || !diffPopulation(curPopulation,  population[dx][dy])) {
					continue;
				}
				
				visited[dx][dy] = true;
				countries.add(new Node(dx, dy));
				totalPopulation += population[dx][dy];
				countryCount++;
			}
		}
		return totalPopulation / countryCount;
	}
	
    //BFS를 통해서 좌표값이 -1인 값에 인구수를 넣는다.
	private static int move(Node start, int peopleAvg) {
		Deque<Node> countries = new ArrayDeque<>();
		countries.add(start);
		population[start.x][start.y] = peopleAvg;
		
		int cnt = 0;
		while (!countries.isEmpty()) {
			Node cur = countries.poll();
			
			for (int i = 0; i < 4; i++) {
				int dx = cur.x + dr[i];
				int dy = cur.y + dc[i];

				if (!arrayBoundsValidation(dx, dy)) {
					continue;
				}
				
				if(population[dx][dy] != -1) {
					continue;
				}
				
				countries.add(new Node(dx, dy));
				population[dx][dy] = peopleAvg;
				cnt++;
			}
		}
		
		return cnt;
	}

	private static boolean diffPopulation(int cur, int nxt) {
		int gap = Math.abs(cur - nxt);
		return gap >= L && gap <= R;
	}

	private static boolean arrayBoundsValidation(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
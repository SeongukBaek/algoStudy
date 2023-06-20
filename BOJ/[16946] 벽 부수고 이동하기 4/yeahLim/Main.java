import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Main {

	static int n, m;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Map<Integer, Integer> setCount = new HashMap<>();
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		n = Integer.parseInt(info[0]);
		m = Integer.parseInt(info[1]);
		map = new int[n][m];
		int[][] smashedMap = new int[n][m];	
		for (int i = 0; i < n; i++) {
			String infor = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = infor.charAt(j) - '0';
				smashedMap[i][j] = map[i][j];
			}
		}
		
		
		/* BFS : 집합 찾기 */
		int num = 2;
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0 && !visited[i][j]) {
					searchSet(i, j, num);
					num++;
				}
			}
		}

		
		/* 벽부셔서 이동할 수 있는 개수 세기 */
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1) {
					smashedMap[i][j] = move(i, j);
				}
			}
		}
		
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(smashedMap[i][j] % 10);
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}

	private static void searchSet(int x, int y, int num) {
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		
		visited[x][y] = true;
		int count = 0;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			map[cur[0]][cur[1]] = num;
			count++;
			
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if (nx < 0 || nx >= n || ny < 0 || ny >= m ) continue;
				if (visited[nx][ny]) continue;
				if (map[nx][ny] == 0) {
					visited[nx][ny] = true;
					q.offer(new int[] {nx, ny});
				}				
			}
		}
		
		setCount.put(num, count);
	}

	private static int move(int x, int y) {
		boolean[] moved = new boolean[setCount.size() + 2];
		int count = 0;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || nx >= n || ny < 0 || ny >= m ) continue;
			if (moved[map[nx][ny]]) continue;
			if (map[nx][ny] == 1) continue;
			
			moved[map[nx][ny]] = true;
			count += setCount.get(map[nx][ny]);
			
		}

		return count + 1;
		
	}
	
	
}
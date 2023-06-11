import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static char[][] map;
	static int[][] visited;
	static int N, M;
	static int[] dx = { -1, 0, 0, 1 };
	static int[] dy = { 0, -1, 1, 0 };

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(findMinSafeZone());
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visited = new int[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
			}
		}
	}

	private static int findMinSafeZone() {
		int answer = 0;
		//이번 BFS로 방문한 곳을 표시하기 위한 변수
		int turn = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int curX = i;
				int curY = j;
				turn++;

				//한번 방문한 곳을 방문하지 않으면서 이번 탐색에서만 방문한 곳으로 돌아간다면 cycle이 있는 것
				while (visited[curX][curY] == 0) {
					visited[curX][curY] = turn;
					int dir = findDir(map[curX][curY]);
					curX += dx[dir];
					curY += dy[dir];
					
					//이번 탐색에서 방문한 곳으로 갔다면 answer++
					if (visited[curX][curY] == turn) {
						answer++;
						break;
					}
				}
			}
		}
		return answer;
	}

	private static int findDir(char dir) {
		if (dir == 'U') {
			return 0;
		}
		if (dir == 'L') {
			return 1;
		}
		if (dir == 'R') {
			return 2;
		}
		return 3;
	}
}
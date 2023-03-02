import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
    //바이러스를 퍼트릴때 사용할 바이러스의 x,y좌표 저장한 List
	static List<int[]> virusPos;
    //조합으로 벽을 놓을 위치를 고를 때 사용할 빈 공간의 x,y좌표 저장한 List
	static List<int[]> blankPos;
	static int[] dx = { 1, 0, 0, -1 };
	static int[] dy = { 0, 1, -1, 0 };
	static int best;

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		virusPos = new ArrayList<>();
		blankPos = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					blankPos.add(new int[] { i, j });
				}
				if (map[i][j] == 2) {
					virusPos.add(new int[] { i, j });
				}
			}
		}
	}

	static void makeWall() {
        //blankPos에 몇번째에 있는 빈 공간 좌표가 벽을 놓을 위치로 선정 되었는지 저장할 배열
		int[] visited = new int[3];
		chooseBlank(0, visited);
	}

	static void spreadVirusAndFindMax(int[] visited) {
		Queue<int[]> que = new LinkedList<>();
		for (int i = 0; i < virusPos.size(); i++) {
			que.add(virusPos.get(i));
		}
		int[][] tmpMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmpMap[i][j] = map[i][j];
			}
		}
		for (int i = 0; i < 3; i++) {
			int[] cur = blankPos.get(visited[i]);
			tmpMap[cur[0]][cur[1]] = 1;
		}
		while (!que.isEmpty()) {
			int[] curPos = que.poll();
			for (int i = 0; i < 4; i++) {
				int nx = curPos[0] + dx[i];
				int ny = curPos[1] + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}
				if (tmpMap[nx][ny] == 0) {
					tmpMap[nx][ny] = 2;
					que.add(new int[] { nx, ny });
				}
			}
		}
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tmpMap[i][j] == 0) {
					count++;
				}
			}
		}
		best = Math.max(best, count);
	}

	static void chooseBlank(int depth, int[] visited) {
		if (depth == 3) {
			spreadVirusAndFindMax(visited);
			return;
		}
		int start = (depth == 0) ? -1 : visited[depth - 1];
		for (int i = start + 1; i < blankPos.size(); i++) {
			visited[depth] = i;
			chooseBlank(depth + 1, visited);
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		makeWall();
		System.out.println(best);
	}
}
import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int map[][];
	static Queue<int[]> q = new LinkedList<>();
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean visited[][][];

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[2][N][M];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
	}

	static void move() {
		if(N-1 == 0 && M-1 == 0){
            System.out.print(1);
            return;
        }
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}
				if (map[nx][ny] == 0) {
					if (!visited[cur[3]][nx][ny]) {
						q.add(new int[] { nx, ny, cur[2] + 1, cur[3] });
						visited[cur[3]][nx][ny] = true;
					}
				} else {
					if (cur[3] == 0 && !visited[1][nx][ny]) {
						q.add(new int[] { nx, ny, cur[2] + 1, 1 });
						visited[1][nx][ny] = true;
					}
				}
				if (nx == N - 1 && ny == M - 1) {
					System.out.println(cur[2] + 1);
					return ;
				}
			}
		}
		System.out.println(-1);
	}

	public static void main(String[] args) throws IOException {
		input();
		q.add(new int[] { 0, 0, 1, 0 });
		move();
	}
}
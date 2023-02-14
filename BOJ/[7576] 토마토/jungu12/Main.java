import java.util.*;
import java.io.*;

public class Main {
	static int[][] warehouse;
	static Queue<int[]> queue = new LinkedList<>();
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());

		warehouse = new int[y][x];
		for (int i = 0; i < y; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < x; j++) {
				warehouse[i][j] = Integer.parseInt(st.nextToken());
				// 초기 상태를 큐에 삽입
				if (warehouse[i][j] == 1) {
					queue.add(new int[] { i, j });
				}
			}
		}
	}

	static int bfs() {
		int result = 1;
		while (!queue.isEmpty()) {
			int[] xy = queue.poll();
			int x = xy[1];
			int y = xy[0];
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				// 정해진 배열의 크기를 넘는지 check
				if (nx >= 0 && nx < warehouse[0].length && ny >= 0 && ny < warehouse.length) {
					//토마토가 이미 익었는지 확인
					if (warehouse[ny][nx] == 0) {
						queue.add(new int[] { ny, nx });
						warehouse[ny][nx] = warehouse[y][x] + 1;
						if (warehouse[ny][nx] > result) {
							result = warehouse[ny][nx];
						}
					}
				}
			}
		}
		//안익는 토마토가 있는지 확인
		if(!checkPossible()) {
			return -1;
		}
		//초기 배열에 토마토 값이 
		return result - 1;
	}
	
	static boolean checkPossible() {
		for(int i = 0; i < warehouse.length; i++) {
			for(int j = 0 ; j < warehouse[0].length; j++) {
				if(warehouse[i][j] == 0)
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		input();
		System.out.println(bfs());
	}
}
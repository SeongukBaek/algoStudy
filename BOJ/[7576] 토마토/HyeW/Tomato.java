import java.util.*;
import java.io.*;

public class Tomato {
	static final int DIRECTION_LENGTH = 4;
	static int[][] tomatoes;
	static Queue<Node> done;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		tomatoes = new int[N][M];
		done = new LinkedList<>();
		int day = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				tomatoes[i][j] = Integer.parseInt(st.nextToken());

				if (tomatoes[i][j] == 1) {
					done.add(new Node(i, j));
				}
			}
		}
		
		day = countDay();
		
		System.out.println(day);
	}
	
	/**
	 * 토마토가 다 익는 날짜를 반환한다.
	 * 모든 토마토가 익을 수 없다면 -1을 반환한다.
	 * 
	 * @return int
	 */
	static int countDay() {
		int day = -1;
		boolean isFinished = checkTomato();
		
		while (!done.isEmpty()) {
			day++;
			oneDay();
		}
		isFinished = checkTomato();
		
		if(!isFinished)
			return -1;
		return day;
	}
	
	/**
	 * 하루 동안 익는 토마토를 탐색한다.
	 */
	static void oneDay() {
		int parent = done.size();

		while (parent-- != 0) {
			Node cur = done.poll();

			for (int i = 0; i < DIRECTION_LENGTH; i++) {
				int dx = cur.x + dr[i];
				int dy = cur.y + dc[i];

				if (dx >= N || dy >= M || dx < 0 || dy < 0)
					continue;
				if (tomatoes[dx][dy] != 0)
					continue;

				tomatoes[dx][dy] = 1;
				done.add(new Node(dx, dy));
			}
		}
	}

	/**
	 * 모든 토마토가 익었는지 확인한다.
	 * 
	 * @return boolean
	 */
	static boolean checkTomato() {

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(tomatoes[i][j] == 0) {
					return false;
				}
			}
		}
		
		return true;
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
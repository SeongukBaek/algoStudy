import java.io.*;
import java.util.*;

class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

}

public class Main {
	private static int m; // 가로 줄의 수
	private static int n; // 세로 줄의 수
	private static int[][] tomato;
	private static int day;
	private static Queue<Point> todayQueue = new LinkedList<>();
	private static Queue<Point> tomorrowQueue = new LinkedList<>();
	private static int[] dx = { 1, -1, 0, 0 };
	private static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		tomato = new int[n][m];

		// 토마토 농장 초기화
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				tomato[i][j] = Integer.parseInt(st.nextToken());
				if (tomato[i][j] == 1) {
					todayQueue.add(new Point(j, i));
				}
			}
		}

		while (todayQueue.size() > 0) {
			dayPass();
		}
		
		if (tomatoRipeCheck()) {
			System.out.println(day - 1);
		}
		else {
			System.out.println(-1);
		}
	}

	// 내일 창고의 상황
	private static void dayPass() {
		while (!todayQueue.isEmpty()) {
			Point cur = todayQueue.poll();
			ripeTomato(cur.x, cur.y);
		}
		day++;
		// 하루가 지났으니 내일 큐를 오늘 큐로 넘김
		todayQueue.addAll(tomorrowQueue);
		// 내일 큐는 삭제
		tomorrowQueue.clear();
	}

	// 토마토 익히기
	private static void ripeTomato(int x, int y) {

		for (int i = 0; i < 4; i++) {
			int cx = x + dx[i];
			int cy = y + dy[i];

			if (validateCheck(cx, cy) && tomato[cy][cx] == 0) {
				tomato[cy][cx] = 1;
				tomorrowQueue.add(new Point(cx, cy));
			}
		}
	}

	// 유효성 검사
	private static boolean validateCheck(int x, int y) {
		if (x < 0 || y < 0 || x >= m || y >= n) {
			return false;
		}
		return true;
	}
	
	// 토마토가 다 익었는지 검사
	private static boolean tomatoRipeCheck() {
		for (int[] item: tomato) {
			for (int i: item) {
				if (i == 0) {
					return false;
				}
			}
		}
		return true;
	}
}

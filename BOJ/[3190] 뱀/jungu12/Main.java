import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] board;
	static Deque<Position> snakePosition = new ArrayDeque<>();
	static Deque<DirectionInfo> dirInfo = new ArrayDeque<>();
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	static class Position {
		int x;
		int y;

		Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class DirectionInfo {
		int time;
		String dir;

		DirectionInfo(int time, String dir) {
			this.time = time;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		board = new int[N + 1][N + 1];

		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			board[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 2;
		}

		int L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			dirInfo.add(new DirectionInfo(Integer.parseInt(st.nextToken()), st.nextToken()));
		}

		snakePosition.add(new Position(1, 1));
		board[0][0] = 1;
		playGame();
	}

	private static void playGame() {
		int dir = 3;
		int time = 0;
		while (true) {
			time++;
			Position pos = snakePosition.peekLast();
			int nx = pos.x + dx[dir];
			int ny = pos.y + dy[dir];
			
			//벽에 부딪히거나 자신의 몸통과 만난 경우 종료
			if (!isMoveable(nx, ny)) {
				System.out.println(time);
				break;
			}

			snakePosition.add(new Position(nx, ny));

			// 이동한 곳에 사과가 없는 경우
			if (board[nx][ny] == 0) {
				Position tail = snakePosition.poll();
				board[tail.x][tail.y] = 0;
			}
			
			//이동 완료
			board[nx][ny] = 1;

			// 뱀 방향 갱신
			if (!dirInfo.isEmpty() && dirInfo.peek().time == time) {
				dir = changeDir(dir, dirInfo.poll().dir);
			}
		}
	}

	private static int changeDir(int dir, String nextDir) {
		if (nextDir.equals("D")) {
			dir = (dir + 3) % 4;
		}
		if (nextDir.equals("L")) {
			dir = (dir + 1) % 4;
		}
		return dir;
	}

	private static boolean isMoveable(int nx, int ny) {
		if (nx <= 0 || nx > N || ny <= 0 || ny > N || board[nx][ny] == 1) {
			return false;
		}
		return true;
	}
}
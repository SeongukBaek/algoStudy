import java.io.*;
import java.util.*;

public class Easy2048 {
	static final int MOVECOUNT = 5;
	static final int MOVETYPE = 4;
	static int N;
	static int[][] board;
	static int max = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(board[i][j], max);
			}
		}

		moveBlocks(0, new int[MOVECOUNT]);
		System.out.println(max);
	}

	static void moveBlocks(int r, int[] order) {
		if (r == MOVECOUNT) {
			int[][] temp = copy();
	
			for (int i = 0; i < MOVECOUNT; i++) {
				switch (order[i]) {
				case 0:
					moveUp(temp);
					break;
				case 1:
					moveDown(temp);
					break;
				case 2:
					moveLeft(temp);
					break;
				case 3:
					moveRight(temp);
					break;
				}
			}
			return;
		}

		// 0: 상 , 1: 하, 2: 좌, 3: 우
		for (int i = 0; i < MOVETYPE; i++) {
			order[r] = i;
			moveBlocks(r+1,order);
		}
	}

	static int[][] copy() {
		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			temp[i] = board[i].clone();
		}
		return temp;
	}

	static void moveUp(int[][] board) {
		boolean[] isUnioned = new boolean[N];

		for (int i = 1; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if (board[i][j] == 0) {
					continue;
				}

				int curX = i;
				while (curX > 0 && board[curX - 1][j] == 0) {
					curX--;
				}

				moveOneBlock(curX, j, i, j, board);
				if (curX == 0) {
					continue;
				}

				if (!isUnioned[j] && board[curX-1][j] == board[curX][j]) {
					isUnioned[j] = true;
					unionBlock(curX-1, j, curX, j, board);
				}else {
					isUnioned[j] = false;
				}
			}
		}

	}

	static void moveDown(int[][] board) {
		boolean[] isUnioned = new boolean[N];
		
		for (int i = N - 2; i >= 0; i--) {
			for (int j = 0; j < N; j++) {

				if (board[i][j] == 0) {
					continue;
				}

				int curX = i;
				while (curX < N - 1 && board[curX + 1][j] == 0) {
					curX++;
				}

				moveOneBlock(curX, j, i, j, board);
				if (curX == N - 1) {
					continue;
				}

				if (!isUnioned[j] && board[curX + 1][j] == board[curX][j]) {
					isUnioned[j] = true;
					unionBlock(curX+1, j, curX, j, board);
				}else {
					isUnioned[j] = false;
				}
			}
		}

	}

	static void moveLeft(int[][] board) {
		boolean[] isUnioned = new boolean[N];

		for (int j = 1; j < N; j++) {
			for (int i = 0; i < N; i++) {

				if (board[i][j] == 0) {
					continue;
				}

				int curY = j;
				while (curY > 0 && board[i][curY - 1] == 0) {
					curY--;
				}

				moveOneBlock(i, curY, i, j, board);
				if (curY == 0) {
					continue;
				}
				
				if (!isUnioned[i] && board[i][curY - 1] == board[i][curY]) {
					isUnioned[i] = true;
					unionBlock(i, curY - 1, i, curY, board);
				}else {
					isUnioned[i] = false;
				}
			}
		}

	}

	static void moveRight(int[][] board) {
		boolean[] isUnioned = new boolean[N];

		for (int j = N - 2; j >= 0; j--) {
			for (int i = 0; i < N; i++) {

				if (board[i][j] == 0) {
					continue;
				}

				int curY = j;
				while (curY < N - 1 && board[i][curY + 1] == 0) {
					curY++;
				}
				moveOneBlock(i, curY, i, j, board);
				if (curY == N - 1) {
					continue;
				}

				if (!isUnioned[i] && board[i][curY + 1] == board[i][curY]) {
					isUnioned[i] = true;
					unionBlock(i, curY + 1, i, curY, board);
				}else {
					isUnioned[i] = false;
				}
			}
		}
	}

	static void unionBlock(int x1, int y1, int x2, int y2, int[][] board) {
		board[x1][y1] *= 2;
		board[x2][y2] = 0;

		max = Math.max(max, board[x1][y1]);
	}

	static void moveOneBlock(int curX, int curY, int originX, int originY, int[][] board) {
		if (curX != originX || curY != originY) {
			board[curX][curY] = board[originX][originY];
			board[originX][originY] = 0;
		}
	}

}
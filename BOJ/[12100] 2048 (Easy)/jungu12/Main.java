import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] board;
	static int max;

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static void swipeUp() {
		for (int i = 0; i < N; i++) {
			int index = 0;
			int before = 0;
			for (int j = 0; j < N; j++) {
				if (board[j][i] == 0) {
					continue;
				}
				if (before == board[j][i]) {
					board[index - 1][i] = before * 2;
					before = 0;
					board[j][i] = 0;
				} else {
					before = board[j][i];
					board[j][i] = 0;
					board[index][i] = before;
					index++;
				}
			}
		}
	}

	static void swipeDown() {
		for (int i = 0; i < N; i++) {
			int index = N - 1;
			int before = 0;
			for (int j = N - 1; j >= 0; j--) {
				if (board[j][i] == 0) {
					continue;
				}
				if (before == board[j][i]) {
					board[index + 1][i] = before * 2;
					before = 0;
					board[j][i] = 0;
				} else {
					before = board[j][i];
					board[j][i] = 0;
					board[index][i] = before;
					index--;
				}
			}
		}
	}

	static void swipeLeft() {
		for (int i = 0; i < N; i++) {
			int index = 0;
			int before = 0;
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 0) {
					continue;
				}
				if (before == board[i][j]) {
					board[i][index - 1] = before * 2;
					before = 0;
					board[i][j] = 0;
				} else {
					before = board[i][j];
					board[i][j] = 0;
					board[i][index] = before;
					index++;
				}
			}
		}
	}

	static void swipeRight() {
		for (int i = 0; i < N; i++) {
			int index = N - 1;
			int before = 0;
			for (int j = N - 1; j >= 0; j--) {
				if (board[i][j] == 0) {
					continue;
				}
				if (before == board[i][j]) {
					board[i][index + 1] = before * 2;
					before = 0;
					board[i][j] = before;
				} else {
					before = board[i][j];
					board[i][j] = 0;
					board[i][index] = before;
					index --;
				}
			}
		}
	}

	static int[][] copyBoard(int[][] board) {
		int[][] copyBoard = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copyBoard[i][j] = board[i][j];
			}
		}
		return copyBoard;
	}

	static void playGame(int cnt) {
		if (cnt == 5) {
			findMaxBlock();
			return;
		}
		int[][] copyBoard = copyBoard(board);
		swipeLeft();
		playGame(cnt + 1);
		board = copyBoard(copyBoard);
		swipeRight();
		playGame(cnt + 1);
		board = copyBoard(copyBoard);
		swipeUp();
		playGame(cnt + 1);
		board = copyBoard(copyBoard);
		swipeDown();
		playGame(cnt + 1);
		board = copyBoard(copyBoard);
	}

	static void findMaxBlock() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = Math.max(max, board[i][j]);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		playGame(0);
		System.out.println(max);
	}
}
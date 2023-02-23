import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] house;
	static int count;

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		house = new int[N][N];
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				house[j][i] = Integer.parseInt(st.nextToken());
			}
		}
	}

	//가로로 놓을 수 있는지
	static void checkState1(int x, int y, int state) {
		if (x + 1 < N && house[x + 1][y] == 0) {
			movePipe(x + 1, y, 1);
		}
	}

	//대각선으로 놓을 수 있는지
	static void checkState2(int x, int y, int state) {
		if (x + 1 < N && y + 1 < N && house[x + 1][y + 1] == 0 && house[x + 1][y] == 0 && house[x][y + 1] == 0) {
			movePipe(x + 1, y + 1, 2);
		}
	}

	//세로로 놓을 수 있는지
	static void checkState3(int x, int y, int state) {
		if (y + 1 < N && house[x][y + 1] == 0) {
			movePipe(x, y + 1, 3);
		}
	}

	//state : 1 -> 가로 state : 2 -> 대각선 state : 3 -> 세로
	static void movePipe(int x, int y, int state) {
		if (x == N - 1 && y == N - 1) {
			count++;
			return;
		}
		if (state == 1) {
			checkState1(x, y, state);
			checkState2(x, y, state);
		}
		if (state == 2) {
			checkState1(x, y, state);
			checkState2(x, y, state);
			checkState3(x, y, state);
		}
		if (state == 3) {
			checkState2(x, y, state);
			checkState3(x, y, state);
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		movePipe(1, 0, 1);
		System.out.println(count);
	}
}
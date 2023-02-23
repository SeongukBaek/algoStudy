import java.io.*;
import java.util.*;

public class CarryPipe {
	static int[][] house;
	static Pipe[][] pipes;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		house = new int[N][N];
		pipes = new Pipe[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				house[i][j] = Integer.parseInt(st.nextToken());
				if (house[i][j] == 1) {
					pipes[i][j] = new Pipe(0, 0, 0);
				}
			}
		}

		pipes[0][1] = new Pipe(1, 0, 0);
		pipes[0][0] = new Pipe(0, 0, 0);
		carryPipes();

		System.out.println(pipes[N - 1][N - 1].hor + pipes[N - 1][N - 1].dia + pipes[N - 1][N - 1].ver);

	}

	static void carryPipes() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if (pipes[i][j] != null) {
					continue;
				}

				int hor = 0;
				int dia = 0;
				int ver = 0;

				// 가로
				if (j - 1 >= 0) {
					hor = pipes[i][j - 1].hor + pipes[i][j - 1].dia;
				}

				// 대각선
				if (j - 1 >= 0 && i - 1 >= 0 && checkSpace(i, j)) {
					dia = pipes[i - 1][j - 1].hor + pipes[i - 1][j - 1].dia + pipes[i - 1][j - 1].ver;
				}

				// 세로
				if (i - 1 >= 0) {
					ver = pipes[i - 1][j].ver + pipes[i - 1][j].dia;
				}

				pipes[i][j] = new Pipe(hor, dia, ver);
			}
		}
	}

	static boolean checkSpace(int x, int y) {
		return (house[x - 1][y] != 1 && house[x][y - 1] != 1);
	}

	static class Pipe {
		int hor;
		int dia;
		int ver;

		public Pipe(int hor, int dia, int ver) {
			super();
			this.hor = hor;
			this.dia = dia;
			this.ver = ver;
		}

	}
}
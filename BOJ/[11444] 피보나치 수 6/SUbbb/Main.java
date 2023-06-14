import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static long[][] matrix = { { 1L, 1L }, { 1L, 0L } };
	private static final long MOD = 1000000007;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());

		if (N == 1) {
			System.out.println(1);
			return;
		}

		System.out.println(power(N - 1)[0][0] % MOD);
	}

	private static long[][] power(long y) {
		long[][] answer = new long[2][2];

		for (int i = 0; i < 2; i++) {
			answer[i][i] = 1L;
		}

		while (y > 0) {
			if ((y & 1) == 1) {
				answer = matrixByMatrix(answer, matrix);
			}
			matrix = matrixByMatrix(matrix, matrix);
			y >>= 1;
		}

		return answer;
	}

	private static long[][] matrixByMatrix(long[][] answer, long[][] arr2) {
		long[][] temp = new long[2][2];

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					temp[i][k] += (answer[i][j] * arr2[j][k]) % MOD;
				}
			}
		}

		return temp;
	}
}
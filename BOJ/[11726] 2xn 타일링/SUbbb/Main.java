import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int DIV = 10007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		if (N <= 2) {
			System.out.print(N);
			return;
		}

		int doublePrev = 1;
		int prev = 2;
		int now = 0;

		for (int index = 2; index < N; index++) {
			now = (doublePrev + prev) % DIV;
			doublePrev = prev;
			prev = now;
		}

		System.out.print(now);
	}
}
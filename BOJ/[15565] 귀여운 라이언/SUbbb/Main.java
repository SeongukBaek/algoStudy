import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");

		int N = Integer.parseInt(info[0]);
		int K = Integer.parseInt(info[1]);

		int[] dolls = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int start = 0;
		int end = 0;
		int minSet = N + 1;
		int ryan = 0;

		while (end < N) {
			if (dolls[end++] == 1) {
				ryan++;
			}

			while (ryan == K) {
				// 정답 갱신
				minSet = Math.min(minSet, end - start);

				// start 옮기기
				if (dolls[start++] == 1) {
					ryan--;
				}
			}
		}

		if (minSet == N + 1) {
			minSet = -1;
		}

		System.out.println(minSet);
	}
}
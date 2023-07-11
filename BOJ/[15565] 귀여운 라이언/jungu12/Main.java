import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int[] dolls;

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(findMinSize());
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		dolls = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			dolls[i] = Integer.parseInt(st.nextToken());
		}
	}

	private static int findMinSize() {
		int min = Integer.MAX_VALUE;
		int end = -1;
		int count = 0;
		for (int start = 0; start < N; start++) {
			while (end < N - 1 && count < K) {
				end++;
				if (dolls[end] == 1) {
					count++;
				}
			}
			
			if(count == K) {
				min = Math.min(min, end - start + 1);
			}
			
			if(dolls[start] == 1) {
				count--;
			}
		}

		if (min == Integer.MAX_VALUE) {
			return -1;
		}
		return min;
	}
}

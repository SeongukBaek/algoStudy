import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] evenObstacles, oddObstacles;
	static int[] even, odd;
	static int N, H;
	static int[] count;

	public static void main(String[] args) throws IOException {
		init();
		int min = countMinObstaclesToDestroy();
		System.out.println(min + " " + countMinSection(min));
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		evenObstacles = new int[H];
		oddObstacles = new int[H];
		even = new int[H];
		odd = new int[H];
		count = new int[H];

		for (int i = 0; i < N; i++) {
			if (i % 2 == 0) {
				evenObstacles[Integer.parseInt(br.readLine())]++; 
			}
			if (i % 2 != 0) {
				oddObstacles[Integer.parseInt(br.readLine())]++;
			}
		}
		
		//누적합 배열 만들기
		for (int i = 1; i < H; i++) {
			even[i] = even[i - 1] + evenObstacles[i];
			odd[i] = odd[i - 1] + oddObstacles[i];
		}
	}

	//각 구간들에서 부숴야 하는 장애물의 최소값을 반환
	private static int countMinObstaclesToDestroy() {
		for (int i = 0 ; i < H; i++) {
			count[i] += even[H - 1] - even[i];
			count[i] += odd[H - 1] - odd[H - i - 1];
		}
		return Arrays.stream(count).min().getAsInt();
	}

	//장애물을 최소로 부수고 지나갈 수 있는 구간의 개수를 반환
	private static int countMinSection(int min) {
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			if (count[i] == min) {
				cnt++;
			}
		}
		return cnt;
	}
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder answer = new StringBuilder();
		for (int test = 0; test < T; test++) {
			int n = Integer.parseInt(br.readLine());
			int[][] stickers = new int[2][n];

			for (int row = 0; row < 2; row++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int col = 0; col < n; col++) {
					stickers[row][col] = Integer.parseInt(st.nextToken());
				}
			}

			int nothing = 0;
			int up = stickers[0][0];
			int down = stickers[1][0];

			for (int index = 1; index < n; index++) {
				// 현재 열에서 아무 스티커도 뽑지 않는 경우
				// -> 이전 열에서 뽑은 스티커 중 최대 값 선택
				int tempNothing = Math.max(Math.max(nothing, up), down);

				// 현재 열에서 위쪽 스티커를 뽑는 경우
				// -> 이전 열에서 아무것도 뽑지 않거나, 아래쪽 스티커를 뽑은 경우 중 최대에 현재 스티커 더하기
				int tempUp = Math.max(nothing, down) + stickers[0][index];

				// 현재 열에서 아래쪽 스티커를 뽑는 경우
				// -> 이전 열에서 아무것도 뽑지 않거나, 위쪽 스티커를 뽑은 경우 중 최대에 현재 스티커 더하기
				int tempDown = Math.max(nothing, up) + stickers[1][index];

				nothing = tempNothing;
				up = tempUp;
				down = tempDown;
			}

			answer.append(Math.max(Math.max(nothing, up), down)).append("\n");
		}

		System.out.print(answer);
	}
}
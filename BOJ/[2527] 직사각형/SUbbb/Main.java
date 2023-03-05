import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	private static boolean[] horizontalCover;
	private static boolean[] verticalCover;
	private static int horizontalCount;
	private static int verticalCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder answer = new StringBuilder();

		for (int count = 0; count < 4; count++) {
			horizontalCover = new boolean[50001];
			verticalCover = new boolean[50001];
			horizontalCount = 0;
			verticalCount = 0;

			st = new StringTokenizer(br.readLine());
			fillCover(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			computeCoverCount(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			answer.append(determineCode()).append("\n");
		}
		System.out.print(answer);
	}

	/**
	 * 첫번째 직사각형의 정보를 받아 가로, 세로 체크 배열의 내용을 갱신한다.
	 * */
	private static void fillCover(int x, int y, int p, int q) {
		for (int index = x; index <= p; index++) {
			horizontalCover[index] = true;
		}
		for (int index = y; index <= q; index++) {
			verticalCover[index] = true;
		}
	}

	/**
	 * 두번째 직사각형의 정보를 받아 가로, 세로별 겹치는 부분의 개수를 구한다.
	 * */
	private static void computeCoverCount(int x, int y, int p, int q) {
		for (int index = x; index <= p; index++) {
			if (horizontalCover[index]) {
				horizontalCount++;
			}
		}
		for (int index = y; index <= q; index++) {
			if (verticalCover[index]) {
				verticalCount++;
			}
		}
	}

	/**
	 * 가로, 세로별 겹치는 부분의 개수로 해당되는 코드 문자를 반환한다.
	 * */
	private static char determineCode() {
		if (horizontalCount == 0 || verticalCount == 0) {
			return 'd';
		}

		if (horizontalCount == 1 && verticalCount == 1) {
			return 'c';
		}

		if (horizontalCount == 1 || verticalCount == 1) {
			return 'b';
		}
		return 'a';
	}
}
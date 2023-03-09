import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 0은 색종이를 붙이지 않아야 하는 칸, 1은 색종이를 붙여야 하는 칸, 2는 색종이를 붙인 칸
	private static final int[][] board = new int[10][10];
	private static int coloredPaperCount = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int x = 0; x < 10; x++) {
			st = new StringTokenizer(br.readLine());
			for (int y = 0; y < 10; y++) {
				board[x][y] = Integer.parseInt(st.nextToken());
			}
		}

		pasteColoredPaper(0, 0, new int[]{5,5,5,5,5}, 0);

		System.out.print(getAnswer());
	}

	/**
	 * 주어진 좌표에 색종이를 붙일 수 있는지 확인하고, 붙일 수 있는 최대 크기의 색종이를 우선적으로 붙인다.
	 * 이후에는 Y좌표 방향으로 1칸씩 이동하면서 재귀호출한다.
	 * */
	private static void pasteColoredPaper(int currentX, int currentY, int[] coloredPapers, int count) {
		// 다 탐색한 경우 정답을 갱신
		if (currentX == 9 && currentY == 10) {
			coloredPaperCount = Math.min(coloredPaperCount, count);
			return;
		}
		// 오른쪽으로 이동하다가 종이밖으로 벗어나면 아래 행으로 이동
		if (currentY == 10) {
			pasteColoredPaper(currentX + 1, 0, coloredPapers, count);
			return;
		}

		// 이미 구한 최소 개수보다 많아지는 경우는 더 이상 탐색하지 않는다.
		if (count >= coloredPaperCount) {
			return;
		}

		// 아직 색종이를 붙이지 않은 1인 칸이라면
		if (board[currentX][currentY] == 1) {
			// 큰 색종이부터 붙여보기
			for (int coloredPaper = 4; coloredPaper >= 0; coloredPaper--) {
				// 해당 색종이가 없거나, 현재 색종이를 붙일 수 없는 경우는 패스
				if (coloredPapers[coloredPaper] == 0 || !canPasteColoredPaper(coloredPaper, currentX, currentY)) {
					continue;
				}

				attachColoredPaper(currentX, currentY, coloredPaper);
				coloredPapers[coloredPaper]--;
				pasteColoredPaper(currentX, currentY + 1, coloredPapers, count + 1);
				detachColoredPaper(currentX, currentY, coloredPaper);
				coloredPapers[coloredPaper]++;
			}
			return;
		}
		pasteColoredPaper(currentX, currentY + 1, coloredPapers, count);
	}

	/**
	 * 현재 좌표에 주어진 크기의 색종이를 붙일 수 있는지 여부를 반환
	 * */
	private static boolean canPasteColoredPaper(int coloredPaper, int currentX, int currentY) {
		// 종이 범위를 넘어가는 경우는 불가능.
		if (currentX + coloredPaper + 1 > 10 || currentY + coloredPaper + 1 > 10) {
			return false;
		}

		for (int x = currentX; x < currentX + coloredPaper + 1; x++) {
			for (int y = currentY; y < currentY + coloredPaper + 1; y++) {
				if (board[x][y] != 1) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 해당 부분에 색종이를 붙였음을 표시
	 * */
	private static void attachColoredPaper(int currentX, int currentY, int coloredPaper) {
		for (int x = currentX; x < currentX + coloredPaper + 1; x++) {
			for (int y = currentY; y < currentY + coloredPaper + 1; y++) {
				board[x][y] = 2;
			}
		}
	}

	/**
	 * 해당 부분에 색종이를 붙이지 않았음을 표시
	 * */
	private static void detachColoredPaper(int currentX, int currentY, int coloredPaper) {
		for (int x = currentX; x < currentX + coloredPaper + 1; x++) {
			for (int y = currentY; y < currentY + coloredPaper + 1; y++) {
				board[x][y] = 1;
			}
		}
	}

	private static int getAnswer() {
		if (coloredPaperCount == Integer.MAX_VALUE) {
			return -1;
		}
		return coloredPaperCount;
	}
}
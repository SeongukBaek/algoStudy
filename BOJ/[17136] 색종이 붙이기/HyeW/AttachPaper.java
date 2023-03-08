import java.io.*;
import java.util.*;

public class AttachPaper{
	static final int PAPER_SIZE = 10;
	static final int COLORPAPER_TYPE = 5;
	static final int COLORPAPER_CNT = 5;
	static int[][] paper;
	static int[] paperCnt;
	static int minCnt = Integer.MAX_VALUE;
	static int space;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		paperCnt = new int[COLORPAPER_TYPE];
		paper = new int[PAPER_SIZE][PAPER_SIZE];
		for (int i = 0; i < PAPER_SIZE; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < PAPER_SIZE; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
				if (paper[i][j] == 1) {
					space++;
				}
			}
		}

		getMinPaperCnt(space, 0, paper, paperCnt);
		if (minCnt == Integer.MAX_VALUE) {
			minCnt = -1;
		}
		System.out.println(minCnt);
	}
    
	private static void getMinPaperCnt(int space, int cnt, int[][] paper, int[] paperCnt) {

		// 모든 칸에 색종이를 붙였을 경우 종료
		if (space == 0) {
			minCnt = Math.min(minCnt, cnt);
			return;
		}

		if (cnt >= minCnt) {
			return;
		}

		for (int i = 0; i < PAPER_SIZE; i++) {
			for (int j = 0; j < PAPER_SIZE; j++) {
				if (paper[i][j] != 1) {
					continue;
				}
				for (int size = COLORPAPER_TYPE; size > 0; size--) {
					// size크기의 색종이를 다 사용한 경우
					if (paperCnt[size - 1] >= 5) {
						continue;
					}

					int[][] copyPaper = attachColorPaper(i, j, paper, size);
					// 색종이를 붙이지 못하는 경우
					if (copyPaper == null) {
						continue;
					}

					paperCnt[size - 1]++;
					getMinPaperCnt(space - (size * size), cnt + 1, copyPaper, paperCnt);
					paperCnt[size - 1]--;
				}
        //모든 크기를 확인하고 나면 그 전 단계로 돌아가기
				return;
			}
		}
	}

	/**
	 * size크기의 색종이를 종이에 붙여준다.
	 * 
	 * @param 색종이를 붙여야 할 종이, 붙일 색종이의 크기
	 * @return 색종이를 붙이고 난 종이 반환 ( 붙이지 못하면 null 반환 )
	 */
	private static int[][] attachColorPaper(int x, int y, int[][] paper, int size) {
		int[][] copyPaper = copy(paper);

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (!arrayBoundsValidation(i + x, j + y)) {
					return null;
				}
				if (copyPaper[x + i][y + j] == 0) {
					return null;
				}
				copyPaper[x + i][y + j] = 0;
			}
		}

		return copyPaper;
	}

	private static boolean arrayBoundsValidation(int x, int y) {
		return (x >= 0 && y >= 0 && x < PAPER_SIZE && y < PAPER_SIZE);
	}

	private static int[][] copy(int[][] paper) {
		int[][] copy = new int[PAPER_SIZE][PAPER_SIZE];

		for (int i = 0; i < PAPER_SIZE; i++) {
			copy[i] = paper[i].clone();
		}

		return copy;
	}

}
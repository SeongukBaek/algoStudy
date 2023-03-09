import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	final static int PAPER_SIZE = 10;
	static int[][] paper = new int[PAPER_SIZE][PAPER_SIZE];
	static int[] coloredPaperNum = new int[] { 5, 5, 5, 5, 5 };
	static int min = Integer.MAX_VALUE;
	static int attachedPaperCount, oneCount;

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int i = 0; i < PAPER_SIZE; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < PAPER_SIZE; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
				if (paper[i][j] == 1) {
					oneCount++;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		attachPaper();
		if (min == Integer.MAX_VALUE) {
			min = -1;
		}
		System.out.println(min);
	}
	
	/**
	 * 1인 지점에서 붙일 수 있는 가장 큰 size의 색종이부터 붙인다.
	 * 해당 지점에서 붙일 수 있는 색종이가 없다면 반환 해준다.
	 */
	private static void attachPaper() {
		for (int i = 0; i < PAPER_SIZE; i++) {
			for (int j = 0; j < PAPER_SIZE; j++) {
				if (oneCount == 0) {
					min = Math.min(min, attachedPaperCount);
					return;
				}
				if (attachedPaperCount == 25 || attachedPaperCount >= min) {
					return;
				}
				if (paper[i][j] == 1) {
					for (int size = 5; size > 0; size--) {
                        //선택한 size의 색종이가 없는 경우
						if (coloredPaperNum[size - 1] == 0) {
							continue;
						}
                        //선택한 size의 색종이를 붙일 수 있는 경우
						if (checkCanAttach(i, j, size)) {
							int[][] copyPaper = copyPaper(); // 다음 depth로 가기전 배열 복사
							int tmpOneCount = oneCount; // paper[][]에서 남은 1의 개수 복사
							int tmp = attachedPaperCount; //사용한 색종이 수 복사
							int[] copyColoredPaperNum = coloredPaperNum.clone(); //size별 색종이의 남은 개수 복사
							attach(i, j, size);
							attachPaper();
							paper = copyPaper;
							oneCount = tmpOneCount;
							coloredPaperNum = copyColoredPaperNum.clone();
							attachedPaperCount = tmp;
						}
					}
                    //해당 좌표에서 모든 size의 색종이를 붙일 수 없는 경우 반환
					return;
				}
			}
		}
	}

	/**
	 * 색종이를 붙일 수 있는지 확인한다.
	 */
	private static boolean checkCanAttach(int x, int y, int size) {
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (i >= 10 || j >= 10 || paper[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	private static int[][] copyPaper() {
		int[][] copyPaper = new int[PAPER_SIZE][PAPER_SIZE];
		for (int i = 0; i < PAPER_SIZE; i++) {
			for (int j = 0; j < PAPER_SIZE; j++) {
				copyPaper[i][j] = paper[i][j];
			}
		}
		return copyPaper;
	}

	/**
	 * 색종이를 붙인다.
	 * 색종이를 붙인 곳의 paper는 0으로 바꾸어준다.
	 * 색종이를 사용한 개수를 증가, 남은 색종이 수를 감소 시켜준다.
	 */
	private static void attach(int x, int y, int size) {
		attachedPaperCount++;
		coloredPaperNum[size - 1]--;
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				paper[i][j] = 0;
				oneCount--;
			}
		}
	}
}
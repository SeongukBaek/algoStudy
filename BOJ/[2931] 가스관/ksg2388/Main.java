import java.io.*;
import java.util.*;

/*
 1. 불필요한 블록은 없으므로 탐색을 통해 블럭이 이어지지 않는 곳을 찾는다.
 2. 거기에 적절한 블록읖 찾아 끼운다.
 */

public class Main {
	static int n, m;
	static char[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Block target;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];

		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		checkBlock();
		System.out.print((target.x + 1) + " " + (target.y + 1) + " ");
		findBlock();
	}

	// 비어있는 곳 찾기
	public static void checkBlock() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// '-'블록의 경우 왼쪽, 오른쪽이 연결되어 있어야함
				if (map[i][j] == '-') {
					if (j - 1 >= 0 && map[i][j - 1] == '.') {
						target = new Block(i, j - 1);
						return;
					}
					if (j + 1 < m && map[i][j + 1] == '.') {
						target = new Block(i, j + 1);
						return;
					}
				}
				// '|'블록의 경우 위, 아래가 연결되어 있어야함
				if (map[i][j] == '|') {
					if (i - 1 >= 0 && map[i - 1][j] == '.') {
						target = new Block(i - 1, j);
						return;
					}
					if (i + 1 < n && map[i + 1][j] == '.') {
						target = new Block(i + 1, j);
						return;
					}
				}
				// '+'블록의 경우 위, 아래, 왼쪽, 오른쪽이 연결되어 있어야함
				if (map[i][j] == '+') {
					if (i - 1 >= 0 && map[i - 1][j] == '.') {
						target = new Block(i - 1, j);
						return;
					}
					if (i + 1 < n && map[i + 1][j] == '.') {
						target = new Block(i + 1, j);
						return;
					}
					if (j - 1 >= 0 && map[i][j - 1] == '.') {
						target = new Block(i, j - 1);
						return;
					}
					if (j + 1 < m && map[i][j + 1] == '.') {
						target = new Block(i, j + 1);
						return;
					}
				}
				// 1번 블록의 경우 아래쪽, 오른쪽이 연결되어 있어야함
				if (map[i][j] == '1') {
					if (i + 1 < n && map[i + 1][j] == '.') {
						target = new Block(i + 1, j);
						return;
					}
					if (j + 1 < m && map[i][j + 1] == '.') {
						target = new Block(i, j + 1);
						return;
					}
				}
				// 2번 블록의 경우 위쪽, 오른쪽이 연결되어 있어야함
				if (map[i][j] == '2') {
					if (i - 1 >= 0 && map[i - 1][j] == '.') {
						target = new Block(i - 1, j);
						return;
					}
					if (j + 1 < m && map[i][j + 1] == '.') {
						target = new Block(i, j + 1);
						return;
					}
				}
				// 3번 블록의 경우 위쪽, 왼쪽이 연결되어 있어야함
				if (map[i][j] == '3') {
					if (i - 1 >= 0 && map[i - 1][j] == '.') {
						target = new Block(i - 1, j);
						return;
					}
					if (j - 1 >= 0 && map[i][j - 1] == '.') {
						target = new Block(i, j - 1);
						return;
					}
				}
				// 4번 블록의 경우 아래쪽, 왼쪽이 연결되어 있어야함
				if (map[i][j] == '4') {
					if (i + 1 < n && map[i + 1][j] == '.') {
						target = new Block(i + 1, j);
						return;
					}
					if (j - 1 >= 0 && map[i][j - 1] == '.') {
						target = new Block(i, j - 1);
						return;
					}
				}
			}
		}
	}

	// 7가지 블록 놓고 비교
	public static void findBlock() {
		int cx = target.x;
		int cy = target.y;

		if (chechBlock1(cx, cy)) {
			System.out.println('|');
			return;
		}
		if (chechBlock2(cx, cy)) {
			System.out.println('-');
			return;
		}
		if (chechBlock3(cx, cy)) {
			System.out.println('+');
			return;
		}
		if (chechBlock4(cx, cy)) {
			System.out.println('1');
			return;
		}
		if (chechBlock5(cx, cy)) {
			System.out.println('2');
			return;
		}
		if (chechBlock6(cx, cy)) {
			System.out.println('3');
			return;
		}
		if (chechBlock7(cx, cy)) {
			System.out.println('4');
		}
	}

	// 블록 '|'를 놓은 경우 위, 아래, 오른쪽 ,왼쪽 검사
	public static boolean chechBlock1(int x, int y) {
		// 위, 아래가 없는 경우
		if (x - 1 < 0 || x + 1 >= n) {
			return false;
		}
		// 위쪽
		if (map[x - 1][y] == '.' || map[x - 1][y] == '-' || map[x - 1][y] == '2' || map[x - 1][y] == '3') {
			return false;
		}
		// 아래쪽
		if (map[x + 1][y] == '.' || map[x + 1][y] == '-' || map[x + 1][y] == '1' || map[x + 1][y] == '4') {
			return false;
		}
		// 왼쪽
		if (y - 1 >= 0) {
			if (map[x][y - 1] == '-' || map[x][y - 1] == '+' || map[x][y - 1] == '1' || map[x][y - 1] == '2') {
				return false;
			}
		}
		// 오른쪽
		if (y + 1 < m) {
			if (map[x][y + 1] == '-' || map[x][y + 1] == '+' || map[x][y + 1] == '3' || map[x][y + 1] == '4') {
				return false;
			}
		}
		return true;
	}

	// 블록 '-'를 놓은 경우 위, 아래, 오른쪽 ,왼쪽 검사
	public static boolean chechBlock2(int x, int y) {
		// 왼쪽, 오른쪽이 없는 경우
		if (y - 1 < 0 || y + 1 >= m) {
			return false;
		}
		// 위쪽
		if (x - 1 >= 0) {
			if (map[x - 1][y] == '|' || map[x - 1][y] == '+' || map[x - 1][y] == '1' || map[x - 1][y] == '3') {
				return false;
			}
		}

		// 아래쪽
		if (x + 1 < n) {
			if (map[x + 1][y] == '|' || map[x + 1][y] == '+' || map[x + 1][y] == '2' || map[x + 1][y] == '3') {
				return false;
			}
		}

		// 왼쪽
		if (map[x][y - 1] == '|' || map[x][y - 1] == '.' || map[x][y - 1] == '3' || map[x][y - 1] == '4') {
			return false;
		}
		// 오른쪽
		if (map[x][y + 1] == '|' || map[x][y + 1] == '.' || map[x][y + 1] == '1' || map[x][y + 1] == '2') {
			return false;
		}
		return true;
	}

	// 블록 '+'를 놓은 경우 위, 아래, 오른쪽 ,왼쪽 검사
	public static boolean chechBlock3(int x, int y) {
		// 왼쪽, 오른쪽, 위, 아래가 없는 경우
		if (y - 1 < 0 || y + 1 >= m || x - 1 < 0 || x + 1 >= n) {
			return false;
		}
		// 위쪽
		if (map[x - 1][y] == '.' || map[x - 1][y] == '-' || map[x - 1][y] == '2' || map[x - 1][y] == '3') {
			return false;
		}

		// 아래쪽
		if (map[x + 1][y] == '.' || map[x + 1][y] == '-' || map[x + 1][y] == '1' || map[x + 1][y] == '4') {
			return false;
		}

		// 왼쪽
		if (map[x][y - 1] == '.' || map[x][y - 1] == '|' || map[x][y - 1] == '3' || map[x][y - 1] == '4') {
			return false;
		}
		// 오른쪽
		if (map[x][y + 1] == '.' || map[x][y + 1] == '|' || map[x][y + 1] == '1' || map[x][y + 1] == '2') {
			return false;
		}
		return true;
	}

	// 블록 '1'를 놓은 아래, 오른쪽
	public static boolean chechBlock4(int x, int y) {
		// 오른쪽, 아래가 없는 경우
		if (y + 1 >= m || x + 1 >= n) {
			return false;
		}
		// 위쪽
		if (x - 1 >= 0) {
			if (map[x - 1][y] == '|' || map[x - 1][y] == '+' || map[x - 1][y] == '1' || map[x - 1][y] == '3') {
				return false;
			}
		}

		// 아래쪽
		if (map[x + 1][y] == '.' || map[x + 1][y] == '-' || map[x + 1][y] == '1' || map[x + 1][y] == '4') {
			return false;
		}

		// 왼쪽
		if (y - 1 >= 0) {
			if (map[x][y - 1] == '-' || map[x][y - 1] == '+' || map[x][y - 1] == '1' || map[x][y - 1] == '2') {
				return false;
			}
		}
		// 오른쪽
		if (map[x][y + 1] == '.' || map[x][y + 1] == '|' || map[x][y + 1] == '1' || map[x][y + 1] == '2') {
			return false;
		}
		return true;
	}

	// 블록 '2'를 놓은 아래, 오른쪽
	public static boolean chechBlock5(int x, int y) {
		// 위쪽, 오른쪽가 없는 경우
		if (y + 1 >= m || x - 1 < 0) {
			return false;
		}
		// 위쪽
		if (map[x - 1][y] == '.' || map[x - 1][y] == '-' || map[x - 1][y] == '2' || map[x - 1][y] == '3') {
			return false;
		}

		// 아래쪽
		if (x + 1 < n) {
			if (map[x + 1][y] == '|' || map[x + 1][y] == '+' || map[x + 1][y] == '2' || map[x + 1][y] == '3') {
				return false;
			}
		}

		// 왼쪽
		if (y - 1 >= 0) {
			if (map[x][y - 1] == '-' || map[x][y - 1] == '+' || map[x][y - 1] == '1' || map[x][y - 1] == '2') {
				return false;
			}
		}
		// 오른쪽
		if (map[x][y + 1] == '.' || map[x][y + 1] == '|' || map[x][y + 1] == '1' || map[x][y + 1] == '2') {
			return false;
		}
		return true;
	}

	// 블록 '3'를 놓은 위, 왼쪽
	public static boolean chechBlock6(int x, int y) {
		// 위쪽, 왼쪽가 없는 경우
		if (y - 1 < 0 || x - 1 < 0) {
			return false;
		}
		// 위쪽
		if (map[x - 1][y] == '.' || map[x - 1][y] == '-' || map[x - 1][y] == '2' || map[x - 1][y] == '3') {
			return false;
		}

		// 아래쪽
		if (x + 1 < n) {
			if (map[x + 1][y] == '|' || map[x + 1][y] == '+' || map[x + 1][y] == '2' || map[x + 1][y] == '3') {
				return false;
			}
		}

		// 왼쪽
		if (map[x][y - 1] == '.' || map[x][y - 1] == '|' || map[x][y - 1] == '3' || map[x][y - 1] == '4') {
			return false;
		}
		// 오른쪽
		if (y + 1 < m) {
			if (map[x][y + 1] == '-' || map[x][y + 1] == '+' || map[x][y + 1] == '3' || map[x][y + 1] == '4') {
				return false;
			}
		}
		return true;
	}

	// 블록 '4'를 놓은 아래쪽, 왼쪽
	// 아래쪽 왼쪽만 연결있으면 ok?
	public static boolean chechBlock7(int x, int y) {

		// 아래쪽, 왼쪽가 없는 경우
		if (y - 1 < 0 || x + 1 >= n) {
			return false;
		}

		
		// 위쪽
		if (x - 1 >= 0) {
			if (map[x - 1][y] == '|' || map[x - 1][y] == '+' || map[x - 1][y] == '1' || map[x - 1][y] == '4') {
				return false;
			}
		}
		// 아래쪽
		if (map[x + 1][y] == '.' || map[x + 1][y] == '-' || map[x + 1][y] == '1' || map[x + 1][y] == '4') {
			return false;
		}

		// 왼쪽
		if (map[x][y - 1] == '.' || map[x][y - 1] == '|' || map[x][y - 1] == '3' || map[x][y - 1] == '4') {
			return false;
		}

		// 오른쪽
		if (y + 1 < m) {
			if (map[x][y + 1] == '-' || map[x][y + 1] == '+' || map[x][y + 1] == '3' || map[x][y + 1] == '4') {
				return false;
			}
		}
		return true;
	}
}

class Block {
	int x;
	int y;

	Block(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
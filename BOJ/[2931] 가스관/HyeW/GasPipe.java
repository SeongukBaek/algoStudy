import java.io.*;
import java.util.*;

public class GasPipe {
	static int N, M;
	static char[][] europe;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static Queue<Node> gas;
	static Node removedBlock, start;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		europe = new char[N][M];
		for (int i = 0; i < N; i++) {
			europe[i] = br.readLine().toCharArray();
		}

		gas = new LinkedList<>();
		start = getStartNode();
		
		runGas(start);
		Node firstRemovedBlock = removedBlock;
		char block = findBlockType(firstRemovedBlock);
		
		
		System.out.println((firstRemovedBlock.x + 1) + " " + (firstRemovedBlock.y + 1) 
				+ " " + block);
	}

	/**
	 * 가스가 시작하는 가스관을 찾는다.
	 * 
	 * @return 모스코바와 만나는 첫번째 가스관 정보를 Node로 반환
	 */
	static Node getStartNode() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (europe[i][j] != 'M') {
					continue;
				}

				for (int dir = 0; dir < 4; dir++) {
					int dx = i + dr[dir];
					int dy = j + dc[dir];

					if (dx >= N || dy >= M || dx < 0 || dy < 0)
						continue;

					if (europe[dx][dy] == '.' || europe[dx][dy] =='Z')
						continue;

					return new Node(dx, dy, dir);
				
				}
			}
		}
		
		return null;
	}

	static boolean runGas(Node start) {
		boolean isFlow = true;
		
		visited = new boolean[N][M];
		gas.clear();
		gas.add(start);
		visited[start.x][start.y] = true;
		
		while (!gas.isEmpty()) {
			Node cur = gas.poll();

			switch (europe[cur.x][cur.y]) {
			case '|':
				isFlow = blockVer(cur);
				break;
			case '-':
				isFlow = blockHor(cur);
				break;
			case '+':
				isFlow = blockCross(cur);
				break;
			case '1':
				isFlow = block1(cur);
				break;
			case '2':
				isFlow = block2(cur);
				break;
			case '3':
				isFlow = block3(cur);
				break;
			case '4':
				isFlow = block4(cur);
			}

			if (!isFlow) {
				return false;
			}
		}
		
		return true;
	}

	/**
	 * 블록 '|'를 처리한다.
	 * 
	 * @param 현재 가스의 위치
	 * @return 가스 운반 가능여부를 boolean으로 반환
	 */
	static boolean blockVer(Node cur) {
		if(cur.dir == 0 || cur.dir == 1) {
			return checkPipe(cur.x, cur.y, cur.dir);
		}	
		return false;
	}

	static boolean blockHor(Node cur) {
		if(cur.dir == 2 || cur.dir == 3) {
			return checkPipe(cur.x, cur.y, cur.dir);
		}
		return false;
	}

	static boolean blockCross(Node cur) {
		int inputDir = cur.dir;
		
		if(cur.dir == 3 || cur.dir == 1) {
			inputDir--;
		}else {
			inputDir++;
		}
		
		for (int i = 0; i < 4; i++) {
			if (i == inputDir) {
				continue;
			}

			if (!checkPipe(cur.x, cur.y, i)) {
				return false;
			}
		}

		return true;
	}

	static boolean block1(Node cur) {
		int dir = -1;

		if (cur.dir == 0) {
			dir = 3;
		} else if (cur.dir == 2) {
			dir = 1;
		}else {
			return false;
		}
	
		return checkPipe(cur.x, cur.y, dir);
	}
	
	static boolean block2(Node cur) {
		int dir = -1;

		if (cur.dir == 1) {
			dir = 3;
		}  else if (cur.dir == 2) {
			dir = 0;
		} else {
			return false;
		}

		return checkPipe(cur.x, cur.y, dir);
	}

	static boolean block3(Node cur) {
		int dir = -1;

		if (cur.dir == 1) {
			dir = 2;
		} else if (cur.dir == 3) {
			dir = 0;
		} else {
			return false;
		}
		
		return checkPipe(cur.x, cur.y, dir);
	}

	static boolean block4(Node cur) {
		int dir = -1;

		if (cur.dir == 0) {
			dir = 2;
		} else if (cur.dir == 3) {
			dir = 1;
		} else {
			return false;
		}

		return checkPipe(cur.x, cur.y, dir);
	}

	static char findBlockType(Node removedBlock) {
		
		char up = 'x';
		if(removedBlock.x-1 >= 0) {
			up = europe[removedBlock.x-1][removedBlock.y];
		}
		char down = 'x';
		if(removedBlock.x+1 < N) {
			down = europe[removedBlock.x+1][removedBlock.y];
		}
		char left = 'x';
		if(removedBlock.y-1 >= 0) {
			left = europe[removedBlock.x][removedBlock.y-1];
		}
		char right = 'x';
		if(removedBlock.y+1 < M) {
			right = europe[removedBlock.x][removedBlock.y+1];
		}

		boolean upCheck =  (up == '|' || up == '+' || up == '1' || up =='4');
		boolean downCheck =  (down == '|' || down == '+' || down == '2' || down == '3');
		boolean leftCheck = (left == '-' || left == '+' || left == '1' || left == '2');
		boolean rightCheck = (right == '-' || right == '+' || right == '2' || right == '4');
		
        //모양별 탐색
		if(upCheck && downCheck && leftCheck && rightCheck) {
			return '+';
		}
		if(upCheck && downCheck) {
			return '|';
		}
		if(leftCheck && rightCheck) {
			return '-';
		}
		if(downCheck && rightCheck) {
			return '1';
		}
		if(upCheck && rightCheck) {
			return '2';
		}
		if(leftCheck && upCheck) {
			return '3';
		}
		if(leftCheck&&downCheck) {
			return '4';
		}
		
		return 'x';
	}
	
	static boolean checkPipe(int x, int y, int dir) {

		int dx = x + dr[dir];
		int dy = y + dc[dir];

		if (europe[dx][dy] == '.' || europe[dx][dy] == 'M') {
			removedBlock = new Node(dx, dy, dir);
			return false;
		}

		if (ableToFlow(dx, dy)) {
			visited[x][y] = true;
			gas.add(new Node(dx, dy, dir));
		}

		return true;
	}

	static boolean ableToFlow(int x, int y) {
		// 이미 방문한 곳이고 도착지점이라면 확인할 필요가 없다.
		return (!visited[x][y] && europe[x][y] != 'Z');
	}

	static boolean arrayBoundsValidation(int x, int y) {
		return ( x < N && y < M && x >= 0 && y >= 0 );
	}
    
	static class Node {
		int x;
		int y;
		int dir;

		public Node(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
}
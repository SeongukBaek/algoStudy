import java.io.*;
import java.util.*;

public class Main {
	static int n, result;
	static int[][] map;
	static int[][] temp;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visited = new boolean[5];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

//		temp = copyArray(map);
//		runCommand(0);
//
//		for (int[] a : temp) {
//			for (int b : a) {
//				System.out.print(b + " ");
//			}
//			System.out.println();
//		}
		moveBlock(0, new int[5]);
		System.out.println(result);
	}

	public static void moveBlock(int depth, int[] cmd) {
		if (depth == 5) {
			temp = copyArray(map);
			for (int c : cmd) {
				runCommand(c);
			}
			// 배열의 최대값 찾기
			result = Math.max(result, findMaxValue());
			return;
		}

		for (int i = 0; i < 4; i++) {
			cmd[depth] = i;
			moveBlock(depth + 1, cmd);
			cmd[depth] = 0;
		}
	}
	
	public static int findMaxValue() {
		int max = 0;
		
		for (int[]values : temp) {
			for (int value: values) {
				max = Math.max(max, value);
			}
		}
		
		return max;
	}

	public static void runCommand(int c) {
		// 위쪽으로 이동
		if (c == 0) {
			moveUp();
		}
		// 오른쪽 이동
		if (c == 1) {
			moveRight();
		}
		// 아래쪽 이동
		if (c == 2) {
			moveDown();
		}
		//왼쪽 이동
		if (c == 3) {
			moveLeft();
		}

	}

	public static void moveUp() {
		Queue<Integer> queue = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			int[] next = new int[n];
			int idx = 0;
			for (int j = 0; j < n; j++) {
				if (temp[j][i] == 0) {
					continue;
				}
				// 큐가 빈 경우
				if (queue.isEmpty()) {
					queue.add(temp[j][i]);
					continue;
				}
				// 큐에 값이 있고 그 값이 새로들어온 값과 같은 경우
				if (queue.peek() == temp[j][i]) {
					next[idx++] = queue.poll() * 2;
				} else {
					next[idx++] = queue.poll();
					queue.add(temp[j][i]);
				}
			}
			if (!queue.isEmpty()) {
				next[idx++] = queue.poll();
			}
			
			for (int j = 0; j < n; j++) {
				temp[j][i] = next[j];
			}
		}
	}

	public static void moveRight() {
		Queue<Integer> queue = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			int[] next = new int[n];
			int idx = n - 1;
			for (int j = n - 1; j >= 0; j--) {
				if (temp[i][j] == 0) {
					continue;
				}
				// 큐가 빈 경우
				if (queue.isEmpty()) {
					queue.add(temp[i][j]);
					continue;
				}
				// 큐에 값이 있고 그 값이 새로들어온 값과 같은 경우
				if (queue.peek() == temp[i][j]) {
					next[idx--] = queue.poll() * 2;
				} else {
					next[idx--] = queue.poll();
					queue.add(temp[i][j]);
				}
			}
			if (!queue.isEmpty()) {
				next[idx--] = queue.poll();
			}
			for (int j = 0; j < n; j++) {
				temp[i][j] = next[j];
			}
		}
	}
	
	public static void moveDown() {
		Queue<Integer> queue = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			int[] next = new int[n];
			int idx = n - 1;
			for (int j = n - 1; j >= 0; j--) {
				if (temp[j][i] == 0) {
					continue;
				}
				// 큐가 빈 경우
				if (queue.isEmpty()) {
					queue.add(temp[j][i]);
					continue;
				}
				// 큐에 값이 있고 그 값이 새로들어온 값과 같은 경우
				if (queue.peek() == temp[j][i]) {
					next[idx--] = queue.poll() * 2;
				} else {
					next[idx--] = queue.poll();
					queue.add(temp[j][i]);
				}
			}
			if (!queue.isEmpty()) {
				next[idx--] = queue.poll();
			}
			
			for (int j = 0; j < n; j++) {
				temp[j][i] = next[j];
			}
		}
	}
	
	public static void moveLeft() {
		Queue<Integer> queue = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			int[] next = new int[n];
			int idx = 0;
			for (int j = 0; j < n; j++) {
				if (temp[i][j] == 0) {
					continue;
				}
				// 큐가 빈 경우
				if (queue.isEmpty()) {
					queue.add(temp[i][j]);
					continue;
				}
				// 큐에 값이 있고 그 값이 새로들어온 값과 같은 경우
				if (queue.peek() == temp[i][j]) {
					next[idx++] = queue.poll() * 2;
				} else {
					next[idx++] = queue.poll();
					queue.add(temp[i][j]);
				}
			}
			if (!queue.isEmpty()) {
				next[idx++] = queue.poll();
			}
			for (int j = 0; j < n; j++) {
				temp[i][j] = next[j];
			}
		}
	}

	public static int[][] copyArray(int[][] arr) {
		int[][] temp = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				temp[i][j] = arr[i][j];
			}
		}
		return temp;
	}
}
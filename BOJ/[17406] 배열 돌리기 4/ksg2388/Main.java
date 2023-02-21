import java.io.*;
import java.util.*;

public class Main {
	static int n, m, k;
	static int[][] arr;
	static String[] command;
	static boolean[] visited;
	static int result = 10000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		command = new String[k];
		visited = new boolean[k];

		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; ++j) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// r c s 입력 받기
		for (int i = 0; i < k; ++i) {
			command[i] = br.readLine();
		}
		
		checkArrayMinValue(0, new ArrayList<Integer>());
		System.out.println(result);
	}

	public static int[][] rotateArray(int r, int c, int s) {
		int size = s * 2 + 1;
		int[][] temp = new int[size][size];

		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				temp[i][j] = arr[r - s - 1 + i][c - s - 1 + j];
			}
		}

		temp = rotate(temp, size);

		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				arr[r - s - 1 + i][c - s - 1 + j] = temp[i][j];
			}
		}

		return arr;
	}

	public static int[][] rotate(int[][] arr, int size) {
		int[][] temp = new int[size][size];
		
		for (int i = 0; i < size / 2; i++) {
			// 왼쪽 방향 이동
			for (int j = size - i - 1; j >=  1 + i; j--) {
				temp[i][j] = arr[i][j - 1];
			}
			// 오른쪽 방향 이동
			for (int j = i; j < size - i - 1; j++) {
				temp[size - i - 1][j] = arr[size - i - 1][j + 1];
			}
			// 아래 방향 이동
			for (int j = size - i - 1; j >= 1 + i; j--) {
				temp[j][size - i - 1] = arr[j - 1][size - i - 1];
			}
			// 위 방향 이동
			for (int j = i; j < size - i - 1; j++) {
				temp[j][i] = arr[j + 1][i];
			}
		}
		temp[size / 2][size / 2] = arr[size / 2][size / 2];

		return temp;
	}
	
	public static void checkArrayMinValue(int depth, List<Integer> idx) {
		if (depth == k) {
			// 배열 돌리기 후 비교
			int[][] temp = new int[n][m];
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < m; ++j) {
					temp[i][j] = arr[i][j];
				}
			}
			
			for (int i = 0; i < k; ++i) {
				String[] cmd = command[idx.get(i)].split(" ");
				int r = Integer.parseInt(cmd[0]);
				int c = Integer.parseInt(cmd[1]);
				int s = Integer.parseInt(cmd[2]);
				arr = rotateArray(r, c, s);
			}
			// 최소값 구하기
			for (int[] a : arr) {
				int sum = 0;
				for (int b: a) {
					sum += b;
				}
				if (sum < result) {
					result = sum;
				}
			}
			arr = temp;
			return;
		}
		
		for (int i = 0; i < k; ++i) {
			if (!visited[i]) {
				visited[i] = true;
				idx.add(i);
				checkArrayMinValue(depth + 1, idx);
				visited[i] = false;
				idx.remove(depth);
			}
		}	
	}
}
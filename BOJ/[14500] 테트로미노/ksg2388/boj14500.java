import java.io.*;
import java.util.*;

public class boj14500 {
	private static int n, m, result;
	private static int[][] tetromino;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		tetromino = new int[n][m];

		// 입출력 부분
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				tetromino[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				checkTet1(j, i);
				checkTet2(j, i);
				checkTet3(j, i);
				checkTet4(j, i);
				checkTet5(j, i);
			}
		}
		
		System.out.println(result);
	}

	// ㅡ 모양 검사
	private static void checkTet1(int x, int y) {
		int temp1 = tetromino[y][x], temp2 = tetromino[y][x];
		// 세로 값 구하기
		if (y + 3 < n) {
			for (int i = 1; i < 4; i++) {
				temp1 += tetromino[y + i][x];
			}
		}

		// 가로 값 구하기
		if (x + 3 < m) {
			for (int i = 1; i < 4; i++) {
				temp2 += tetromino[y][x + i];
			}
		}

		int sum = Math.max(temp1, temp2);
		if (result < sum) {
			result = sum;
		}
	}

	// ㅁ 모양 검사
	private static void checkTet2(int x, int y) {
		if (y + 1 < n && x + 1 < m) {
			int temp = tetromino[y][x] + tetromino[y + 1][x] + tetromino[y][x + 1] + tetromino[y + 1][x + 1];
			if (result < temp) {
				result = temp;
			}
		}
	}

	// L 모양 검사, 4방향 검사
	private static void checkTet3(int x, int y) {
		int temp1 = tetromino[y][x], temp2 = tetromino[y][x], temp3 = tetromino[y][x], temp4 = tetromino[y][x],
				temp5 = tetromino[y][x], temp6 = tetromino[y][x], temp7 = tetromino[y][x], temp8 = tetromino[y][x];

		//1
		if (y + 2 < n && x + 1 < m)
			temp1 += tetromino[y + 1][x] + tetromino[y + 2][x] + tetromino[y + 2][x + 1];
		//2 
		if (y - 2 >= 0 && x - 1 >= 0)
			temp2 += tetromino[y - 1][x] + tetromino[y - 2][x] + tetromino[y - 2][x - 1];
		//3
		if (y - 1 >= 0 && x + 2 < m)
			temp3 += tetromino[y][x + 1] + tetromino[y][x + 2] + tetromino[y - 1][x + 2];
		//4
		if (y + 1 < n && x - 2 >= 0) {
			temp4 += tetromino[y][x - 1] + tetromino[y][x - 2] + tetromino[y + 1][x - 2];
		}
		
		//5
		if (y + 2 < n && x - 1 >= 0) {
			temp5 += tetromino[y + 1][x] + tetromino[y + 2][x] + tetromino[y + 2][x - 1];
		}
		//6
		if (y - 1 >= 0 && x - 2 >= 0) {
			temp6 += tetromino[y][x - 1] + tetromino[y][x - 2] + tetromino[y - 1][x - 2];
		}
		//7
		if (y - 2 >= 0 && x + 1 < m) {
			temp7 += tetromino[y - 1][x] + tetromino[y - 2][x] + tetromino[y - 2][x + 1];
		}
		//8
		if (y + 1 < n && x + 2 < m) {
			temp8 += tetromino[y][x + 1] + tetromino[y][x + 2] + tetromino[y + 1][x + 2];
		}
			
		
		int sum = Math.max(Math.max(Math.max(temp1, temp2), Math.max(temp3, temp4)), Math.max(Math.max(temp5, temp6), Math.max(temp7, temp8)));
		if (result < sum) {
			result = sum;
		}
	}
	
	// l_
	// 	  ㅣ모양 검사, 2방향 검사
	private static void checkTet4(int x, int y) {
		int temp1 = tetromino[y][x], temp2 = tetromino[y][x], temp3 = tetromino[y][x], temp4 = tetromino[y][x];
		
		//1
		if (y + 2 < n && x + 1 < m)
			temp1 += tetromino[y + 1][x] + tetromino[y + 1][x + 1] + tetromino[y + 2][x + 1];
		//2
		if (y + 1 < n && x - 2 >= 0)
			temp2 += tetromino[y][x - 1] + tetromino[y + 1][x - 1] + tetromino[y + 1][x - 2];
		
		//3
		if (y + 2 < n && x - 1 >= 0)
			temp3 += tetromino[y + 1][x] + tetromino[y + 1][x - 1] + tetromino[y + 2][x - 1];
		//4
		if (y - 1 >= 0 && x - 2 >= 0)
			temp4 += tetromino[y][x - 1] + tetromino[y - 1][x - 1] + tetromino[y - 1][x - 2];
		
		int sum = Math.max(Math.max(temp1, temp2), Math.max(temp3, temp4));
		if (result < sum) {
			result = sum;
		}
	}
	
	// ㅗ 모양 검사, 4방향 검사
	private static void checkTet5(int x, int y) {
		int temp1 = tetromino[y][x], temp2 = tetromino[y][x], temp3 = tetromino[y][x], temp4 = tetromino[y][x];

		//1
		if (y + 1 < n && x - 2 >= 0)
			temp1 += tetromino[y][x - 1] + tetromino[y][x - 2] + tetromino[y + 1][x - 1];
		//2 
		if (y - 2 >= 0 && x - 1 >= 0)
			temp2 += tetromino[y - 1][x] + tetromino[y - 2][x] + tetromino[y - 1][x - 1];
		//3
		if (y - 1 >= 0 && x + 2 < m)
			temp3 += tetromino[y][x + 1] + tetromino[y][x + 2] + tetromino[y - 1][x + 1];
		//4
		if (y + 2 < n && x + 1 < m)
			temp4 += tetromino[y + 1][x] + tetromino[y + 2][x] + tetromino[y + 1][x + 1];
		
		int sum = Math.max(Math.max(temp1, temp2), Math.max(temp3, temp4));
		if (result < sum) {
			result = sum;
		}
	}
}

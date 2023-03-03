import java.io.*;
import java.util.Arrays;

public class Main_12100{
	
	static int N, max;
	static int[][] matrix;
	static int[] dList;
	static int[][] copy;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		matrix = new int[N][N];
		copy = new int[N][N];
		dList = new int[5];
		
		for(int i = 0; i < N; i++) {
			String[] s = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		copyMatrix(matrix, copy);
		
		perm(0, 0);
		//playGame();
		System.out.println(max);
		
		br.close();
	}
	
	static void playGame() {
		
		for(int i = 0; i < 5; i++) {
			if(dList[i] == 0) {
				moveUp();
			}
			else if(dList[i] == 1) {
				moveRight();
			}
			else if(dList[i] == 2) {
				moveDown();
			}
			else if(dList[i] == 3){
				moveLeft();
			}
		}
	}
	
	static void moveUp() {
		int index;
		int[][] temp = new int[N][N];
		
		for(int i = 0; i < N; i++) { // 모든 열 다 땡겨줄 것 
			for(int j = 0; j < N; j++) {
				
				// 만약 0이 아닌 숫자가 나오면 
				if(matrix[j][i] != 0) {
					index = j; // 그 숫자 인덱스 찍어두고 
					
					for(int k = j+1; k < N; k++) { // 그 다음부터 돌면서 
						
						if(matrix[k][i] != 0) {
							if(matrix[k][i] == matrix[index][i]) { // 만약 같은 숫자가 나오면
								matrix[index][i] = matrix[index][i] * 2; // 찍어둔 자리에 더해서 넣기 
								matrix[k][i] = 0; // 더한 숫자 자리는 0으로 만들기 
								j = k; // 그 뒤부터 다시 탐색 
								break;
							}
							else {
								break;
							}
						}
					}
				}
			}
		}
		
		// 0으로 된 빈공간 없이 숫자 땡겨주기 
		for(int i = 0; i < N; i++) {
			int idx = 0;
					
			for(int j = 0; j < N; j++) {
				// 2차원 배열 하나 더 만들어서 0이 아닌 값이 나올때마다 추가
				if(matrix[j][i] != 0) {
					temp[idx][i] = matrix[j][i];
					idx++;
				}
			}
		}
		copyMatrix(temp, matrix);	
	}
	
	static void moveDown() {
		int index;
		int[][] temp = new int[N][N];
		
		for(int i = 0; i < N; i++) { // 모든 열 다 땡겨줄 것 
			for(int j = N-1; j >= 0; j--) {
				
				// 만약 0이 아닌 숫자가 나오면 
				if(matrix[j][i] != 0) {
					index = j; // 그 숫자 인덱스 찍어두고 
					
					for(int k = j-1; k >= 0; k--) { // 그 다음부터 돌면서 
						
						if(matrix[k][i] != 0) {
							if(matrix[k][i] == matrix[index][i]) { // 만약 같은 숫자가 나오면
								matrix[index][i] = matrix[index][i] * 2; // 찍어둔 자리에 더해서 넣기 
								matrix[k][i] = 0; // 더한 숫자 자리는 0으로 만들기 
								j = k; // 그 뒤부터 다시 탐색 
								break;
							}
							else {
								break;
							}
						}
					}
				}
			}
		}
			
		
		// 0으로 된 빈공간 없이 숫자 땡겨주기 
		for(int i = 0; i < N; i++) {
			int idx = N-1;
					
			for(int j = N-1; j >= 0; j--) {
				// 2차원 배열 하나 더 만들어서 0이 아닌 값이 나올때마다 추가
				if(matrix[j][i] != 0) {
					temp[idx][i] = matrix[j][i];
					idx--;
				}
			}
		}
		copyMatrix(temp, matrix);	
	}
	
	static void moveLeft() {
		int index;
		int[][] temp = new int[N][N];
		
		for(int i = 0; i < N; i++) { // 모든 행 다 땡겨줄 것 
			for(int j = 0; j < N; j++) {
				
				// 만약 0이 아닌 숫자가 나오면 
				if(matrix[i][j] != 0) {
					index = j; // 그 숫자 인덱스 찍어두고 
					
					for(int k = j+1; k < N; k++) { // 그 다음부터 돌면서 
						
						if(matrix[i][k] != 0) {
							if(matrix[i][k] == matrix[i][index]) { // 만약 같은 숫자가 나오면
								matrix[i][index] = matrix[i][index] * 2; // 찍어둔 자리에 더해서 넣기 
								matrix[i][k] = 0; // 더한 숫자 자리는 0으로 만들기 
								j = k; // 그 뒤부터 다시 탐색 
								break;
							}
							else {
								break;
							}
						}
					}
				}
			}
		}
		
		// 0으로 된 빈공간 없이 숫자 땡겨주기 
		for(int i = 0; i < N; i++) {
			int idx = 0;
					
			for(int j = 0; j < N; j++) {
				// 2차원 배열 하나 더 만들어서 0이 아닌 값이 나올때마다 추가
				if(matrix[i][j] != 0) {
					temp[i][idx] = matrix[i][j];
					idx++;
				}
			}
		}
		copyMatrix(temp, matrix);
	}
	
	static void moveRight() {
		int index;
		int[][] temp = new int[N][N];
		
		for(int i = 0; i < N; i++) { // 모든 행 다 땡겨줄 것 
			for(int j = N-1; j >= 0; j--) {
				
				// 만약 0이 아닌 숫자가 나오면 
				if(matrix[i][j] != 0) {
					index = j; // 그 숫자 인덱스 찍어두고 
					
					for(int k = j-1; k >= 0; k--) { // 그 다음부터 돌면서 
						
						if(matrix[i][k] != 0) {
							if(matrix[i][k] == matrix[i][index]) { // 만약 같은 숫자가 나오면
								matrix[i][index] = matrix[i][index] * 2; // 찍어둔 자리에 더해서 넣기 
								matrix[i][k] = 0; // 더한 숫자 자리는 0으로 만들기 
								j = k; // 그 뒤부터 다시 탐색 
								break;
							}
							else {
								break;
							}
						}
					}
				}
			}
		}
		
		// 0으로 된 빈공간 없이 숫자 땡겨주기 
		for(int i = 0; i < N; i++) {
			int idx = N-1;
					
			for(int j = N-1; j >= 0; j--) {
				// 2차원 배열 하나 더 만들어서 0이 아닌 값이 나올때마다 추가
				if(matrix[i][j] != 0) {
					temp[i][idx] = matrix[i][j];
					idx--;
				}
			}
		}
		copyMatrix(temp, matrix);
	}
	
	static void copyMatrix(int[][] a, int[][] b) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				b[i][j] = a[i][j];
			}
		}
	}
	
	static void findMax() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				max = Math.max(max, matrix[i][j]);
			}
		}
	}
	
	// 4방향으로 5개 뽑는 경우의 수 구하기 
	static void perm(int n, int index) {
		
		if(n == 5) { // 5개 뽑았으면 
			playGame(); // 해당 조합따라서 게임 시작(이동)
			findMax(); // 게임 끝나면 max값 찾기 
			
			copyMatrix(copy, matrix); // 맵 원래대로 돌려놓기 
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			dList[index] = i;
			perm(n+1, index+1);
		}
	}
}

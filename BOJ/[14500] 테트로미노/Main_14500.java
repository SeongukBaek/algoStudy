package algo;

import java.util.*;
import java.io.*;

public class Main_14500 {
	// 검사할 방향의 x좌표 y좌표 (왼, 위, 오, 아)
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
	static boolean[][] visit; // 방문했는지 안했는지 
	static int[][] matrix; // 숫자 저장 
	static int N, M;
	static int max;
	
	
	static void dfs(int n, int i, int j, int sum) {
		
		if(n == 4) {
			//System.out.println("-------------"+sum);
			return;
		}
		
		for(int a = 0; a<4; a++) {
			int xx = i+dx[a];
			int yy = j+dy[a];
			
			if(xx >= 0 & xx < N) {
				if(yy >= 0 && yy < M) {
					if(!visit[xx][yy]) {
						visit[xx][yy] = true;
						//System.out.println(matrix[xx][yy]);
						sum += matrix[xx][yy];
						if(sum > max)
							max = sum;
						dfs(n+1, xx, yy, sum);
						visit[xx][yy] = false;
						sum -= matrix[xx][yy];
					}
				}
			}
		}
	}
	
	
	// 예외 모양 처리 
	static void exshape(int i, int j) {
		int sum = matrix[i][j];
		
		if((i + 1 < N && i >= 0) && (j - 1 >= 0 && j + 1 < M)) {
			sum += matrix[i][j-1] + matrix[i+1][j] + matrix[i][j+1];
			if(sum > max) max = sum;
		}
		sum = matrix[i][j];
		
		if((i - 1 >= 0 && i < N) && (j - 1  >= 0 && j + 1 < M)) {
			sum += matrix[i][j-1] + matrix[i-1][j] + matrix[i][j+1];
			if(sum > max) max = sum;
		}
		
		sum = matrix[i][j];
		if((i - 1 >= 0 && i + 1 < N) && (j + 1 < M && j >= 0)) {
			sum += matrix[i-1][j] + matrix[i][j+1] + matrix[i+1][j];
			if(sum > max) max = sum;
		} 
		
		sum = matrix[i][j];
		if((i - 1 >= 0 && i + 1 < N) && (j - 1 >= 0 && j < M)) {
			sum += matrix[i][j-1] + matrix[i-1][j] + matrix[i+1][j];
			if(sum > max) max = sum;
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		matrix = new int[N][M];
		visit = new boolean[N][M];
		
		// 매트릭스에 숫자 넣기 
		for(int i = 0; i<N; i++) {
			String[] s = br.readLine().split(" ");
			
			for(int j = 0; j<M; j++) {
				matrix[i][j] = Integer.parseInt(s[j]);
				visit[i][j] = false;
			}
		}
		
		max = 0;
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(!visit[i][j]) {
					visit[i][j] = true;
					dfs(1, i, j, matrix[i][j]);
					visit[i][j] = false;
				}
				exshape(i, j);
			}
			
		}
		
		System.out.println(max);
	}
}

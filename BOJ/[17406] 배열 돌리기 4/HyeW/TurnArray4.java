import java.io.*;
import java.util.*;

public class TurnArray4 {
	static int[][] arr;
	static int N,M,K;
	static int MIN = Integer.MAX_VALUE;
	static Rotation[] rotations;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		rotations = new Rotation[K];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int r, c, s;
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken())-1;
			c = Integer.parseInt(st.nextToken())-1;
			s = Integer.parseInt(st.nextToken());
			rotations[i] = new Rotation(r,c,s);
		}
		
		order(0, arr, new boolean[K]);
		
		System.out.println(MIN);
	}
	
	static int[][] rotateArr(int r, int c , int s, int[][] array){
		int[][] temp = new int[N][M];
		int x = r - s;
		int y = c - s;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				temp[i][j] = array[i][j];
			}
		}
		temp[r][c] = array[r][c];
		for(int i = 0; i < s; i++) {
			int sx = x + i;
			int sy = y + i;
			int ex = r + s - i;
			int ey = c + s - i;
			
			for(int j = 1; j < 2*(s-i)+1; j++) {
				temp[sx][sy+j] = array[sx][sy+j-1]; //상
				temp[sx+j][ey] = array[sx+j-1][ey]; //좌
				temp[ex][ey-j] = array[ex][ey-j+1]; //하
				temp[ex-j][sy] = array[ex-j+1][sy]; //우
			}
			
		}
		
		return temp;
	}
	
	static void order(int d, int[][] array, boolean[] visited) {
		
		if(d == K) {
			checkMin(array);
		}
		
		for(int i = 0; i < K; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			order(d+1, rotateArr(rotations[i].r, rotations[i].c, rotations[i].s, array), visited);
			visited[i] = false;
		}
		
	}
	
	static void checkMin(int[][] array) {
		
		for(int i = 0; i < N; i++) {
			int sum = 0;
			for(int j = 0; j < M; j++) {
				sum += array[i][j];
			}
			MIN = Math.min(MIN, sum);
		}
	}
	
	static class Rotation{
		int r;
		int c;
		int s;
		
		public Rotation(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
}
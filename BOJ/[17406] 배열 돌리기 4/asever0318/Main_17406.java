package week4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Info{
	int r;
	int c;
	int s;
	
	public Info(int r, int c, int s) {
		this.r = r;
		this.c = c;
		this.s = s;
	}
}

public class Main_17406 {
	
	static int N, M, R;
	static int[][] matrix;
	static int[][] copy;
	static List<Info> list = new ArrayList<>();
	static int min;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]); // 배열 크기 N*M
		M = Integer.parseInt(str[1]);
		R = Integer.parseInt(str[2]); // 배열 회전 횟수 
		
		matrix = new int[N][M]; // 원본 배열 
		copy = new int[N][M];  // 복사할 배열 
		
		for(int i = 0; i < N; i++) {
			str = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(str[j]);
				copy[i][j] = matrix[i][j];
			}
		}
		
		int r, c, s;
		
		for(int i = 0; i < R; i++) {
			str = br.readLine().split(" ");
			r = Integer.parseInt(str[0]) - 1; // 인덱스로 사용할 것이므로 -1 씩 해줌 
			c = Integer.parseInt(str[1]) - 1;
			s = Integer.parseInt(str[2]);
			
			list.add(new Info(r, c, s));
		}
		
		min = Integer.MAX_VALUE;
		visited = new boolean[R];
		perm(0, new int[R]); // 순열 
		System.out.println(min);
		
	}

	static void perm(int toSelect, int[] selected) {
		if(toSelect == R) {
			// 배열 초기화 
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					copy[i][j] = matrix[i][j];
				}
			}
			
			for(int i = 0; i < R; i++) {
				int index = selected[i];
				rotate(list.get(index));
			}
			
			for(int i = 0; i < N; i++) {
				int sum = 0;
				
				for(int j = 0; j < M; j++) {
					sum += copy[i][j];
				}
				
				if(min > sum) {
					min = sum;
				}
			}
			return;
		}
		
		for(int i = 0; i < R; i++) {
			if(!visited[i]) {
				visited[i] = true;
				selected[toSelect] = i;
				perm(toSelect + 1, selected);
				visited[i] = false;
			}
		}
	}
	
	static void rotate(Info info) {
		int r = info.r;
		int c = info.c;
		int s = info.s;
		
		int startx = r - s; 
		int starty = c - s;
		int endx = r + s; 
		int endy = c + s; 
	
		// 시계방향으로 회전 할 것임 
		for(int i = 0; i < s; i++) {
			int x = startx + i;
			int y = starty + i;
			int temp = copy[x][y];
			int d = 0;
			
			// 4방향으로 바꿔주면서  옮기기 
			while(d < 4) {
				int xx = x + dx[d];
				int yy = y + dy[d];
				
				if (xx >= startx + i && xx <= endx - i && yy >= starty + i && yy <= endy - i) {
					copy[x][y] = copy[xx][yy];
					x = xx;
					y = yy;
				}else {
					d++;
				}
			}
			// temp에 담아둔 모서리 부분 마지막에 옮기기 
			copy[x][y + 1] = temp;
		}
	}
}

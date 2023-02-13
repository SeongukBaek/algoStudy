package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;


// BOJ[7576] 토마토 
public class Main_7576 {
	static int N, M;
	static int[][] tomato;
	static Queue queue;
	static int day;
	static int[] start; // x좌표, y좌표, day 
	
					  // 왼 위 오 아 
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
	
	// 상하좌우 한 칸씩 이동 --> bfs
	private static void bfs() {
		
		// 시작점 큐가 다 빌때까지 
		while(!queue.isEmpty()) {
			// 시작점 좌표 하나 빼오기 
			start = (int[]) queue.poll();
			day = start[2];
			
			// 네 방향 탐색 
			for(int i = 0; i < 4; i++) {
				int xx = start[0] + dx[i];
				int yy = start[1] + dy[i];
				
				// 탐색할 좌표가 matrix를 벗어나지 않는 지 검사 
				if((0 <= xx && xx < M) && (0 <= yy && yy < N)) {
					// 만약 토마토가 익지 않았으면 
					if(tomato[xx][yy] == 0) {
						tomato[xx][yy] = 1;
						int[] index = {xx, yy, start[2]+1};
						queue.add(index);
					}
				}
			}
		}
	}
	
	// 안익은 토마토가 있는지 확인하기 
	private static void checkTomato() {
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				// 만약 안익은 토마토가 남아있으면 
				if(tomato[i][j] == 0) { 
					day = -1;
					return;
				}
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]); // 열  
		M = Integer.parseInt(str[1]); // 행 
		
		tomato = new int[M][N];
		queue = new LinkedList<int[]>();
		
		// 토마토 입력 받기 
		for(int i = 0; i < M; i++) {
			str = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				tomato[i][j] = Integer.parseInt(str[j]);
				
				// 출발점이 여러 개이므로 큐에 넣어서 순서대로 처리해주기 
				if(tomato[i][j] == 1) {
					int[] xy = new int[3];
					xy[0] = i; // 시작점의 x좌표 
					xy[1] = j; // 시작점의 y좌표 
					xy[2] = 0; // 시작점이니까 day 0
					queue.add(xy); // 큐에 넣기 
				}
			}
		}
		
		bfs();
		checkTomato();
		
		System.out.println(day);
		
	}
}

package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

// BOJ[2206] 벽 부수고 이동하기 
public class Main_2206 {
	static class map{
		int x;
		int y;
		int cnt;
		boolean crashed;
		
		public map(int x, int y, int cnt, boolean crashed) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.crashed = crashed;
		}
	}
	
	
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
	static int min, count, N, M;
	static boolean[][][] visited;
	static int[][] matrix;
	static Queue<map> q;
	
	public static void bfs() {
		
		while(!q.isEmpty()) {
			map now = q.poll(); // 다음 이동할 곳 하나씩 가져오기 
			//System.out.println(now.x + " "+ now.y);
			
			if(now.x == N - 1 && now.y == M - 1) {  // 도착 좌표에 도착하면 끝 
				System.out.println(now.cnt);		// 매트릭스에 0부터 넣었으니까 [N-1][M-1]
				return;
			}
			
			// 상하좌우 4방향 
			for(int i = 0; i < 4; i++) {
				
				int xx = now.x + dx[i];
				int yy = now.y + dy[i];
				
				if(0 <= xx && xx < N && 0 <= yy && yy < M){ // 매트릭스를 벗어나지 않고 
					if(matrix[xx][yy] == 0) { // 벽이 아니면
						//System.out.println("벽 아님");
						if(!visited[xx][yy][0] && !now.crashed) { // 벽을 부순적이 없으면 
							q.add(new map(xx, yy, now.cnt+1, false));
							visited[xx][yy][0] = true;
						}else if(!visited[xx][yy][1] && now.crashed) { // 벽을 부순 적 있으면
							q.add(new map(xx, yy, now.cnt+1, true));
							visited[xx][yy][1] = true;
						}
					}else if(matrix[xx][yy] == 1){ // 벽을 만나면 
						//System.out.println("벽 만남 ");
						if(!now.crashed) { // 한 번도 벽을 부순 적 없으면 부순다 
							q.add(new map(xx, yy, now.cnt+1, true));
							visited[xx][yy][1] = true;
						}
					}
				}
			}
		}
		System.out.println(-1);
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
	
		matrix = new int[N][M]; 
		visited = new boolean[N][M][2]; // 마지막은 벽을 부쉈을때와 안부쉈을때를 나눠서 생각하기 위해  
				
		// 맵 입력 
		for(int i = 0; i < N; i++) {
			str = br.readLine().split("");
			for(int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(str[j]);
				visited[i][j][0] = false;
				visited[i][j][1] = false;
			}
		}
		
		q = new LinkedList<>();
		q.add(new map(0, 0, 1, false)); // 출발지점 큐에 넣기 

		// 시작지점 방문 표시 
		visited[0][0][0] = true;
		visited[0][0][1] = true;
		
		bfs();
		
		br.close();
	}
}

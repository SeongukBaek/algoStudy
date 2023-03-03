import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

// 연구소 
public class Main_14502 {
	static int N, M, max, count;
	static int[][] matrix;
	static Queue<int[]> vQueue, temp;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		
		matrix = new int[N][M];
		vQueue = new LinkedList<>();
		temp = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			s = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(s[j]);
				if(matrix[i][j] == 2) {
					temp.offer(new int[] {i, j}); // 바이러스 위치 큐에 넣기 
				}
			}
		}

		makeWall(0);
		System.out.println(max);
	}
	
	// 바이러스 4방으로 퍼지기 --> 0인 영역 2로 바꾸기 
	static void virus(int x, int y) {
		
		for(int d = 0; d < 4 ; d++) {
			
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			// 매트릭스 벗어나면 x
			if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
				continue;
			}
			
			// 1로 막혀있거나 2면 x
			if(matrix[nx][ny] == 1 || matrix[nx][ny] == 2) {
				continue;
			}
			
			if(matrix[nx][ny] == 0) { // 방문 안했으면 
				matrix[nx][ny] = 2;
				virus(nx, ny);
			}
		}
		return;
	}
	
	// 안전지대 카운트 
	static void countSafezone() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(matrix[i][j] == 0) {
					count++;
				}
			}
		}
	}
	
	static void init(int[][] a, int[][] b) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				a[i][j] = b[i][j];
			}
		}
	}

	// 벽 3개 세우기
	static void makeWall(int n) {
		
		if(n == 3) { // 벽 3개 다 세웠으면 
			vQueue.addAll(temp);
			int[][] tmp = new int[N][M];
			init(tmp, matrix);
			
			while(!vQueue.isEmpty()) { 
				int[] current = vQueue.poll();
				int i = current[0];
				int j = current[1];
				
				virus(i, j);
			}
			countSafezone(); // 안전지대 카운트
			max = Math.max(max, count); // max 갱신 
			count = 0; // 안전지대 카운트 초기화  
			init(matrix, tmp);
			
			return;
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(matrix[i][j] == 0) { // 빈 공간이면
					matrix[i][j] = 1; // 벽 세우기 
					makeWall(n+1);
					matrix[i][j] = 0;
				}
			}
		}
	}
}

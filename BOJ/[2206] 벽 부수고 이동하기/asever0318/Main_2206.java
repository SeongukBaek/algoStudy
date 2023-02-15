package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

// BOJ[2206] �� �μ��� �̵��ϱ� 
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
			map now = q.poll(); // ���� �̵��� �� �ϳ��� �������� 
			//System.out.println(now.x + " "+ now.y);
			
			if(now.x == N - 1 && now.y == M - 1) {  // ���� ��ǥ�� �����ϸ� �� 
				System.out.println(now.cnt);		// ��Ʈ������ 0���� �־����ϱ� [N-1][M-1]
				return;
			}
			
			// �����¿� 4���� 
			for(int i = 0; i < 4; i++) {
				
				int xx = now.x + dx[i];
				int yy = now.y + dy[i];
				
				if(0 <= xx && xx < N && 0 <= yy && yy < M){ // ��Ʈ������ ����� �ʰ� 
					if(matrix[xx][yy] == 0) { // ���� �ƴϸ�
						//System.out.println("�� �ƴ�");
						if(!visited[xx][yy][0] && !now.crashed) { // ���� �μ����� ������ 
							q.add(new map(xx, yy, now.cnt+1, false));
							visited[xx][yy][0] = true;
						}else if(!visited[xx][yy][1] && now.crashed) { // ���� �μ� �� ������
							q.add(new map(xx, yy, now.cnt+1, true));
							visited[xx][yy][1] = true;
						}
					}else if(matrix[xx][yy] == 1){ // ���� ������ 
						//System.out.println("�� ���� ");
						if(!now.crashed) { // �� ���� ���� �μ� �� ������ �μ��� 
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
		visited = new boolean[N][M][2]; // �������� ���� �ν������� �Ⱥν������� ������ �����ϱ� ����  
				
		// �� �Է� 
		for(int i = 0; i < N; i++) {
			str = br.readLine().split("");
			for(int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(str[j]);
				visited[i][j][0] = false;
				visited[i][j][1] = false;
			}
		}
		
		q = new LinkedList<>();
		q.add(new map(0, 0, 1, false)); // ������� ť�� �ֱ� 

		// �������� �湮 ǥ�� 
		visited[0][0][0] = true;
		visited[0][0][1] = true;
		
		bfs();
		
		br.close();
	}
}

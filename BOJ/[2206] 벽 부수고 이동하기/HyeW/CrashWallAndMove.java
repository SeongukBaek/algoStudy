import java.io.*;
import java.util.*;

public class CrashWallAndMove {
	static int N, M;
	static char[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = { 0, 0, -1, 1};
	static int[][] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		visited = new int[N][M];
		
		int result = findRoad(0,0);
		
		System.out.println(result);
		
	}
	
	static int findRoad(int x, int y) {
		
		 //시작점과 도착점이 같은 경우
        	if(x == N-1 && y == M-1)
			return 1;
		
		Queue<Node> path = new LinkedList<>();
		
		Node first = new Node(x, y,1,false);
		visited[x][y] = 2;
		path.offer(first);
		
		while(!path.isEmpty()) {
			Node cur = path.poll();
			
			for(int i = 0; i < 4; i++) {
				
				int dx = cur.x + dr[i];
				int dy = cur.y + dc[i];
				
				//종료지점 도착
				if(dx == N-1 && dy == M-1)
					return cur.cnt + 1;
				
				if(dx < 0 || dy < 0 || dx >= N || dy >= M)
					continue;
				if(cur.isCrashed && visited[dx][dy] != 0)
					continue;
				else if(!cur.isCrashed && visited[dx][dy] == 2)
					continue;
				
				//벽일때
				if(map[dx][dy] == '1')
				{
					if(cur.isCrashed) {
						continue;
					}
					else {
						Node next = new Node(dx,dy,cur.cnt+1,true);
						visited[dx][dy] = 1;
						path.add(next);
						
					}
				}
				else {
					Node next = new Node(dx,dy,cur.cnt+1,cur.isCrashed);
					if(cur.isCrashed)
						visited[dx][dy] = 1;
					else
						visited[dx][dy] = 2;
					path.add(next);
				}
				
			}
		}
		
		return -1;
	}
	
	static class Node{
		int x;
		int y;
		int cnt;
		boolean isCrashed;
		
		public Node(int x, int y, int cnt, boolean isCrashed) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.isCrashed = isCrashed;
		}	
	}
}
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_코드트리빵 {
	
	static class Pos{
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, M;
	static int[][] map;
	static Pos[] store; // 편의점 
	static Pos[] people; // 사람 위치
	static int time; 
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};
	
	static boolean[][] visited;
	static int[][] min;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		store = new Pos[M];
		people = new Pos[M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			store[i] = new Pos(x - 1, y - 1);
		}
		
		// 처음에는 사람들이 격자 밖에 있음 (-1, -1)로 초기화
		for(int i = 0; i < M; i++) {
			people[i] = new Pos(-1, -1);
		}
		
		while(true) {
			time++;
			simulation();
			
			if(end()) {
				break;
			}
		}
		
		System.out.println(time);
	}
	
	
	static void bfs(Pos start) {
		
		visited = new boolean[N][N];
		min = new int[N][N];
		
		Queue<Pos> order = new LinkedList<>();
		
		order.add(start);
		visited[start.x][start.y] = true;
		min[start.x][start.y] = 0;
		
		while(!order.isEmpty()) {
			Pos current = order.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				
				// 맵을 벗어나는지 확인 
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
					continue;
				}
				
				
				// 만약 맵을 벗어나지 않고, 방문하지 않았고, 이동 가능한 곳이면
				if(!visited[nx][ny] && map[nx][ny] != 2) {
					visited[nx][ny] = true;
					min[nx][ny] = min[current.x][current.y] + 1;
					order.add(new Pos(nx, ny));
				}
			}
		}
	}
	
	static void simulation() {
		
		for(int i = 0; i < M; i++) {
			// 격자 밖에 있는 사람이면 패스
			if(people[i].x == -1 && people[i].y == -1) {
				continue;
			}
			
			// 이미 편의점에 도착한 사람이면 패스
			if(people[i].x == store[i].x && people[i].y == store[i].y) {
				continue;
			}
			
			// 편의점에서 현재 위치까지 오는 최단 거리 
			bfs(store[i]);
			
			int minDistance = Integer.MAX_VALUE;
			int minX = -1;
			int minY = -1;
			
			// 4방향으로 돌면서 거리가 가장 작은 곳 고르기
			for(int j = 0; j < 4; j++) {
				int nx = people[i].x + dx[j];
				int ny = people[i].y + dy[j]; 
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
					continue;
				}
				
				if(visited[nx][ny] && minDistance > min[nx][ny]) {
					minDistance = min[nx][ny];
					minX = nx; 
					minY = ny;
				}
			}
			
			// 우선순위가 가장 높은 위치로 움직임 
			people[i] = new Pos(minX, minY);
		}
		
		// 편의점에 도착하면 map에 이동 불가능 표시 
		for(int i = 0; i < M; i++) {
			if(people[i].x == store[i].x && people[i].y == store[i].y) {
				map[people[i].x][people[i].y] = 2; 
			}
		}
		
		// 현재 시간이 M보다 크면 리턴
		if(time > M) {
			return;
		}
		
		// 편의점으로부터 가장 가까운 베이스 캠프고르기 
		bfs(store[time - 1]);
		
		int minDistance = Integer.MAX_VALUE;
		int minX = -1;
		int minY = -1;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(visited[i][j] && map[i][j] == 1 && minDistance > min[i][j]) {
					minDistance = min[i][j];
					minX = i;
					minY = j;
				}
			}
		}
		
		// 우선순위가 가장 높은 베이스 캠프로 이동
		people[time - 1] = new Pos(minX, minY);
		map[minX][minY] = 2; // 해당 위치는 이동 못함으로 표시 
	}
	
	static boolean end() {
		for(int i = 0; i < M; i++) {
			if(people[i].x != store[i].x || people[i].y != store[i].y) {
				return false;
			}
		}
		return true;
	}
}


import java.io.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Fish implements Comparable<Fish>{
		int x, y;

		public Fish(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Fish arg0) {
			if(arg0.x == this.x) {
				return this.y - arg0.y;
			}
			return this.x - arg0.x;
		}
	}
	
	static int N, sharkSize, fishNum, fishCount, totalTime;
	static int[][] map;
	static Fish shark;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 9) { // 상어 초기 위치
					shark = new Fish(i, j);
					map[i][j] = 0;
				}
				
				if(map[i][j] > 0) {
					fishNum++;
				}
			}
		}

		sharkSize = 2;
		if(fishNum == 0) {
			totalTime = 0;
		}else {
			Fish current = shark;
			int count = 0;
			
			while(fishNum > fishCount) {
				Fish next = findFish(current); // 먹을 수 있는 가장 가까운 물고기 찾기 
				
				if(next.x == -1 && next.y == -1) { // 먹을 수 있는 물고기가 없으면 끝 
					break;
				}
				
				fishCount++; // 먹은 물고기 수 ++
				count++; // 상어크기를 위한 카운트 
				map[next.x][next.y] = 0; // 물고기 먹었으니 0으로 표시 
				
				// 상어의 크기만큼 물고기를 먹을 때마다 사이즈 1증가 
				if(count == sharkSize) {
					sharkSize++;
					count = 0;
				}
				
				current = next;
			}
		}
		
		System.out.println(totalTime);
	}
	
	// 현재 좌표에서 가장 가까운 먹을 수 있는 물고기 찾기 
	static Fish findFish(Fish start) {
		
		boolean[][] visited = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				visited[i][j] = false;
			}
		}
		
		Fish target = new Fish(-1, -1);
		Queue<Fish> q = new LinkedList<>();
		PriorityQueue<Fish> next = new PriorityQueue<>();
		q.add(start);
		visited[start.x][start.y] = true;
		int time = 0;
		boolean check = false;
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for(int i = 0; i < size; i++) {
				Fish current = q.poll();
				
				for(int d = 0; d < 4; d++) {
					int nx = current.x + dx[d];
					int ny = current.y + dy[d];
					
					if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
						continue;
					}
					
					// 상어 크기보다 큰 물고기가 있으면 이동 x
					if(sharkSize < map[nx][ny]) {
						continue;
					}
					
					if(visited[nx][ny]) {
						continue;
					}
					
					// 상어크기보다 작은 먹을 수 있는 물고기를 찾으면 
					if(map[nx][ny] > 0 && map[nx][ny] < sharkSize) {
						check = true;
						next.add(new Fish(nx, ny));
					}
					
					visited[nx][ny] = true;
					q.add(new Fish(nx, ny));
				}
			}
			time++;
			if(check) {
				totalTime += (time);
				break;
			}
		}
		
		
		// 같은 거리에 여러 물고기가 있을 때, 위쪽 -> 왼쪽 우선순위
		if(next.size() > 0) {
			target = next.poll();
		}
		
		
		return target;
	}
}

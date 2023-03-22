import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static class Pos{
		int x, y, cnt;

		public Pos(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}	
	}
	
	static int[] dx = {-1, 0 ,1, 0};
	static int[] dy = {0, 1 ,0, -1};
	static char[][] map;
	static Pos start, end, lever;
	
	public static int solution(String[] maps) {
		
        map = new char[maps.length][maps[0].length()];
        
        for(int i = 0; i < maps.length; i++) {
        	map[i] = maps[i].toCharArray();
        	for(int j = 0; j < maps[i].length(); j++) {
        		if(map[i][j] == 'S') { // 시작지점
        			start = new Pos(i, j, 0);
        		}
        		
        		if(map[i][j] == 'L') { // 레버
        			lever = new Pos(i, j, 0);
        		}
        		
        		if(map[i][j] == 'E') { // 출구
        			end = new Pos(i, j, 0);
        		}
        	}
        }
        
        int toLever = findMinCost(start, lever);
        int toEnd = findMinCost(lever, end);
        
        if(toLever == -1 || toEnd == -1) {
        	return -1;
        }
        
        return toLever + toEnd;
    }

	
	static int findMinCost(Pos start, Pos end) {
		boolean[][] visited = new boolean[map.length][map[0].length];
		Queue<Pos> queue = new LinkedList<>();
		int min = 9999;
		
		queue.add(start);
		visited[start.x][start.y] = true;
		
		while(!queue.isEmpty()) {
			Pos current = queue.poll();
			
			// 도착지 도착하면 걸린 시간 return 
			if(current.x == end.x && current.y == end.y) {
				if(min > current.cnt) {
					min = current.cnt;
					break;
				}
			}
			
			for(int d = 0; d < 4; d++) {
				int nx = current.x + dx[d];
				int ny = current.y + dy[d];
				
				if(nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) {
					continue;
				}
				
				if(map[nx][ny] == 'X') { // 벽이면
					continue;
				}
				
				if(!visited[nx][ny]) { // 방문 안했으면 
					visited[nx][ny] = true; // 방문표시
					queue.add(new Pos(nx, ny, current.cnt+1));
				}
			}
		}
		
		if(min == 9999) {
			return -1;
		}
		
		return min; // 탈출할 수 없다면 -1
	}
}
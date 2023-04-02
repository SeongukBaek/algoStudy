import java.io.*;
import java.util.*;

public class Main {
	static int n, m, k;
	static int[][] map;
	static Team[] team;
	static int[] dx = {-1, 1, 0, 0}; // 방향 : 상,하,좌,우
	static int[] dy = {0, 0, -1, 1};
	static boolean[][] visited;
	static class Point {
		int x, y, value;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
			this.value = map[x][y];
		}
	}
	static class Team {
		int[][] teamMap;
		Point head, tail;
		int direction;
		public Team(Point head) {
			this.head = head;
		}
	}
	
    public static void main(String[] args) throws IOException {
        
    	/* 입력 및 변수 선언 */
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        visited = new boolean[n][n];
        team = new Team[m];
        int k = 0;
        for(int i=0; i<n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<n; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        		
        		// 빈칸은 방문처리
        		if(map[i][j] == 0) {
        			visited[i][j] = true;
        		}
        		
        		else if(map[i][j] == 1) {
        			team[k++] = (new Team(new Point(i, j)));
        		}
        		
        	}
        }
        
              
        for(int i=0; i<m; i++) {
            searchTeam(team[i]);
        }
        
        for(int i=0; i<k; i++) {
        	for(int j=0; j<m; j++) {
        		moveTeam(team[j]);
        	}
        }  
    }


	/* team 탐색해서 구분하기 */
	private static void searchTeam(Team team) {
		int[][] tm = team.teamMap = new int[n][n];
		tm[team.head.x][team.head.y] = team.head.value;
		Deque<Point> teamQ = new ArrayDeque<>();
		teamQ.offer(team.head);
		
		while(!teamQ.isEmpty()) {
			Point point = teamQ.poll();
			int x = point.x;
			int y = point.y;
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				if(visited[nx][ny]) continue;
				
				// 팀원일 경우
				visited[nx][ny] = true;
				tm[nx][ny] = map[nx][ny]; // teamMap에 추가;
				teamQ.offer(new Point(nx, ny));  // teamQ에 추가
			}
		}		
//		for(int[] tms : tm) {
//			for(int t : tms)
//				System.out.print(t + " ");
//			System.out.println();
//		}
//		System.out.println("----------------");
	}
	
	/* 팀원 이동시키기 */
    private static void moveTeam(Team team) {
    	// 원본 - map / 카피,로직에 이용 - teamMap
    	visited = new boolean[n][n];
    	int[][] cptm = new int[n][n];
		for(int i=0; i<n; i++) {
			cptm = team.teamMap.clone();
		}
		visited[team.head.x][team.head.y] = true;
		Deque<Point> teamQ = new ArrayDeque<>();
		teamQ.offer(team.head);
		
		while(!teamQ.isEmpty()) {
			Point point = teamQ.poll();
			int x = point.x;
			int y = point.y;
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				if(visited[nx][ny] || team.teamMap[nx][ny] == 0) continue;
				
				// 팀원일 경우
				visited[nx][ny] = true;
				cptm[nx][ny] = map[nx][ny]; // teamMap에 추가;
				teamQ.offer(new Point(nx, ny));  // teamQ에 추가
			}
		}
		
	}
	
}
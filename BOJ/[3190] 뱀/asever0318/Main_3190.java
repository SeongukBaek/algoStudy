import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Snake {
		int x, y;
		int d; // 상(0) 오(1) 하(2) 왼(3)

		public Snake(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	
	static class Info {
		int time;
		char d;
		
		public Info(int time, char d) {
			super();
			this.time = time;
			this.d = d;
		}
	}
	
	static int N;
	static int[][] board;
	static Queue<Info> rotate;
	static int totalTime; // 총 시간 
	static List<Snake> snake; // 뱀 정보 저장할 리스트 
	static int[] dx = {-1, 0, 1, 0}; // 상(0) 오(1) 하(2) 왼(3)
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		board = new int[N+1][N+1];
		
		int K = Integer.parseInt(br.readLine());
		
		// 보드에 사과 표시 
		for(int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			board[x][y] = 1; // 사과는 1로 표시 
		}
		
		int info = Integer.parseInt(br.readLine());
		rotate = new LinkedList<>();
		for(int i = 0; i < info; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
			rotate.add(new Info(time, d)); // 회전 정보 큐에 넣기 
		}
		
		playGame();
		
		System.out.println(totalTime);
	}
	
	static void playGame() {
		
		snake = new ArrayList<>();
		
		// 처음에 뱀의 길이는 1이고 오른쪽을 향하고 있으며, 맨위맨좌측에 있다.
		snake.add(new Snake(1, 1, 1));
		board[1][1] = 2; // 맵에서 뱀은 2로 표시 
		
		Info info = rotate.poll(); 
		
		while(true) {
			totalTime++;
			
			Snake head = snake.get(0); // 뱀머리
			
			// 다음 좌표 구하기 
			int nx = head.x + dx[head.d];
			int ny = head.y + dy[head.d];
			
			// 만약 다음 좌표가 벽이거나 자신의 몸이면 게임 종료 
			if(!isVaildIndex(nx, ny) || !isNotSnake(nx, ny)) {
				return;
			}
			
			int direction = head.d;
			if(info.time == totalTime) { // 회전해야 될 시간이 흐른 시간과 같아지면 
				// 새로운 방향 찾아주기 
				direction = rotation(snake.get(0).d, info.d);
				
				if(!rotate.isEmpty()) { // 큐가 비어있지 않으면 (회전정보가 남아있으면)
					info = rotate.poll();
				}
			}
			
			snake.add(0, new Snake(nx, ny, direction)); // 뱀머리 이동(추가)
			moveSnake(nx, ny); // 사과가 있으면 꼬리 변화 x, 사과가 없으면 꼬리 줄이기 
		}
	}
	
	// 보드를 벗어나는지 아닌지(벽인지 아닌지) 
	static boolean isVaildIndex(int nx, int ny) {
		if(nx < 1 || nx > N || ny < 1 || ny > N) {
			return false; // 벽
		}
		return true;
	}
	
	// 해당 좌표가 뱀이 아닌지(뱀이면 false)
	static boolean isNotSnake(int nx, int ny) {
		if(board[nx][ny] == 2) {
			return false; // 뱀
		}
		return true;
	}
	
	// 현재 방향에서 주어진 방향으로 90도 회전 후 반환  
	static int rotation(int currentD, char newD) {
		if(newD == 'D') {
			if(currentD == 3) {
				currentD = 0;
			}else {
				currentD++;
			}
		}
		
		if(newD == 'L') {
			if(currentD == 0) {
				currentD = 3;
			}else {
				currentD--;
			}
		}
		
		return currentD; // 바뀐 방향 반환 
	}
	
	// 뱀 이동시키기 
	static void moveSnake(int nx, int ny) {
		if(board[nx][ny] == 0) { // 사과가 없으면
			int tail = snake.size() - 1; // 뱀꼬리
			int x = snake.get(tail).x;
			int y = snake.get(tail).y;
			
			board[x][y] = 0; // 맵에서 꼬리 없애주고 
			snake.remove(tail); // 꼬리 줄이기 
		}
		
		board[nx][ny] = 2; // 보드에 뱀으로 표시 
	}
}

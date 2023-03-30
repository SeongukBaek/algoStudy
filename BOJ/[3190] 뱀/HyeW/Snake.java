import java.io.*;
import java.util.*;

public class Snake {
	static int N;
	static int[][] board;
	static Map<Integer,Character> turns;
	//상우하좌
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		board = new int[N][N];
		//사과 저장
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			board[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] 
					= 1;
		}
		//이동할 시간과 방향 저장
		int L = Integer.parseInt(br.readLine());
		turns = new HashMap<>();
		for(int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			turns.put(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
		}
		
		System.out.println(startGame());
	}
	
	private static int startGame() {
		int curDir = 1;
		Deque<Body> snake = new LinkedList<>();
		snake.add(new Body(0,0));
		board[0][0] = 2;
		
		int time = 0;
		while(true) {
			time++;
			Body cur = snake.peekFirst();
		
			int dx = cur.x + dr[curDir];
			int dy = cur.y + dc[curDir];
			
			//벽이나 자기 몸에 닿으면 게임 종료
			if(!arrayBoundsValidation(dx, dy) || board[dx][dy] == 2) {
				break;
			}
			
			snake.addFirst(new Body(dx, dy));
			//사과를 안먹었을 때 뱀의 꼬리를 줄임
			if(board[dx][dy] == 0) {
				Body tail = snake.pollLast();
				board[tail.x][tail.y] = 0;
			}
			board[dx][dy] = 2;
			
            //현재 시간에 회전여부 체크
			if(turns.containsKey(time)) {
				curDir = changDir(curDir, turns.get(time));
			}
		}
		return time;
	}

	private static boolean arrayBoundsValidation(int x, int y) {
		return x >= 0 && y >= 0 && x < N & y < N;
	}

	private static int changDir(int cur, Character d) {
		if(d == 'D') {
			return (cur+1)%4; 
		}else {
			return (cur-1) >= 0 ? cur-1 :3;
		}
	}

	static class Body {
		int x;
		int y;
		
		public Body(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}

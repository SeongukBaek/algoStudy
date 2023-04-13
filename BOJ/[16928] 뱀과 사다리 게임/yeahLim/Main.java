import java.io.*;
import java.util.*;

public class Main {
	static int[] board = new int[101];
	
	public static void main(String[] args) throws IOException {
		
		/* 입력 및 변수 초기화 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			board[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			board[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(playGame());
		
	}
	
	/* BFS : 게임 실행 */
	private static int playGame() {
		class Current {
			int x, count;
			public Current(int x, int count) {
				this.x = x;
				this.count = count;
			}
		}
		Deque<Current> queue = new ArrayDeque<>();
		queue.add(new Current(1, 0));
		boolean[] visited = new boolean[100]; // 100번 째 칸에 대한 방문 여부는 저장할 필요 없음
		int answer = 100;
		
		while(!queue.isEmpty()) {
			Current cur = queue.poll();
			
			// 도착
			if(cur.x == 100) {
				if(answer > cur.count) {
					answer = cur.count;
				}
				break;
			}
			
			if(visited[cur.x]) continue;
			
			// 방문 안했을 경우
			visited[cur.x] = true;
			
			// 사다리나 뱀이 있는 경우
			if(board[cur.x] != 0) {
				cur = new Current(board[cur.x], cur.count); // 변한 위치로 업데이트
			}
			
			// 없는 경우
			for(int i=1; i<=6; i++) {
				if(cur.x + i > 100) break;
				queue.add(new Current(cur.x+i, cur.count+1));
			}
		}
		
		return answer;
	}
}

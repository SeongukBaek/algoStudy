import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] move;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		move = new int[101]; // 0보다 큰 값이면 어디론가 이동함 
		
		for(int i = 0; i < N+M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			move[from] = to;
		}
		
		System.out.println(playGame());
	}
	
	static int playGame() {
		int cnt = 0;
		int num = 1;
		boolean[] visited = new boolean[101];
		Queue<Integer> q = new LinkedList<>();
		q.add(num);
		
		while(!q.isEmpty()) {
			
			// 주사위 1번 던질 때마다 카운트하기 위해서 이전큐 사이즈만큼 돌기
			int size = q.size();
			
			for(int n = 0; n < size; n++) {  
				int c = q.poll();
				
				for(int i = 1; i <= 6; i++) { 
					int nextNum = c + i; // 주사위 눈 크기만큼 이동
					
					if(nextNum == 100) { // 100에 도착하면 끝 
						return cnt+1; // 현재 시간 카운트 되지 않았으므로 +1 해서 리턴 
					}
					
					if(nextNum > 100) { // 다음에 갈 숫자가 100보다 크면 패스 
						continue;
					}
					
					if(move[nextNum] != 0) { // 0이 아니면 어디로든 이동
						nextNum = move[nextNum];
					}
					
					if(visited[nextNum]) { // 방문했으면 패스
						continue;
					}
					
					q.add(nextNum);
					visited[nextNum] = true;
				}
			}
			cnt++;
		}
		
		return cnt;
	}
}

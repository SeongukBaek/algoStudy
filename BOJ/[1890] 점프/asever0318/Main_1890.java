import java.io.*;
import java.util.StringTokenizer;


public class Main_1890 {
	
	static class Point{
		boolean visited = false;
		long cnt; // 해당 좌표까지 올 수 있는 경우의 수 
		
		public Point() {
			super();
		}
	}
	
	static int N;
	static int[][] map;
	static int[] dx = {0, 1};
	static int[] dy = {1, 0};
	//static Point[][] checkMap; // 갈 수 있는 방향 표시해 줄 이차원 배열 
	static long count[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		//checkMap = new Point[N][N];
		count = new long[N][N];
		
		// map 입력 받기 
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				//checkMap[i][j] = new Point();
			}
		}
		
		dp_playGame();
		
		System.out.println(count[N-1][N-1]);
	}
	
	
	// bottom-up --> 반복문 
	// 이전 
	static void dp_playGame() {
		
		// (0, 0)에서 (0, 0)에 올 수 있는 경우의 수 = 1
		count[0][0] = 1;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				
				// 도착지는 0이기 때문에 2방향 for문을 돌아서 2번 더 더해지는 문제
				// 도착지 도착하면 멈춰줘야 함
				if(i == N-1 && j == N - 1) {
					break;
				}
				// 여기까지 오는 경우의 수가 없으면 넘어가기 
				if(count[i][j] == 0) {
					continue;
				}
				
				for(int d = 0; d < 2; d++) {
					
					int nx = i + (dx[d] * map[i][j]); // map에 적힌 거리만큼 점프 
					int ny = j + (dy[d] * map[i][j]);
					
					if(nx < N && ny < N) { // 범위를 벗어나지 않으면
						// (오른쪽 or 아래쪽만 검사하니까 N을 벗어나는지만 확인하면 됨)
						
						count[nx][ny] += count[i][j];
					}
				}
			}
		}
	}
}

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;


// [19237] 어른 상어 
public class Main_19237 {
	
	static class Shark{
		// 위치, 상어 숫자, 방향 
		int x, y, d = -1;

		public Shark(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static Shark[] sharks; // 상어 관리할 배열 
	static int N, M, k;
	static int[][] map;
	static int[][] smell; // 냄새 저장할 배열 
	static int[][] time; // 시간 저장할 배열 
	static int[][][] dirSharks; // [상어][방향][우선순위]
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, -1, 1};
	static HashMap<Integer, int[]> sharkNewPos; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		sharks = new Shark[M+1]; // 입력받는 번호대로 관리하려고 M+1
		smell = new int[N][N];
		dirSharks = new int[M+1][4+1][4]; // 상어, 방향 입력 그대로 사용하려고 +1
		time = new int[N][N];
		sharkNewPos = new HashMap<>();
		
		// 상어 움직일 맵 입력 
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] != 0) { // 0이 아니면 상어
					sharks[map[i][j]] = new Shark(i, j); // Shark배열 해당 번호에 저장 
					smell[i][j] = map[i][j]; // 맨 처음에는 모든 상어가 자신의 위치에 냄새를 뿌린다
					time[i][j] = k;
				}
			}
		}
		
		// 각 상어의 초기 방향 저장 
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < M+1; i++) {
			sharks[i].d = Integer.parseInt(st.nextToken());
		}
		
		// 각 상어의 방향에 따른 우선순위 
		for(int i = 1; i <= M; i++) { // 각 상어마다 
			for(int j = 1; j <= 4; j++) { // 4방향 (위, 아래, 왼쪽, 오른쪽)
				st = new StringTokenizer(br.readLine());
				
				// 해당 방향에서의 우선순위 입력
				for(int n = 0; n < 4; n++) {
					dirSharks[i][j][n] = Integer.parseInt(st.nextToken());
				}
			}
		}

		
		//System.out.println(Arrays.toString(dirSharks[3][3]));
		countTime();
		
		br.close();
	}
	
	static void countTime() {
		
		// 처음부터 한 마리만 있는 경우 
		if(countEnd()) {
			System.out.println(0);
			return;
		}
		
		int second = 1;
		
		// 1000초까지만 반복 
		while(second <= 1000) {
			
			// 상어 한마리씩 데려와서 이동위치 저장 
			for(int i = 1; i <= M; i++) {
				
				if(sharks[i] == null) { // 쫓겨난 놈이면 x 
					continue;
				}

				boolean moveCheck = moveSharkEmpty(i); // 이동할 곳이 있는지 없는지 체크
				
				if(!moveCheck) { // 이동할 빈공간이 없었으면
					// 자신의 냄새가 있는 곳으로 이동할 수 있는지 확인
					moveCheck = moveSharkSmell(i);
				}
			}
			
			// 시간 감소시키기 --> 냄새 사라짐 
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(smell[i][j] != 0) { // 시간이 남아있으면 
						time[i][j]--; // 1초 감소
											
						if(time[i][j] == 0) { // 시간이 0이 되면 냄새 사라진거니까
							smell[i][j] = 0; // smell 맵에 표시 
						}
					}
				}
			}					
			
			// 마지막에 해당 위치로 옮겨주기 
			for(int i = 1; i <= M; i++) { // 각 상어 돌면서 
				
				if(sharks[i] == null) { // 쫓겨난 상어면 넘어가기
					continue;
				}
				
				// 해당 상어에 대한 이동정보 가져오기 
				int[] info = sharkNewPos.get(i);
				
				moveShark(i, info[0], info[1], info[2]); // nx, ny, d
				
			} 	
			
			// 1번 상어 한마리만 남으면 종료 
			if(countEnd()) {
				break;
			}
			
			second++;
		}
		
		// 1000초가 넘어도 다른 상어가 남아있으면 출력 -1 
		if(second > 1000) {
			System.out.println(-1);
		}
		else {
			System.out.println(second);			
		}
	}
	
	static boolean countEnd() { 
		for(int i = 2; i <= M; i++) {
			if(sharks[i] != null) {
				return false;
			}
		}
		
		return true;
	}
	
	// 인접한 곳에 빈 공간이 있으면 이동하기  
	static boolean moveSharkEmpty(int index) {
		Shark shark = sharks[index]; // 해당 index 상어 데려오기
		int[] priorityDir = dirSharks[index][shark.d]; // 해당 방향의 우선순위 가져오기 
		
		for(int d = 0; d < 4; d++) { // 우선순위대로 인접한 곳 탐색
			int nx = shark.x + dx[priorityDir[d]];
			int ny = shark.y + dy[priorityDir[d]];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
				continue;
			}
			
			// 해당 좌표에 상어가 없고, 냄새도 없다면
			if(map[nx][ny] == 0 && smell[nx][ny] == 0) {
				sharkNewPos.put(index, new int[] {nx, ny, priorityDir[d]});
				return true; // 이동 공간이 있음 
			}
		}
		return false; // 이동할 곳이 없음 
	}
	
	
	// 빈 공간이 없을 경우 자신의 냄새가 있는 곳으로 이동하기 
	static boolean moveSharkSmell(int index) {
		Shark shark = sharks[index]; // 해당 index 상어 데려오기
		int[] priorityDir = dirSharks[index][shark.d]; // 해당 방향의 우선순위 가져오기 
		
		for(int d = 0; d < 4; d++) { // 우선순위대로 인접한 곳 탐색
			int nx = shark.x + dx[priorityDir[d]];
			int ny = shark.y + dy[priorityDir[d]];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
				continue;
			}
			
			// 자신의 냄새가 있다면(냄새맵에 저장된 번호가 자신의 번호와 같으면)
			if(smell[nx][ny] == index) {
				sharkNewPos.put(index, new int[] {nx, ny, priorityDir[d]});
				return true; // 이동 공간이 있음 
			}
		}
		return false; // 이동할 곳이 없음 
	}
	
	
	// 이동할 위치, 방향 
	static void moveShark(int index, int x, int y, int d) {
		Shark shark = sharks[index];
		
		// 지금 있는 자리 0으로 표시  
		map[shark.x][shark.y] = 0;
		
		shark.x = x;
		shark.y = y;
		shark.d = d;
		
		if(map[x][y] != 0) { // 만약 이동위치에 다른 상어가 있으면 
			
			if(map[x][y] > index) { // 지금 상어 숫자가 더 작으면 
				sharkOut(map[x][y]); // 원래 있던 상어 out
				
				map[x][y] = index; // 지금 상어 번호로 바꾸기 
				smell[x][y] = index; // 냄새 저장
				time[x][y] = k; // 시간 저장 
			}else { // 원래 있던 상어가 더 작으면 
				// 격자 밖으로 쫓겨나기 
				sharkOut(index);
			}
		}
		else { // 이동 위치에 다른 상어가 없으면
			map[x][y] = index; // 맵에 지금 상어 넣고
			smell[x][y] = index; // 냄새맵에 지금 상어 냄새 넣고
			time[x][y] = k; // 시간 저장 
		}
	}
	
	// 해당 상어 삭제 
	static void sharkOut(int index) {
		sharks[index] = null;
	}
}

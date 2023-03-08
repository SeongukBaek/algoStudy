package week6;

import java.io.*;
import java.util.*;

public class Main_19237 {
	
	static int n, m, k;
	static int[][] map;
	static int[][][] smell; // 상어인덱스와 남은 시간
	static class Shark {
		int x, y;
		int idx; // 인덱스
		int head; // 상어의 현재 방향 
		int[][] direction = new int[5][5]; // 방향 우선순위
		boolean outOfMap = false;
		public Shark(int x, int y, int idx) {
			this.x = x;
			this.y = y;
			this.idx = idx;
		}
	}
	static Shark[] sharks;
	static int sharkCnt;
	
	public static void main(String[] args) throws IOException {
		
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		smell = new int[n][n][2];
		sharks = new Shark[m+1];
		sharkCnt = m;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > 0) {
					sharks[map[i][j]] = new Shark(i, j, map[i][j]);
					smell[i][j][0] = map[i][j];
					smell[i][j][1] = k;
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=m; i++) {
			sharks[i].head = Integer.parseInt(st.nextToken());
		}
		for(int i=1; i<=m; i++) {
			for(int j=1; j<=4; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=1; k<=4; k++) {
					sharks[i].direction[j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}	
		
		/* 실행 및 출력 */
		System.out.println(chaseAwaySharks());
	}
	
	static int chaseAwaySharks() {
		int time = 0;
		while(sharkCnt > 1 && time <= 100) {
			
			// 상어 한 마리씩 이동 시킨다
			for(int i=1; i<=m; i++) {
				
				if(sharks[i].outOfMap) continue;
				System.out.println("#");
				moveShark(sharks[i]); 
			}
			
			// 다른 상어를 만난다
			checkMeetShark();
			
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					smell[i][j][1] -= 1; // 냄새 시간 -1
					if(smell[i][j][1] == 0) smell[i][j][0] = 0; // 냄새가 사라진다
				}
			}
						
			time++; // 시간 +1
			
			
			System.out.println("상어 상태 확인");
			for (int i = 1; i <= m; i++) {
				System.out.print(sharks[i].outOfMap + " ");
			}
			System.out.println();
			for (int[][] a : smell) {
				for (int[] b: a) {
					System.out.print(b[1] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
				
		return 1000 > time ? time : -1;
	}	
	

	/* 갈 수 있는 공간을 찾는다 */
	static void moveShark(Shark shark) {
		
		// 상어가 위를 향할때
		if(shark.head == 1) {
			
			// 빈 공간이면 이동
			for(int i=1; i<=4; i++) {		
				if(checkSpace(shark.direction[1][i], shark)) return;  
			}	
			
			// 자신의 냄새가 있는 공간이면 이동
			for(int i=1; i<=4; i++) {		
				if(checkOwnSmell(shark.direction[1][i], shark)) return;  
			}	
		}
		
		// 상어가 아래를 향할때
		else if(shark.head == 2) {
			
			for(int i=1; i<=4; i++) {		
				if(checkSpace(shark.direction[2][i], shark)) return;  
			}	
			
			for(int i=1; i<=4; i++) {		
				if(checkOwnSmell(shark.direction[2][i], shark)) return;  
			}

		}
		
		// 상어가 왼쪽을 향할때
		else if(shark.head == 3) {
				
			for(int i=1; i<=4; i++) {		
				if(checkSpace(shark.direction[3][i], shark)) return;  
			}	
			
			for(int i=1; i<=4; i++) {		
				if(checkOwnSmell(shark.direction[3][i], shark)) return;  
			}
		}
		
		// 상어가 오른쪽을 향할때
		else {
			
			for(int i=1; i<=4; i++) {		
				if(checkSpace(shark.direction[4][i], shark)) return;  
			}	
			
			for(int i=1; i<=4; i++) {		
				if(checkOwnSmell(shark.direction[4][i], shark)) return;  
			}
		}
	}
	
	/* 해당 좌표가 빈 공간이지 확인한다 */
	static boolean checkSpace(int dir, Shark shark) {
		int x = shark.x;
		int y = shark.y;
		
		// 위
		if(dir == 1) {
			x -= 1;
			if(!checkBoundary(x)) return false;
			if(!checkEmpty(x, y)) return false;
			shark.x -= 1; // 빈 공간이면 이동한다
			shark.head = 1; // 헤드의 방향을 바꾼다
			smell[x][y][0] = shark.idx; // 냄새를 뿌린다
			smell[x][y][1] = k+1;
			
		}
		
		// 아래
		else if(dir == 2) {
			x += 1;
			if(!checkBoundary(x)) return false;
			if(!checkEmpty(x, y)) return false;
			shark.x += 1;
			shark.head = 2;
			smell[x][y][0] = shark.idx; 
			smell[x][y][1] = k+1;
		}
		
		// 왼쪽
		else if(dir == 3) {
			y -= 1;
			if(!checkBoundary(y)) return false;
			if(!checkEmpty(x, y)) return false;
			shark.y -= 1;
			shark.head = 3;
			smell[x][y][0] = shark.idx; 
			smell[x][y][1] = k+1;
		}
		
		// 오른쪽
		else {
			y += 1;
			if(!checkBoundary(y)) return false;
			if(!checkEmpty(x, y)) return false;
			shark.y += 1;
			shark.head = 4;
			smell[x][y][0] = shark.idx; 
			smell[x][y][1] = k+1;
		}
		return true;
	}
	
	/* 좌표가 벗어나지 않는지 확인한다 */
	static boolean checkBoundary(int x) {
		return (0<=x && x<n);
	}
	
	/* 빈 공간인지 확인한다 */
	static boolean checkEmpty(int x, int y) {
		return (smell[x][y][1] == k+1 || smell[x][y][0] == 0);

	}
	
	/* 자기 냄새가 있는 곳인지 확인한다 */
	static boolean checkOwnSmell(int dir, Shark shark) {
		int x = shark.x;
		int y = shark.y;
		
		// 위
		if(dir == 1) {
			
			x -= 1;
			if(!checkBoundary(x)) return false;
			if(smell[x][y][0] != shark.idx) return false; // 자기 냄새가 아니면
			shark.x -= 1; // 자기 공간이면 이동한다
			shark.head = 1; // 헤드의 방향을 바꾼다
			smell[x][y][1] = k+1; // 냄새를 다시 뿌린다
		}
		
		// 아래
		else if(dir == 2) {
			
			x += 1;
			if(!checkBoundary(x)) return false;
			if(smell[x][y][0] != shark.idx) return false;
			shark.x += 1;
			shark.head = 2;
			smell[x][y][1] = k+1;
		}
		
		// 왼쪽
		else if(dir == 3) {
			
			y -= 1;
			if(!checkBoundary(y)) return false;
			if(smell[x][y][0] != shark.idx) return false;
			shark.y -= 1;
			shark.head = 3;
			smell[x][y][1] = k+1;
		}
		
		// 오른쪽
		else {
			
			y += 1;
			if(!checkBoundary(y)) return false;
			if(smell[x][y][0] != shark.idx) return false;
			shark.y += 1;
			shark.head = 4;
			smell[x][y][1] = k+1;
		}
		
		return true;
	}
	
	/* 다른 상어를 만나는지 확인한다 */
	static void checkMeetShark() {

		for(int i=m; i>=1; i--) {
			if(sharks[i].outOfMap) continue; 
			for(int j=i-1; j>=1; j--) {
				
				if(sharks[j].outOfMap) continue; 
				
				// 이동 후 다른 상어 만났을 경우
				if(sharks[i].x == sharks[j].x && sharks[i].y == sharks[j].y) {
					sharkCnt--;
					sharks[i].outOfMap = true;
					smell[sharks[i].x][sharks[i].y][0] = j;
				}
			}
		}	
	}
}

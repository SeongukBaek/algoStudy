import java.io.*;
import java.util.*;

public class Main {
	static int[][] room;
	static int[] airCondition;
	static int R, C;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = { 0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
        // 방 정보 저장하기
		airCondition = new int[2];
		room = new int[R][C];
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if(room[i][j] == -1) {
					airCondition[1] = i;
				}
			}
		}
		
		airCondition[0] = airCondition[1] - 1;
		
        // 미세먼지 제거하기
		while(T-- > 0) {
			spreadDusts();
			runAirCondition();
		}
		System.out.println(getTotalDust());
	}

    /* 모든 미세먼지 확산 시키기 */
	private static void spreadDusts() {
		int[][] tempRoom = new int[R][C];
		for(int air : airCondition) {
			tempRoom[air][0] = -1;
		}
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(room[i][j] > 0) {
					spreadDust(tempRoom, i, j);
				}
			}
		}
		
		room = tempRoom;
	}
	
    /* 한 칸에 있는 미세먼지 확산 시키기 */
	private static void spreadDust(int[][] tempRoom, int x, int y) {
		int childDust = room[x][y]/5;
		
		for(int i = 0; i < 4; i++) {
			int dx = x + dr[i];
			int dy = y + dc[i];
			
			if(isMapOut(dx, dy)) {
				continue;
			}
			
			if(room[dx][dy] == -1) {
				continue;
			}
			
			tempRoom[dx][dy] += childDust;
			tempRoom[x][y] -= childDust;
		}
		tempRoom[x][y] += room[x][y];
	}

	private static boolean isMapOut(int x, int y) {
		return x < 0 || y < 0 || x >= R || y >= C;
	}

    /* 공기청정기 작동 시키기 */
	private static void runAirCondition() {
		runUpCycle();
		runDownCycle();
	}

	private static void runUpCycle() {
		//왼쪽
		for(int i = airCondition[0]-1; i > 0; i--) {
			room[i][0] = room[i-1][0];
		}
		//위쪽
		for(int i = 0; i < C - 1; i++) {
			room[0][i] = room[0][i+1];
		}
		//오른쪽
		for(int i = 0; i < airCondition[0]; i++) {
			room[i][C-1] = room[i+1][C-1];
		}
		//아래쪽
		for(int i = C - 1; i > 1; i--) {
			room[airCondition[0]][i] = room[airCondition[0]][i-1];
		}
		room[airCondition[0]][1] = 0;
	}

	private static void runDownCycle() {
		//왼쪽
		for(int i = airCondition[1]+1; i < R-1; i++) {
			room[i][0] = room[i+1][0];
		}
		//아래쪽
		for(int i = 0; i < C - 1; i++) {
			room[R-1][i] = room[R-1][i+1];
		}
		//오른쪽
		for(int i = R - 1; i > airCondition[1] -1; i--) {
			room[i][C-1] = room[i-1][C-1];
		}
		//위쪽
		for(int i = C - 1; i > 1; i--) {
			room[airCondition[1]][i] = room[airCondition[1]][i-1];
		}
		room[airCondition[1]][1] = 0;
	}

    /* 남아 있는 미세먼지 구하기 */
	private static int getTotalDust() {
		int count = 0;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(room[i][j] > 0) {
					count += room[i][j];
				}
			}
		}
		return count;
	}

	static class Position{
		int x;
		int y;
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}

}

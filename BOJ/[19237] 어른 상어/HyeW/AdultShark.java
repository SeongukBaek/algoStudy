import java.io.*;
import java.util.*;

public class AdultShark {
	static final int DIRECTION_NUM = 4;
	static int N, M, k;
	static Smell[][] sea;
	static List<Shark> sharks;
	static int[] dr = { -1, 1, 0, 0 }; // 상하좌우(1,2,3,4)
	static int[] dc = { 0, 0, -1, 1 };
	static int[][][] priority; //상어 번호, 방향, 우선순위

	public static void main(String[] args) throws IOException {

		init();
		//맨처음 냄새 뿌리기
		spreadSmell();
	
		int time = 0;
		while(sharks.size() > 1 && time <= 1000) {
			//이동하기 -> 상어 잡아먹기
			move();
			//냄새 지우기
			reduceSmell();
			//냄새뿌리기
			spreadSmell();
			time++;
		}
		System.out.println((time > 1000) ? -1 : time);
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		sea = new Smell[N][N];
		sharks = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				sea[i][j] = new Smell(0, 0, Integer.parseInt(st.nextToken()));
				if (sea[i][j].shark != 0) {
					sharks.add(new Shark(i, j, 0, sea[i][j].shark));
				}
			}
		}
		Collections.sort(sharks, (o1, o2) -> o1.num - o2.num);

		// 상어 방향 받기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			sharks.get(i).dir = Integer.parseInt(st.nextToken())-1;
		}
		
		// 상어 우선 순위 초기화
		priority = new int[M][DIRECTION_NUM][DIRECTION_NUM];
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < DIRECTION_NUM; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < DIRECTION_NUM; k++) {
					priority[i][j][k] = Integer.parseInt(st.nextToken())-1;
				}
			}
		}
		
	}

	private static void spreadSmell() {
		
		for(Shark shark : sharks) {
			sea[shark.x][shark.y].num = shark.num;
			sea[shark.x][shark.y].w = k;
		}
	}
	
	private static void move() {
		
		for(int i = 0; i < sharks.size(); i++) {
			Shark shark = sharks.get(i);
			getNextMove(shark);
			
			//이동한 좌표에 다른 상어가 있는 경우
			if(sea[shark.x][shark.y].shark != 0) {
				sharks.remove(i--);
				continue;
			}
			
			sea[shark.x][shark.y].shark = shark.num;	
		}
		
	}
	
	private static void getNextMove(Shark shark) {
		int curX = shark.x;
		int curY = shark.y;
		int curD = shark.dir;
		
		for(int d = 0; d < DIRECTION_NUM; d++) {
			int dx = curX + dr[priority[shark.num-1][curD][d]];
			int dy = curY + dc[priority[shark.num-1][curD][d]];
			
			if(!arrayBoundsValidation(dx,dy)) {
				continue;
			}
			
			//내 냄새이고 아직 이동 좌표를 못찾았을 경우
			if(sea[dx][dy].num == shark.num && curX == shark.x && curY == shark.y) {
				shark.x = dx;
				shark.y = dy;
				shark.dir = priority[shark.num-1][curD][d];
				sea[curX][curY].shark = 0;
				continue;
			}
			
			if(sea[dx][dy].w != 0) {
				continue;
			}
			
			//아무 냄새도 없는 칸 찾은 경우
			shark.x = dx;
			shark.y = dy;
			shark.dir = priority[shark.num-1][curD][d];
			sea[curX][curY].shark = 0;
			return;
		}
	}
	
	private static boolean arrayBoundsValidation(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < N);
	}
	
  //냄새 1씩 감소시키기
	private static void reduceSmell() {
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(sea[i][j].w == 0) {
					continue;
				}
				
				sea[i][j].w -= 1;
				if(sea[i][j].w > 0) {
					continue;
				}
				
        //냄새가 사라졌다면 상어 번호도 지워주기
				sea[i][j].num = 0;
			}
		}
		
	}
	
	private static class Shark {
		int x;
		int y;
		int dir; //상어 방향
		int num; //상어 번호

		public Shark(int x, int y, int dir, int num) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.num = num;
		}
	}

	private static class Smell {
		int num; //냄새 주인
		int w;	//냄새 세기
		int shark; //현재 상어 표시

		public Smell(int num, int w, int shark) {
			this.num = num;
			this.w = w;
			this.shark = shark;
		}
	}
}
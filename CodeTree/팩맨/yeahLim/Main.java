import java.io.*;
import java.util.*;

public class Main {
	static int[] pacman = new int[2];
	static List<Integer>[][] monsters = new ArrayList[4][4];
	static List<Integer>[][] eggs = new ArrayList[4][4];
	static int[][] corpses = new int[4][4];
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	static List<Integer>[][] newMonsters;
	static int monsterCount;
	static int[][] path = new int[3][2];
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		
		/* 입력 및 변수 초기화 */
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				monsters[i][j] = new ArrayList<>();
				eggs[i][j] = new ArrayList<>();
			}
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		pacman[0] = Integer.parseInt(st.nextToken()) - 1;
		pacman[1] = Integer.parseInt(st.nextToken()) - 1;
		// 몬스터 생성
		while(m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			monsters[x][y].add(Integer.parseInt(st.nextToken())-1);
		}	
		
		/* 실행 */
		for(int i=0; i<t; i++) {
			playPacman();	
		}
		
		/* 출력 */
		int answer = 0;
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				answer += monsters[i][j].size();
			}
		}
		System.out.println(answer);
	}
	
	/* 팩맨 한 턴 실행 */
	private static void playPacman() {
		// 1. 몬스터 복제 시도
		copyMonsters();
		// 2. 몬스터 이동
		moveMonsters();
		// 3. 팩맨 이동
		monsterCount = -1;
		visited = new boolean[4][4];
		visited[pacman[0]][pacman[1]] = true;
		movePacman(0, 0, new int[3][2], pacman[0], pacman[1]);
		// 몬스터 먹어치우기
		killMonsters();
		// 4. 몬스터 시체 소멸
		perishCorpses();
		// 5. 몬스터 복제 완성
		hatchMonsters();
	}

	/* 1. 몬스터 복제 시도 */
	private static void copyMonsters() {
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				eggs[i][j].addAll(monsters[i][j]); 
			}
		}
	}
	
	/* 2. 몬스터 이동 */	
	private static void moveMonsters() {
		// 새 배열
		newMonsters = new List[4][4];
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				newMonsters[i][j] = new ArrayList<>();
			}
		}
		// 새 배열에 몬스터 이동
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				for(int k=0; k<monsters[i][j].size(); k++) {
					moveAmonster(i, j, monsters[i][j].get(k));								
				}
			}
		}
		// 원래 배열에 새 배열 복사
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				monsters[i][j] = newMonsters[i][j];
			}
		}
	}
	
	/* 몬스터 한 마리 이동 */
	private static void moveAmonster(int i, int j, int monster) {
		boolean isMoved = false;
		int count = 0;
		
		for(int d=monster; d<8; d++) {
			int nx = dx[d] + i;
			int ny = dy[d] + j;
			if(checkMove(nx, ny)) {
				isMoved = true;
				newMonsters[nx][ny].add(d);
				break;
			}
			if(d == 7) {
				d = -1;
			}
			if(count++ == 8) break;
		}
		
		// 이동할 칸이 없는 경우
		if(!isMoved) {
			newMonsters[i][j].add(monster);
		}
	}

	/* 몬스터가 이동할 수 있는지 확인 */
	private static boolean checkMove(int nx, int ny) {
		// 격자 밖을 벗어낫을 경우
		if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) return false;
		// 팩맨이 있는 경우
		if(pacman[0] == nx && pacman[1] == ny) return false;
		// 몬스터 시체가 있는 경우
		if(corpses[nx][ny] > 0) return false;
		return true;
	}
	
	/* 3. 팩맨 이동 : DFS */
	private static void movePacman(int depth, int tmpMonsterCount, int[][] tmpPath, int x, int y) {
		if(depth == 3) {		
			// 몬스터의 수가 더 많을 때
			if(tmpMonsterCount > monsterCount) {
				for(int i=0; i<3; i++) {
					path[i] = tmpPath[i].clone();
				}
				monsterCount = tmpMonsterCount;
				pacman[0] = path[2][0];
				pacman[1] = path[2][1];
			}
			return;
		}
		
		for(int i=0; i<8; i+=2) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0 || nx>=4 || ny<0 || ny>=4) continue;		
			tmpPath[depth][0] = nx;
			tmpPath[depth][1] = ny;
			// 이미 방문한 경우
			if(visited[nx][ny]) {
				movePacman(depth+1, tmpMonsterCount, tmpPath, nx, ny);
			}
			// 아닌 경우
			else {
				visited[nx][ny] = true;
				movePacman(depth+1, tmpMonsterCount+monsters[nx][ny].size(), tmpPath, nx, ny);
				visited[nx][ny] = false;
			}			
		}
	}
	
	/* 몬스터 먹어치우기 */
	private static void killMonsters() {	
		if(monsterCount == -1) return;
		for(int i=0; i<3; i++) {
			int x = path[i][0];
			int y = path[i][1];
			if(monsters[x][y].isEmpty()) continue;
			corpses[x][y] = 3;
			monsters[x][y].clear();

		}
	}
	
	/* 4. 몬스터 시체 소멸 */
	private static void perishCorpses() {
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(corpses[i][j] != 0) {
					corpses[i][j]--; // 시체 턴 줄이기
				}	
			}
		}
	}
	
	/* 5. 몬스터 복제 완성 */
	private static void hatchMonsters() {
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				monsters[i][j].addAll(eggs[i][j]);
				eggs[i][j].clear();
			}
		}
	}
}
package CodeTree;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_싸움땅 {
	
	// 플레이어 정보 담을 클래스 
	static class Player{
		int x, y, d, s, gun,point;
		
		public Player(int x, int y, int d, int s, int gun, int point) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
			this.s = s;
			this.gun = gun; // 플레이어가 가지고 있는 총 
			this.point = point;
		}
	}
	
	static List<Integer>[][] matrix; // 총을 여러개 놓을 수 있기 때문에 ArrayList 배열 사용
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static List<Player> players; // 플레이어 리스트 
	static int N, M, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 격자의 크기
		M = Integer.parseInt(st.nextToken()); // 플레이어의 수
		K = Integer.parseInt(st.nextToken()); // 라운드의 수 
		
		players = new ArrayList<>();
		matrix = new ArrayList[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				matrix[i][j] = new ArrayList<>();
				
				int num = Integer.parseInt(st.nextToken());
				if(num != 0) {
					matrix[i][j].add(num);
				}
			}
		}
		
		for(int i = 0; i < M; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st2.nextToken()); // 플레이어 위치 
			int y = Integer.parseInt(st2.nextToken()); 
			int d = Integer.parseInt(st2.nextToken()); // 방향
			int s = Integer.parseInt(st2.nextToken()); // 플레이어 초기 능력치 
			
			players.add(new Player(x-1, y-1, d, s, 0, 0)); // 각 플레이어 정보를 리스트에 추가 
		}
		
		// K라운드까지 반복 
		for(int i = 0; i < K; i++) {
			gameRound();
		}
		
		printPoint();
		
		br.close();
	}
	
	static void gameRound() {
		
		// 모든 플레이어 한 번씩 진행해야 한 라운드 끝남 
		for(int i = 0; i < M; i++) {
			movePlayer(players.get(i));
		}
	}
	
	static void movePlayer(Player p) {
		
		int nx = p.x + dx[p.d];
		int ny = p.y + dy[p.d];
		
		// 만약 해당 방향으로 나갈 때 격자를 벗어나는 경우 
		if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
			// 정반대 방향으로 방향을 바꾸어서 1만큼 이동 
			if(p.d == 0) {
				p.d = 2;
			}else if(p.d == 1) {
				p.d = 3;
			}else if(p.d == 2) {
				p.d = 0;
			}else if(p.d == 3){
				p.d = 1;
			}
			
			nx = p.x + dx[p.d];
			ny = p.y + dy[p.d];
		}
		
		Player p2 = findPlayer(nx, ny);
		
		// 만약 이동한 방향에 플레이어가 없다면
		if(p2 == null) {
			// 해당 칸에 총이 있는지 확인 
			if(!matrix[nx][ny].isEmpty()) { // 그 자리에 총이 있다면 
				swapGun(nx, ny, p);
			}
			
			p.x = nx;
			p.y = ny;
		}else { //이동한 방향에 플레이어가 있다면 
			// 두 플레이어 싸움 
			int p1Power = p.s + p.gun;
			int p2Power = p2.s + p2.gun;
			int getPoint = Math.abs(p1Power - p2Power);
			
			// 능력치와 공격력의 합의 차이가 더 큰 사람이 승리 
			if(p1Power > p2Power) {
				p.point += getPoint; // 능력치 + 공격력의 합의 차이만큼 포인트 얻음 
				// 이긴 사람 위치를 먼저 옮겨줌 
				p.x = nx;
				p.y = ny;
				loser(nx, ny, p2); // 진 사람이 총을 놓고 가야됨 
				winner(nx, ny, p);
			}else if(p1Power == p2Power) { // 만약 power가 같으면 초기 능력치로 비교 
				if(p.s > p2.s) {
					p.point += getPoint;
					p.x = nx;
					p.y = ny;
					loser(nx, ny, p2);
					winner(nx, ny, p);
				}else {
					p2.point += getPoint;
					p2.x = nx;
					p2.y = ny;
					loser(nx, ny, p);
					winner(nx, ny, p2);
				}
			}else {
				p2.point += getPoint;
				p2.x = nx;
				p2.y = ny;
				loser(nx, ny, p);
				winner(nx, ny, p2);
			}
		}
	}
	
	static void swapGun(int x, int y, Player p) {
		int max = 0;
		int index = 0;
		
		if(!matrix[x][y].isEmpty()) { // 그 자리에 총이 있다면 
			
			// 총들 중 가장 공격력이 높은 총 찾기 
			for(int i = 0; i < matrix[x][y].size(); i++) {
				if(max < matrix[x][y].get(i)) {
					max = matrix[x][y].get(i);
					index = i;
				}
			}
			
			// 플레이어가 가진 총이 있으면
			if(p.gun != 0) {
				// 가지고있는 총과 비교하여 공격력이 더 센 총 획득하고 남은 총 자리에 두기 
				if(max > p.gun) {
					matrix[x][y].remove(index);
					matrix[x][y].add(p.gun);
					p.gun = max;
				}
			}else {
				p.gun = max; // 플레이어가 총 가지고 
				matrix[x][y].remove(index); // 총은 칸에서 삭제
			}
		}
	}
	
	static void winner(int x, int y, Player p) {
		// 칸에 떨어져 있는 총들과 원래 들고 있던 총 중 가장 공격력이 높은 총 획득 
		// 총이 여러 개 놓일 수 있음 .. 
		swapGun(x, y, p);
	}
	
	static void loser(int x, int y, Player p) {
		// 진 플레이어는 본인이 가지고 있는 총을 격자에 내려놓고
		matrix[x][y].add(p.gun);
		p.gun = 0;
		
		//오른쪽으로 90도 회전하며 빈 칸이 보이면 이동 
		for(int d = 0; d < 4; d++) {
			int nd = (p.d + d) % 4;
			int nx = x + dx[nd];
			int ny = y + dy[nd];
			
			Player p2 = findPlayer(nx, ny);
			
			// 만약 이동하려는 칸에 플레이어가 있거나 격자 범위 밖인 경우 x
			if(p2 != null || nx < 0 || nx >= N || ny < 0 || ny >= N) {
				continue;
			}
			
			// 갈 수 있는 칸이고 그 칸에 총이 있으면 
			if(!matrix[nx][ny].isEmpty()) {
				swapGun(nx, ny, p); // 비교 후 공격력이 더 큰 총 들기 
			}
			
			// 위치 저장해주고 for문 나가기 
			p.x = nx;
			p.y = ny;
			p.d = nd;
			
			break;
		}
	}
	
	static Player findPlayer(int x, int y) {
		
		// 플레이어 리스트 돌면서 해당 좌표에 플레이어가 있는지 확인 
		for(int i = 0; i < M; i++) {
			if(x == players.get(i).x && y == players.get(i).y) {
				return players.get(i);
			}
		}
		return null;
	}
	
	static void printPoint() {
		for(Player i : players) {
			System.out.print(i.point+" ");
		}
	}
}

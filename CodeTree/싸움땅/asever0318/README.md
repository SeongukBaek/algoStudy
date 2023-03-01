# [삼성 SW 역량테스트] 싸움땅

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
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
```
- Player 클래스를 만들어서 플레이어의 좌표, 방향, 초기능력치, 총, 포인트를 관리했다.

```java
static List<Integer>[][] matrix;
static List<Player> players; // 플레이어 리스트 
```
- 한 칸에 총을 여러개 놓을 수 있기 때문에 맵을 만들 때 ArrayList 배열을 사용했다.
- 플레이어들은 따로 리스트를 만들어서 사용했다.

```java
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
```
- 플레이어 리스트를 돌면서 모든 플레이어의 이동이 끝나면 한 라운드가 끝난다.
- 이동한 방향에 플레이어가 있을 경우 싸움을 하는데 이때 이긴 사람의 좌표를 먼저 바꿔주고 진 사람이 총을 두고 떠나면 이긴사람의 총을 바꿔주도록 했다.

## :black_nib: **Review**
- 구현하는 문제라 어렵지 않다고 생각했는데 코드 작성하다보니 빼먹는 부분이 많아서 고칠 게 많았다.
- 테스트케이스 6번에서 막혔는데 아직 해결하지 못했다.. 

# 싸움땅

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
static void move(Player player) {
	mapForPlayer[player.x][player.y] = 0;
	int nx = player.x + dx[player.dir];
	int ny = player.y + dy[player.dir];
	// 움직이려는 방향이 map 밖이면 진행방향 정반대로 변경
	if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
		player.dir = (player.dir + 2) % 4;
		nx = player.x + dx[player.dir];
		ny = player.y + dy[player.dir];
	}
	// 이동
	player.x = nx;
	player.y = ny;
	// 이동하려는 곳에 다른 player가 있는 경우
	if (mapForPlayer[nx][ny] != 0) {
		Player vsPlayer = players.get(mapForPlayer[nx][ny] - 1);
		Player loser = null;
		Player winner = null;
		if (player.gunAbility + player.initAbility > vsPlayer.gunAbility + vsPlayer.initAbility) {
			winner = player;
			loser = vsPlayer;
		}
		if (player.gunAbility + player.initAbility < vsPlayer.gunAbility + vsPlayer.initAbility) {
			winner = vsPlayer;
			loser = player;
		}
		if (player.gunAbility + player.initAbility == vsPlayer.gunAbility + vsPlayer.initAbility) {
			if (player.initAbility > vsPlayer.initAbility) {
				winner = player;
				loser = vsPlayer;
			}
			if (player.initAbility < vsPlayer.initAbility) {
				winner = vsPlayer;
				loser = player;
			}
		}
		doTasksAfterFight(winner, loser);
	}
	// 이동하려는 곳에 다른 player가 없는 경우
	else {
		mapForPlayer[nx][ny] = player.num;
		if (!map[nx][ny].isEmpty() && (int) map[nx][ny].peek() > player.gunAbility) {
			if (player.gunAbility != 0) {
				map[nx][ny].add(player.gunAbility);
			}
			player.gunAbility = (int) map[nx][ny].poll();
		}
	}
}
```
   - 총이 놓인 현재 상황을 priorityQueue[][] map에 저장하였다.
   - player의 위치 정보를 int[][] mapForPlayer에 저장하였다.
   - player가 이동할때 해당 위치에 다른 player가 있는지, 없는지의 경우로 나누어 구현하였다.

```java
static void doTasksAfterFight(Player winner, Player loser) {
	// 이긴 player의 점수 갱신
	winner.score += winner.gunAbility + winner.initAbility - loser.gunAbility - loser.initAbility;
	//진 player는 총을 해당 격자에 내려 놓음
	if (loser.gunAbility != 0) {
		map[loser.x][loser.y].add(loser.gunAbility);
		loser.gunAbility = 0;
	}
	//진 player가 이동할 곳을 찾음
	int loserX = loser.x + dx[loser.dir];
	int loserY = loser.y + dy[loser.dir];
	while (loserX < 0 || loserX >= n || loserY < 0 || loserY >= n || mapForPlayer[loserX][loserY] != 0) {
		loser.dir = (loser.dir + 1) % 4;
		loserX = loser.x + dx[loser.dir];
		loserY = loser.y + dy[loser.dir];
	}
	//진 player 이동 및 위치 갱신
	loser.x = loserX;
	loser.y = loserY;
	mapForPlayer[loserX][loserY] = loser.num;
	//이동한 곳에 총이 있다면 주움
	if (!map[loserX][loserY].isEmpty()) {
		loser.gunAbility = (int) map[loserX][loserY].poll();
	}
	//이긴 player는 승리한 칸에서 가장 쎈 총을 획득
	if (!map[winner.x][winner.y].isEmpty() && (int) map[winner.x][winner.y].peek() > winner.gunAbility) {
		map[winner.x][winner.y].add(winner.gunAbility);
		winner.gunAbility = (int) map[winner.x][winner.y].poll();
	}
	//이긴 player 위치 갱신
	mapForPlayer[winner.x][winner.y] = winner.num;
}
  ```
   - 문제에 명시된 싸움 후 task들을 수행 해준다.
  
  
## :black_nib: **Review**
 - player를 이동시면서 해야할 task가 많은 문제였다. 차근차근 명시된 것을 구현하는 것이 중요한 문제였다.

  
  	

  

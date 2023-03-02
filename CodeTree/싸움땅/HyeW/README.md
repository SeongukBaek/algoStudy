# [삼성 SW 역량테스트] 싸움땅

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
ground = new PriorityQueue[n][n];
for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
        ground[i][j] = new PriorityQueue<>(Comparator.reverseOrder());
    }
}
```
주어진 무기 정보는 `2차원 PriorityQueue`에 저장했다.<br/>
공격력이 높은 순으로 정렬이 되도록 내림차순으로 설정했다.


```java
static void runGame() {

		for (int i = 0; i < m; i++) {
			Player cur = players.get(i);
			int dx = cur.x + dr[cur.d];
			int dy = cur.y + dc[cur.d];

			// 이전 좌표 지움
			visited[cur.x][cur.y] = 0;

			// 벽만났을때
			if (!arrayBoundsValidation(dx, dy)) {
				cur.d = turn180(cur.d);
				dx = cur.x + dr[cur.d];
				dy = cur.y + dc[cur.d];
			}

			// 플레이어 만났을때
			if (visited[dx][dy] != 0) {
				// 1. 싸운다.
				int winner = getWinner(visited[dx][dy] - 1, i);
				int loser = getLoser(winner, visited[dx][dy] - 1, i);
				
				// 2-1. 이긴 사람 제자리
				visited[dx][dy] = winner + 1;
				players.get(winner).x = dx;
				players.get(winner).y = dy;
				
				// 2-2. 진 사람 이동 (진 사람은 총 떨어트림)
				players.get(loser).x = dx;
				players.get(loser).y = dy;
				dropGun(players.get(loser));
				players.get(loser).gun = 0;
				moveLoser(players.get(loser), loser);
				
				// 3. 각자 총 줍기
				getGun(players.get(loser));
				
				dropGun(players.get(winner));
				getGun(players.get(winner));


			} else {// 플레이어를 만나지 않았을 때 총 그냥 주움
				visited[dx][dy] = i + 1;
				players.get(i).x = dx;
				players.get(i).y = dy;
				dropGun(players.get(i));
				getGun(players.get(i));
			}

		}
	}
```
`runGame()`은 한 라운드의 동작을 나타낸다.<br/>
모든 플레이어는 차례대로 자기가 바라보는 방향으로 한 칸 전진한다.
1. 만약 벽이라면 반대편으로 이동한다.
2. 플레이어를 만나면 싸운다.
    2-1. 이긴 플레이어는 싸운 자리에 머문다.
    2-2. 진 플레이어는 총을 놓고 오른쪽으로 90도씩 회전하며 빈칸이 나오면 이동한다.
    2-3. 두 플레이어는 해당 자리에서 총을 줍는다.

## :black_nib: **Review**
- 구현문제는 어떤 흐름으로 코드를 작성할지 미리 생각하면 생각보다 쉽게 문제가 풀리는 것 같다.
- 하지만 중간에 이긴 플레이어 점수를 계산하는 도중에 기존 값에 새로운 값을 더하는 것이 아니라 새로운 값으로 덮어쓰는 실수를 해서 한참 이 문제를 찾았다.
중간중간 값이 제대로 나오는지 확인하는 습관을 길러야 겠다.
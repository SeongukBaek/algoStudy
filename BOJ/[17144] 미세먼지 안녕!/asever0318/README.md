# [17144] 미세먼지 안녕!

## :pushpin: **Algorithm**

bfs, 구현

## :round_pushpin: **Logic**

```java
for(int t = 0; t < T; t++) {

		// 1. 미세먼지 확산
		moveDust(dust); // 다음에 퍼질 먼지 큐 반환
		copyMap(map, score);

		// 2. 공기청정기 작동
		airMachineTop();
		airMachineBottom();
		copyMap(score, map);

		// 3. 먼지 위치 세팅
		findDust();

}
System.out.println(countDust());
```

- 크게 2단계로 처리했다.
- 1. 미세먼지 확산
- 2. 공기청정기 작동(위, 아래)
- 3. 새로운 먼지 위치를 찾아준다.

```java
static void moveDust(Queue<Pos> queue) {

        while(!queue.isEmpty()) {

            Pos current = queue.poll();
            int dustSize = map[current.x][current.y] / 5;
            int count = 0;

            for(int d = 0; d < 4; d++) {
                int nx = current.x + dx[d];
                int ny = current.y + dy[d];

                if(nx < 0 || nx >= R || ny < 0 || ny >= C) {
                    continue;
                }

                if(map[nx][ny] >= 0) { // 빈 공간일때만 확산
                	score[nx][ny] += dustSize; // 미세먼지 확산
                    count++; // 몇 칸에 퍼졌는지 카운트
                }
            }

            score[current.x][current.y] -= (dustSize*count);
        }
    }
```

- 미세먼지 확산 : BFS로 4방향을 탐색하면서 빈 공간일 경우 먼지를 확산시킨다.

```java
 static void airMachineTop() {
        // 위
        int x = machine.get(0).x;
        int y = machine.get(0).y;
        int d = 0;

        map[x-1][y] = 0; // 먼지가 공기청정기에 들어가면 없어져야 함!

        while(true) {
            // 공기청정기가 있던 자리로 돌아오면 작동 종료

            int nx = x + up[d][0];
            int ny = y + up[d][1];

            // 바람이 부는 루트 전체를 한칸씩 이동

            // 벽을 만나면 방향 틀기
            if(nx < 0 || nx > machine.get(0).x || ny < 0 || ny >= C ) {
                d++;
                if(d == 4) {
                    d = 0;
                }
                continue;
            }


            int temp = map[nx][ny];
            map[nx][ny] = map[x][y];
            map[x][y] = temp;
            x = nx;
            y = ny;

            if(x == machine.get(0).x && y == machine.get(0).y) {
                break;
            }
        }
    }
```

- 공기청정기 작동 : 시계 / 반시계 방향으로 테두리(?)를 돌면서 한 칸씩 배열을 밀어준다. 공기청정기에 닿으면 돌리기를 멈춘다.

```java
static void findDust() {
    	Queue<Pos> newDust = new LinkedList<>();

    	for(int i = 0; i < R; i++) {
    		for(int j = 0; j < C; j++) {
    			if(map[i][j] > 0) {
    				newDust.add(new Pos(i, j));
    			}
    		}
    	}
    	dust.addAll(newDust);
    }
```

- 공기청정기 작동 후에는 남아있는 먼지의 위치를 찾아준다. --> 다음 먼지 확산에 사용

## :black_nib: **Review**

- 문제에 있는대로 하면 되는 구현문제였다.
- 먼지가 공기청정기에 들어가면 사라져야 하는데 처음에 이 처리를 해주지 않아서 틀렸다. 먼저 들어가게 되는 좌표의 먼지를 0으로 없애주고 배열을 돌려서 해결할 수 있었다.
- 공기청정기 작동할 때 위쪽과 아래쪽 함수를 따로 만들었는데 비슷한 코드라서 합칠 수 있을 거 같다..

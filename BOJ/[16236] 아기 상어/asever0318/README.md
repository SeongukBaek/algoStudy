# [16236] 아기 상어

## :pushpin: **Algorithm**

BFS, 구현

## :round_pushpin: **Logic**

```java
while(fishNum > fishCount) {
    Fish next = findFish(current); // 먹을 수 있는 가장 가까운 물고기 찾기

    if(next.x == -1 && next.y == -1) { // 먹을 수 있는 물고기가 없으면 끝
        break;
    }

    fishCount++; // 먹은 물고기 수 ++
    count++; // 상어크기를 위한 카운트
    map[next.x][next.y] = 0; // 물고기 먹었으니 0으로 표시

    // 상어의 크기만큼 물고기를 먹을 때마다 사이즈 1증가
    if(count == sharkSize) {
        sharkSize++;
        count = 0;
    }

    current = next;
}

```

- 전체적인 흐름은 먹을 수 있는 가장 가까운 물고기를 찾고, 해당 물고기를 먹었을 때 물고기 수와 상어의 크기, 맵을 갱신해준다.
- 다시 해당 위치에서 가장 가까운 물고기를 찾아서 물고기를 다 먹거나 더 이상 먹을 수 있는 물고기가 없을 때까지 반복한다.

```java
static Fish findFish(Fish start) {
    ...
    while(!q.isEmpty()) {
			int size = q.size();

			for(int i = 0; i < size; i++) {
				Fish current = q.poll();

				for(int d = 0; d < 4; d++) {
					int nx = current.x + dx[d];
					int ny = current.y + dy[d];

					if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
						continue;
					}

					// 상어 크기보다 큰 물고기가 있으면 이동 x
					if(sharkSize < map[nx][ny]) {
						continue;
					}

					if(visited[nx][ny]) {
						continue;
					}

					// 상어크기보다 작은 먹을 수 있는 물고기를 찾으면
					if(map[nx][ny] > 0 && map[nx][ny] < sharkSize) {
						check = true;
						next.add(new Fish(nx, ny));
					}

					visited[nx][ny] = true;
					q.add(new Fish(nx, ny));
				}
			}
			time++;
			if(check) {
				totalTime += (time);
				break;
			}
		}

		// 같은 거리에 여러 물고기가 있을 때, 위쪽 -> 왼쪽 우선순위
		if(next.size() > 0) {
			target = next.poll();
		}

		return target;
}
```

- BFS로 start 위치에서부터 인접한 4방향을 탐색하면서 가장 가까운(가장 먼저 발견한) 먹을 수 있는 물고기를 찾는다.
- 동일한 레벨에 여러 물고기가 있을 수 있으므로 큐의 사이즈로 레벨을 체크해주고 PriorityQueue를 통해서 같은 레벨에서 먹을 수 있는 물고기가 여러마리일 경우 위쪽 -> 왼쪽으로 우선순위를 두고 관리했다.

## :black_nib: **Review**

- 한 번만에 통과했다 야호
- BFS 한 번으로 모든 물고기를 처리하려고 해서 잘안풀렸는데, 한 마리씩 적용했더니 풀렸다.
- 오랜만에 PriorityQueue를 사용할 수 있었던 문제였다.

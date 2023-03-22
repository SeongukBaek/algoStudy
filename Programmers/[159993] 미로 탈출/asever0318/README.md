# [159993] 미로 탈출

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
int toLever = findMinCost(start, lever);
        int toEnd = findMinCost(lever, end);

        if(toLever == -1 || toEnd == -1) {
        	return -1;
        }

        return toLever + toEnd;

```

- 출발지 --> 레버 --> 도착지로 가는 최소 시간을 찾는 것이기 때문에 출발지 --> 레버의 최소 시간과 레버 --> 도착지의 최소 시간을 각각 구해서 더 해주면 답을 찾을 수 있다고 생각했다.

```java
static int findMinCost(Pos start, Pos end) {
		boolean[][] visited = new boolean[map.length][map[0].length];
		Queue<Pos> queue = new LinkedList<>();
		int min = 9999;

		queue.add(start);
		visited[start.x][start.y] = true;

		while(!queue.isEmpty()) {
			Pos current = queue.poll();

			// 도착지 도착하면 걸린 시간 return
			if(current.x == end.x && current.y == end.y) {
				if(min > current.cnt) {
					min = current.cnt;
					break;
				}
			}

			for(int d = 0; d < 4; d++) {
				int nx = current.x + dx[d];
				int ny = current.y + dy[d];

				if(nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) {
					continue;
				}

				if(map[nx][ny] == 'X') { // 벽이면
					continue;
				}

				if(!visited[nx][ny]) { // 방문 안했으면
					visited[nx][ny] = true; // 방문표시
					queue.add(new Pos(nx, ny, current.cnt+1));
				}
			}
		}

		if(min == 9999) {
			return -1;
		}

		return min; // 탈출할 수 없다면 -1
	}
```

- 시작점과 도착점을 매개변수로 넘겨주고 시작점부터 4방향을 탐색하면서 벽이 아니고 방문하지 않은 곳이면 큐에 넣고 방문하도록 했다.
- 도착점과 좌표가 같으면 시간을 비교해 min값을 갱신해주고, min값의 갱신이 없었으면 도착점까지 가지 못한 것이므로 -1을 반환해준다.

## :black_nib: **Review**

- 처음에 출발지에서 레버까지만 탈출 여부를 확인하고 레버에서 도착점까지도 탈출을 고려해야 되는 것을 놓쳤다. toLever와 toEnd 둘 다 탈출 여부를 확인해주고 둘 중 하나라도 탈출하지 못했으면 -1을 반환하도록 고쳐주었다.

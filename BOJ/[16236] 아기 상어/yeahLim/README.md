# [16236] 아기 상어

## :pushpin: **Algorithm**

구현, BFS, 우선순위 큐, 그래프 이론, 그래프 탐색, 시뮬레이션

## :round_pushpin: **Logic**

```java
pq = new PriorityQueue<>((o1, o2) ->
    (o1.dist != o2.dist) ? Integer.compare(o1.dist, o2.dist) :
    (o1.x != o2.x) ? Integer.compare(o1.x, o2.x) : Integer.compare(o1.y, o2.y));
```

- priority queue를 이용해서, 상어가 물고기를 먹을 수 있는 우선 순위를 결정해 높은 순위인 것만 먹도록 했다.
  - 1. 거리순
  - 2. 가장 위쪽 순
  - 3. 가장 왼쪽 순

```java
while(isEaten) {
    isEaten = false;
    visited = new boolean[n][n];
    // 우선순위 1. 거리순 2. 가장 위쪽 3. 가장 왼쪽
    pq = new PriorityQueue<>((o1, o2) ->
            (o1.dist != o2.dist) ? Integer.compare(o1.dist, o2.dist) :
            (o1.x != o2.x) ? Integer.compare(o1.x, o2.x) : Integer.compare(o1.y, o2.y));
    pq.offer(new Current(sharkX, sharkY, 0)); // 상어의 x좌표, y좌표, 이동한 거리
    visited[sharkX][sharkY] = true;

    while(!pq.isEmpty()) {
        Current cur = pq.poll();

        // 상어보다 작은 크기의 물고기일 경우
        if(map[cur.x][cur.y] != 0 && map[cur.x][cur.y] < sharkSize) {
            map[cur.x][cur.y] = 0;
            sharkX = cur.x;
            sharkY = cur.y;
            time += cur.dist;
            // 상어 크기 만큼의 물고기를 먹었을 때
            if(++eatenFish == sharkSize) {
                eatenFish = 0;
                sharkSize++;
            }
            isEaten = true;
            break;
        }

        for(int i = 0; i < 4; i++) {
            int nx = cur.x + dx[i];
            int ny = cur.y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
            if (visited[nx][ny]) continue;
            if (map[nx][ny] > sharkSize) continue; // 상어보다 클 경우

            visited[nx][ny] = true;
            pq.offer(new Current(nx, ny, cur.dist + 1));
        }
    }
}
```

- 상어보다 물고기가 작으면 먹고, break해준다.
- 먹었으면, 다시 priority queue를 초기화해주고, 그 위치에서 다시 먹을 수 있는 물고기를 탐색한다.
- 위 과정을 상어가 먹을 수 있는 물고기가 없을때까지 반복해준다.

## :black_nib: **Review**

- priorityqueue에서 compare함수를 이용해서 여러개의 우선순위를 정의하는 방법은 배웠다.

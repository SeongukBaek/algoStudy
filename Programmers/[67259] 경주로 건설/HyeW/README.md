# [67259] 경주로 건설

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
while(!path.isEmpty()){
    Road cur = path.poll();
    for(int i = 0; i < 4; i++){
        int dx = dr[i]+cur.x;
        int dy = dc[i]+cur.y;

        if(isMapOut(dx, dy) || board[dx][dy] == 1){
            continue;
        }
        int fee = minCost[cur.x][cur.y][cur.dir] + 100;
        // 다음 좌표 값이 일직선으로 도로를 놓을 수 없다면 500원을 추가한다.
        if(cur.dir != i && oppositeDir(cur.dir) != i){
            fee += 500;
        }
        // 방문한 좌표이고 현재 비용이 저장된 최소 비용보다 크다면 다음으로 넘어간다.
        if(minCost[dx][dy][i] != 0 && minCost[dx][dy][i] <= fee){
            continue;
        }
        minCost[dx][dy][i] = fee;
        path.add(new Road(dx, dy, i));
    }
}
```
- 비용 계산을 방향으로 나누어 계산한다.
  - 그래서 `int[][][] minCost` 3차원 배열을 두어 방향에 따른 최소비용을 저장한다.
- BFS의 visited 처리는 현재 좌표의 최소비용을 가지고 방문처리와 같은 효과를 냈다.

## :black_nib: **Review**

- 처음에 BFS 큐를 비용순으로 정렬해서 문제를 해결했는데 해당 좌표에 먼저 도착했다고 가장 적은 비용이 나오는 것이 아니었다.!
- 방향에 따라 다음 좌표에 대한 비용이 달라지기 때문에 방향별로 비용 최소값을 저장해 문제를 해결했다.
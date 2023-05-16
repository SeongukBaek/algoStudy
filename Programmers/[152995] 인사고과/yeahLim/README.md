# [67259] 경주로 건설

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
// 직선으로 가는 경우
if (i % 2 == cur.dir % 2) {
    if (cost[nx][ny][i] > cost[cur.x][cur.y][cur.dir] + 100) {
        cost[nx][ny][i] = cost[cur.x][cur.y][cur.dir] + 100;
        q.offer(new Current(nx, ny, i));

    }
}
// 코너로 도는 경우
else {
    if (cost[nx][ny][i] > cost[cur.x][cur.y][cur.dir] + 600) {
        cost[nx][ny][i] = cost[cur.x][cur.y][cur.dir] + 600;
        q.offer(new Current(nx, ny, i));

    }
}
```

- 상(0), 하(1), 좌(2), 우(3)의 방향 숫자를 지정하였다.
- 방향 숫자를 짝수로 나눴을 때, 같을 경우를 직선으로 가는 경우, 다를 경우 코너로 도는 경우로 나누어서 진행했다.
- cost값이 더 작아질 경우에만 queue에 넣어주었다.

## :black_nib: **Review**

- 처음에 DFS로 풀려고하다가, 코드 작성하면서 BFS로 바꾸어서 풀었다.
- 방향처리 할때, 어느 방향으로 왔는지 처리하는 부분이 중요했다.
- cost의 초기 값을 MAX값으로 주었는데, 이때 start지점의 cost값을 다시 0으로 설적해 주지 않아서 계속 MAX값만 정답이 나왔다. 이 부분 조심해야겠다.

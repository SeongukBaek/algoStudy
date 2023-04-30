# [13549] 숨바꼭질 3

## :pushpin: **Algorithm**

DP, BFS

## :round_pushpin: **Logic**

```java
q.offer(new int[]{n, 1}); // 위치, 시간
visited[n] = 1;
```

- 처음에 시간을 0으로 초기화 해줘야하나, 0은 아직 방문하지 않은 곳의 시간을 의미하기 때문에 1로 설정한 후, 반환할때 1을 빼준다.

```java
while(!q.isEmpty()) {
    int[] cur = q.poll();

    int next = cur[0] - 1;
    if(next >= 0 && (visited[next] > cur[1] || visited[next] == 0)) {
        visited[next] = cur[1] + 1;
        q.offer(new int[] {next, cur[1] + 1});
    }

    next = cur[0] + 1;
    if(next <= 100000 && (visited[next] > cur[1] || visited[next] == 0)) {
        visited[next] = cur[1] + 1;
        q.offer(new int[]{next, cur[1] + 1});
    }

    next = cur[0] * 2;
    if(next <= 100000 && (visited[next] > cur[1] || visited[next] == 0)) {
        visited[next] = cur[1];
        q.offer(new int[] {next, cur[1]});
    }
}
```

- visited 배열을 boolean이 아닌 int형으로 선언해준다.
- 아직 방문하지 않은 경우(visited[i] = 0), 혹은 방문했으나 더 시간이 짧을 경우, visited에 시간을 업데이트해주고, queue에 추가해준다.

## :black_nib: **Review**

- BFS로 가중치가 있는 간선을 해결해볼 수 있는 시간이었다.

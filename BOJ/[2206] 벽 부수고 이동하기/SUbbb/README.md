# [2206] 벽 부수고 이동하기

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
// 벽이고, 이전에 벽을 부순 적이 없는 경우
if (map[nx][ny] == 1 && !isBoomed) {
    isVisited[nx][ny][1] = true;
    path.add(new Location(nx, ny, nCost, true));
}
// 벽이 아닌 경우
else if (map[nx][ny] == 0){
    // 이전에 벽을 부순 적이 있고, 방문한 적이 없다면
    if (isBoomed && !isVisited[nx][ny][1]) {
        isVisited[nx][ny][1] = true;
        path.add(new Location(nx, ny, nCost, isBoomed));
    }
    // 이전에 벽을 부순 적이 없고, 방문한 적이 없다면
    else if (!isBoomed && !isVisited[nx][ny][0]){
        isVisited[nx][ny][0] = true;
        path.add(new Location(nx, ny, nCost, isBoomed));
    }
}
```

- 벽을 부수거나, 부수지 않은 경우에 대해 모두 최단 경로를 구한다.

## :black_nib: **Review**
- 아이디어는 금방 떠올릴 수 있었는데, 3차원 배열을 만드는 구현에서 좀 막혀서 정답 코드를 참고했다...
# [49994] 방문 길이

## :pushpin: **Algorithm**

그래프 탐색

## :round_pushpin: **Logic**

```java
private void traverse(String dirs) {
    for (int index = 0; index < dirs.length(); index++) {
        int direction = convertToInt(dirs.charAt(index));
        
        int nx = x + moves[direction][0];
        int ny = y + moves[direction][1];
        
        // 맵밖으로 나가는 경우, 무시
        if (!isIn(nx, ny)) {
            continue;
        }
        
        // 방문한 적 없는 경우
        if (!isVisited[nx][ny][direction]) {
            length++;
            isVisited[nx][ny][direction] = true;
            isVisited[x][y][oppositeDirection(direction)] = true;
        }
        
        x = nx;
        y = ny;
    }
}
```

- 주어진 방향으로 이동한다.
- 이때 방문한 적 없는 경우 최초 방문 길이를 증가시키고, `[출발][도착][주어진 방향]`과, `[도착][출발][주어진 방향의 반대 방향]`에 방문 처리를 수행한다.

## :black_nib: **Review**

- 주어진 입력 범위가, 시작점을 0,0으로 잡고 수행해도 가능한 범위여서, 일단 좌표를 이동시키는 것부터 로직의 시작이었다.
- 처음에는 선 기준이 아닌 좌표 기준으로 방문 처리를 생각했는데, 다시 보니 선 기준 방문 처리가 필요했다.
  - 생각해보면, 주어진 방향과 출발, 도착점을 알면 그 선을 표현할 수 있겠다 싶었다.
  - 이런 아이디어는 .. 좋은 것 같다.

# [1890] 점프

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
for(int i = 0; i < N; i++) {
  for(int j = 0; j < N; j++) {
    int jump = map[i][j];
    if(jump == 0) {
      break;
    }
    if(arrayBoundsValidation(i+jump, j)) {
      visited[i+jump][j] += visited[i][j];
    }

    if(arrayBoundsValidation(i, j+jump)) {
      visited[i][j+jump] += visited[i][j];
    }
  }
}
```

현재 좌표`(x, y)`에서 점프해서 갈 수있는 좌표`(dx, dy)`를 구한다. <br/>
각 지점에 `(0,0)`에서 부터 점프해서 올 수 있는 경로 개수를 저장한 배열이 `visited`이다.
<br/>
`visited[dx][dy]`에 `visited[x][y]`를 더해준다.

## :black_nib: **Review**

- 파이프 옮기기와 비슷한 DP문제였다.

  - 하지만 파이프 옮기기는 현재 좌표에 올 수 있는 파이프 방법 수를 찾는 것이였고 이번 문제는 DP는 현재 좌표에서 갈 수 있는 좌표에 값을 더해주는 방법이었다.

- 처음에 DFS + DP로 문제를 해결했는 메모리초과가 났다. int이차원 배열인 visited에 경로 개수를 저장하고 방문한 지점이라면 더 깊이 탐색하지 않고 visited배열 값을 넘겨줬다.

  ```java
  private static long searchPath(int x, int y) {

      if (x == N - 1 && y == N - 1) {
        return 1;
      }

      int jump = map[x][y];

      // 오른쪽 탐색
      if (y + jump < N) {
        if (visited[x][y + jump] == 0) {
          // 방문하지 않은 곳이라면 탐색
          visited[x][y] += searchPath(x, y + jump);
        } else {
          // 방문한 곳이라면 저장된 값 반환
          visited[x][y] += visited[x][y + jump];
        }
      }

      // 아래쪽 탐색
      if (x + jump < N) {
        if (visited[x + jump][y] == 0) {
          // 방문하지 않은 곳이라면 탐색
          visited[x][y] += searchPath(x + jump, y);
        } else {
          // 방문한 곳이라면 저장된 값 반환
          visited[x][y] += visited[x + jump][y];
        }
      }

      return visited[x][y];

    }
  ```

  이렇게 하면 아마 N이 100이고 모든 좌표가 1일때 10000정도의 depth가 생겨서 메모리 초과가 나을거라고 생각한다.

- 그래서 먼저 DP를 사용할 생각이 들면 DP만 사용하며 풀 수 있는지 한번 고민하고 안된다면 다른 알고리즘과 조합해서 풀 생각을 해야겠다.

# [17070] 파이프 옮기기 1 

## :pushpin: **Algorithm**

DP (동적 계획법), 그래프 이론

## :round_pushpin: **Logic**

```java
pipes = new Pipe[N][N];
for (int i = 0; i < N; i++) {
    st = new StringTokenizer(br.readLine());
    for (int j = 0; j < N; j++) {
        house[i][j] = Integer.parseInt(st.nextToken());
        if (house[i][j] == 1) {
            pipes[i][j] = new Pipe(0, 0, 0);
        }
    }
}

pipes[0][1] = new Pipe(1, 0, 0);
pipes[0][0] = new Pipe(0, 0, 0);
carryPipes();
```
벽이 있는 좌표와 파이프가 있는 좌표를 초기화 시킨다.

```java
for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {

        if (pipes[i][j] != null) {
            continue;
        }

        int hor = 0;
        int dia = 0;
        int ver = 0;

        // 가로
        if (j - 1 >= 0) {
            hor = pipes[i][j - 1].hor + pipes[i][j - 1].dia;
        }

        // 대각선
        if (j - 1 >= 0 && i - 1 >= 0 && checkSpace(i, j)) {
            dia = pipes[i - 1][j - 1].hor + pipes[i - 1][j - 1].dia + pipes[i - 1][j - 1].ver;
        }

        // 세로
        if (i - 1 >= 0) {
            ver = pipes[i - 1][j].ver + pipes[i - 1][j].dia;
        }

        pipes[i][j] = new Pipe(hor, dia, ver);
    }
}
```
현재 위치가 `(x, y)` 일 때, <br/>
- **가로** 방향으로 오는 파이프 개수는 `(x, y-1)`좌표에 있는 **가로, 대각선** 방향의 파이프 개수의 합
- **대각선** 방향으로 오는 파이프 개수는 `(x-1, y-1)`좌표에 있는 **가로, 대각선, 세로** 방향의 파이프 개수의 합
- **세로** 방향으로 오는 파이프 개수는 `(x-1, y-1)`좌표에 있는 **대각선, 세로** 방향의 파이프 개수의 합

으로 현재 좌표에 올 수 있는 파이프 이동 방법 개수를 구할 수 있다.

## :black_nib: **Review**

- DP와 탐색을 합친 문제이다.
    - 처음에 bfs로 접근해서 현재 좌표에서 갈 수 있는 좌표를 구하는 방법으로 도전했었다. 하지만 이미 처리된 좌표에서 새로운 값이 추가되는 문제가 생겨서 bfs로는 해결할 수가 없었다.
    - dp문제는 내가 있는 좌표의 값을 얻기위한 정규식을 만드는 방법으로 접근을 해야한다.
- 파이프가 가로, 세로, 대각선으로 있는 방향에 따라 갈 수 있는 좌표가 달라지기에 가로, 세로, 대각선으로 나누어 파이프를 저장했다.
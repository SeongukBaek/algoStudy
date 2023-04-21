# [49994] 방문 길이

## :pushpin: **Algorithm**

그래프 탐색

## :round_pushpin: **Logic**

```java
boolean[][][] visited = new boolean[11][11][4];
```

- x좌표와 y좌표가 -5부터 5개로 각각 11개, 그리고 좌표들의 이동 방향 4개를 반영해서 초기화 시켜주었다.

```java
for(char dir : dirs.toCharArray()) {
    nextX = curX;
    nextY = curY;

    if (dir == 'U' && nextX < 10) {
        nextX += 1;
        d = 0;
    } else if (dir == 'D' && nextX > 0) {
        nextX -= 1;
        d = 1;
    } else if (dir == 'R' && nextY < 10) {
        nextY += 1;
        d = 2;
    } else if (dir == 'L' && nextY > 0) {
        nextY -= 1;
        d = 3;
    }
    else continue;


    if (!visited[nextX][nextY][d]) {
        visited[nextX][nextY][d] = true;
        if(d == 0 || d == 2) {
            visited[curX][curY][d+1] = true;
        }
        else visited[curX][curY][d-1] = true;
        answer++;
    }
    curX = nextX;
    curY = nextY;

}
```

- U, D, R, L일 경우 각각 이동할 다음 좌표를 구해주고, 그 좌표에 아직 방문하지 않았다면, 방문처리를 해준다.
- 이때, a -> b 좌표로 가는 경우와, b -> a로 가는 경우 모두 방문 처리를 해준다.

## :black_nib: **Review**

- 이렇게 칸이 아닌 좌표로 주어질 때, 어떻게 구현해야할지 약간 혼란스러웠다.
- 칸을 기준으로 구현하면, 코드가 너무 복잡해져서, 좌표로 구현하는 방법을 참고했다.
- 좌표로 구현할때, 추가로 방향도 같이 visited배열에 넣어주면 된다!

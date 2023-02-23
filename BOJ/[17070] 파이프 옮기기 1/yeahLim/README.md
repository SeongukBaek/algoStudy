# [17070] 파이프 옮기기 1 - Java

## :pushpin: **Algorithm**

BFS, 그래프 이론

## :round_pushpin: **Logic**

```java
static class Current {
    int x;
    int y;
    int direction;
    public Current(int x, int y, int direction) {
        this.x = x; // x좌표
        this.y = y; // y좌표
        this.direction = direction; // 방향 (가로:0, 세로: 1, 대각선: 2)
    }
}

static void movePipe(){
        Queue<Current> q = new LinkedList<>();
```
- 큐에 Current값을 넣어줘서 큐가 빌때까지 BFS로 탐색해서 풀어주었다.

```java
static boolean checkWall(int x, int y, int direction) {

    if(direction == 0) { // 가로일때
        return (y >= n || house[x][y] == 1);

    } else if(direction == 1) { // 세로일때
        return (x >= n || house[x][y] == 1);

    } else { // 대각선일때
        return (x >= n || y >= n || house[x][y-1] == 1 || house[x-1][y] == 1 || house[x][y] == 1); // 이동할때 3칸 차지
    }
}

```

- 조건문에 checkWall()를 넣어서, 벽이 만나면 true, 벽이 없다면 false를 반환해서 false일때만 탐색한다. 

## :black_nib: **Review**

- bfs로 푸니까 시간초과가 나서 dp로 풀어야하나 했는데
  ```if(house[n-1][n-1] == 1) return ;``` 도착지가 벽인 경우를 처음부터 빠로 예외처리를 해줬더니 통과가 됐다.

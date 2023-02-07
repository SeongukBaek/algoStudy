# [14500] 테트로미노

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
private static int placeTetromino(List<Node> squares, int x, int y) {
    int sum = 0;
    for (Node square : squares) {
        int nx = square.x + x;
        int ny = square.y + y;

        if (!isIn(nx, ny)) {
            return -1;
        }
        sum += map[nx][ny];
    }
    return sum;
}
```

- 특정 테트로미노의 좌표 모음과 시작 좌표를 받아, 해당 위치에 테트로미노를 놓았을 경우의 영역 숫자의 합을 반환한다.

## :black_nib: **Review**
- 모든 경우의 수를 다 구해두고, 하나씩 확인해보는 방식으로 구현했다.
- DFS 방식으로 메모리 사용량을 줄일 방법도 있다는 것을 알게 됐다.
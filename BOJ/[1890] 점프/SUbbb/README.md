# [1890] 점프

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
for (int x = 0; x < N; x++) {
    for (int y = 0; y < N; y++) {
        int jump = map[x][y];
        if (jump == 0) {
            continue;
        }
        
        // 오른쪽
        if (y + jump < N) {
            paths[x][y + jump] += paths[x][y];
        }
        
        // 아래
        if (x + jump < N) {
            paths[x + jump][y] += paths[x][y];
        }
    }
}
```

- 점프할 수 있는 거리가 0이 아닌 칸들로부터, 오른쪽, 아래쪽으로 이동한다.
- 점프가 가능하다면, 현재 좌표까지 올 수 있는 경우의 수를 더해준다.

## :black_nib: **Review**
- 이전 정보를 사용하지 않고, ++로 경우의 수를 증가시켜주는 방식으로 구현했더니 메모리초과가 계속 발생했다.
- 메모이제이션을 활용하는 것을 까먹지 말자...
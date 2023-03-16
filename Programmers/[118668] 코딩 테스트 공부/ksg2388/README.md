# [118668] 코딩 테스트 공부

## :pushpin: **Algorithm**

다익스트라 알고리즘

## :round_pushpin: **Logic**

```java
if (status[cur.x + 1][cur.y] > status[cur.x][cur.y] + 1) {
    status[cur.x + 1][cur.y] = status[cur.x][cur.y] + 1;
    next.add(new Problem(cur.x + 1, cur.y, status[cur.x + 1][cur.y]));
}
if (status[cur.x][cur.y + 1] > status[cur.x][cur.y] + 1) {
    status[cur.x][cur.y + 1] = status[cur.x][cur.y] + 1;
    next.add(new Problem(cur.x, cur.y + 1, status[cur.x][cur.y + 1]));
}
```

- 처음에는 알고력만 늘리는 경우와 코딩력만 늘리는 경우를 확인하여 갱신해준다.

```java
for (int[] problem : problems) {
    // 현재 알고력과 코딩력이 모두 만족하는 경우
    if (problem[0] <= cur.x && problem[1] <= cur.y) {
        int nx = cur.x + problem[2];
        int ny = cur.y + problem[3];

        if (nx > maxAlp) {
            nx = maxAlp;
        }
        if (ny > maxCop) {
            ny = maxCop;
        }
        if (status[nx][ny] > status[cur.x][cur.y] + problem[4]) {
            status[nx][ny] = status[cur.x][cur.y] + problem[4];
            next.add(new Problem(nx, ny, status[nx][ny]));
        }
    }
}
```

- 모든 문제들을 확인하면서 문제를 풀 수 있는 경우 시간을 비교하여 값을 갱신해준다.
- 그 과정에서 목표치인 최대 알고력과 최대 코딩력을 초과하는 경우는 그 값을 최대값으로 바꿔준다.

## :black_nib: **Review**

- 알고력과 코딩력을 좌표로 생각하고 문제를 푸는 접근 방식이 쉽게 떠오르지 않았다.
- 앞으로 2개의 값들이 주어진다면 먼저 x, y 좌표로 생각하고 접근 해보아야겠다.

# [16724] 피리 부는 사나이

## :pushpin: **Algorithm**

분리 집합, DFS

## :round_pushpin: **Logic**

```java
while (!locations.isEmpty()) {
    int[] current = locations.poll();

    int direction = getDirection(map[current[0]][current[1]]);

    int nx = current[0] + directions[direction][0];
    int ny = current[1] + directions[direction][1];

    // 새로운 좌표의 부모 좌표 표현값이 탐색 시작 좌표(x, y)의 부모 좌표 표현값과 같다면 사이클이 발생한 것이고, 안전 구역 증가
    if (parents[nx][ny] == number) {
        count++;
        return;
    }

    // 새로운 좌표의 부모 좌표 표현값이 탐색 시작 좌표(x, y)의 부모 좌표 표현값보다 작다면, 안전 구역을 합칠 수 있으므로 그대로 사용
    if (parents[nx][ny] < number) {
        return;
    }

    // 새로운 좌표의 부모 좌표 표현값이 더 크다면, 더 작은 부모 좌표를 표현하는 number를 가르키도록 갱신
    if (parents[nx][ny] > number) {
        parents[nx][ny] = number;
        locations.add(new int[] {nx, ny});
    }
}
```

- 부모 좌표를 표현하는 숫자값을 사용한다.
- 새로 도달한 좌표의 부모 좌표 표현값이 탐색을 시작한 좌표의 표현값과 같다면, 같은 안전구역을 한 바퀴 탐색한 것이므로, 하나의 안전구역이 만들어 진 것이다.
- 만약 새로 도달한 좌표의 부모 좌표 표현값이 더 작다면, 기존에 존재하는 안전구역에 합칠 수 있음을 의미하기에, 더 이상 탐색할 필요 없이 안전 구역 수를 증가시키지 않는다.
- 그렇지 않은 경우는 더 작은 부모 좌표 표현값을 가지도록 하고 스택에 추가해 계속해서 탐색하도록 한다.

## :black_nib: **Review**

- 아이디어는 생각할 수 있었는데, 처음에 막혔던 부분은 **어떻게 좌표를 분리 집합의 원소로 표현할 것인가**였다.
  - 행과 열을 이용해서 고유한 번호를 만들 수 있었다.
- DFS와 부모 좌표 표현값 갱신을 무지성으로 하다보니 시간초과가 발생했고, 굳이 부모 좌표 표현값을 계속 갱신할 필요가 없었다는 점을 통해 시간 초과를 해결할 수 있었다.

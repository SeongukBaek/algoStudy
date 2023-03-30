# [3190] 뱀

## :pushpin: **Algorithm**

구현, 시뮬레이션, 덱

## :round_pushpin: **Logic**

```java
private static int moveSnake() {
    int second = 0;

    while (true) {
        Location currentHead = snake.getFirst();

        // 방향 이동
        if (directions[second] == 'L') {
            direction = rotateLeft(direction);
        }
        if (directions[second] == 'D') {
            direction = rotateRight(direction);
        }

        int newX = currentHead.x + moves[direction][0];
        int newY = currentHead.y + moves[direction][1];

        // 벽 또는 자기자신의 몸이라면 종료
        if (!isIn(newX, newY) || map[newX][newY] == -1) {
            break;
        }

        // 일단 머리는 옮기기
        snake.addFirst(new Location(newX, newY));

        // 사과가 있어도 처리할 것 없음 -> 어차피 뱀 머리 이동 처리를 밑에서 해줄 것

        // 사과가 없다면
        if (map[newX][newY] == 0) {
            Location tail = snake.pollLast();
            map[tail.x][tail.y] = 0;
        }

        // 뱀 머리 이동 처리
        map[newX][newY] = -1;

        second++;
    }

    return second;
}
```

- 뱀의 방향을 바꾸는 시간이라면 방향을 바꾼다.
- 다음 좌표를 확인하고, 벽 또는 자기 자신이라면 종료한다.
  - 이때 자기자신임을 확인하는 것은 map의 값으로 확인한다.
- 이후 이동을 시작하는데, 우선 뱀의 머리를 먼저 옮겨둔다.
- 이후 사과가 있는 경우에 대해서는 따로 처리하지 않는다. 어차피 뱀의 상태는 변화가 없기 때문이다.
- 사과가 없다면, 뱀은 크기를 늘릴 수 없어 꼬리를 이동시켜야 한다.
- 이후 map에 뱀의 머리 이동 처리를 수행한다.

## :black_nib: **Review**

- 시뮬레이션 문제로, 주어진 로직을 순서대로 구현했다.
- 뱀의 머리가 이동하고, 꼬리가 줄어들 수 있기에 앞뒤로 삽입 삭제가 가능한 Deque 자료구조가 떠올라 사용했다.

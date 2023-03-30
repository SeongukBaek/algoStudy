# [3190] 뱀

## :pushpin: **Algorithm**

구현, 시뮬레이션, 큐

## :round_pushpin: **Logic**

```java
// 뱀이 벽에 부딪히거나 벽에 부딪히면 게임 종료
while (true) {
  time++;
  // 방향을 바꿔야하는 시간이지 확인
  if (!command.isEmpty() && time - 1 == command.peek().t) {
    Command cmd = command.poll();
    if (cmd.d == 'D') {
      direction = direction + 1 > 3 ? 0 : direction + 1;
    } else {
      direction = direction - 1 < 0 ? 3 : direction - 1;
    }
  }
  // 머리를 늘려 다음칸으로 이동한다.
  head.x += dx[direction];
  head.y += dy[direction];

  // 이동한 칸이 벽이나 자기 몸통인 경우 게임 종료
  if (gameEnd(head.x, head.y)) {
    return;
  }
  // 이동한 칸에 사과가 있는 경우
  if (map[head.x][head.y] == 2) {
    map[head.x][head.y] = 1;
    baaam.offer(new Node(head.x, head.y));
    continue;
  }

  // 이동한 칸에 사과가 없는 경우
  map[head.x][head.y] = 1;
  baaam.offer(new Node(head.x, head.y));
  Node tail = baaam.poll();
  map[tail.x][tail.y] = 0;
}
```

- 이동하기 전 뱀의 방향을 바꾸어야 하는 시간이 지났으면 방향을 먼저 바꿔준다.
- 이후 뱀이 보고있는 방향으로 이동한다.
- 이동한 칸이 벽이나 자기 몸통인 경우 게임이 종료된다.
- 이동한 칸에 사과가 있는 경우는 머리만 이동한다.
- 이동한 칸에 사과가 없는 경우는 머리를 이동 후 꼬리를 지워준다.

## :black_nib: **Review**

- 큐를 이용하여 뱀의 이동을 쉽게 구현할 수 있었다.
- 문제에 예시 그림이 없어서 초반에 잘 이해가 되지 않았다.

# [19237] 어른 상어

## :pushpin: **Algorithm**

구현, 우선순위 큐

## :round_pushpin: **Logic**

```java
int time = 0;
while (sharks.size() > 1 && time <= 1000) {
    moveSharks();
    decreaseSmells();
    spreadSmells();
    time++;
}

```

- 로직의 순서는 다음과 같
다.
  - 상어들을 이동시킨다.
  - 이전의 냄새들을 1씩 감소시킨다.
  - 이동한 상어의 좌표에 냄새를 뿌린다.

```java
boolean hasSelect = false;
for (int direction : shark.order[d]) {
    int nx = x + directions[direction][0];
    int ny = y + directions[direction][1];

    if (!isInBoard(nx, ny) || board[nx][ny] != null) {
        continue;
    }

    // 아무 냄새가 없는 칸을 찾은 경우
    x = nx;
    y = ny;
    d = direction;
    hasSelect = true;
    break;
}

// 아직 이동할 칸을 못 찾은 경우
if (!hasSelect) {
    for (int direction : shark.order[d]) {
        int nx = x + directions[direction][0];
        int ny = y + directions[direction][1];

        if (!isInBoard(nx, ny) || board[nx][ny] == null || board[nx][ny].sharkIndex != shark.index) {
            continue;
        }

        x = nx;
        y = ny;
        d = direction;
        break;
    }
}
```

- 상어가 이동할 수 있는 좌표는 아무 냄새가 없는 칸이거나, 자신의 냄새가 있는 칸이다.
- 따라서 우선순위에 맞게 탐색을 수행한다.

```java
private static void spreadSmells() {
    while (!movedSharks.isEmpty()) {
        Shark currentShark = movedSharks.poll();
        int x = currentShark.x;
        int y = currentShark.y;

        // 이미 다른 상어가 냄새를 뿌린 경우
        // 이 상어는 격자 밖으로 퇴출
        if (board[x][y] != null && board[x][y].sharkIndex != currentShark.index) {
            sharks = sharks.stream().filter(shark -> shark.index != currentShark.index).collect(Collectors.toList());
            continue;
        }

        board[x][y] = new Board(currentShark.index, k);
    }
}
```

- 번호가 작은 상어부터 냄새를 뿌린다.
- 만약 이미 해당 좌표에 다른 상어가 냄새를 뿌렸다면, 현재 상어는 격자 밖으로 퇴출되어야 하기에 상어 리스트에서 제거한다.

## :black_nib: **Review**
- 문제 설명이 길고, 조건이 많아서 처음에 잘 이해하는 것이 관건이다.
- 주석을 달면서 구현의 순서를 잘 맞추면서 구현했다.
- spreadSmells() 구현 시에, 상어 리스트에서 특정 상어를 제거해야 하는 로직이 필요했는데, 리스트는 탐색 중에 요소를 제거하는 것이 불가능하기에 스트림의 filter를 이용해서 해당하는 상어를 제거한 새 리스트를 할당하는 방식으로 구현해보았다.


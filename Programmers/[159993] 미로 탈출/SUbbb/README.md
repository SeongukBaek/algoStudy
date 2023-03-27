# [159993] 미로 탈출

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
// 출발점에서 레버까지 가는 최소 시간
int toLever = findTarget(startX, startY, leverX, leverY);
if (toLever == -1) {
    return -1;
}

// 레버에서 출구까지 가는 최소 시간
int toEnd = findTarget(leverX, leverY, endX, endY);
if (toEnd == -1) {
    return -1;
}

return toLever + toEnd;
```

- 문제의 목표는 출발점 ~ 레버까지의 최소 시간 + 레버 ~ 출구까지의 최소 시간을 반환하는 것이다.

```java
private int findTarget(int x, int y, int targetX, int targetY) {
    boolean[][] isVisited = new boolean[row][col];
    Queue<int[]> locations = new LinkedList<>();
    locations.add(new int[]{x, y});
    isVisited[x][y] = true;

    int cost = 0;
    boolean canMove;

    while (!locations.isEmpty()) {
        int size = locations.size();
        canMove = false;

        for (int count = 0; count < size; count++) {
            int[] current = locations.poll();
            int curX = current[0];
            int curY = current[1];

            for (int[] move : moves) {
                int nx = curX + move[0];
                int ny = curY + move[1];

                if (!isInMap(nx, ny) || isVisited[nx][ny] || maps[nx].charAt(ny) == 'X') {
                    continue;
                }

                if (nx == targetX && ny == targetY) {
                    return cost + 1;
                }

                canMove = true;
                // 방문 처리를 미리 해줘야 큐에 중복 값이 들어가지 않음!!
                isVisited[nx][ny] = true;
                locations.add(new int[] {nx, ny});
            }
        }

        if (!canMove) {
            break;
        }
        cost++;
    }

    return -1;
}
```

- 목표 좌표까지의 BFS를 수행한다. 
- 만약 현재 좌표로부터 더 이상 할 수 없다면 종료한다.
- 이때, 다음 방문할 좌표를 큐에 넣기 전에 미리 방문 처리를 해줘야 중복된 값이 큐에 들어가는 것을 방지할 수 있다.

## :black_nib: **Review**

- 아이디어는 쉽게 잡았고, 입력 범위도 크지 않아 BFS로 충분히 가능하겠다 싶었다.
- 하지만 시간초과가 계속 발생해서 코드를 보다보니 방문 처리의 위치로 인해 중복된 값이 발생하고 있었다.
- BFS에서도 방문 처리 위치에 따라 시간초과가 충분히 발생할 수 있다는 것을 명심하자.

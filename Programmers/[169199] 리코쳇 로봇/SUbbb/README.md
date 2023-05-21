# [169199] 리코쳇 로봇

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
private int findMinMove(int[] robot) {
    int cost = 0;
    Queue<int[]> robots = new ArrayDeque<>();
    isVisited[robot[0]][robot[1]][0] = isVisited[robot[0]][robot[1]][1] = isVisited[robot[0]][robot[1]][2] = isVisited[robot[0]][robot[1]][3] = true;
    robots.add(new int[] {robot[0], robot[1], cost});

    while (!robots.isEmpty()) {
        int[] current = robots.poll();

        // 4 방향 탐색 후, 이동 가능한 경우를 큐에 삽입
        // 이때 이미 해당 좌표에 해당 방향으로 방문한 경우는 제외
        for (int direction = 0; direction < 4; direction++) {
            int[] newLocation = move(current[0], current[1], direction);

            // 해당 방향으로 이동했을 때, 이미 해당 좌표에 해당 방향으로 방문한 적 있는 경우는 처리하지 않는다.
            if (isVisited[newLocation[0]][newLocation[1]][direction]) {
                continue;
            }

            if (board[newLocation[0]].charAt(newLocation[1]) == 'G') {
                return current[2] + 1;
            }

            isVisited[newLocation[0]][newLocation[1]][direction] = true;
            robots.add(new int[] {newLocation[0], newLocation[1], current[2] + 1});
        }
    }

    return -1;
}
```

- 좌표와 방향에 대한 방문 처리를 수행한다.

## :black_nib: **Review**

- 방향에 대한 방문 처리만 생각해주면 간단한 BFS 문제였다.

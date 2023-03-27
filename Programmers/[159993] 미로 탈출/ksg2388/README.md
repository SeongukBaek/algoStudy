# [159993] 미로 탈출

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

출발점에서 레버까지 이동한 후 다시 레버부터 출발점까지 이동한다.

```java
int visited[][];    // 0인경우 미방문, 1인경우 아직한 레버를 작동시키지 않고 방문한 경우
```

- 방문배열을 int타입으로 지정하여 1인 경우 레버를 작동하지 않고 방문한 경우, 2인 경우 레버를 작동 후 이동한 경우로 설정해둔다.

```java
int findOptimalPath(String[] maps, Node start) {
    Deque<Node> nextPaths = new LinkedList<>();
    nextPaths.offer(start);
    visited[start.x][start.y] = 1;

    while(!nextPaths.isEmpty()) {
        Node cur = nextPaths.poll();

        // 다음 이동할 위치 탐색
        for (int i = 0; i < 4; i++) {
            int nx = cur.x + dx[i];
            int ny = cur.y + dy[i];

            // 맵 밖으로 나가거나 벽이거나 방문한 경우 무시
            if (isMapOut(nx, ny) || maps[nx].charAt(ny) == 'X' || isVisited(nx, ny, cur.onLever)) {
                continue;
            }

            // 통로에 도착했고 레버를 작동 시킨 경우
            if (maps[nx].charAt(ny) == 'E' && cur.onLever) {
                return cur.d + 1;
            }

            // 이동 위치가 레버인 경우
            if (maps[nx].charAt(ny) == 'L') {
                nextPaths.offer(new Node(nx, ny, cur.d + 1, true));
                visited[nx][ny] = 2;
                continue;
            }
            nextPaths.offer(new Node(nx, ny, cur.d + 1, cur.onLever));
            if (cur.onLever) {
                visited[nx][ny] = 2;
            }
            else {
                visited[nx][ny] = 1;
            }
        }
    }
    return -1;
}
```

- 레버를 작동 시켰고 통로에 도착한 경우 종료한다.
- 이동 위치가 레버인 경우 onLever 값을 true로 바꾸어준다.
- 레버를 작동한 경우는 레버를 작동시키지 않고 왔던 길로 다시 이동할 수 있다.

## :black_nib: **Review**

- 비슷한 문제를 한 번 풀어봤던 기억이 있어서 생각보다 쉽게 해결했다.
- 다양한 문제를 풀어보는 것이 중요하다는 생각이 들었다.

# [159993] 미로 탈출

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
 int timeSearchLever = escapeMaze(start, lever); // 레버 찾는 시간 구하기
if(timeSearchLever == -1) return timeSearchLever;
int timeSearchExit = escapeMaze(lever, end); // 출구 찾는 시간 구하기
if(timeSearchExit == -1) return timeSearchExit;
else return timeSearchLever + timeSearchExit;
```

- 출발지점에서 레버까지의 최소 시간에 레버에서부터 도착지점까지의 최소 시간을 반환한다.
- 만약 레버를 찾을 수 없거나, 출구를 찾을 수 없으면 -1을 반환하다.

```java
int escapeMaze(int[] start, int[] end) {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    boolean[][] visited = new boolean[n][m];
    Queue<Current> queue = new ArrayDeque();
    queue.add(new Current(start[0], start[1], 0));

    while(!queue.isEmpty()) {
        Current cur = queue.poll();

        if(cur.x == end[0] && cur.y == end[1]) return cur.time;

        for(int i=0; i<4; i++) {
            int x = cur.x + dx[i];
            int y = cur.y + dy[i];

            if(x<0 || x>=n || y<0 || y>=m) continue; // 범위를 벗어나는 경우
            if(map[x][y] == 'X') continue; // 통로가 아닌 경우
            if(visited[x][y]) continue; // 이미 방문 한 경우

            visited[x][y] = true;
            queue.add(new Current(x, y, cur.time+1));
        }
    }

    return -1; // 못찾으면 -1 반환
}
```

- start, end값을 매개변수로 넘겨 받는다.
  - `start : 출발지점 /  end : lever가 있는 곳` 을 넘겨 받아 먼저 실행하고
  - `start : lever가 있는 곳 /  end : 도착지점` 을 넘겨 받아 실행한다.
- BFS로 최단 경로를 찾는다

## :black_nib: **Review**

- 요즘 그래프 문제가 재미있다! 내가 처음 생각한 알고리즘을 그대로 짜서 한번에 통과하는 짜릿함을 느끼는 중이당ㅎㅎ

# [삼성 SW 역량테스트] 꼬리잡기놀이

## :pushpin: **Algorithm**

구현, 시뮬레이션, BFS

## :round_pushpin: **Logic**

```java
public static void playRound(int round) {
  // 머리 사람이 먼저 이동 후 팀 이동
  for (int i = 0; i < m; i++) {
    moveTeam(headPosion[i], i);
  }
  // 공 던지기
  tossBall(round);
}
```

- 한 라운드씩 이동을 하는데 머리의 위치를 기준으로 팀들이 따라서 이동한 후 이동이 끝나면 공을 던진다.

```java
// 머리가 우선 이동
for (int i = 0; i < 4; i++) {
  int nx = start.x + dx[i];
  int ny = start.y + dy[i];
  // 맵 밖으로 나가는 경우 무시
  if (isOutMap(nx, ny)) {
    continue;
  }
  // 이동선이나 꼬리를 만날 경우 이동
  if (map[nx][ny] == 4 || map[nx][ny] == 3) {
    map[nx][ny] = 1;
    headPosion[idx].x = nx;
    headPosion[idx].y = ny;
  }
  // 다음 이동 위치 저장
  if (map[nx][ny] == 2) {
    next = new Node(nx, ny);
  }
}
```

- 머리의 위치를 기준으로 사방탐색을 통해 이동선을 찾는 경우 그 위치로 머리를 이동시킨다. 이 경우 그 위치가 꼬리인 경우도 같이 찾는데 머리와 꼬리가 맞닿아있는 경우가 있을 수도 있기 때문이다.
- 몸통인 경우는 다음으로 이동해야하는 경우이기때문에 next에 그 위치를 저장시켜준다.

```java
boolean isBody = true;
// 몸통들 이동
while (isBody) {
  Node cur = new Node(next.x, next.y);
  for (int i = 0; i < 4; i++) {
    int nx = cur.x + dx[i];
    int ny = cur.y + dy[i];
    // 이동할 수 없는 위치거나 이전에 왔던 방향이라면 무시
    if (isOutMap(nx, ny) || (nx == prev.x && ny == prev.y)) {
      continue;
    }
    // 몸통인 경우 계속 반복
    if (map[nx][ny] == 2) {
      map[prev.x][prev.y] = 2;
      prev = new Node(cur.x, cur.y);
      next = new Node(nx, ny);
      break;
    }
    // 꼬리나 머리를 만나는 경우 종료 (1까지 검사하는 이유는 꼬리가 머리로 바뀐 경우)
    if (map[nx][ny] == 3 || map[nx][ny] == 1) {
      map[prev.x][prev.y] = 2;
      prev = new Node(cur.x, cur.y);
      next = new Node(nx, ny);
      isBody = false;
      break;
    }
  }
}
```

- 몸통의 좌표를 기준으로 사방탐색을 통해 몸통인 경우 큐에 넣어 계속해서 탐색하고 꼬리를 찾은 경우는 반복문을 탈출해준다. 이 경우 값이 1인 경우도 반복문을 종료하는데 그 경우는 꼬리의 위치로 머리가 이동하여 꼬리가 머리로 바뀌어있는 경우가 있기 때문이다.

```java
// 꼬리 이동
map[prev.x][prev.y] = 3;
// 꼬리가 있던 곳이 값이 바뀌지 않았으면
if (map[next.x][next.y] == 3) {
  map[next.x][next.y] = 4;
}
```

- 꼬리를 이동 시키고 꼬리가 있던 값이 머리로 바뀌지않은 경우는 그 위치의 값을 이동선의 값인 4로 바꾸어준다.

## :black_nib: **Review**

- 구현하기전에 생각하는 과정에서 따로 자료구조를 만들지 않고 그냥 하려다가 코드를 짜는데 오히려 더 복잡해졌다... 특히 머리와 몸통을 바꾸는 부분이 너무 길어졌다.
- 이런 문제는 귀찮더라도 초반에 정보를 다 저장해놓고 문제를 푸는 것이 쉬울 것 같다.
- 중간 중간 탐색을 하는 부분이 너무 많아 마음에 들지 않는다...

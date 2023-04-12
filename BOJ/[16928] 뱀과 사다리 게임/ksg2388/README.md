# [16928] 뱀과 사다리 게임

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
// 사다리 입력 받아서 맵 수정
for (int i = 0; i < n; i++) {
  st = new StringTokenizer(br.readLine());
  int from = Integer.parseInt(st.nextToken());
  int to = Integer.parseInt(st.nextToken());
  map[from] = to;
}

// 뱀 입력 받아서 맵 수정
for (int i = 0; i < m; i++) {
  st = new StringTokenizer(br.readLine());
  int from = Integer.parseInt(st.nextToken());
  int to = Integer.parseInt(st.nextToken());
  map[from] = to;
}
```

- 뱀과 사다리의 정보를 받아 바로 map에 저장해준다.

```java
Queue<Integer> nextPoint = new LinkedList<>();
int count = 0;
nextPoint.add(1);

while (!nextPoint.isEmpty()) {
  int size = nextPoint.size();
  count++;
  for (int q = 0; q < size; q++) {
    int cur = nextPoint.poll();

    for (int i = 1; i <= 6; i++) {
      int nextPosition = map[cur] + i;

      if (nextPosition >= 100) {
        return count;
      }

      if (visited[nextPosition]) {
        continue;
      }

      visited[nextPosition] = true;
      nextPoint.offer(nextPosition);
    }
  }
}
```

- BFS를 이용하여 주사위를 굴렸을 때 갈 수 있는 경우인 1 ~ 6 까지의 경우를 순서대로 가보면서 100에 도달하는 경우 종료한다.

## :black_nib: **Review**

- 처음에는 이동할 때마다 뱀과 사다리의 정보를 확인하고 있는 경우 이동하는 방식으로 구현했다.
- 하지만 이동할 때마다 확인하는 건 비효율적인것같아서 미리 map에 정보를 저장해두고 이동하는 방식으로 구현하였다.

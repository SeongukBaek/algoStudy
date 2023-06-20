# [16946] 벽 부수고 이동하기 4

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

- 다음과 같은 로직으로 구현하였다
- 1. 벽으로 둘러쌓인 칸들을 그룹화 해주었다. 그룹에는 임의의 순서로 번호를 붙어 그룹의 총 칸 수와 함께 Map에 저장하였다.
- 2. 벽인 부분을 순회하면서 상하좌우에 어떤 그룹들이 있는지 찾는다. 겹치는 그룹은 계산에 포함하지 않도록 하기위해 Set에 그룹의 번호를 넣어주었다.

```java
private static void findGroup() {
    int index = 1;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            if (map[i][j] == 0 && group[i][j] == 0) {
                groupInfo.put(index, makeGroup(i, j, index));
                index++;
            }
        }
    }
}

private static int makeGroup(int i, int j, int groupIdx) {
    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[] { i, j });
    int count = 1;
    group[i][j] = groupIdx;

    while (!queue.isEmpty()) {
        int[] cur = queue.poll();

        for (int dir = 0; dir < 4; dir++) {
            int nx = cur[0] + dx[dir];
            int ny = cur[1] + dy[dir];

            if (!isIn(nx, ny)) {
                continue;
            }

            if (map[nx][ny] == 0 && group[nx][ny] == 0) {
                queue.add(new int[] { nx, ny });
                group[nx][ny] = groupIdx;
                count++;
            }
        }
    }
    return count;
}
```

- 벽으로 둘러 쌓인 칸들을 그룹화 해주는 코드이다.

```java
private static int mapCount(int i, int j) {
    int sum = 1;
    HashSet<Integer> adjacentGroup = new HashSet<>();

    if (map[i][j] == 0) {
        return 0;
    }

    for (int dir = 0; dir < 4; dir++) {
        int nx = i + dx[dir];
        int ny = j + dy[dir];

        if (!isIn(nx, ny)) {
            continue;
        }

        if (group[nx][ny] == 0) {
            continue;
        }

        if (map[nx][ny] == 0) {
            adjacentGroup.add(group[nx][ny]);
        }
    }

    for (int groupIdx : adjacentGroup) {
        sum += groupInfo.get(groupIdx);
    }

    return sum % 10;
}
```

- 상하좌우의 어떤 그룹이 있는지 찾고 총 칸 수 % 10 한 값을 리턴해주는 메소드다.

## :black_nib: **Review**

- 처음에는 모든 벽을 시작으로 bfs로 인접 칸수를 계산하는 코드를 작성하였는데 시간 초과가 나였다.
- 위 방법은 `n * n`의 배열이 주어진다면 약 O(n^4)의 시간 복잡도를 가지는 코드라 시간 초과가 난 것 같다...
- 그래서 지금의 방법으로 수정했다.. 힘들었다 ..

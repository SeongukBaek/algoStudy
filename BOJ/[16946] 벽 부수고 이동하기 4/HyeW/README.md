# [16946] 벽 부수고 이동하기 4

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
static Map<Integer, Integer> group
```
- 0으로 연결된 칸을 그룹으로 묶어 분류한다.
- Map을 이용해 그룹이 가진 칸의 개수를 저장한다.

```java
for (int x = 0; x < N; x++) {
    for (int y = 0; y < M; y++) {
        // 벽이고 이미 확인한 칸은 확인하지 않는다
        if (map[x][y] == '1' || mark[x][y] > 0) {
            continue;
        }
        // 그룹 번호가 가진 칸의 개수 저장하기
        group.put(num, markRoad(x, y, num));
        num++;
    }
}
```
- BFS를 사용해서 벽이 없는 칸이 연결된 칸의 개수를 구한다.

```java
boolean[] checked = new boolean[num];
int cnt = 1; // 칸 개수
for (int i = 0; i < 4; i++) {
    int dx = x + dr[i];
    int dy = y + dc[i];

    if (isMapOut(dx, dy)) {
        continue;
    }
    // 현재 칸과 인접한 그룹 번호
    int groupNum = mark[dx][dy];

    if (checked[groupNum] || groupNum < 1) {
        continue;
    }
    cnt += group.get(groupNum);
    checked[groupNum] = true;
}
```
- 벽을 부수었을 경우 갈 수 있는 칸의 수를 구한다.
  - 인접한 칸만 확인하면 된다.
  - 만약 인접한 칸이 0이라면 해당 칸의 그룹이 가지고 있는 칸의 수를 더해준다.
  - 이때 그룹은 중복될 수 없으니 boolean[] checked로 중복 검사를 한다.

## :black_nib: **Review**

- 시간초과문제로 애먹은 문제이다.
  - 출력을 StringBuilder로 바꿔도 시간초과가 났다.
  - 마지막 답을 출력할 때 HashSet으로 중복 체크를 했는데 이때 생성 시간을 단축하기 위해 boolean[]으로 바꿨다. 하지만 시간초과는 해결되지 않았다.
  - 처음엔 답을 따로 새로만든 이차원 배열에 저장하고 출력했는데 함수를 사용해 바로 출력하도록 바꾸었다. 이 과정을 거쳐서 시간초과를 해결할 수 있었다.
- 제 코드 실행시간 줄일 수 있는 부분이 있다면 말해주세요~
# [2931] 가스관

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
for (Location pipe : pipes) {
    if (findEmpty(pipe)) {
        break;
    }
}
```

- 가스관을 하나씩 보면서, 가스관이 연결할 수 있는 방향에 연결되어 있지 않은, 즉 해커가 훔쳐간 파이프의 위치를 찾는다.

```java
private static void flowFromEmpty() {
    List<Integer> nearPipes = new ArrayList<>();

    for (int direction = 0; direction < 4; direction++) {
        int nx = empty.x + directions[direction][0];
        int ny = empty.y + directions[direction][1];

        if (!isInBoundary(nx, ny)) {
            continue;
        }

        if (isGasPipe(map[nx][ny]) && canConnect(nx, ny, direction)) {
            nearPipes.add(direction);
        }
    }

    // 가스관 정보를 탐색하면서 현재 저장한 연결해야 하는 방향과 일치하는 가스관을 찾는다.
    for (Map.Entry<Character, List<Integer>> entry : pipeInfo.entrySet()) {
        if (entry.getValue().equals(nearPipes)) {
            result.append(entry.getKey());
        }
    }
}
```

- 해커가 훔쳐간 파이프의 위치에서, 사방 탐색을 하면서 가스관을 찾는다.
- 만약 가스관이 있고, 그 가스관이 현재 위치와 연결될 수 있다면, 그 방향을 저장한다.
- 파이프별로 연결될 수 있는 방향은 정해져있으므로, 아래 반복문에서 이를 확인한다.

## :black_nib: **Review**
- 당연히 M에서부터 출발해서 비어있는 파이프를 찾고, 로직을 수행하려 했었다.
- 하지만 결국 연결을 확인하는 문제이므로 굳이 출발점이 있어야 할 것 같진 않았고, 가스관 정보만 가지고도 풀 수 있지 않을까 싶었다.
- 또한, 파이프별로 확인해야 하는 방향을 미리 저장해두면 좀 더 로직 진행이 깔끔할 것 같아서 이를 사용했다.
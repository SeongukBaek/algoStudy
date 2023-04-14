# [16928] 뱀과 사다리 게임

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
while (!blocks.isEmpty()) {
    diceCount++;
    int parent = blocks.size();

    while (parent-- > 0) {
        int cur = blocks.poll();
        for (int i = 1; i <= DICE; i++) {
            int nxt = cur + i;
            // map밖으로 나갔을 때 패스
            if (nxt > MAP_SIZE - 1) {
                continue;
            }
            // 이미 방문한 곳이면 패스
            if (visited[nxt]) {
                continue;
            }
            // 목적지에 도착하면 주사위 던진 수 반환
            if (nxt == MAP_SIZE - 1) {
                return diceCount;
            }

            visited[nxt] = true;
            // 뱀이나 사다리를 가는 경우
            if (map[nxt] != 0) {
                if (visited[map[nxt]]) {
                    continue;
                }
                blocks.add(map[nxt]);
                visited[map[nxt]] = true;
            }else { // 뱀이나 사다리가 없는 경우
                blocks.add(nxt);
            }
        }
    }
}
```

현재 좌표에서 주사위가 1~6이 모두 나왔을 경우를 생각한다.<br/>
다음 좌표에 뱀또는 사다리가 나왔다면 해당 아이템이 가리키는 자리를 add하고 아니라면 현재 이동한 좌표를 add하며 자리를 이동한다.<br/>
목적지에 도착하면 주사위를 던진 횟수를 반환한다.

## :black_nib: **Review**

- 처음에는 뱀을 가지 않는게 가장 빠르게 도착하지 알았는데 사다리-> 뱀 -> 사다리 로 갔을 때 더 빠른 경우가 있었다.
- 주사위가 1~6까지 가지니 평소에 하는 4방탐색 BFS를 6방탐색 BFS라고 생각하고 문제를 푸니 쉽게 생각할 수 있었다.
- 다 풀고 나서 뱀과 사다리를 타고 난 최종 좌표만 큐에 넣어야 하는데 뱀과 사다리가 있는 좌표도 큐에 넣어서 헤맸었다 ㅠ.ㅠ
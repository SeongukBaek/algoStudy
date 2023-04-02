# [16928] 뱀과 사다리 게임

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
while (!cannes.isEmpty()) {
    int size = cannes.size();

    for (int count = 0; count < size; count++) {
        current = cannes.poll();

        for (int dice = 1; dice <= 6; dice++) {
            // 이동할 칸
            int next = current + dice;

            if (next == 100) {
                return diceCount + 1;
            }

            if (next > 100 || isVisited[next]) {
                continue;
            }

            isVisited[next] = true;

            // 해당 칸의 뱀이나 사다리 확인
            int nextCan = cuts[next];
            if (nextCan == 100) {
                return diceCount + 1;
            }

            if (isVisited[nextCan]) {
                continue;
            }

            // 사다리나 뱀이 없다면 주사위로 이동할 칸으로 할당
            if (nextCan == 0) {
                nextCan = next;
            }

            isVisited[nextCan] = true;
            cannes.add(nextCan);
        }
    }

    diceCount++;
}
```

- 주사위를 한 번씩 던지면서, 100에 도달하는 최소 주사위 개수를 반환한다.

## :black_nib: **Review**

- 처음에는 무조건 뱀이 있는 칸을 피하는 것이 최소 주사위 개수라고 생각했는데, 그렇지 않은 경우가 있었다.
- 따라서 모든 경우를 다 확인해보면서 최소 주사위 개수를 구하는 BFS 방식을 사용했다.
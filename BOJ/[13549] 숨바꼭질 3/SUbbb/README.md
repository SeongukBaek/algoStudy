# [13549] 숨바꼭질 3

## :pushpin: **Algorithm**

DP, BFS

## :round_pushpin: **Logic**

```java
while (!canReach.isEmpty()) {
    int currentLocation = canReach.poll();

    if (currentLocation == K) {
        break;
    }
    
    // 현재 지점까지 가는 거리
    int prevCost = minTimes[currentLocation];
    
    // -1로 이동 가능하고, 현재 지점에서 -1 이동한 좌표로의 비용이 갱신되는 경우
    if (currentLocation - 1 >= 0 && prevCost + 1 < minTimes[currentLocation - 1]) {
        minTimes[currentLocation - 1] = prevCost + 1;
        canReach.add(currentLocation - 1);
    }
    // +1로 이동 가능하고, 현재 지점에서 +1 이동한 좌표로의 비용이 갱신되는 경우
    if (currentLocation + 1 <= TARGET && prevCost + 1 < minTimes[currentLocation + 1]) {
        minTimes[currentLocation + 1] = prevCost + 1;
        canReach.add(currentLocation + 1);
    }
    // *2로 이동 가능하고, 현재 지점에서 *2 이동한 좌표로의 비용이 갱신되는 경우
    if (currentLocation * 2 <= TARGET && prevCost < minTimes[currentLocation * 2]) {
        minTimes[currentLocation * 2] = prevCost;
        canReach.add(currentLocation * 2);
    }
}
```

- N으로부터 갈 수 있는 3가지 경우로 모두 이동하면서 최단 시간을 구한다.

## :black_nib: **Review**

- 간단한 방식의 DP 겸 BFS 문제였다!
# [20168] 골목 대장 호석 - 기능성

## :pushpin: **Algorithm**

DFS

## :round_pushpin: **Logic**

```java
static void goAToB(int start, int max, int sum) {
    if(sum > C) {
        return;
    }

    if(start == B) { // 도착지에 도착하면 min값 갱신
        min = Math.min(max, min);
        check = true;
        return;
    }

    for(int i = 0; i < map[start].size(); i++) {
        int next = map[start].get(i).v;
        int cost = map[start].get(i).cost;

        if(visited[next]) {
            continue;
        }

        visited[next] = true;
        goAToB(next, Math.max(max, cost), sum+cost);
        visited[next] = false;
    }
}
```

- 출발지에서 갈 수 있는 모든 방향을 DFS로 탐색한다.
- 다음 교차로로 넘어갈 때마다 요금의 max값을 갱신하고, 수치심을 최소화 해야 하기 때문에 도착하면 max값 중 min값을 갱신한다.

## :black_nib: **Review**

- 양방향 그래프 복습할 수 있었던 문제였다!

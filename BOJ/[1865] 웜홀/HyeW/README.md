# [1865] 웜홀

## :pushpin: **Algorithm**

벨만 포드

## :round_pushpin: **Logic**

```java
private static boolean getShortestPath(int start) {
    distance[start] = 0;
    for (int i = 1; i < N; i++) {
        for (int j = 1; j <= N; j++) {
            for (Node nxt : graph[j]) {
                if (distance[nxt.v] > distance[j] + nxt.w) {
                    distance[nxt.v] = distance[j] + nxt.w;
                }
            }
        }
    }

    // 음의 사이클이 생기는지 확인한다.
    for (int j = 1; j <= N; j++) {
        for (Node nxt : graph[j]) {
            if (distance[nxt.v] > distance[j] + nxt.w) {
                distance[nxt.v] = distance[j] + nxt.w;
                return true;
            }
        }
    }
    return false;
}
```

- 벨만포드 알고리즘을 사용해서 음의 사이클이 생기는 경우 true를 리턴해 답을 찾았다.
- 처음 지점으로 돌아온다는 의미는 사이클이 생긴다는 것이고 도착했을 때 과거가 된다는 의미는 사이클의 경로 값이 마이너스가 돼야 한다는 의미로 음의 사이클의 유무를 확인한다.

## :black_nib: **Review**

- 벨만 포드 알고리즘..어려웠다..벨만 포드 문제를 좀 더 풀어봐야겠다..
- 그리고 원래 벨만포드 알고리즘에선 `distance[i] != INF`하면 좋은데 지금 문제에선 음의 사이클이 생기는지만 확인하기위해 한 노드만 봐서 해당 조건을 빼는 것이 맞다. 
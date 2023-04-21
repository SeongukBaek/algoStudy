# [1865] 웜홀

## :pushpin: **Algorithm**

벨만포드

## :round_pushpin: **Logic**

```java

boolean checkCycle = false;
for(int i = 1; i <= N; i++) {
    if(bellmanFord(i)) { // true면 음의 사이클 o
        System.out.println("YES");
        checkCycle = true;
        break;
    }
}

if(!checkCycle) { // 음의 사이클 x --> 도착 x
    System.out.println("NO");
}

```

- 정점 1부터 N까지 모든 정점을 탐색하면서 음의 사이클이 있는 지 확인한다.
- 음의 사이클이 존재하면 계속해서 더 작은 시간을 가지므로 출발시간보다 더 되돌아갈 수 있음이 된다.

```java
static boolean bellmanFord(int start) {
    Arrays.fill(dist, INF); // 거리 배열 무한대로 초기화
    dist[start] = 0; // 출발지는 자기자신이므로 0으로 초기화
    boolean update = false; // 최소비용이 갱신되었는지 확인

    for(int i = 0; i < N-1; i++) { // (정점의 수-1)번 반복
        update = false;

        for(int j = 1; j <= N; j++) { // 모든 정점 탐색
            for(int k = 0; k < linked[j].size(); k++) { // j 정점에 연결된 모든 정점 탐색
                Point next = linked[j].get(k);

                if(dist[j] == INF) { // 해당 정점까지 갈 수 없으면 패스
                    continue;
                }

                // next에 이미 저장된 최소 비용보다 현재 정점에서 다음 정점으로 가는 비용이 더 작으면 갱신
                if(dist[next.end] > dist[j] + next.w) {
                    dist[next.end] = dist[j] + next.w;
                    update = true;
                }
            }
        }

        if(!update) { // 최소 비용 갱신이 일어나지 않았으면 끝
            return false; // 음의 사이클 x
        }
    }

    if(update) {
        // n-1만큼 업데이트가 일어났으면 마지막 한 번 더 검사 --> 음의 사이클 체크
        for(int j = 1; j <= N; j++) { // 모든 정점 탐색
            for(int k = 0; k < linked[j].size(); k++) { // j 정점에 연결된 모든 정점 탐색
                Point next = linked[j].get(k);

                if(dist[j] == INF) { // 해당 정점까지 갈 수 없으면 패스
                    continue;
                }

                // next에 이미 저장된 최소 비용보다 현재 정점에서 다음 정점으로 가는 비용이 더 작으면 갱신
                if(dist[next.end] > dist[j] + next.w) {
                    return true; // 음의 사이클 o
                }
            }
        }
    }

    return false; // 음의 사이클 x
}
```

- (정점의 수 - 1)번만큼 최소 비용 갱신을 시도하는데 만약 그 이상으로 최소 비용이 갱신된다면 음의 사이클이 존재하는 것이다.
- 따라서 (N-1)만큼 최소 비용 갱신을 하되 N-1번 이전에 갱신이 멈추면 사이클이 없는 것으로 간주하고 false를 반환하고, N-1번까지 모두 갱신이 일어나면 마지막으로 한 번 더 갱신을 시도해서 갱신이 일어나면 음의 사이클이 있는 것으로 true를 반환한다. (update 변수로 갱신이 일어나는지 확인한다.)
- 최소 비용 갱신 방법은 벨만포드 알고리즘을 이용하는데, dist 거리 배열을 이용하여 해당 정점까지의 최소 비용을 저장해주고, 해당 정점이 무한대라면 갈 수 없는 곳이므로 패스하고 만약 무한대가 아니라면 이미 저장된 해당 정점까지의 최소 비용보다 현재 정점에서 다음 정점으로 가는 비용이 더 작으면 갱신한다.

## :black_nib: **Review**

- 벨만포드 알고리즘에 대해서 공부할 수 있는 문제였다. 이 문제의 경우 음의 간선이 존재하기 때문에 벨만포드 알고리즘을 이용하여 문제를 해결할 수 있었다. 음의 간선, 최단 경로는 벨만포드 기억하자!
- 벨만포드 알고리즘 : 한 정점에서 다른 모든 정점으로 가는 최단 경로(음의 간선 가능), A에서 B까지의 최단 거리는 최대 V-1개의 정점을 지나기 때문에 최단 거리 갱신은 V-1만큼 가능하고 V-1번 이상 갱신이 일어나면 음수 사이클이 존재하는 것이다.

# [1238] 파티

## :pushpin: **Algorithm**

다익스트라

## :round_pushpin: **Logic**
```java
cost1 = getCost(X, map); // X에서 각 마을로 가는 비용
cost2 = getCost(X, reverseMap); // 각 마을에서 X로 가는 비용

for(int i = 1; i <= N; i++) {
    max = Math.max(max, cost1[i]+cost2[i]); // 왕복값이 가장 큰 것이 답이 됨
}

System.out.println(max);
```
- X까지 가는 길과 X에서 돌아오는 길이 다를 수 있으므로 각각 cost1과 cost2로 따로 구한 뒤 두 길의 합이 가장 큰 것을 정답으로 한다.
- X에서 각 마을로 가는 비용은 다익스트라 알고리즘을 통해서 바로 구할 수 있다.
- 각 마을에서 X로 가는 비용은 원래 map의 역방향에 X를 시작점으로 하는 다익스트라 알고리즘을 적용해 구할 수 있다.

```java
static int[] getCost(int start, List<Info>[] linked) {
    PriorityQueue<Info> pq = new PriorityQueue<>(); // 최저비용 기준 유선순위 큐
    int[] cost = new int[N+1];
    boolean[] visited = new boolean[N+1];
    initArr(cost); // 무한대로 초기화 
    
    cost[start] = 0;
    pq.add(new Info(start, 0));
    
    while(!pq.isEmpty()) {
        Info current = pq.poll();
        
        // 이미 방문한 점이면 패스
        if(visited[current.end]) {
            continue;
        }
        
        visited[current.end] = true;
        // 현재 정점에 연결된 모든 정점 탐색
        for(int i = 0; i < linked[current.end].size(); i++) {
            Info next = linked[current.end].get(i);
            if(cost[next.end] > next.cost + current.cost) {
                cost[next.end] = next.cost + current.cost;
                pq.add(new Info(next.end, cost[next.end]));
            }
        }
    }
    return cost;
}
```
- 다익스트라 알고리즘으로 시작점을 기준으로 모든 점까지의 최저비용을 찾는다. 

## :black_nib: **Review**
- 처음에 오는 길과 돌아가는 길이 같다고 생각해서 잘못 풀이했다. 역시 문제를 잘 읽어야 한다..
- 다익스트라 알고리즘을 떠올리지 못해서 풀이를 찾아봤다. 다음엔 꼭 잘풀어야지!
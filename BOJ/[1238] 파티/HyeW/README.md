# [1238] 파티

## :pushpin: **Algorithm**

다익스트라

## :round_pushpin: **Logic**

```java
for (int i = 0; i < M; i++) {
    st = new StringTokenizer(br.readLine());
    int v1 = Integer.parseInt(st.nextToken());
    int v2 = Integer.parseInt(st.nextToken());
    int w = Integer.parseInt(st.nextToken());
    
    map[v1].add(new Road(v2, w));
    mapReverse[v2].add(new Road(v1, w));
}

dist = getShortestPath(map);
distReverse = getShortestPath(mapReverse);
```

- X좌표에서 모든 지점으로 가는 최단 거리는 기본 다익스트라를 사용해 구한다.
- 모든 지점에서 X좌표로 가는 최단 거리를 구하기위해선 경로의 시작과 끝점을 반대로 저장하여 다익스트라를 사용한다.

## :black_nib: **Review**

- 다익스트라는 어떤 지점을 시작점으로 모든 좌표의 최단거리를 구하는 것이라 X좌표로 가는 길의 경로를 구하는 좋은 방법이 떠오르지 않아 블로그를 참고했다.
  - 경로를 반대로 저장하면 해결되는 문제로 간단하지만 내가 생각하긴 어려운 문제였다.
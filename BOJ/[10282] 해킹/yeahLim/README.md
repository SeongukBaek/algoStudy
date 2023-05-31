# [10282] 해킹

## :pushpin: **Algorithm**

다익스트라

## :round_pushpin: **Logic**

```java
 for (int i = 0; i < d; i++) {
    st = new StringTokenizer(br.readLine());
    int a = Integer.parseInt(st.nextToken());
    int b = Integer.parseInt(st.nextToken());
    int s = Integer.parseInt(st.nextToken());
    graph[b].add(new Node(a, s));
}
```
- a가 b에 의존하기 때문에 a -> b가 아니라 b -> a이다.

```java
if (dist[cur.n] < cur.weight) {
    continue;
}
```
- visited 대신에 dist와 현재 가중치를 비교해서 클 경우는 탐색하지 않은 식으로 사용할 수 있다.


## :black_nib: **Review**

- 네트워크가 들어가면 그래프 이론일 확률이 높아진다는 것을 배웠다.
- 다익스트라 오랜만인데 다시 정리할 수 있어서 좋았다.

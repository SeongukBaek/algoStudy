# [1238] 파티

## :pushpin: **Algorithm**

다익스트라

## :round_pushpin: **Logic**

```java
for (int index = 0; index < M; index++) {
    info = br.readLine().split(" ");

    int a = Integer.parseInt(info[0]) - 1;
    int b = Integer.parseInt(info[1]) - 1;
    int s = Integer.parseInt(info[2]);

    roadInfo.get(a).add(new Node(b, s));
    reverseRoadInfo.get(b).add(new Node(a, s));
}
```

- 각 점에서 파티 마을까지의 최단 거리를 N번의 다익스트라가 아닌, 1번의 다익스트라로 구하기 위해 주어진 이동 정보를 반대로 저장한다.

## :black_nib: **Review**

- 처음 아이디어는, 모든 점에서 다익스트라를 돌린 후, (각 점 -> 파티 마을 + 파티 마을 -> 각 점) 중 최댓값을 찾으려 했다.
  - 하지만 4%에서 틀렸습니다가 떴다..
- 게시판을 참고하니, 다익스트라 2번만으로 해결이 가능했다. 파티 마을을 시작점으로 하는 다익스트라 1번과, 주어진 이동정보를 반대로 한 후, 파티 마을을 시작점으로 하는 다익스트라 1번을 수행한다.
  - 이동정보를 반대로 하고, 파티 마을을 시작점으로 하게 되면, 각 점으로부터 파티 마을까지 가는 최단 거리를 구할 수 있게 되기 때문이다.
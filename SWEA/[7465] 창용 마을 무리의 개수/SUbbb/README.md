# [7465] 창용 마을 무리의 개수

## :pushpin: **Algorithm**

DFS

## :round_pushpin: **Logic**

```java
private static void connectFriends(int person) {
    isVisited[person] = true;
    for (int friend : friends.get(person)) {
        if (isVisited[friend]) {
            continue;
        }
        connectFriends(friend);
    }
}
```

- 방문하지 않은 정점(사람)에 대해 방문할 수 있는 만큼 방문하고 방문처리를 수행한다.

## :black_nib: **Review**
- 연결성을 확인하는 기본적인 문제로, DFS나 Union-Find 알고리즘을 사용해 해결할 수 있는 문제였다.
- 둘 중 어떤 알고리즘이 더 빠를지에 대해서는 문제의 조건에 따라 다른 것 같았다.
  - 이미 그래프 정보가 고정되어 있는 경우는 DFS, 동적으로 변할 수 있는 경우는 Union-Find가 조금 더 빠른 것 같다.
# [7465] 창용 마을 무리의 개수

## :pushpin: **Algorithm**
DFS

## :round_pushpin: **Logic**
``` java
for (int i = 0; i < n; i++) {
    if (!check[i]) {
        checkFriends(i, n);
        count++;
    }
}

for (int i = 0; i < n; i++) {
    if (!check[i]) {
        count++;
    }
}
```
- 방문하지 않은 경우에 checkFiends 메소드를 이용해 서로 친분이 있는 모든 사람들을 방문처리한다.
- 마지막으로 방문이 되지 않은 사람들의 숫자만큼 count를 증가시킨다.

## :black_nib: **Review**
- 푸는 방법에 여러가지 방법들이 있는데 어느 방법이 가장 효율이 좋은지 궁금해졌다.
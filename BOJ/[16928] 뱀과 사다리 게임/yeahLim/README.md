# [16928] 뱀과 사다리 게임

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
while(!queue.isEmpty()) {
    Current cur = queue.poll();

    // 도착
    if(cur.x == 100) {
        if(answer > cur.count) {
            answer = cur.count;
        }
        break;
    }

    if(visited[cur.x]) continue;

    // 방문 안했을 경우
    visited[cur.x] = true;

    // 사다리나 뱀이 있는 경우
    if(board[cur.x] != 0) {
        cur = new Current(board[cur.x], cur.count); // 변한 위치로 업데이트
    }

    // 없는 경우
    for(int i=1; i<=6; i++) {
        if(cur.x + i > 100) break;
        queue.add(new Current(cur.x+i, cur.count+1));
    }
}
```

- BFS로 주사위를 던져서 갈 수 있는 모든 곳을 queue에 추가한다.
- 사다리, 뱀을 만났을 때, 변한 위치로 업데이트 시켜주고, 그 위치에서 주사위를 던진다.

## :black_nib: **Review**

- 처음에는 DP를 이용해서 풀으려고 하다가, 뱀을 만날때 다시 되돌아가는 경우 처리를 할 수가 없어서, BFS를 이용했다.
- 사다리나 뱀이 있는 경우에 변한 위치를 다시 queue에 추가하는 실수를 저질렀다.
  queue에 추가해주면 제일 뒤로 들어가기 때문에, 변한 위치를 지금 바로 탐색할 수가 없다.
  따라서 사다리와 뱀을 타고 변한 위치로 돌아가서 그곳에서 주사위를 던져 이동할 수 있게 짰다.
- BFS에서 visited처리할 때, 다시 false처리를 해줘야하는지 아닌지를 항상 잘 판단하며 유의하자!

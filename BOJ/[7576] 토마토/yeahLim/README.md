# [7576] 토마토

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
if(0<=x && x<n && 0<=y && y<m && tomato[x][y] == 0) {
  queue.add(new int[] {x, y}); // 익은 토마토에 추가
  tomato[x][y] = tomato[i][j] + 1; // 토마토가 익는 데까지 걸린 날 수
}
```
- 아직 토마토가 익지않았다면, 익은 토마토에 추가해주고, 토마토가 익는데 걸린 날의 숫자를 대입해준다.


```java
 // 안익은 토마토가 있으면 -1 반환
if(tomato[i][j] == 0) {
    return -1;
}

// 익는데 가장 오래걸린 날 수 찾기
if(max < tomato[i][j]) {
    max = tomato[i][j];
}
```
- 모든 토마토를 순회하면서 가장 오래걸린 날짜를 출력한다.

## :black_nib: **Review**
- 유지보수 및 가독성을 위해 기능별로 메소드를 나눴다.

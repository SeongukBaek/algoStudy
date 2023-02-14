# [2206] 벽 부수고 이동하기

## :pushpin: **Algorithm**
BFS

## :round_pushpin: **Logic**
``` java
Place(int x, int y, int dis, int breakCnt) {
	this.x = x;
	this.y = y;
	this.dis = dis;
	this.breakCnt = breakCnt;
}
```
- visited배열을 int타입으로 설정해 초기 값을 엄청 큰 값으로 설정해준다.
- 벽을 부순 경우 breakCnt에 값을 증가시켜주고 이동 시 visited보다 breakCnt값이 작은 경우에는 그 위치로 방문할 수가 있다.

## :black_nib: **Review**
- 처음에 문제를 접근할 때 DFS를 이용해 접근했는데 시간초과가 났다. 최단경로 문제를 해결할때는 BFS를 사용해야 한다는 것을 알았다.
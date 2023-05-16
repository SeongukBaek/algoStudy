# [67259] 경주로 건설

## :pushpin: **Algorithm**

BFS, DP

## :round_pushpin: **Logic**

```java
void makeRoad() {
    //queue에는 int[0] = X좌표, int[1] = Y좌표, int[2] = 방향 이 저장 된다.
    Queue<int[]> queue = new LinkedList<>();

    queue.add(new int[] { 0, 0, 0 });
    queue.add(new int[] { 0, 0, 1 });
    queue.add(new int[] { 0, 0, 2 });
    queue.add(new int[] { 0, 0, 3 });
    Arrays.fill(fee[0][0], 0);

    while(!queue.isEmpty()) {
        int[] curPos = queue.poll();

        for (int i = 0; i < 4; i++) {
            int dir = (curPos[2] + i) % 4;
            int additionalFee = curPos[2] == dir ? 100 : 600;

            int nextX = curPos[0] + dx[dir];
            int nextY = curPos[1] + dy[dir];

            //맵 밖인 경우 패스, 벽을 만난 경우 패스
            if(!isIn(nextX, nextY) || board[nextX][nextY] == 1) {
                continue;
            }

            //더 가격이 낮은 경로가 이미 있는 경우 패스
            if(fee[nextX][nextY][dir] < fee[curPos[0]][curPos[1]][curPos[2]] + additionalFee) {
                continue;
            }

            fee[nextX][nextY][dir] = fee[curPos[0]][curPos[1]][curPos[2]] + additionalFee;
            queue.add(new int[] { nextX, nextY, dir });

        }
    }
}
```

- fee배열은 3차원으로 선언되어야한다. 현재 위치에서 비용이 크더라도 다음 방향이 어디냐에 따라 비용이 역전될 수 있기 때문이다.
- queue에는 도로가 깔린곳의 좌표와 도로의 방향이 저장된다.

## :black_nib: **Review**

- 시작할 때 깔리는 도로는 무조건 100원이 든다는 것을 고려하지 못하였다..
- 비용을 저장할때 방향을 고려해야 한다는 것을 생각하지 못하였다..
- 하지만 생각보다 금방 풀었다 ^^

# [169199] 리코쳇 로봇

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
int countMoveToGoal() {
    Queue<int[]> queue = new LinkedList<>();

    //시작 위치를 큐에 삽입
    queue.add(new int[] { startX, startY });

    //visited배열에 현재 라운드를 저장함
    visited[startX][startY] = 1;

    while (!queue.isEmpty()) {
        int[] curPos = queue.poll();
        //현재 라운드
        int curTurn = visited[curPos[0]][curPos[1]];

        //목표 위치에 도달한 경우
        if (board[curPos[0]].charAt(curPos[1]) == 'G') {
            return curTurn - 1;
        }

        for (int dir = 0; dir < 4; dir++) {
            int curX = curPos[0];
            int curY = curPos[1];
            while(true) {
                int nextX = curX + dx[dir];
                int nextY = curY + dy[dir];

                //더 이상 미끄러질수 없다면
                if (!isIn(nextX, nextY) || board[nextX].charAt(nextY) == 'D') {
                    //이미 방문한 곳이면 패스
                    if (visited[curX][curY] != 0) {
                        break;
                    }

                    queue.add(new int[] { curX, curY });
                    visited[curX][curY] = curTurn + 1;
                    break;
                }

                //로봇 이동 처리
                curX = nextX;
                curY = nextY;
            }
        }
    }
    return -1;
}
```

- 벽을 만나거나 맵 밖으로 나갔다면 그 전 좌표로 이동한다.
- 방문 처리 배열에 함께 현재 라운드를 저장하여 몇 번 이동 했는지를 알 수 있게 해준다.

## :black_nib: **Review**

- 미끄러지며 이동하는 부분을 구현하는게 은근히 까다로웠다.

# [16724] 피리 부는 사나이

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
private static int findMinSafeZone() {
    int answer = 0;
    //이번 BFS로 방문한 곳을 표시하기 위한 변수
    int turn = 0;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            int curX = i;
            int curY = j;
            turn++;

            //한번 방문한 곳을 방문하지 않으면서 이번 탐색에서만 방문한 곳으로 돌아간다면 cycle이 있는 것
            while (visited[curX][curY] == 0) {
                visited[curX][curY] = turn;
                int dir = findDir(map[curX][curY]);
                curX += dx[dir];
                curY += dy[dir];

                //이번 탐색에서 방문한 곳으로 갔다면 answer++
                if (visited[curX][curY] == turn) {
                    answer++;
                    break;
                }
            }
        }
    }
    return answer;
}
```

- 이 문제는 cycle의 개수만큼 safety zone을 만들어 주면 된다.
- 출발지가 바뀔 때 마다, turn 변수를 ++ 하여 사용해준다.
- visited 배열에는 현재 turn 값을 넣어서 새로 cycle이 생기는지 판단하는데 사용한다.

## :black_nib: **Review**

- testcase만 보고 다시 시작 지점으로 돌아오면 cycle이 생기는 것이라고 착각하여 코드를 짰었다.
- 하지만 금방 어디가 문제인지 알아내서 고칠 수 있었다 !

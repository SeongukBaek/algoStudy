# [3190] 뱀

## :pushpin: **Algorithm**

구현, 시뮬레이션

## :round_pushpin: **Logic**

```java
static List<Current> snake = new LinkedList<>();
```

- snake 변수에 현재 뱀의 몸이 위치한 모든 좌표를 저장한다.

```java
/* 더미 게임 실행 */
private static int playDummy() {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    int x = 0, y = 0; // (0, 0)에서 시작
    int head = 1; // head 오른쪽으로 초기화
    int time = 0; // 시간
    int orderIndex = 0;
    snake.add(new Current(x, y));

    while(true) {

        // 시간 재기
        time++;

        int nx = x + dx[head];
        int	ny = y + dy[head];

        // 종료 조건
        if(checkFinish(nx, ny)) break;

        snake.add(new Current(nx, ny));

        // 사과가 있을때
        if(isApple[nx][ny]) isApple[nx][ny] = false;
        // 없을때
        else snake.remove(0);


        // 회전 방향
        if(orderIndex < orderCnt && time == orders.get(orderIndex).time) {

            // 왼쪽으로 90도
            if(orders.get(orderIndex).direction == 'L') {
                head -= 1;
                if(head == -1) head = 3;
            }
            // 오른쪽으로 90도
            else head = (head + 1) % 4;

            orderIndex++;
        }

        x = nx;
        y = ny;
    }
    return time;
}
```

- 초기 값을 설정하고, 사과가 있을 경우/없을 경우, 회전방향에 대한 오더를 조건에 따라 구현하였다.
- 그리고 queue를 사용하지 않는 대신 list의 x, y값을 nx, ny로 업데이트해주며 종료 조건에 부합할 때까지 반복한다.

## :black_nib: **Review**

- 주어진 문제의 조건대로 다 실행했다.
- 처음에는 bfs를 이용해서 풀으려고 했으나, queue가 조회하는 데 적합하지 않은 자료구조라 linkedlist로 바꿔서 풀었다.

# [7576] 토마토

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

모든 토마토가 익으려면 며칠이 걸리는 지 구하는 문제이다. <br/>
하루동안 토마토가 익도록 하고 모든 토마토가 다 익었는지 검사하면 된다.<br/>

```java
static int countDay() {
    int day = -1;
    boolean isFinished = checkTomato();
    
    while (!done.isEmpty()) {
        day++;
        oneDay();
    }
    isFinished = checkTomato();
    
    if(!isFinished)
        return -1;
    return day;
}
```
`checkTomato()`는 이중 for문을 사용해 모든 토마토가 익었는지 확인하는 함수이다.<br/>
큐에 노드(익은 토마토)가 없을 때까지 토마토을 익히며 날짜를 계산한다.<br/><br/>

```java

static void oneDay() {
    int parent = done.size();

    while (parent-- != 0) {
        Node cur = done.poll();

        for (int i = 0; i < DIRECTION_LENGTH; i++) {
            int dx = cur.x + dr[i];
            int dy = cur.y + dc[i];

            if (dx >= N || dy >= M || dx < 0 || dy < 0)
                continue;
            if (tomatoes[dx][dy] != 0)
                continue;

            tomatoes[dx][dy] = 1;
            done.add(new Node(dx, dy));
        }
    }
}

```
`oneDay()`는 하루동안 익는 토마토를 구하는 함수이다. BFS를 사용하여 인접 토마토가 익도록 한다.<br/> 또한, 맨처음 큐에 담긴 토마토를 다 꺼냈을 때 하루가 끝나기 때문에 `parent`변수를 이용해 하루 동안 익는 토마토를 구해주었다.

## :black_nib: **Review**
- 익은 토마토를 기준으로 옆으로 서서히 퍼져나가기 때문에 BFS를 사용하면 되는 문제이다. 하지만 기본 BFS에서 추가된 요건은 최소 날짜를 구하는 것이 었고 큐의 사이즈를 계속 측정하면서 문제를 해결했다.
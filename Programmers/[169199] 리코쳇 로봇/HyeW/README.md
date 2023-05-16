# [169199] 리코쳇 로봇

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
public int getMinMove(Position start)
```

- 기본 BFS를 사용해서 목표지점까지의 최단거리를 구했다. <br/>

```java
 /* 로봇이 미끄러져서 도착한 위치좌표를 반환한다. */
public Position slideRobot(Position cur, int dir){
    int dx = cur.x;
    int dy = cur.y;

    while(!isMapOut(dx, dy) && board[dx].charAt(dy) != 'D'){
        dx += dr[dir];
        dy += dc[dir];
    }
    return new Position(dx - dr[dir], dy - dc[dir]);
}
```
- 4방을 한 칸씩 움직이는 것이 아니라 벽이나 맵의 끝을 만날때까지 이동하기 때문에
이동에 대한 함수를 따로 만들어 주었다.

## :black_nib: **Review**

- 이동만 슬라이드로 하는 평범한 BFS 문제였다.
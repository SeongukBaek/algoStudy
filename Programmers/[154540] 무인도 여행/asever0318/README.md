# [154540] 무인도 여행

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
static int countDays(int x, int y){
    Queue<Pos> q = new LinkedList<>();
    int count = map[x][y];
    map[x][y] = 0;
    q.add(new Pos(x, y));

    while(!q.isEmpty()){
        Pos current = q.poll();

        for(int d = 0; d < 4; d++){
            int nx = current.x + dx[d];
            int ny = current.y + dy[d];

            if(nx < 0 || nx >= N || ny < 0 || ny >= M){
                continue;
            }

            if(map[nx][ny] == 0){
                continue;
            }

            count += map[nx][ny];
            map[nx][ny] = 0;
            q.add(new Pos(nx, ny));
        }
    }

    return count;
}
```

- 모든 칸에서 상하좌우 모든 방향으로 탐색하기 위해서 BFS를 사용했다.
- 한 섬에 방문할 때마다 해당 섬은 0으로 바꿔주어서 중복을 피했다.

## :black_nib: **Review**

- 이런 문제는 이제 익숙해져서 빨리 풀 수 있는 것 같다!

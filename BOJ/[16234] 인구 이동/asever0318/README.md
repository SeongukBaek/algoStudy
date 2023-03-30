# [16234] 인구이동

## :pushpin: **Algorithm**

BFS, 구현, 시뮬레이션

## :round_pushpin: **Logic**

```java
while(true) {
            visited = new boolean[N][N];
            int[][] preMap = new int[N][N];
            copyMap(preMap, map); // 현재 맵 복사해놓기

            for(int i = 0; i < N; i++) { // 전체 나라에 대해 확인하면서
                for(int j = 0; j < N; j++) {

                    if(visited[i][j]) { // 방문한 나라이면 패스
                        continue;
                    }

                    // 방문하지 않은 나라이면(연합에 속하지 않은 나라이면)
                    List<Pos> u = findUnion(i, j); // 연합찾고

                    movePopulation(u); // 인구이동하고
                }
            }

            if(isSame(preMap)) { // 인구 이동이 없다면 종료

                break;
            }
            days++;
        }
```

- 각 나라를 돌면서 만약 연합에 포함되지 않은 나라이면 해당 나라부터 인접 국가들을 확인해서 새로운 연합을 만들고 만들어진 연합에 대해 인구이동을 한다.
- 만약 인구이동이 없다면 종료한다.

```java
    static List<Pos> findUnion(int i, int j) {

        // 4방향으로 인접한 나라들 확인하면서 인구수의 차이가 L이상 R이하라면 맵에 표시
        Queue<Pos> queue = new LinkedList<>();
        List<Pos> union = new ArrayList<>();

        queue.add(new Pos(i, j));
        union.add(new Pos(i, j));
        visited[i][j] = true;

        while(!queue.isEmpty()) {
            Pos current = queue.poll();

            for(int d = 0; d < 4; d++) {
                int nx = current.x + dx[d];
                int ny = current.y + dy[d];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) { // 맵 벗어나는지 확인하기
                    continue;
                }

                if(!open(current, nx, ny)) { // 국경 열지 확인하기
                    continue;
                }

                if(visited[nx][ny]) {
                	continue;
                }

                visited[nx][ny] = true;
                queue.add(new Pos(nx, ny));
                union.add(new Pos(nx, ny));
            }
        }
        return union;
    }
```

- 연합을 찾는 함수
- BFS로 4방향으로 인접한 나라들 확인하면서 인구수의 차이가 L이상 R이하라면 맵에 표시한다.
- 해당 나라가 맵을 벗어나지 않고, 국경을 열어야하고, 방문하지 않은 나라라면 연합에 추가한다.

```java
static void movePopulation(List<Pos> union) {
        int size = union.size();
        int sum = 0;

        // 인구 총 합 구하기
        for(int i = 0; i < size; i++) {
            sum += map[union.get(i).x][union.get(i).y];
        }

        int p = sum / size; // 인구 이동 후 새로운 인구값
        for(int i = 0; i < size; i++) {
            map[union.get(i).x][union.get(i).y] = p; // 맵에 새로운 인구값으로 갱신
        }
    }

```

- 인구 이동하는 함수
- 해당 연합의 총 인구 합을 구해서 인구 이동 후의 새로운 인구값을 구한 후 맵에 새로운 인구값으로 갱신한다.

## :black_nib: **Review**

- 이차원 맵을 복사할 때 맵 복사가 제대로 안되어서 애를 먹었다. 배열 복사할 때 객체 하나하나 복사 해주어야 하는 것(깊은 복사)을 배웠다.

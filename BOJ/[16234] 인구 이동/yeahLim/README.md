# [16234] 인구 이동

## :pushpin: **Algorithm**

구현, 그래프 이론, 그래프 탐색, BFS, 시뮬레이션

## :round_pushpin: **Logic**

```java
static ArrayList<Nation> unitedNations; // 같은 연합인 나라들

while(true) {
    boolean isMoved = false;
    visited = new boolean[n][n];
    for(int i=0; i<n; i++) {
        for(int j=0; j<n; j++) {
            if(visited[i][j]) continue;
            int sum = searchPopulation(i, j);
            if(unitedNations.size() > 1) {
                unionNations(sum);
                isMoved = true;
            }
        }
    }
    if(!isMoved) return day;
    day++;
}
```

- 이중 for문을 돌려서 모든 나라들 다 탐색한다.

```java
while(!queue.isEmpty()) {
    nation = queue.poll();

    for(int i=0; i<4; i++) {

        int nx = nation.x + dx[i];
        int ny = nation.y + dy[i];

        if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
        if(visited[nx][ny]) continue;
        if(!checkPopulation(nation, map[nx][ny])) continue;

        Nation newNation = new Nation(nx, ny);
        sum += newNation.population;
        unitedNations.add(newNation);
        queue.offer(newNation);
        visited[newNation.x][newNation.y] = true;

    }
}
```

- BFS 시에 방문처리가 안 된 나라들을 탐색해서, unitedNations에 연합이 가능한 나라를 추가하고, 방문 처리를 한다.

```java
/* 국경선 열린 나라끼리 연합 */
private static void unionNations(int sum) {
    int average = sum / unitedNations.size();
    for(Nation nation : unitedNations) {
        map[nation.x][nation.y] = average;
    }
}
```

- 연합할 수 있는 국가끼리 연합을 시킨다.

- 위 과정을 연합할 수 있는 국가가 나올때까지 반복한다.

## :black_nib: **Review**

- 처음 풀었을때, 모든 나라를 다 탐색하지않고 효율적인 방법을 모색하다가, 어려움에 처했다. 항상 문제 풀기 전에 완전탐색을 할 수 있는 조건인지 확인하자!

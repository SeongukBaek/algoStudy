# [16946] 벽 부수고 이동하기 4

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
/* BFS : 집합 찾기 */
private static void searchSet(int x, int y, int num) {
    Deque<int[]> q = new ArrayDeque<>();
    q.offer(new int[] {x, y});
    
    visited[x][y] = true;
    int count = 0;
    
    while (!q.isEmpty()) {
        int[] cur = q.poll();
        
        map[cur[0]][cur[1]] = num;
        count++;
        
        for (int i = 0; i < 4; i++) {
            int nx = cur[0] + dx[i];
            int ny = cur[1] + dy[i];
            
            if (nx < 0 || nx >= n || ny < 0 || ny >= m ) continue;
            if (visited[nx][ny]) continue;
            if (map[nx][ny] == 0) {
                visited[nx][ny] = true;
                q.offer(new int[] {nx, ny});
            }				
        }
    }
    
    setCount.put(num, count);
}
```
- 0으로 이어져있는 부분 집합으로 만들어주고, map에 집합의 인덱스와 원소 개수를 넣어준다.

```JAVA
/* 벽부셔서 이동할 수 있는 개수 세기 */
private static int move(int x, int y) {
    boolean[] moved = new boolean[setCount.size() + 2];
    int count = 0;
    
    for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        
        if (nx < 0 || nx >= n || ny < 0 || ny >= m ) continue;
        if (moved[map[nx][ny]]) continue;
        if (map[nx][ny] == 1) continue;
        
        moved[map[nx][ny]] = true;
        count += setCount.get(map[nx][ny]);
        
    }

    return count + 1;
    
}
```
- 각 원소의 사방을 탐색해, 집합의 원소개수를 더해준다.


## :black_nib: **Review**

- 계속 시간 초과가 나서 곤란했던 문제이다. 결국 stringbuilder를 이용하여 통과했다. 앞으로 출력할 때, stringbuilder를 애용해야겠다.
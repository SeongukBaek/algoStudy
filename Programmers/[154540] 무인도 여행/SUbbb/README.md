# [154540] 무인도 여행

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
private int computeConnectedAreas(int x, int y) {
    int area = 0;
    Queue<int[]> locations = new ArrayDeque<>();
    locations.add(new int[] {x, y});
    isVisited[x][y] = true;
    area += maps[x].charAt(y) - '0';
    
    while (!locations.isEmpty()) {
        int[] current = locations.poll();
        int cx = current[0];
        int cy = current[1];
        
        for (int[] direction : directions) {
            int nx = cx + direction[0];
            int ny = cy + direction[1];
            
            if (!isIn(nx, ny) || isVisited[nx][ny] || maps[nx].charAt(ny) == 'X') {
                continue;
            }
            
            isVisited[nx][ny] = true;
            locations.add(new int[] {nx, ny});
            area += maps[nx].charAt(ny) - '0';
        }
    }
    
    return area;
}
```

- BFS로 연속된 숫자 구역을 카운트한다.

## :black_nib: **Review**

- BFS 매우 쉬운 형태!

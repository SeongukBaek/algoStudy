# [삼성 SW 역량테스트] 코드트리 빵

## :pushpin: **Algorithm**

구현, 시뮬레이션, BFS

## :round_pushpin: **Logic**

```java
private static int bfs(Location location) {
  Queue<Location> locations = new ArrayDeque<>();
  locations.add(location);
  int baseCampCount = baseCamps;
  
  // 각 좌표까지의 최소 이동 거리 갱신
  int[][] distance = new int[n][n];
  int minDistance = n * n;
  
  while (!locations.isEmpty()) {
    Location currentLocation = locations.poll();
    
    for (int[] move : moves) {
      int nx = currentLocation.x + move[0];
      int ny = currentLocation.y + move[1];
      
      // 격자 밖이거나, 이미 최소 거리가 갱신된 경우 패스
      if (!isInMap(nx, ny) || distance[nx][ny] != 0) {
        continue;
      }
      
      // 이전 좌표까지의 거리 + 1로 현재 좌표까지의 최소 이동 거리 갱신
      distance[nx][ny] = distance[currentLocation.x][currentLocation.y] + 1;
      
      // 현재 좌표가 방문가능한 베이스캠프이면서, 최소 이동 거리라면
      if (map[nx][ny] == 1 && minDistance > distance[nx][ny]) {
        // 갱신!
        minDistance = distance[nx][ny];
        // 방문처리, 더이상 해당 좌표로 이동 불가
        map[nx][ny] = 2;
        baseCampCount--;
      } else {
        // 그렇지 않은 경우는 큐에 넣고 추가 이동
        locations.add(new Location(nx, ny));
      }
    }
      
    // 모든 베이스캠프에 대한 최소 거리 갱신이 끝났다면 종료!
    if (baseCampCount == 0) {
      break;
    }
  }
  
  return minDistance;
}
```

- 편의점에서부터 모든 베이스캠프를 방문하면서 최소 거리를 갱신한다.
- 방문한 베이스캠프는 방문처리를 위해 2로 저장한다.

## :black_nib: **Review**
- '각 편의점에서부터 출발해 모든 베이스캠프를 방문할 수 있는 최소 거리 중, 최대 값이 정답이 된다' 라는 아이디어가 핵심이었다.
# [14502] 연구소 - Java

## :pushpin: **Algorithm**

그래프 이론, 그래프 탐색, BFS, 브루트포스

## :round_pushpin: **Logic**

```java
for (int x = 0; x < N; x++) {
    st = new StringTokenizer(br.readLine());
    for (int y = 0; y < M; y++) {
        int number = Integer.parseInt(st.nextToken());
        map[x][y] = number;

        if (number == 2) {
            virus.add(new Location(x, y));
        }

        if (number == 0) {
            wallCandidates.add(new Location(x, y));
        }
    }
}
```

- 바이러스 위치는 큐로서 저장한다.
- 또한 벽 위치도 저장해서, 이후에 3중 for문으로 모든 경우의 벽을 세우기 위해 사용한다.

```java
private static void buildWall() {
    // 첫 번째 벽 선택
    for (int first = 0; first < wallCandidates.size() - 2; first++) {
        Location firstWall = wallCandidates.get(first);
        // 두 번째 벽 선택
        for (int second = first + 1; second < wallCandidates.size() - 1 && second != first; second++) {
            Location secondWall = wallCandidates.get(second);
            // 세 번째 벽 선택
            for (int third = second + 1; third < wallCandidates.size() && third != second; third++) {
                temp = copyMap();
                Location thirdWall = wallCandidates.get(third);
                temp[firstWall.x][firstWall.y] = 1;
                temp[secondWall.x][secondWall.y] = 1;
                temp[thirdWall.x][thirdWall.y] = 1;

                spreadVirus();
                max = Math.max(max, countSafeArea());
            }
        }
    }
}
```

- 모든 경우의 벽을 세워보아야하기에, 위와 같이 3중 for문을 통해 벽을 세워보고, 최대 안전 구역을 계산한다.

## :black_nib: **Review**
- 이전에도 아이디어는 금방 잡았으나 배열의 복사 문제에서 애썼던 기록이 있다.
- 이제 이러한 시뮬레이션식의 문제는 배열의 초기화, 복사 문제가 골치 아프다는 것을 알기에 이를 유념해서 코드를 짤 수 있었다.
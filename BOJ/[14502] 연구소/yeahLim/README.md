# [14502] 연구소 - Java

## :pushpin: **Algorithm**

조합, 그래프 이론, 그래프 탐색, BFS, 브루트포스

## :round_pushpin: **Logic**

```java
for(int i=startX; i<n; i++) {
    for(int j=startY; j<m; j++) {
        if(map[i][j] == 0) {
            map[i][j] = 1;
            if(j != m-1) {
                build3Walls(i, j+1, depth+1);
            } else {
                build3Walls(i+1, 0, depth+1); // j가 마지막 열일때 0으로 초기화
                startY = 0;  // j가 마지막 열일때 0으로 초기화
            }
            map[i][j] = 0;
        } else {
            if(j == m-1) { // j가 마지막 열일때 0으로 초기화
                startY = 0;
            }
        }
    }
}
```
- 3개의 벽을 세울때 조합을 이용했다. 2차원 배열로 조합을 구현했다. 
- i는 startX부터 시작하고 j는 startY부터 시작했다. 다만, startY가 마지막 열일때는 0으로 초기화하는 코드가 별도로 필요하다.

```java
if(map[curr[0]][curr[1]] == 2) {
    if(map[x][y] != 2) {
        map[x][y] = 2;
        q.add(new int[] {x, y});
    }
}
```

- 현 위치에 바이러스(2)가 존재한다면, 사방에 2를 대입하고 큐에 넣어서 또 그 위치의 사방에 2를 대입해주었다.

## :black_nib: **Review**
- 처음으로 2차원 배열로 조합을 구현했다. startY값이 마지막 열일때마다 0으로 초기화 해줘야해서 코드가 조금 지저분한 느낌이 들었다. 왜 사람들이 2차원 배열을 사용하지 않는지 알게되었다.
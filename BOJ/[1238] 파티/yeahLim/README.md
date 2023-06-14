# [1238] 파티

## :pushpin: **Algorithm**

플로이드워샬

## :round_pushpin: **Logic**

```java
for (int k = 1; k <= n; k++) {
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
        }
    }
}

for (int i = 1; i <= n; i++) {
    answer = Math.max(dist[i][x] + dist[x][i], answer);
}
```

- 플로이드워샬로 최소 시간을 각각 구한 다음에 각 학생이 파티를 하는 학생에 집에 가고 오는 거리가 제일 큰 학생의 시간을 출력

## :black_nib: **Review**

- n의 최대값이 1000이어서, '''O(n^3)'''인 플로이드워샬을 사용할 수 있었다.
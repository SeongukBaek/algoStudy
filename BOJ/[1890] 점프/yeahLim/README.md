# [1890] 점프

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
for(int i=0; i<n; i++) {
        for(int j=0; j<n; j++){

            // 점프의 길이가 0일때
            if(board[i][j] == 0) {
                continue;
            }

            int x = i + board[i][j];
            int y = j + board[i][j];

            // 아래로 갈 수 있을 때
            if (x < n) {
                routeCnt[x][j] += routeCnt[i][j]; // 현재 경로의 개수를 더해준다
            }

            // 오른쪽으로 갈 수 있을 때
            if (y < n) {
                routeCnt[i][y] += routeCnt[i][j];
            }
        }
    }

    return routeCnt[n-1][n-1];
}
```

- 첫번째 좌표에 경로의 개수를 1로 설정한다.
- 현재 경로에서 갈 수 있는 다음의 경로에 현재 경로의 개수를 더해준다.
- 위를 반복하면 자동으로 도착 좌표를 알 수 있다.

## :black_nib: **Review**

- dp에서 공식을 구하는 게 중요하다는 것을 알게되었다.

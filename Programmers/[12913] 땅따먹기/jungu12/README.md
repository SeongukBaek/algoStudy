# [12913] 땅따먹기

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
int solution(int[][] land) {
        int[][] dp = new int[land.length][4];

        dp[0] = land[0].clone();

        for(int i = 1; i < land.length; i++) {
            for(int j = 0; j < 4; j++) {
                int max = 0;
                for(int k = 0; k < 4; k++) {
                    if(j == k) {
                        continue;
                    }

                    max = Math.max(max, dp[i - 1][k]);
                }
                dp[i][j] = max + land[i][j];
            }
        }

        return Arrays.stream(dp[land.length - 1]).max().getAsInt();
    }
```

- dp 배열에는 해당 땅까지 온 경우 중 가장 최적의 경우가 저장된다.
- 그 다음 row를 볼때는 윗 row에서 같은 coloum을 제외하고 가장 큰 값을 골라 현재 칸의 값을 더해주어 dp배열에 저장해준다.

## :black_nib: **Review**

- Bottom-up 방식으로 쉽게 풀 수 있는 DP 문제였다.

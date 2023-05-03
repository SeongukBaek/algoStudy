# [12913] 땅따먹기

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
// 초기값 설정
dp[0] = land[0].clone();

for (int i = 1; i < n; i++) {
    for (int j = 0; j < 4; j++) {
        dp[i][j] = land[i][j] + getMaxValue(i, j);
    }
}
```

- 각 원소마다 자기의 값(`land[i][j]`)과 그 전 행 중에 가장 큰 값(`getMaxValue(i, j)`)을 더한다.

```java
/* 같은 열에서 최대값 구하기 */
public int getMaxValue(int i, int j) {
    int max = 0;
    for (int k = 0; k < 4; k++) {
        if (j == k) continue;
        max = Math.max(max, dp[i-1][k]);
    }
    return max;
}
```

- 현재 열과 같은 열의 값은 제외한 값 중에서 최대값을 반환한다.

## :black_nib: **Review**

- only dp문제는 언제나 재밌다

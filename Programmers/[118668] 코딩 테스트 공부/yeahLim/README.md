# [118668] 코딩 테스트 공부

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
// dp를 이용해서 최단 시간 구하기
for(int i=alp; i<=maxAlp; i++) {
    for(int j=cop; j<=maxCop; j++) {

        // 알고력+1 올리기
        dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+1);
        // 코딩력+1 올리기
        dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j]+1);

        // 문제들로 실력 올리기
        for(int k=0; k<pro.length; k++) {

            // 현재의 알고력과 코딩력으로 문제를 풀 수 없는 경우
            if(i < pro[k][0] || j < pro[k][1]) continue;

            int x = (i + pro[k][2] > maxAlp) ? maxAlp : i + pro[k][2];
            int y = (j + pro[k][3] > maxCop) ? maxCop : j + pro[k][3];

            dp[x][y] = Math.min(dp[x][y], dp[i][j]+pro[k][4]);
        }
    }

}
```

- 기본값으로 주어지는 알고력과 코딩력을 시작 좌표로 잡고, 전체 문제 중 알고력의 최대값과 코딩력의 최대값을 목표 좌표로 삼았다.
- 모든 dp배열에 최대값을 넣어주고, 시작 지점인 `dp[alp][cop]`에 0을 설정한다.
- 이후, dp를 이용해서 최단 시간을 구한다.
  - 알고력 +1 올리는 방법, 코딩력 +1 올리는 방법, 주어진 문제로 올리는 방법 총 3가지 방법이 있다.

## :black_nib: **Review**

- 처음에는 완전탐색으로 문제를 풀려고 했으나 문제에서는 더 효율적인 방법을 요구했기 때문에 dp를 이용했다.
- dp를 몇 번 사용하지 않아서 어려웠다. 원하는 값을 목표하는 값으로 바꿀때 각 항목을 좌표로 바꿔 생각해서도 문제를 풀 수 있다는 것을 유념하자!
- dp는 풀고나면 쉬운데,, 풀때가 젤 어렵당!

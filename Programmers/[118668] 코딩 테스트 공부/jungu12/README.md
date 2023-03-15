# [118668] 코딩 테스트 공부

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
public int solution(int alp, int cop, int[][] problems) {
    int maxAlp = 0;
    int maxCop = 0;

    // 문제 중 가장 높게 요구되는 코딩력과 알고력을 구함
    for (int i = 0; i < problems.length; i++) {
        maxAlp = Math.max(problems[i][0], maxAlp);
        maxCop = Math.max(problems[i][1], maxCop);
    }

    // 초기 알고력, 코딩력이 max값 보다 높을 때
    if (maxAlp <= alp && maxCop <= cop) {
        return 0;
    }

    // dp[][] 배열 선언을 위해 초기 값이 max보다 클 때는 바꾸어줌
    if (alp > maxAlp) {
        alp = maxAlp;
    }
    if (cop > maxCop) {
        cop = maxCop;
    }

    int[][] dp = new int[maxAlp + 2][maxCop + 2];

    for (int i = alp; i <= maxAlp; i++) {
        for (int j = cop; j <= maxCop; j++) {
            dp[i][j] = Integer.MAX_VALUE;
        }
    }

    dp[alp][cop] = 0;

    for (int i = alp; i <= maxAlp; i++) {
        for (int j = cop; j <= maxCop; j++) {
            // 공부하여 알고력 늘리기
            dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
            // 공부하여 코딩력 늘리기
            dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);

            // 문제 풀기
            for (int[] p : problems) {
                //해당 문제를 풀 수 없는 경우
                if (i < p[0] || j < p[1]) {
                    continue;
                }
                //문제를 풀어 알고력과 코딩력 모두 max를 넘은 경우
                if (i + p[2] > maxAlp && j + p[3] > maxCop) {
                    dp[maxAlp][maxCop] = Math.min(dp[maxAlp][maxCop], dp[i][j] + p[4]);
                }
                //문제를 풀어 알고력만 max를 넘은 경우
                else if (i + p[2] > maxAlp) {
                    dp[maxAlp][j + p[3]] = Math.min(dp[maxAlp][j + p[3]], dp[i][j] + p[4]);
                }
                //문제를 풀어 코딩력만 max를 넘은 경우
                else if (j + p[3] > maxCop) {
                    dp[i + p[2]][maxCop] = Math.min(dp[i + p[2]][maxCop], dp[i][j] + p[4]);
                }
                //코딩력, 알고력 모두 max에 도달하지 못한 경우
                else if (i + p[2] <= maxAlp && j + p[3] <= maxCop) {
                    dp[i + p[2]][j + p[3]] = Math.min(dp[i + p[2]][j + p[3]], dp[i][j] + p[4]);
                }
            }

        }
    }
    return dp[maxAlp][maxCop];
}
```

- 초기 알고력과 코딩력으로 모든 문제를 풀 수 있는 경우, 초기 알고력과 코딩력을 maxAlp와 maxCop로 바꾸어준다.
- 문제를 풀다가 알고력과 코딩력이 maxAlp와 maxCop를 넘어가는 경우, 알고력과 코딩력을 maxAlp와 maxCop로 바꾸어준다.

- 로직은 아래와 같다
- 공부하여 알고력, 코딩력을 늘린 값을 dp배열에 저장한다.
- 풀 수 있는 문제가 있다면 문제를 풀어 알고력, 코딩력을 늘린 값을 dp배열에 저장한다.
- bottom-up으로 dp[maxAlp][maxcop]에 도달 한다면, return 해준다.

## :black_nib: **Review**

- 알고력과 코딩력이라는 생소한 변수가 주어져서 DP로 풀어야 하는 것을 알기 힘들었다..
- dp배열을 넘어가는 경우들을 따져줘야 하는 것을 생각하기 힘들었다.

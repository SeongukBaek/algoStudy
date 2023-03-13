# [118668] 코딩 테스트 공부

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
for (int currentAlg = alp; currentAlg <= targetAlp; currentAlg++) {
    for (int currentCop = cop; currentCop <= targetCop; currentCop++) {
        // 먼저 공부로 갱신할 수 있는 알고력과 코딩력 계산
        // 알고력 늘리기
        dp[currentAlg + 1][currentCop] = Math.min(dp[currentAlg + 1][currentCop], dp[currentAlg][currentCop] + 1);
        // 코딩력 늘리기
        dp[currentAlg][currentCop + 1] = Math.min(dp[currentAlg][currentCop + 1], dp[currentAlg][currentCop] + 1);

        // 이후 문제를 풀어보기
        for (int[] problem : problems) {
            // 해당 문제를 풀 수 없는 경우 패스
            if (currentAlg < problem[0] || currentCop < problem[1]) {
                continue;
            }

            // 해당 문제를 풀어서 갱신되는 알고력과 코딩력
            int newAlp = currentAlg + problem[2];
            int newCop = currentCop + problem[3];
            // 이전 문제를 푸는데 걸린 최소 시간에 현재 문제를 푸는 데 걸리는 시간을 더한 값
            int newCost = dp[currentAlg][currentCop] + problem[4];

            // 문제를 품으로써 얻는 능력이 목표치보다 큰 경우
            if (newAlp > targetAlp && newCop > targetCop) {
                // 목표치를 달성하는데 걸리는 최소 시간을 갱신
                dp[targetAlp][targetCop] = Math.min(dp[targetAlp][targetCop], newCost);
            }
            // 알고력만 큰 경우
            else if (newAlp > targetAlp) {
                dp[targetAlp][newCop] = Math.min(dp[targetAlp][newCop], newCost);
            }
            // 코딩력만 큰 경우
            else if (newCop > targetCop) {
                dp[newAlp][targetCop] = Math.min(dp[newAlp][targetCop], newCost);
            }
            // 알고력 코딩력 둘 다 목표치 이하인 경우
            else {
                dp[newAlp][newCop] = Math.min(dp[newAlp][newCop], newCost);
            }
        }
    }
}
```

- 주어진 alp, cop로부터 모든 문제를 풀 수 있는 목표 알고력, 코딩력까지 도달하는 최소 시간을 구한다.
- 먼저, 능력을 올릴 수 있는 방법은 공부를 하거나, 풀 수 있는 문제를 푸는 것이다.
- 풀 수 있는 문제를 푸는데, 풀고 난 이후 능력치에 따라 dp 배열을 갱신한다.

## :black_nib: **Review**

- 처음에는 우선순위 큐를 사용해서 각 문제들을 특정 순서로 탐색하면서 문제를 풀면서 목표치를 채울려고 했다.
- 하지만 알고력이나 코딩력 중 하나가 우선순위를 가질 수도 있고 아닐 수도 있어 정렬 조건을 맞출 수 없었다..
- 모르겠어서 풀이를 참고했는데 DP로 풀 수 있는 문제여서 .. 좀 신기했다.

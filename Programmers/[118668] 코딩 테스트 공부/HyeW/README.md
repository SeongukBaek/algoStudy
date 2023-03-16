# [118668] 코딩 테스트 공부

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
private void getMinTime(int alp, int cop, int[][] problems) {
  minTime[alp][cop] = 0;

  for (int r = alp; r <= maxAlp; r++) {
    for (int c = cop; c <= maxCop; c++) {
      int cur = minTime[r][c];

      // 알고리즘 공부를 했을 경우
      minTime[r + 1][c] = Math.min(minTime[r + 1][c], cur + 1);
      // 코딩 공부를 했을 경우
      minTime[r][c + 1] = Math.min(minTime[r][c + 1], cur + 1);

      // 문제를 풀었을 경우
      for (int[] problem : problems) {
        if (r < problem[0] || c < problem[1]) {
          continue;
        }

        int dr = Math.min(r + problem[2], maxAlp);
        int dc = Math.min(c + problem[3], maxCop);

        minTime[dr][dc] = Math.min(minTime[dr][dc], cur + problem[4]);
      }

    }
  }
}
```

row는 알고력을 col은 코딩력을 나타내는 2차원 배열을 만든다.<br/>
기본 알고력과 코딩력이 시작점이 되며 모든 문제를 풀기위해 필요한 알고력과 코딩력이 끝점이 된다.<br/>
그래서 시작점을 0으로 두고 알고력또는 코딩력을 1씩 올렸을 때, 또는 문제를 풀었을 때 올릴 수 있는 '(알고력, 코딩력)'좌표에 시간을 넣어준다.<br/>
해당 좌표를 다 기입하면 끝점의 값이 답이 된다.

## :black_nib: **Review**

- 처음에 모든 문제를 풀어야 하는 것으로 이해를 해서 dfs를 사용해 로직을 구현했다가 시간초과가 났었다.
  - 이후에 문제를 제대로 이해하고 dp를 생각했는데 시작점을 생각해내지 못해 답을 참고하여 문제를 해결했다. dp문제를 풀 때 신경 써야하는 부분 중 하나가 시작점과 끝점을 명확하게 정하는 것이란 걸 배웠다.

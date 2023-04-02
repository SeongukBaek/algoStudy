# [9465] 스티커

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
/* dp : 스티커 탐색 */
static int searchSticker() {
    // 초기값 설정
    dp[0][0] = stickers[0][0];
    dp[1][0] = stickers[1][0];

    for(int i=1; i<n; i++) {
        dp[0][i] = Math.max(dp[0][i-1], dp[1][i-1]+stickers[0][i]);
        dp[1][i] = Math.max(dp[1][i-1], dp[0][i-1]+stickers[1][i]);
    }

    return Math.max(dp[0][n-1], dp[1][n-1]);
}
```

- 사방으로 인접한 변에서는 값을 가져올 수 없기 때문에, `바로 이전 열의 값`과 `현재 값과 한 칸 이전의 다른행의 값을 더한 값` 중에서 큰 값을 구한다.
- 행이 2개밖에 없기 때문에, 0행일때 / 1행일때 두 가지 경우를 나눠서 규칙(점화식)을 찾았다.
  - 0행일 때는 `dp[0][i-1]`와 `dp[1][i-1]+stickers[0][i]`중에서 큰 값을 넣는다.
  - 1행일 때는 `dp[1][i-1]`와 `dp[0][i-1]+stickers[1][i]`중에서 큰 값을 넣는다.

## :black_nib: **Review**

- 어제 들은 수업에서 dp는 수학적 귀납법을 이용한 알고리즘이라는 것을 배웠다. 이를 이용하여 초기값을 설정해서 점화식(규칙)을 찾아서 쉽게 풀 수 있었다! 내가 푼 dp문제 중에 가장 쉬운 것일지도 모른다 ㅎㅎ

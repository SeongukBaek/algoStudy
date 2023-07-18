# [1563] 개근상

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int[][][] dp = new int[N + 1][2][3]; // 날짜, 지각 횟수, 연속으로 결석한 횟수
    dp[1][0][0] = 1;
    dp[1][0][1] = 1;
    dp[1][1][0] = 1;

    for (int i = 2; i <= N; i++) {
        dp[i][0][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % 1000000;
        dp[i][0][1] = dp[i - 1][0][0];
        dp[i][0][2] = dp[i - 1][0][1];
        dp[i][1][0] = (Arrays.stream(dp[i - 1][0]).sum() + Arrays.stream(dp[i - 1][1]).sum()) % 1000000;
        dp[i][1][1] = dp[i - 1][1][0];
        dp[i][1][2] = dp[i - 1][1][1];
    }

    System.out.println((Arrays.stream(dp[N][0]).sum() + Arrays.stream(dp[N][1]).sum()) % 1000000);
}
```

- dp 배열에는 int[][][] dp = new int[N + 1][2][3]; // 날짜, 지각 횟수, 연속으로 결석한 횟수가 들어간다.
- 6가지 경우의 수를 모두 고려하여 식을 세운 후 풀어주었다.

## :black_nib: **Review**

- 최근 푼 DP문제 중에 가장 어려웠다...

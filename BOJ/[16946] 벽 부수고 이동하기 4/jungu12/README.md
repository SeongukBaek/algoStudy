# [2156] 포도주 시식

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int[] table = new int[N + 1];
    int[] dp = new int[N + 1];

    for (int i = 1; i <= N; i++) {
        table[i] = Integer.parseInt(br.readLine());
    }

    dp[1] = table[1];

    if (N > 1) {
        dp[2] = table[1] + table[2];
    }

    for (int i = 3; i <= N; i++) {
        dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + table[i], dp[i - 3] + table[i - 1] + table[i]));
    }

    System.out.println(dp[N]);
}
```

- 연속으로 3잔의 포도주를 마실 수 없다고 문제에 명시 되어 있다.
- 따라서 점화식은 다음과 같이 세울 수 있다.
- dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + table[i], dp[i - 3] + table[i - 1] + table[i]));
- N값이 1인 경우도 존재할 수 있으니 고려하여 dp[2] 값을 넣어주었다.

## :black_nib: **Review**

- 실버 1인데 생각보다 어려웠다..

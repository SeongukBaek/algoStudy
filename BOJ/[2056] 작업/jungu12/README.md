# [2056] 작업

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
int N = Integer.parseInt(br.readLine());
// 각각의 작업을 수행하는 데 걸리는 시간
int[] dp = new int[N + 1];

int ans = 0;
for (int i = 1; i <= N; i++) {
    st = new StringTokenizer(br.readLine());
    int time = Integer.parseInt(st.nextToken());
    int num = Integer.parseInt(st.nextToken());

    dp[i] = time;
    for (int j = 0; j < num; j++) {
        int temp = Integer.parseInt(st.nextToken());
        dp[i] = Math.max(dp[i], dp[temp] + time);
    }
    ans = Math.max(ans, dp[i]);
}
System.out.println(ans);
```

- k번째 작업의 선행 작업들은 모두 k미만 번째 작업이다.
- 그래서 따로 정렬이 필요 없이 dp를 활용하여 풀 수 있다.

## :black_nib: **Review**

- 위상 정렬로 풀고 싶었는데 잘 이해가 되지 않는다..

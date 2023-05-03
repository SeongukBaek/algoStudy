# [12913] 땅따먹기

## :pushpin: **Algorithm**

다이나믹 프로그래밍

## :round_pushpin: **Logic**

```java
 static int[][] dp;

static int solution(int[][] land) {
	int size = land.length;
	dp = new int[size][4];

	for(int i =0; i < 4; i++) {
		dp[0][i] = land[0][i];
	}

	for(int i = 0; i < size-1; i++) {
		for(int j = 0; j < 4; j++) {
			for(int k = 0; k < 4; k++) {
				if(j == k) {
					continue;
				}

				if(dp[i+1][k] < dp[i][j] + land[i+1][k]) {
					dp[i+1][k] = dp[i][j] + land[i+1][k];
				}
			}
		}
	}

	return getMax(size);
}
```

- 2차원 dp 배열을 만들어서 각 칸을 선택했을 때의 누적 최대값을 저장했다.
- 3중 for문을 돌면서 행전체, j열, k열을 탐색한다.
- 선택 한 칸 바로 밑 칸은 선택 할 수 없으므로 j == k인 경우는 continue로 패스하고, j != k일 경우만 dp배열에 저장된 i+1행의 최대값과 (i행의 j번째칸의 누적 최대값 + i+1행의 k번째 값)을 비교하여 누적 최대값을 갱신해준다.
- 마지막 행에는 각 숫자를 선택했을 때 최종 최대값이 저장되고 그 중에서 가장 큰 값이 정답이 된다.

## :black_nib: **Review**

- dp 문제 이렇게만 나오면 좋겠다 ㅎ.ㅎ.ㅎ.

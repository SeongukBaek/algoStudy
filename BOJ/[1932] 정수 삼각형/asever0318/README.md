# [1932] 정수 삼각형

## :pushpin: **Algorithm**

dp

## :round_pushpin: **Logic**

```java
public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N];
		int[][] triangle = new int[N][N];

		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int j = 0;
			while(st.hasMoreTokens()) {
				triangle[i][j] = Integer.parseInt(st.nextToken());
				j++;
			}
		}

		// 가장 마지막 레벨의 수로 dp 배열 초기화
		for(int i = 0; i < N; i++) {
			dp[i] = triangle[N-1][i];
		}

		// 맨 밑단부터 위로 올라오기
		for(int i = N-2; i >= 0; i--) {
			for(int j = 0; j <= i; j++) {
				dp[j] = Math.max(dp[j] + triangle[i][j], dp[j+1] + triangle[i][j]);
			}
		}

		System.out.println(dp[0]);
	}
```

- 가장 밑단의 수로 dp배열을 먼저 초기화 한다.
- 한 칸씩 위로 올라가면서 바로 밑단에서 선택 할 수 있는 2개의 노드 중 누적합 중 큰 것을 선택한다.
- 가장 위의 루트노드가 정답이 된다.

## :black_nib: **Review**

- dp문제인 것과 bottom-up 방식으로 푸는 것을 혼자 생각해내해고 풀어서 뿌듯한 문제였다. 야호

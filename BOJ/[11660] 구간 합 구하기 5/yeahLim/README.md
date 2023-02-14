# [11660] 구간 합 구하기 5

## :pushpin: **Algorithm**

DP, 누적 합

## :round_pushpin: **Logic**

```java
for (int i = 0; i < n; i++) {
			int sum = 0;
			String[] nstr = br.readLine().split(" ");
			// 누적값 넣어주기
			for (int j = 0; j < n; j++) {
				sum += Integer.parseInt(nstr[j]);
				arr[i+1][j+1] = sum;
			}
		}
```

- 입력과 동시에 행을 기준으로 (i,0)에서 (i,n)까지의 값을 더해 누적값을 대입한다. 

```java
int x1, y1, x2, y2;
    for (int i = 0; i < m; i++) {
        st = new StringTokenizer(br.readLine(), " ");
        x1 = Integer.parseInt(st.nextToken());
        y1 = Integer.parseInt(st.nextToken());
        x2 = Integer.parseInt(st.nextToken());
        y2 = Integer.parseInt(st.nextToken());
        int res = 0;
        for(int j=x1; j<=x2; j++) {
            res += arr[j][y2] - arr[j][y1-1];
        }
```

- 큰 누적값에서 작은 누적값 빼서 합을 구해준다.

## :black_nib: **Review**
- 2차원에서 누적값을 어떻게 이용할지 고민했는데, 행을 기준으로 누적값을 구해준 후 열만큼의 누적값을 빼주는 방법을 이용했다.

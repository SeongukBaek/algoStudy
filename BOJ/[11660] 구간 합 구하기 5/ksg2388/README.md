# [11660] 구간 합 구하기 5 

## :pushpin: **Algorithm**
누적 합

## :round_pushpin: **Logic**
``` java
for (int i = 1; i <= n; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				arr[i][j] = arr[i][j - 1] + Integer.parseInt(st.nextToken()) + arr[i - 1][j] 
                -  arr[i - 1][j - 1];
			}
		}
```
- (1, 1)부터 각 점까지의 누적합을 구해 저장함. ex) (2, 2)의 경우 (1, 1) + (1, 2) + (2, 1) + (2, 2)의 값을 저장.
- 출력시에는 누적합들을 조합하여 원하는 값을 만들어 낸다.

## :black_nib: **Review**
- 2차원 배열에서 누적합을 어떻게 사용할지 생각해 볼 수 있었다. 

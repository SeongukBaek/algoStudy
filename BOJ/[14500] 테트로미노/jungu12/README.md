# [14500] 테트로미노

## :pushpin: **Algorithm**

구현, 브루트포스

## :round_pushpin: **Logic**

```java
static void checkA(int i) {
		for (int j = 0; j < M; j++) {
			sum = arr[i][j] + arr[i][j + 1] + arr[i + 1][j] + arr[i + 1][j + 1];
			if (sum >= result) {
				result = sum;
			}
		}
	}
  ```
   - 테트리스 모양 별로 모든 경우의 수를 구현하였다.
   - 현재까지의 최대값과 비교하여 최대값을 갱신한다
  
  
## :black_nib: **Review**
 - 구현외의 다른 방법으로 풀고 싶었으나, 풀지 못하였다.
 - 오목한 모양의 테트리스를 제외하고 dfs 방식으로 풀 수 있다는 것을 알았다.

  
  	

  

# [5215] 햄버거 다이어트

## :pushpin: **Algorithm**

브루트포스

## :round_pushpin: **Logic**
- 햄버거 재료 하나마다 추가한 경우와 추가하지 않은 경우를 모두 구함
  - 백트래킹 이용
```java
static void backtrck(int idx, int scoreSum, int calSum) {

		if (calSum > cal)
			return;
		
		if(idx == n) {
			if(maxScore < scoreSum)			
				maxScore = scoreSum;
			return;
		}
		
		// 추가했을 경우
		backtrck(idx+1, scoreSum+arr[idx][0], calSum+arr[idx][1]);
		// 추가하지 않았을 경우
		backtrck(idx+1, scoreSum, calSum);
	}
```
## :black_nib: **Review**
- dfs 말고 백트래킹을 이용해 쉽게 구할 수 있음.

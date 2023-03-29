# [9465] 스티커

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
/* 세번째 열부터 마지막까지 각 열의 최대 점수를 구한다. */
	private static void getStickers() {
		initScores();
		
		for(int col = 2; col < n; col++) {
			scores[0][col] = Math.max(scores[1][col-1],scores[1][col-2]) + stickers[0][col];
			scores[1][col] = Math.max(scores[0][col-1],scores[0][col-2]) + stickers[1][col];
		}
		
	}
```
n번째 열 스티커는 자신과 다른 행의 n-1열의 스티커를 포함여부를 기준으로 경우가 나뉜다.<br/>
왜냐하면 현재 행이 1번일때 (n-1, 2)스티커를 포함하면 (n-2, 2)스티커를 포함할 수 없게된다.<br/>
그래서 현재행과 다른 행의 n-1열의 스티커를 포함한 것과 n-2열의 스티커를 포함한 것 중 더 큰 점수가 현재 좌표의 최대 값이 된다.

## :black_nib: **Review**
- DP는 어렵다..쉬운 문제였는데..점화식을 세우는게 아직 잘 안된다. 많이 연습해야 겠다.
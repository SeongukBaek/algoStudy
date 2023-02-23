# [17406] 배열 돌리기 4

## :pushpin: **Algorithm**

브루트포스

## :round_pushpin: **Logic**

```java
static void chooseOrder(int cnt) {
	if (cnt == K) {
		rotateArr(order);
		return;
	}
	for (int i = 0; i < K; i++) {
		if (!isSelected[i]) {
			order[cnt] = i;
			isSelected[i] = true;
			chooseOrder(cnt + 1);
			isSelected[i] = false;
		}
	}
}
  ```
  ```java
for (int idx = 0; idx < K; idx++) {
	int r = rotate[order[idx]][0];
	int c = rotate[order[idx]][1];
	int s = rotate[order[idx]][2];

	for (int i = 1; i <= s; i++) {
		int limit = i * 2;
		int curX = r - i;
		int curY = c - i;
		int tmpNow = tmpA[curX][curY];
		int tmpNext;
		for (int j = 0; j < limit * 4; j++) {
			if (j < limit) {
				tmpNext = tmpA[curX][curY + 1];
				tmpA[curX][curY + 1] = tmpNow;
				curY += 1;
				tmpNow = tmpNext;
			} else if (j < limit * 2) {
				tmpNext = tmpA[curX + 1][curY];
				tmpA[curX + 1][curY] = tmpNow;
				curX += 1;
				tmpNow = tmpNext;
			} else if (j < limit * 3) {
				tmpNext = tmpA[curX][curY - 1];
				tmpA[curX][curY - 1] = tmpNow;
				curY -= 1;
				tmpNow = tmpNext;

			} else if (j < limit * 4) {
				tmpNext = tmpA[curX - 1][curY];
				tmpA[curX - 1][curY] = tmpNow;
				curX -= 1;
				tmpNow = tmpNext;
			}
		}
	}
}
  ```
   - 연산의 순서쌍들을 만들어 해당 순서를 지켜 배열을 돌린다.
   - 연산 하기전, 배열을 TMP라는 임시 배열에 저장하여 다음 연산에 문제가 없게 한다.
  
  
## :black_nib: **Review**
 - 입력 받은 원배열을 연산한 후에도 그대로 사용하여 문제 해결에 오랜시간이 걸렸다.


  
  	

  

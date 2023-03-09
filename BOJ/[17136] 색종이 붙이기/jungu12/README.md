# [17136] 색종이 붙이기

## :pushpin: **Algorithm**

구현, 시뮬레이션, 백트래킹

## :round_pushpin: **Logic**

```java
private static void attachPaper() {
	for (int i = 0; i < PAPER_SIZE; i++) {
		for (int j = 0; j < PAPER_SIZE; j++) {
			//모든 1에 색종이를 붙인 경우
			if (oneCount == 0) {
				min = Math.min(min, attachedPaperCount);
				return;
			}
			//색종이를 다 썼거나, 현재까지 붙인 색종이가 min 이상인 경우
			if (attachedPaperCount == 25 || attachedPaperCount >= min) {
				return;
			}
			if (paper[i][j] == 1) {
				for (int size = 5; size > 0; size--) {
					//선택한 size의 색종이가 없는 경우
					if (coloredPaperNum[size - 1] == 0) {
						continue;
					}
					//선택한 size의 색종이를 붙일 수 있는 경우
					if (checkCanAttach(i, j, size)) {
						int[][] copyPaper = copyPaper(); // 다음 depth로 가기전 배열 복사
						int tmpOneCount = oneCount; // paper[][]에서 남은 1의 개수 복사
						int tmp = attachedPaperCount; //사용한 색종이 수 복사
						int[] copyColoredPaperNum = coloredPaperNum.clone(); //size별 색종이의 남은 개수 복사
						attach(i, j, size);
						attachPaper();
						paper = copyPaper;
						oneCount = tmpOneCount;
						coloredPaperNum = copyColoredPaperNum.clone();
						attachedPaperCount = tmp;
					}
				}
				//해당 좌표에서 모든 size의 색종이를 붙일 수 없는 경우 반환
				return;
			}
		}
	}
}
```

- size가 큰 색종이부터 붙이면서, backtracking으로 모든 경우를 탐색하는 방법으로 구현하였다.
- 모든 1에 색종이를 붙였다면, 지금까지의 최소값과 붙인 색종이 수를 비교하여 갱신해준다.
- 현재 좌표에서 모든 size의 색종이를 붙일 수 없다면, 모든 1에 색종이를 붙일 수 없는 경우이다.
  더 볼 필요도 없이 바로 return해준다.

```java
private static void attach(int x, int y, int size) {
	attachedPaperCount++;
	coloredPaperNum[size - 1]--;
	for (int i = x; i < x + size; i++) {
		for (int j = y; j < y + size; j++) {
			paper[i][j] = 0;
			oneCount--;
		}
	}
}
```

- 색종이를 붙이는 과정은 아래와 같다
- 사용한 색종이의 수(attachedPaperCount)를 증가 시킨다.
- 사용한 size의 색종이의 남은 개수를 감소 시킨다.
- paper에 1인 부분을 0으로 바꿔주고 남은 1의 개수(oneCount)를 감소 시킨다.

## :black_nib: **Review**

- 현재 좌표에서 모든 size의 색종이를 붙일 수 없다면 return 해주어야 하는 조건을 뒤늦게 생각하였다..
- 백트래킹으로 모든 경우를 다 탐색하면 왠지 시간초과가 날 것 같은 두려움에 바로 구현을 시작하지 못하였다.

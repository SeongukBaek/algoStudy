## 사용한 알고리즘

순열을 사용

## 중요 구현 로직 및 설명

순열을 이용해 가능한 배열 돌리기 방법 중 각 행의 값이 최소가 되는 값을 찾음

1. 배열을 초기화 하고 각 회전 연산들을 저장해둔다.
2. 저장해둔 회전 연산들을 순열을 이용해 최소 값이 되는 경우를 찾는다.
``` java
if (depth == k) {
	// 배열 돌리기 후 비교
	int[][] temp = new int[n][m];
	for (int i = 0; i < n; ++i) {
		for (int j = 0; j < m; ++j) {
		temp[i][j] = arr[i][j];
		}
	}
			
	for (int i = 0; i < k; ++i) {
		String[] cmd = command[idx.get(i)].split(" ");
		int r = Integer.parseInt(cmd[0]);
		int c = Integer.parseInt(cmd[1]);
		int s = Integer.parseInt(cmd[2]);
		arr = rotateArray(r, c, s);
	}
	// 최소값 구하기
	for (int[] a : arr) {
		int sum = 0;
		for (int b: a) {
			sum += b;
		}
		if (sum < result) {
			result = sum;
		}
	}
	arr = temp;
	return;
}
```
## 풀이 후기

코드가 길어지니 확실히 메소드를 부분별로 나누어 하는 방법이 도움이 많이 되었다.
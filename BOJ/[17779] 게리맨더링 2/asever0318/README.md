# [17779] 게리맨더링 2

## :pushpin: **Algorithm**

구현, 시뮬레이션

## :round_pushpin: **Logic**

```java
// 매트릭스 전체를 돌면서 하나씩 기준점으로 두기
		for(int x = 0; x < N; x++) {
			for(int y = 0; y < N; y++) {
				// d1, d2 뽑을 수 있는 모든 경우의 수 뽑기
				for(int d1 = 1; d1 < N; d1++) {
					for(int d2 = 1; d2 <= N; d2++) {

						// d1과 d2에 대한 조건 모두 만족하면
						if(x + d1 + d2 < N) {
							if(0 <= y - d1 && y < y + d2 && y + d2 < N) {
								// 해당 좌표를 기준점으로 시작
								min = 9999;
								max = 0;
								result = Math.min(result, divide(x, y, d1, d2));
							}
						}
					}
				}
			}
		}
```

- 입력 받을 때 매트릭스 전체를 돌면서 d1, d2의 뽑을 수 있는 모든 경우의 수를 탐색하면서 문제 조건과 일치하는 좌표를 기준점으로 모든 경우의 수를 실행해보고 그 중 해를 찾는다.

```java
// 경계선 세워서 구역 나누기
	static int divide(int x, int y, int d1, int d2) {

		border = new boolean[N][N]; // 경계선 표시해줄 이차원배열

		// 구역 나누기 (경계선 세우기)
		for(int i = 0; i <= d1; i++) {
			border[x+i][y-i] = true; // 경계선 1
			border[x+d2+i][y+d2-i] = true; // 경계선 4
		}

		for(int i = 0; i <= d2; i++) {
			border[x+i][y+i] = true; // 경계선 2
			border[x+d1+i][y-d1+i] = true; // 경계선 3
		}

		countPopulation(x, y, d1, d2);

		return max - min;
	}
```

- 경계선을 표시할 boolean 배열을 만들어서 문제 조건 그대로 경계선을 세웠다.

```java
// 각 구역의 인구 카운트
	static void countPopulation(int x, int y, int d1, int d2) {

		int sum = 0;
		int areaSum = 0; // 5구역 인구수 구하기 위해서 각 구역 인구수 더해줄 거임

		// 1번 선거구
		for(int i = 0; i < x + d1; i++) {
			for(int j = 0; j <= y; j++) {
				if(border[i][j] == true) {
					break; // 경계선과 만나면 다음 행으로 넘어가기
				}
				sum += matrix[i][j];
			}
		}
		minMax(sum);
		areaSum += sum;
		sum = 0;

		// 2번 선거구
		for(int i = 0; i <= x + d2; i++) {
			// 2번 선거구는 오른쪽에서 왼쪽으로 확인하기 --> 경계선을 만나면 다음행으로 넘어갈 수 있게
			for(int j = N-1; j > y; j--) {
				if(border[i][j] == true) {
					break;
				}
				sum += matrix[i][j];
			}
		}
		minMax(sum);
		areaSum += sum;
		sum = 0;

		// 3번 선거구
		for(int i = x + d1; i < N; i++) {
			for(int j = 0; j < y - d1 + d2; j++) {
				if(border[i][j] == true) {
					break;
				}
				sum += matrix[i][j];
			}
		}
		minMax(sum);
		areaSum += sum;
		sum = 0;

		// 4번 선거구 --> 오른쪽에서 왼쪽으로 확인하기 --> 경계선을 만나면 다음행으로 넘어갈 수 있게
		for(int i = x + d2 + 1; i < N; i++) {
			for(int j = N-1; j >= y - d1 + d2; j--) {
				if(border[i][j] == true) {
					break;
				}
				sum += matrix[i][j];
			}
		}
		minMax(sum);
		areaSum += sum;
		sum = 0;

		// 5번 선거구
		sum = total - areaSum;
		minMax(sum);

	}
```

- 1, 3 구역은 왼쪽편에 있기 때문에 왼쪽에서 오른쪽으로 확인하면서 경계선을 발견하면 다음 행으로 넘어가는 방식으로 인구수를 카운트했다.
- 2, 4 구역은 오른쪽 편에 있기 때문에 오른쪽에서 왼쪽으로 확인하면서 경계선을 발견하면 다음 행으로 넘어가는 방식으로 인구수를 카운트했다.
- 5구역은 입력받을 때 모든 인구수를 카운트 해놓고 1, 2, 3, 4구역의 인구수의 합을 빼는 방법으로 구했다.

## :black_nib: **Review**

- 문제에 주어진 그대로 구현하면 되는 문제였다.

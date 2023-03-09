# [17779] 게리맨더링 2

## :pushpin: **Algorithm**

구현, 시뮬레이션

## :round_pushpin: **Logic**

```java
	private static void divideElectionDistrict() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int d1 = 1; d1 < N; d1++) {
					for (int d2 = 1; d2 < N; d2++) {
						if (i + d1 + d2 >= N) {
							continue;
						}
						if (j - d1 < 0 || j + d2 >= N) {
							continue;
						}
						isBorder = new boolean[N][N];
						makeBorder(i, j, d1, d2);
						minGap = Math.min(minGap, calMaxGap(i, j, d1, d2));
					}
				}
			}
		}
	}
```

- 조건에 부합하는 시작점과 d1, d2를 구한다면 경선을 그어준다.
- 그은 경계선을 활용하여 인구수의 차이를 구하는 calMaxGap을 호출해준다.

```java
	private static void makeBorder(int i, int j, int d1, int d2) {
		for (int index = 0; index <= d1; index++) {
			isBorder[i + index][j - index] = true;
			isBorder[i + d2 + index][j + d2 - index] = true;
		}

		for (int index = 0; index <= d2; index++) {
			isBorder[i + index][j + index] = true;
			isBorder[i + d1 + index][j - d1 + index] = true;
		}
	}
```

- 정한 시작점과 d1,d2로, 경계선이라는 의미의 배열인 isBorder[][]을 바꾸어준다.

```java
private static int calMaxGap(int i, int j, int d1, int d2) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int sum = 0;
		int population = 0;

		population = calDistrict1(i, j, d1, d2);
		min = Math.min(min, population);
		max = Math.max(max, population);
		sum += population;

		population = calDistrict2(i, j, d1, d2);
		min = Math.min(min, population);
		max = Math.max(max, population);
		sum += population;

		population = calDistrict3(i, j, d1, d2);
		min = Math.min(min, population);
		max = Math.max(max, population);
		sum += population;

		population = calDistrict4(i, j, d1, d2);
		min = Math.min(min, population);
		max = Math.max(max, population);
		sum += population;

		//총 인구수에서 다른 네곳의 인구수를 빼주어 다섯번째 선거구의 인구수를 구한다.
		population = totalPopulation - sum;
		min = Math.min(min, population);
		max = Math.max(max, population);

		return max - min;
	}
```

- 1~4선거구의 인구수를 구한 후, 총 인구수에서 빼주어 5선거구의 인구수를 구해주었다.

## :black_nib: **Review**

- index를 움직이며 값을 찾는 문제는 변수명이나, 부등호등 사소한 실수를 하지 않게 완전 집중하는게 중요한거 같다..

# [16234] 인구 이동

## :pushpin: **Algorithm**

구현, 그래프 이론, 그래프 탐색, BFS, 시뮬레이션

## :round_pushpin: **Logic**

```java
while (true) {
	// 하루가 시작될 때 방문 처리 배열 초기화
	opened = new int[N][N];
	
	// 각 나라에서 인구 이동이 가능한지 확인해야 함
	// 이때 이미 방문하지 않은 나라에 대해서 이를 판단
	List<Integer> populations = new ArrayList<>();
	int countNumber = 1;
	for (int x = 0; x < N; x++) {
		for (int y = 0; y < N; y++) {
			if (opened[x][y] == 0) {
				// 방문하지 않은 나라라면 해당 나라에서 인구 이동이 가능한 나라 확인
				int dividedPopulation = markMove(x, y, countNumber);
				if (dividedPopulation != 0) {
					populations.add(dividedPopulation);
					countNumber++;
				}
			}
		}
	}
	
	// 이동할 인구가 없다면 종료
	if (populations.size() == 0) {
		break;
	}

	// 실제 인구 이동 실시
	for (int x = 0; x < N; x++) {
		for (int y = 0; y < N; y++) {
			if (opened[x][y] != 0) {
				country[x][y] = populations.get(opened[x][y] - 1);
			}
		}
	}
	
	day++;
}
```

- 각 나라별로 BFS를 통해 연합에 참여하는 나라와 총 인구수를 나눈 값을 저장해둔다.
- BFS 시에 해당 나라가 어떤 연합에 속하는지를 마킹해둔다.
- 이를 List에 저장해두고, 인구 이동을 실시하면서 인구 분배를 수행한다.

## :black_nib: **Review**

- 시뮬레이션 문제이기에, 주어진 로직을 순서대로 구현하면 되는 문제였다.
- 다만 연합을 구하고, 연합의 인구수를 분배하는 로직 구현을 어떻게 할 지가 관건이었다.

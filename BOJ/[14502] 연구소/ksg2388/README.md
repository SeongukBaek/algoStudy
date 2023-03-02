# [14502] 연구소 - Java

## :pushpin: **Algorithm**

BFS, 조합

## :round_pushpin: **Logic**

1. 먼저 맵에서 빈 공간의 벽이 세워질 공간 3개를 제외한 개수를 세어 값을 저장해둔다.
2. 순열을 이용하여 빈 공간 3개를 선택해 벽을 세운다.
3. 그 후 바이러스를 BFS를 이용해 전염시키며 한 공간이 전염될때마다 count 값을 1 증가시킨다.
4. 전염 시킬 수 있는 곳을 다 전염시키고나면 count에서 기존의 바이러스 개수를 빼준 값을 비교하여 가장 큰 값을 출력한다.

```java
    if (depth == 3) {
			int[][] copy = copyArray(map);
			maxNum = Math.max(maxNum, zeroNum - virusInfect(copy));
			return;
		}
```

- 벽이 3개 세워진다면 그때 바이러스 전염을 시작한다.

```java
public static int virusInfect(int[][] arr) {
	Queue<Virus> testQueue = new LinkedList<>();
	testQueue.addAll(queue);
	int count = 0;

	Virus virus = null;
	while (!testQueue.isEmpty()) {
		virus = testQueue.poll();

		// 사방탐색하며 바이러스 전염
		for (int i = 0; i < 4; i++) {
			int nx = virus.x + dx[i];
			int ny = virus.y + dy[i];

			// 맵 밖으로 나가지 않고 비어있는 공간인 경우
			if (nx >= 0 && ny >= 0 && nx < n && ny < m && arr[nx][ny] == 0) {
				testQueue.add(new Virus(nx, ny));
				arr[nx][ny] = 2;
			}
		}
		count++;
	}

	return count - queue.size();
}
```

- BFS를 이용하며 하나씩 전염될때마다 count 값을 증가시킨다.
- count값에서 기존의 바이러스 개수인 queue의 크기만큼 값을 빼준뒤에 그 값을 반환한다.

## :black_nib: **Review**

- 생각보다 쉬운 문제였지만 어떻게 하면 효율적으로 코드를 짤 수 있을지 고민을 많이 해볼 수 있는 문제였다.

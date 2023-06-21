# [20168] 골목 대장 호석 - 기능성

## :pushpin: **Algorithm**

백 트래킹

## :round_pushpin: **Logic**

```java
private static void findMinCost() {
	visited[A] = true;
	goRoad(A, 0, 0);
}

private static void goRoad(int start, int max, int sum) {
	if(sum > C) {
		return;
	}
	if(start == B) {
		answer = Math.min(max, answer);
		return;
	}
	for (int[] destination : roads[start]) {
		if (!visited[destination[0]]) {
			visited[destination[0]] = true;
			goRoad(destination[0], Math.max(max, destination[1]), sum += destination[1]);
			visited[destination[0]] = false;
		}
	}
}
```

- 상납할 요금이 부족하면 return한다.
- 목적지에 도착하면 가장 수치심이 적게 든 길로 왔는지 check 후 answer를 갱신해준다.

## :black_nib: **Review**

- 단순한 백트래킹 문제라 생각했으나..
- 부분 채점 문제였는데 12/18개 밖에 맞추지 못하였다.

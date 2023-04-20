# [2263] 트리의 순회

## :pushpin: **Algorithm**

Divide and Conquer

## :round_pushpin: **Logic**

```java
static boolean hasMinusCycle() {
	dis[1] = 0;

	for(int i = 1; i < N; i++) {
		for(int j = 1; j < road.length; j++) {
			for(edge next: road[j]) {
				if(dis[j] + next.time < dis[next.node]) {
					dis[next.node] = dis[j] + next.time;
				}
			}
		}
	}

	for(int j = 1; j < road.length; j++) {
		for(edge next: road[j]) {
			if(dis[j] + next.time < dis[next.node]) {
				return true;
			}
		}
	}
	return false;
}
```

- 음수 사이클이 존재한다면 문제에서 찾고자 하는 조건을 만족하는 경우이다.
- 음수 사이클이 생기는지 확인하여 생긴다면 true를 반환한다.

## :black_nib: **Review**

- 벨만 포드 알고리즘에 대한 이해도가 부족하다..

# [7576] 토마토

## :pushpin: **Algorithm**
BFS

## :round_pushpin: **Logic**
``` java
private static void dayPass() {
	while (!todayQueue.isEmpty()) {
		Point cur = todayQueue.poll();
		ripeTomato(cur.x, cur.y);
	}
	day++;
	// 하루가 지났으니 내일 큐를 오늘 큐로 넘김
	todayQueue.addAll(tomorrowQueue);
	// 내일 큐는 삭제
	tomorrowQueue.clear();
}
```
- 하루가 지날때마다 todayQueue에 있는 모든 위치들의 토마토를 익히고 거기서 추가되는 토마토들은 tomorrowQueue에 넣어준다. 그리고 하루가 지나면 tomorrowQueue에있는 값들을 todayQueue로 옮겨주고 tomorrowQueue는 비워준다.

## :black_nib: **Review**
- 큐 2개를 사용해 문제를 푸니 메모리 효율이 너무 떨어져서 안좋은 것 같다.
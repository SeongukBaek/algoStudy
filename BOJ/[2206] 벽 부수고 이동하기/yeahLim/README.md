# [2206] 벽 부수고 이동하기

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
public Point(int x, int y, int step, int smash) {
			this.x = x;
			this.y = y;
			this.step = step;
			this.smash = smash;
		}
```
- 객체를 생성해 각 좌표마다의 이동 개수와 벽 부신 횟수의 변수를 넣어주었다.

```java
/* i, j가 범위 내이고, 방문 안 한 경우 */
				if (0 <= i && i < n && 0 <= j && j < m && visited[i][j] > point.smash) {

					/* 벽 아닐 때 */
					if (map[i][j] == 0) {
						queue.add(new Point(i, j, point.step + 1, point.smash));
						visited[i][j] = point.smash; // 벽 한번 부셨으면 1, 아니면 0 넣어주기	

					/* 벽이고, 벽을 부순 횟수가 0일 때 */
					} else if (point.smash == 0) {
						queue.add(new Point(i, j, point.step + 1, point.smash + 1));
						visited[i][j] = point.smash; // 부셨으니까 1 넣어주기
					}
				}
```

- 벽을 부수거나, 부수지 않은 경우에 대해 모두 최단 경로를 구한다.

## :black_nib: **Review**
- 벽을 안 부신 경우와 부신 경우 모두 최단 경로를 구하는 부분이 어려웠다.
- 객체를 이용해 문제를 푸니까 더 가독성이 좋아졌다.

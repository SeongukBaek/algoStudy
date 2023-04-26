# [13549] 숨바꼭질 3

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
PriorityQueue<Subin> queue = new PriorityQueue<>();
		queue.add(new Subin(N, 0));
		dot[N] = 0;

        //수빈이의 초기 위치와 동생의 초기 위치가 같은 경우
		if (N == K) {
			return 0;
		}

		while (!queue.isEmpty()) {
			Subin cur = queue.poll();

            //순간이동하여 동생을 찾을 수 있는 경우
			if (cur.pos * 2 == K) {
				return cur.time;
			}

            //앞 or 뒤로 이동하여 동생을 찾을 수 있는 경우
			if (cur.pos + 1 == K || cur.pos - 1 == K) {
				return cur.time + 1;
			}

            //순간이동하여 이동 한 곳을 queue에 저장
			if (cur.pos * 2 <= 100000 && dot[cur.pos * 2] > cur.time) {
				queue.add(new Subin(cur.pos * 2, cur.time));
				dot[cur.pos * 2] = cur.time;
			}

            //앞으로 이동 한 곳을 queue에 저장
			if (cur.pos + 1 <= 100000 && dot[cur.pos + 1] > cur.time + 1) {
				queue.add(new Subin(cur.pos + 1, cur.time + 1));
				dot[cur.pos + 1] = cur.time + 1;
			}

            //뒤로 이동 한 곳을 queue에 저장
			if (cur.pos - 1 >= 0 && dot[cur.pos - 1] > cur.time + 1) {
				queue.add(new Subin(cur.pos - 1, cur.time + 1));
				dot[cur.pos - 1] = cur.time + 1;
			}
		}
		return -1;
	}
```

- 우선순위 큐에 현재 좌표와 걸린 시간을 저장하고, 걸린 시간 오름차순으로 정렬해주었다.
- 이미 더 적은 시간으로 방문 했던 곳은 pass해준다.
- 동생을 찾을때 까지 계속 BFS를 돌린다.

## :black_nib: **Review**

- '수빈이가 동생을 찾을 수 있는 가장 빠른 시간' 이라는 문구를 보고 바로 BFS를 떠올렸다.
- if문의 순서가 중요하다. 순간이동하여 이동하는 경우를 항상 우선시 해주어야 한다 !!

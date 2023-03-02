# [2383] 점심 식사시간

## :pushpin: **Algorithm**

구현, 우선순위 큐, 브루트포스

## :round_pushpin: **Logic**

1. 사람의 그룹을 2개로 나눈다.
2. 이후 마지막 사람이 계단을 내려가는 시간을 구한다.
3. 나눌 수 있는 모든 경우의 수 만큼 돌려본 뒤 최소값을 출력한다.

```java
  static class People {
		int x;
		int y;
		int useStairNum;
		int arrivalTime;

		People(int x, int y) {
			this.x = x;
			this.y = y;
		}

		// 이용하는 계단까지 도착하는 시간 계산
		void calculateArrivalTime() {
			this.arrivalTime = Math.abs(stairs.get(useStairNum).x - this.x)
					+ Math.abs(stairs.get(useStairNum).y - this.y);
		}
	}
```

- 클래스를 이용하여 사람의 정보에 위치와 이용하는 계단, 그 계단에 도착하는 시간을 계산하여 넣어둔다.

```java
// 계단에 도착하는 시간을 기준으로 정렬
		copyPeople.sort((o1, o2) -> {
			return o1.arrivalTime - o2.arrivalTime;
		});

		// 처음 계단에 도착하는 사람 시간을 넣음
		time = copyPeople.get(0).arrivalTime;
		// 계단을 다 내려가는 시간을 저장
		qList.get(copyPeople.get(0).useStairNum).add(time + 1 + stairs.get(copyPeople.get(0).useStairNum).level);
```

- 사람들을 계단에 도착하는 시간을 기준으로 정렬한다.
- 이후 우선순위큐를 이용하여 계단에 사람들을 도착순서대로 넣어주면서 계단에서 다 내려가는 시간을 계산하여 넣어준다.

```java
    // 계단을 내려갈 수 없는 경우
			else {
				int temp = qList.get(stairNum).poll();
				if (arrivalTime < temp) {
					time = temp;
					// 1분의 대기 시간이 필요 없음
					qList.get(stairNum).add(time + stairs.get(stairNum).level);
				}
				else {
					qList.get(stairNum).add(time + 1 + stairs.get(stairNum).level);
				}
			}
```

- 계단을 3명이 이용하고 있는 경우에 다음 사람의 도착시간과 계단을 가장먼저 내려가는 사람의 시간을 비교한다. 도착시간이 더 늦다면 대기시간이 필요 없기때문에 1분을 기다리지 않고 한 명이 계단을 내려오자마자 계단을 내려가기 시작한다.
- 도착했을때 계단에서 사람이 한명 빠진다면 1분을 기다린 후 계단을 내려간다.

## :black_nib: **Review**

- 어떤 자료구조를 이용할지 많이 고민했던 문제였다. 클래스를 잘 이용하니 생각보다 쉽게 문제가 해결되었다.
- 구현문제는 어떤 자료구조를 사용할지 결정하는게 중요한것같다. 다양한 문제를 풀어보면서 감을 익혀야겠다.

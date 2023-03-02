# [2383] 점심 식사시간

## :pushpin: **Algorithm**

구현, 시뮬레이션, 우선순위 큐

## :round_pushpin: **Logic**

```java
private static void selectStairAndMove(int depth, int peopleNum) {
	if (peopleNum == depth) {
		divideByStairAndCalDistance();
		min = Math.min(min, calFinishTime());
		return;
	}

	Person currentPerson = people.get(depth);
	currentPerson.usingStair = 1;
	selectStairAndMove(depth + 1, peopleNum);
	currentPerson.usingStair = 2;
	selectStairAndMove(depth + 1, peopleNum);
}
```

- 사람들이 어떤 계단을 사용할지 Backtracking으로 정해주었다.
- 만약 depth가 총 사람 수(peopleNum)과 같다면 divideByStairAndCalDistance() 메소드를 호출한다.
- 해당 경우의 최소값과 현재까지의 최소값을 비교하여 갱신해준다.

```java
private static void divideByStairAndCalDistance() {
	peopleUsingStair1 = new ArrayList<>();
	peopleUsingStair2 = new ArrayList<>();
	for (int i = 0; i < peopleNum; i++) {
		Person currentPerson = people.get(i);
		Stair usingStair = null;
		if (currentPerson.usingStair == 1) {
			usingStair = stairs.get(0);
			currentPerson.distance = Math.abs(currentPerson.x - usingStair.x)
					+ Math.abs(currentPerson.y - usingStair.y);
			peopleUsingStair1.add(currentPerson.distance);
		}
		if (currentPerson.usingStair == 2) {
			usingStair = stairs.get(1);
			currentPerson.distance = Math.abs(currentPerson.x - usingStair.x)
					+ Math.abs(currentPerson.y - usingStair.y);
			peopleUsingStair2.add(currentPerson.distance);
		}
	}
	Collections.sort(peopleUsingStair1);
	Collections.sort(peopleUsingStair2);
}
```

- 사람들과 계단간의 거리를 구해주고, 어떤 계단을 사용하는지에 따라 해당하는 계단의 ArrayList에 사람들과 계단간의 거리를 넣어준다.
- 오름차순으로 정렬해주어 시뮬레이션에 편리하게 하였다.

```java
private static int calFinishTime() {
	Queue<Integer> stair = new LinkedList<>();
	int wait = 0;
	int usingStair1Num = peopleUsingStair1.size();
	int stair1Len = stairs.get(0).length;
	int index = 0;
	int minute1 = 1;
	int arrive = 0;
	while (true) {
		// 계단을 다 내려갔다면 stair에 다시 삽입 안해줌
		int size = stair.size();
		for (int i = 0; i < size; i++) {
			int current = stair.poll() - 1;
			if (current != 0) {
				stair.add(current);
			}
			if (current == 0) {
				arrive++;
			}
		}
		while (stair.size() < MAX_MOVE && wait != 0) {
			stair.add(stair1Len);
			wait--;
		}
		if (arrive == usingStair1Num) {
			break;
		}
		while (index < usingStair1Num && peopleUsingStair1.get(index) == minute1) {
			wait++;
			index++;
		}
		minute1++;
	}

	int usingStair2Num = peopleUsingStair2.size();
	int stair2Len = stairs.get(1).length;
	wait = 0;
	index = 0;
	int minute2 = 1;
	arrive = 0;
	while (true) {
		// 계단을 다 내려갔다면 stair에 다시 삽입 안해줌
		int size = stair.size();
		for (int i = 0; i < size; i++) {
			int current = stair.poll() - 1;
			if (current != 0) {
				stair.add(current);
			}
			if (current == 0) {
				arrive++;
			}
		}
		while (stair.size() < MAX_MOVE && wait != 0) {
			stair.add(stair2Len);
			wait--;
		}
		if (arrive == usingStair2Num) {
			break;
		}
		while (index < usingStair2Num && peopleUsingStair2.get(index) == minute2) {
			wait++;
			index++;
		}
		minute2++;
	}
	return Math.max(minute1, minute2);
}
```

- 문제에서 설명한 그대로 차근차근 구현해주었다.

## :black_nib: **Review**

- 이런 구현문제는 나와의 싸움이다. 포기하지 말자

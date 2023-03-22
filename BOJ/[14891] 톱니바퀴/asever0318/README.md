# [14891] 톱니바퀴

## :pushpin: **Algorithm**

구현, 시뮬레이션

## :round_pushpin: **Logic**

```java
static void spinGear(int number, int direction) {

		// 먼저 돌려버리면 다음 바퀴에서 비교할 극 인덱스가 바뀌어버리므로 돌릴 방향 먼저 저장
		int[] spinInfo = new int[4];

		if(number == 1) { // 시작 톱니바퀴
			spinInfo[0] = direction; // 첫 번째 회전 톱니바퀴 방향 저장
			if(gears[0].state[gears[0].right] != gears[1].state[gears[1].left]) {
				// 1번 톱니바퀴와 2번 톱니바퀴의 극이 서로 다르면
				// 반대 방향으로 회전
				spinInfo[1] = findDirection(spinInfo[0]);
			}else {
				spinInfo[1] = 0;
			}

			if(gears[1].state[gears[1].right] != gears[2].state[gears[2].left]) {
				// 2번 톱니바퀴와 3번 톱니바퀴의 극이 서로 다르면
				spinInfo[2] = findDirection(spinInfo[1]);
			}else {
				spinInfo[2] = 0;
			}

			if(gears[2].state[gears[2].right] != gears[3].state[gears[3].left]) {
				// 3번 톱니바퀴와 4번 톱니바퀴의 극이 서로 다르면
				spinInfo[3] = findDirection(spinInfo[2]);
			}else {
				spinInfo[3] = 0;
			}
		}

		if(number == 2) { // 시작 톱니바퀴
			spinInfo[1] = direction;
			// 오른쪽
			if(gears[1].state[gears[1].right] != gears[2].state[gears[2].left]) {
				spinInfo[2] = findDirection(spinInfo[1]);
			}else {
				spinInfo[2] = 0;
			}

			if(gears[2].state[gears[2].right] != gears[3].state[gears[3].left]) {
				// 2번 톱니바퀴와 3번 톱니바퀴의 극이 서로 다르면
				spinInfo[3] = findDirection(spinInfo[2]);
			}else {
				spinInfo[3] = 0;
			}

			// 왼쪽
			if(gears[1].state[gears[1].left] != gears[0].state[gears[0].right]) {
				// 3번 톱니바퀴와 4번 톱니바퀴의 극이 서로 다르면
				spinInfo[0] = findDirection(spinInfo[1]);
			}else {
				spinInfo[0] = 0;
			}
		}
```

- 각 톱니바퀴의 회전방향을 저장해줄 배열 spinInfo를 만들었다.
- 시작 톱니바퀴와 회전 방향을 매개변수로 받아서 해당 톱니바퀴 기준 오른쪽으로 먼저 확인하고 왼쪽 톱니바퀴들의 극이 맞는 지 확인하고 spinInfo에 회전방향을 저장한다.

```java
// 찾아놓은 회전 방향에 맞게 회전 시키기
		for(int i = 0; i < 4; i++) {
			if(spinInfo[i] != 0) {
				gears[i].spin(spinInfo[i]);
			}
		}
```

- spinInfo에 저장된 톱니바퀴의 회전방향대로 톱니바퀴를 회전시킨다.

## :black_nib: **Review**

- 시작 톱니바퀴부터 바퀴회전 시키는 걸 반복문으로 처리하고싶었는데 못해서 노가다로 풀었다...

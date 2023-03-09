# [19237] 어른 상어

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
static Smell[][] sea;
static List<Shark> sharks;
static int[][][] priority; //상어 번호, 방향, 우선순위
```

주어진 격자 정보는 **냄새의 세기**와 **냄새 주인의 번호**를 저장하는 Smell클래스가 자료형인 이차원 배열을 만들어 저장했다.
<br/>
상어를 번호순으로 움직이기 위해 따로 리스트를 구현했다.<br/>
각 상어의 방향 우선순위정보는 int형 3차원 배열을 만들어 저장했다.</br>

```java
while(sharks.size() > 1 && time <= 1000) {
  //이동하기  -> 상어 잡아먹기
  move();
  //냄새 지우기
  reduceSmell();
  //냄새뿌리기
  spreadSmell();
  time++;
}
```

1초마다 동작하는 로직이다.

```java
private static void move() {

  for(int i = 0; i < sharks.size(); i++) {
    Shark shark = sharks.get(i);
    getNextMove(shark);

    //이동한 좌표에 다른 상어가 있는 경우
    if(sea[shark.x][shark.y].shark != 0) {
      sharks.remove(i--);
      continue;
    }

    sea[shark.x][shark.y].shark = shark.num;
  }

}
```

상어를 번호순으로 움직이고 있어 이동할 좌표에 상어가 있다면 현재 상어를 바로 삭제해주면 된다.

```java
for(int d = 0; d < DIRECTION_NUM; d++) {
			int dx = curX + dr[priority[shark.num-1][curD][d]];
			int dy = curY + dc[priority[shark.num-1][curD][d]];

			if(!arrayBoundsValidation(dx,dy)) {
				continue;
			}

			//내 냄새이고 아직 이동 좌표를 못찾았을 경우
			if(sea[dx][dy].num == shark.num && curX == shark.x && curY == shark.y) {
				shark.x = dx;
				shark.y = dy;
				shark.dir = priority[shark.num-1][curD][d];
				sea[curX][curY].shark = 0;
				continue;
			}

			if(sea[dx][dy].w != 0) {
				continue;
			}

			//아무 냄새도 없는 칸 찾은 경우
			shark.x = dx;
			shark.y = dy;
			shark.dir = priority[shark.num-1][curD][d];
			sea[curX][curY].shark = 0;
			return;
		}
```

상어가 움직일 좌표를 찾는 방법은 다음과 같다.<br/><br/>

- 주어진 방향 순으로 주변을 탐색한다.
- 주변에 냄새가 없는 좌표를 못구할 수 있으니, 현재 이동할 좌표를 못 찾고 내 냄새가 있는 구역이라면 다음 이동할 좌표로 지정하고 다음 방향을 탐색한다.
- 만약 냄새가 없는 구역이라면 바로 이동을 한다.

## :black_nib: **Review**

- 단순 구현으로 문제를 해결할 수 있어서 쉽게 풀었다.
  - 하지만 방향 우선순위를 저장하기 위해 어떤 자료구조를 사용할지 고민하는 시간을 많이 가졌다. 고민한 결과, 상어의 개수도 주어지고 방향도 4개로 개수가 정해져 있어 빠른 입출력을 위해 3차원배열로 표현했다.

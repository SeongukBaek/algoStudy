# [14891] 톱니바퀴

## :pushpin: **Algorithm**

구현, 시뮬레이션

## :round_pushpin: **Logic**

톱니바퀴를 char[8]에 담았다.<br/>
왼쪽 톱니바퀴가 돌아가는지 확인하려면 왼쪽 톱니바퀴 index 2번과 내 톱니바퀴 index 6번을 비교하면 된다. 오른쪽 톱니바퀴는 왼쪽과 반대로 확인해주면 된다.

```java
static void turnGear(int gearNum, int dir) {
		
		visited[gearNum] = true;
		
        //왼쪽 기어 회전
		if(gearNum != 0) {
            //맞닿은 톱니의 극이 다르고 방문하지 않았다면 기어 돌리기
			if(gears[gearNum][6] != gears[gearNum-1][2] && !visited[gearNum-1]) {
				turnGear(gearNum-1, dir*(-1));
			}
		}
		
        //오른쪽 기어 회전
		if(gearNum != GEAR_COUNT-1) {
			if(gears[gearNum][2] != gears[gearNum+1][6] && !visited[gearNum+1]) {
				turnGear(gearNum+1, dir*(-1));
			}
		}
		
		if(dir == 1) {
			turnRight(gearNum);
		}else if(dir == -1) {
			turnLeft(gearNum);
		}
	}
```
양쪽 톱니바퀴가 돌아가는지 확인한다.<br>
재귀를 통해서 톱니바퀴를 돌린다.

## :black_nib: **Review**

- 기어가 4개 밖에 없어서 재귀보단 for문으로 해결하면 더 코드도 간단하고 종료조건을 index로 제어할 수 있어서 좋다고 생각했는데 로직 모양은 재귀가 더 이뻐서 재귀로 풀었다.
- 그리고 기어를 하나씩 돌리기위해서 LinkedList 자료구조를 사용하려고 처음에 생각했는데 입력받는 부분이 조금 까다로워서 char배열로 입력받았다. 
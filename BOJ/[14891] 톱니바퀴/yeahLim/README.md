# [14891] 톱니바퀴

## :pushpin: **Algorithm**

구현, 시뮬레이션

## :round_pushpin: **Logic**

```java
static void checkPoles(int num, int dir) {
    int[] rotated; rotated = new int[4];
    rotated[num] = dir;

    // 왼쪽 톱니바퀴 확인
    for(int i=num; i>0; i--) {
        if(gear[i][6] != gear[i-1][2]) {
            rotated[i-1] = rotated[i]*-1;
        }
    }

    // 오른쪽 톱니바퀴 확인
    for(int i=num; i<3; i++) {
        if(gear[i][2] != gear[i+1][6]) {
            rotated[i+1] = rotated[i]*-1;
        }
    }

    // 극이 다른 톱니바퀴만 회전
    for(int i=0; i<4; i++) {
        if(rotated[i] == 0) continue;
        rotateGear(i, rotated[i]);
    }
}
```

- 톱니바퀴의 극들을 확인해서, rotated[i]은 i번 톱니바퀴의 회전 여부가 들어가 있다.
  - rotated[i] = 1이면 시계방향
  - rotated[i] = -1이면 반시계방향

```java
static void rotateGear(int num, int dir) {
```

- 톱니바퀴 번호와 방향 정보를 받아 회전한다.

## :black_nib: **Review**

- 회전 여부를 초반에 다 결정하고, 회전하는 것을 뒤늦게 깨달았다.
- 구현문제 재밌당!!!!!!!!!!

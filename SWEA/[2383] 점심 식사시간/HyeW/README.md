# [2383] 점심 식사시간

## :pushpin: **Algorithm**

구현, 시뮬레이션, 부분 집합

## :round_pushpin: **Logic**

```java
static void downStair(int r) {
    if (r == peopleSize) {
        min = Math.min(getTotalMovingTime(), min);
        return;
    }

    isSelected[r] = true;
    downStair(r + 1);
    isSelected[r] = false;
    downStair(r + 1);
}
```
부분 조합을 사용해서 isSelected가 true인 사람은 계단 1을 내려가고 false인 사람은 계단 2를 내려가도록 한다.<br/>

```java
static int getMovingTime(List<Move> stair) {
    int time = 0;
    int end = stair.size() - 1;

    while (stair.size() > 0) {
        if (stair.size() <= MAX_MOVING_COUNT) {
            end = stair.size() - 1;
            time += stair.get(end).room + stair.get(end).stair;
            break;
        }

        // 이동하기
        time += stair.get(0).room;
        moveAll(stair, stair.get(0).room);

        // 계단 내려가기
        time += stair.get(0).stair;
        moveAll(stair, stair.get(0).stair);

        // 다 내려간 사람 큐에서 제거
        arrivalFloor(stair);
    }

    return time + 1;
}

static class Move implements Comparable<Move> {
  int room;
  int stair;

  public Move(int room, int stair) {
    this.room = room;
    this.stair = stair;
  }

  @Override
  public int compareTo(Move o) {
    return this.room - o.room;
  }
}
```
계단을 내려가는 사람은 이동 거리에 따라 정렬이 되어 있다.<br/>
리스트 제일 앞에 있는 사람의 이동거리만큼 모든 사람을 이동시킨다.<br/>
이동을 다 한 후에 이후에 계단을 내려간다.<br/>
그리고 계단을 내려간 사람을 큐에서 제거하여 인덱스 이동을 따로 하지 않도록 구현했다.<br/>

```java
static void moveAll(List<Move> stair, int dis) {

    for (int i = 0; i < stair.size(); i++) {
        int gap = stair.get(i).room - dis;
        // room은 음수가 되면 안됨
        stair.get(i).room = (gap > 0) ? gap : 0;

        if (i >= MAX_MOVING_COUNT) {
            continue;
        }

        if (gap < 0) {
            // stair은 음수가 될 수 있음
            stair.get(i).stair -= Math.abs(gap);
        }
    }
}
```
첫번째가 이동하는 거리만큼 모든 사람들도 이동하도록 한다.<br/>
하지만 3명만 계단을 내려갈 수 있으므로 3번째이하만 계단이동을 할 수 있도록 코드를 작성했다.<br/>
이동거리는 음수가 안되고 계단을 내려간 거리가 0이되는 이유는 <br/>
계단은 음수이면 바로 큐에서 제거가 가능하지만 
이동거리는 음수가 되어도 계단을 내려가기위해 다시 moveAll()함수를 통해 이동거리를 확인하는 과정이 생기기 때문에 다르게 처리를 해주었다. 

## :black_nib: **Review**
- 계단을 어떻게 내려가도록 할지 고민했던 문제이다. 처음엔 한번에 여러명 도착하도록 하려고 엄청 복잡하게 생각했는데 오류가 있어 결국엔 단순하게 한명씩 내려보내는 방식으로 구현했다.
  - 정렬하는 것말고 단순 구현으로 문제를 해결했는데 구현문제에서 자료구조 활용을 잘하고 싶다. 스터디때 다른 사람들의 문제 풀이를 보면서 어떤 자료구조를 사용했는지에 집중하며 코드리뷰를 해야겠다.
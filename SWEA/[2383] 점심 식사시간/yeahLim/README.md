# [2383] 점심 식사시간

## :pushpin: **Algorithm**

구현, 시뮬레이션, 우선순위 큐

## :round_pushpin: **Logic**

```java
static void combination(int start, int count, int n, boolean[] selected) {
        if(count == n) {
            goToStairEntrance(selected);
            return;
        }

        for(int i=start; i<n; i++) {
            if(!selected[i]) {
                selected[i] = true;
                combination(i+1, count+1, n, selected);
                selected[i] = false;
            }

        }
    }
```

- 조합으로 선택된 사람은 stair[0]을 이용하고, 선택되지 않는 사람은 stair[1]을 이용하도록 했다.

```java
static void goToStairEntrance(boolean[] selected) {
        for(int i=0; i<people.size(); i++) {

            Person p = people.get(i);

            // stair[0]까지의 이동 시간
            if(selected[i]) {
                p.time = Math.abs(p.x - stairs[0].x) + Math.abs(p.y - stairs[0].y);
                stairs[0].ppl.add(p);
            }

            // stair[1]까지의 이동 시간
            else {
                p.time = Math.abs(p.x - stairs[0].x) + Math.abs(p.y - stairs[0].y);
                stairs[1].ppl.add(p);
            }
        }
        goDownStairs(selected);
    }
```
- 입구까지 가는 시간을 구한 후, 계단에서 내려가는 시간을 구한다.

## :black_nib: **Review**
- 계단에서 내려가는 시간 다시 구할 예정!
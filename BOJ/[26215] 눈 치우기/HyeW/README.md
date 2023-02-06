# [26215] 눈 치우기

## :pushpin: **Algorithm**

우선순위 큐

## :round_pushpin: **Logic**

눈이 쌓인 집들이 주어지고 집앞에 쌓인 눈을 다 치워야 한다.<br/>
1분에 두 집의 눈을 각각 1씩 치우거나 한 집의 눈을 1만큼 치울 수 있다.<br/>

1분에 눈을 2번 치우는 횟수를 최대로 하기 위해선 눈이 많은 집 순으로 정렬하여 치우면 된다.

```java
PriorityQueue<Integer> snow = new PriorityQueue<>(Collections.reverseOrder());

/*
* input값 받는 코드
*/

int cnt = 0;
while(!snow.isEmpty()) {

    if(snow.size() == 1){
        cnt += snow.poll();
        break;
    }
    if(cnt > 1440)
        break;

    int a = snow.poll();
    int b = snow.poll();

    cnt += b;

    if(a != b)
        snow.add(a-b);
}

```
우선순위 큐에 눈의 양을 받아 2개씩 큐에서 뽑아 1만큼 빼고 다시 넣어준다.<br/>
만약 큐의 길이가 1이면 눈의 양이 시간이기 때문에 한번에 시간을 계산한다.<br/>
계산하다가 시간이 1440분이 넘으면 눈치우기를 종료하고 답을 출력한다.

## :black_nib: **Review**
- 집 1,2,3 번에 각각 `1 2 5`만큼의 눈이 쌓여 있을 경우, 1, 2번집 먼저 치우면 총 6번을 치워야 하고 2,3번 집을 먼저 치우면 총 5번 치우면 된다.
    - 한 번의 행동에 눈을 2번 치우는 횟수를 최대로 하기 위해선 눈이 많은 집 순으로 정렬하여 치우면 된다.
    - 눈을 치울 때마다 값이 변경되어 정렬을 해야 하기 때문에 우선순위 큐를 사용했다.

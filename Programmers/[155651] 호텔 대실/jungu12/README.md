# [155651] 호텔 대실

## :pushpin: **Algorithm**

구현, 우선순위 큐

## :round_pushpin: **Logic**

```java
class bookInfo {
    int time;
    int type;

    bookInfo(int time, int type) {
        this.time = time;
        this.type = type;
    }
}
```

- 호텔 대실에 대한 정보를 class로 관리하였다.
- type = 1은 입실, type = 2는 퇴실이다.

```java
Queue<bookInfo> pq = new PriorityQueue<>((o1, o2) -> {
    if (o1.time == o2.time) {
        return o2.type - o1.type;
    }
    return o1.time - o2.time;
});

for (int i = 0; i < book_time.length; i++) {
    String startTime = book_time[i][0];
    String endTime = book_time[i][1];
    //입실 정보
    pq.add(new bookInfo(convertToMinute(startTime), 1));
    //퇴실 정보
    pq.add(new bookInfo(convertToMinute(endTime) + 10, 2));
}
```

- PriorityQueue에 호텔 입퇴실 정보를 오름차순으로 정렬하였다.
- 퇴실 시간은 청소 시간을 포함하여 +10분을 해주었다.
- 시간 정보가 같다면 퇴실 시간이 앞에 오도록 정렬하였다.

```java
while (!pq.isEmpty()) {
    bookInfo cur = pq.poll();
    //입실에 대한 정보라면
    if (cur.type == 1) {
        //현재 사용중인 방 갯수++
        count++;
        //최소 객실 수 갱신
        minCount = Math.max(count, minCount);
        continue;
    }
    //퇴실에 대한 정보라면
    if (cur.type == 2) {
        //현재 사용중인 방 갯수--
        count--;
    }
}
```

- 우선 순위 큐에서 하나씩 poll 받아서 현재 사용중인 방 갯수와 최소 객실 수를 갱신해주었다.

## :black_nib: **Review**

- 간단하게 풀 수 있는 구현 문제였다 ~

# [11000] 강의실 배정

## :pushpin: **Algorithm**

우선순위 큐

## :round_pushpin: **Logic**

강의 시작시간 순으로 정렬하여 강의실을 찾아준다.<br/>
수업이 빠른 순으로 강의실을 배정하기 때문에 강의실은 마치는 시간만 알면된다. 그래서 강의실은 수업종료시간으로 오름차순 정렬한다.<br/>

```java
PriorityQueue<Lecture> lecture = new PriorityQueue<>();
PriorityQueue<Integer> room = new PriorityQueue<>();

for(int i = 0; i < n; i++) {
    st = new StringTokenizer(br.readLine());
    int start = Integer.parseInt(st.nextToken());
    int end = Integer.parseInt(st.nextToken());
    lecture.add(new Lecture(start, end));
}

room.add(lecture.poll().end);
while(!lecture.isEmpty()){
    Lecture cur = lecture.poll();

    if(room.peek() <= cur.start)
        room.poll();

    room.add(cur.end);
}
```
가장 빠른 수업을 `lecture`에서 꺼내 수업의 시작시간과 `room`에 담긴 종료시간을 비교한다.<br/> 마치는 시간보다 시작시간이 느리면 해당 강의실에 수업을 배정한다. 만약 모든 강의실에 마치는 시간보다 수업 시작시간이 빠르다면 새 강의실을 만들어 배정시킨다.


## :black_nib: **Review**
- 시작시간이 빠른 순, 마치는 시간이 빠른 순으로 구해야 하고 가장 작은 수만 뽑아 사용하는 것이기 때문에 삽입, 삭제, 조회가 빠른 우선순위 큐를 사용했다.
    - 강의실이 비어있는지 확인하기 위해선 마치는 시간만 알면 되기에 정수를 타입으로 갖는 우선순위 큐를 만들었다.

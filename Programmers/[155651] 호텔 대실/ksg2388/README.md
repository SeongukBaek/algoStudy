# [155651] 호텔 대실

## :pushpin: **Algorithm**

그리디, 우선순위 큐

## :round_pushpin: **Logic**

```javascript
const change_time = book_time.map((time) => {
  const startTime = time[0].split(':').map(Number);
  const endTime = time[1].split(':').map(Number);
  return [startTime[0] * 60 + startTime[1], endTime[0] * 60 + endTime[1]];
});
```

- 시작 시간별로 정렬을 하기위해 문자열을 분으로 변경했다.

```javascript
change_time.forEach((time) => {
  if (!q.length) {
    q.push(time[1] + 10);
  }

  // 대실 가능한 빈 방이 없는 경우
  else if (q[q.length - 1] > time[0]) {
    q.push(time[1] + 10);
  } else {
    // 대실 가능한 경우
    while (q.length) {
      if (q[q.length - 1] <= time[0]) {
        q.pop();
      } else break;
    }
    q.push(time[1] + 10);
  }
  q.sort((a, b) => b - a);
  answer = Math.max(answer, q.length);
});
```

- 다음 사람의 입실 시간과 가장 이른 퇴실 시간을 비교하여 입실 시간이 더 늦다면 새로운 방을 배정해준다.

## :black_nib: **Review**

- 예전에 비슷한 문제를 풀어서 우선순위 큐를 이용해야겠다고 생각했다.
- 문자열이 정렬이 되지 않아서 일일히 분을 시간으로 치환해서 정렬을 했다.
- 문자열을 다루는데 시간을 너무 잡아먹은 것 같다...

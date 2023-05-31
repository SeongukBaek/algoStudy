# [2056] 작업

## :pushpin: **Algorithm**

위상 정렬

## :round_pushpin: **Logic**

```javascript
while (queue.length) {
  let x = queue.shift();
  for (let next of graph[x]) {
    indegrees[next]--;
    finish_time[next] = Math.max(
      finish_time[next],
      finish_time[x] + time[next]
    );
    if (indegrees[next] === 0) queue.push(next);
  }
}
```

- 선행 작업이 끝났다면 해당 작업의 indegrees를 1낮춘다.
- indegrees가 0이 된 작업은 실행할 수 있는 작업이므로 `queue`에 넣어준다.

## :black_nib: **Review**

- 위상정렬 문제를 처음 접해봐서 어떻게 풀어야할지 감이 잡히지 않아 위상정렬부터 공부했다...
- 비슷한 유형의 문제를 더 풀어서 완벽히 이해할 수 있도록 해야갰다.

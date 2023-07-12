function solution(book_time) {
  let answer = 1;
  let q = [];

  const change_time = book_time.map((time) => {
    const startTime = time[0].split(':').map(Number);
    const endTime = time[1].split(':').map(Number);
    return [startTime[0] * 60 + startTime[1], endTime[0] * 60 + endTime[1]];
  });

  change_time.sort((a, b) => {
    return a[0] - b[0];
  });

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

  return answer;
}

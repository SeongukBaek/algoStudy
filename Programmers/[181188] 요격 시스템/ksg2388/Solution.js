function solution(targets) {
  targets.sort((e1, e2) => {
    return e1[1] - e2[1];
  });
  let start = targets[0][0],
    end = targets[0][1];
  let count = 1;

  for (let i = 1; i < targets.length; i++) {
    // 범위 밖인 경우
    if (end <= targets[i][0]) {
      start = targets[i][0];
      end = targets[i][1];
      count++;
      continue;
    }
  }
  return count;
}

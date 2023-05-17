function solution(scores) {
  let target = scores[0];
  let grade = 1;
  let num = 0;
  let curSum = 0;
  scores.sort((o1, o2) => {
    return o2[0] + o2[1] - (o1[0] + o1[1]);
  });

  curSum = scores[0][0] + scores[0][1];

  if (scores[0][0] === target[0] && scores[0][1] === target[1]) {
    return 1;
  }

  // 완호 등수 찾기
  for (let i = 1; i < scores.length; i++) {
    let isIncentive = true;
    // 인센티브 대상인지 확인
    for (let j = 0; j < i; j++) {
      if (scores[i][0] < scores[j][0] && scores[i][1] < scores[j][1]) {
        isIncentive = false;
        break;
      }
    }
    if (!isIncentive) {
      continue;
    }
    if (scores[i][0] === target[0] && scores[i][1] === target[1]) {
      if (curSum !== scores[i][0] + scores[i][1]) {
        num++;
      }
      grade += num;
      return grade;
    }

    num++;
    if (curSum !== scores[i][0] + scores[i][1]) {
      grade += num;
      num = 0;
    }
  }

  return -1;
}

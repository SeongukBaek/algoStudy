function solution(n, info) {
  var answer = [-1];
  const target = Array.from({ length: 11 }, () => 0);
  let maxPoint = 0;

  const checkCombination = (start, depth) => {
    if (depth === n) {
      let point = calcPoint();
      if (point <= 0) {
        return;
      }
      // 점수 계산
      if (maxPoint < point) {
        answer = target.slice();
        maxPoint = point;
        return;
      }

      if (maxPoint === point) {
        for (let i = 11; i >= 0; i--) {
          if (target[i] < answer[i]) {
            return;
          }
          if (target[i] > answer[i]) {
            answer = target.slice();
            return;
          }
        }
      }
      return;
    }

    for (let i = start; i < 11; i++) {
      target[i]++;
      checkCombination(i, depth + 1);
      target[i]--;
    }
  };

  const calcPoint = () => {
    let point = target.reduce((acc, cur, idx) => {
      // 라이언이 점수를 얻는 경우
      if (cur > info[idx]) {
        return (acc += 10 - idx);
      }

      // 어피치가 점수를 얻는 경우
      if (info[idx] > 0 && cur <= info[idx]) {
        return (acc -= 10 - idx);
      }

      return acc;
    }, 0);

    return point;
  };

  checkCombination(0, 0);

  return answer;
}

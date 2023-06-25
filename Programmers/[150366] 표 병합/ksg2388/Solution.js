const sell = Array.from({ length: 2501 }, () => 'EMPTY'); // 각 셀의 값 저장
const merge = Array.from({ length: 2501 }, () => 0); // 병합 정보 표시
const answer = [];

function solution(commands) {
  for (let i = 1; i < 2501; i++) {
    merge[i] = i;
  }

  commands.forEach((command) => {
    const [cmd, ...reminder] = command.split(' ');

    if (cmd === 'UPDATE') {
      updateSell(reminder);
      return;
    }
    if (cmd === 'MERGE') {
      mergeSell(reminder);
      return;
    }
    if (cmd === 'UNMERGE') {
      unmergeSell(reminder);
      return;
    }
    if (cmd === 'PRINT') {
      printSell(reminder);
    }
  });
  return answer;
}

function convertNum(x, y) {
  return 50 * (Number(x) - 1) + Number(y);
}

function updateSell(reminder) {
  if (reminder.length === 2) {
    const [value1, value2] = reminder;
    for (let i = 0; i < 2501; i++) {
      if (sell[i] === value1) sell[i] = value2;
    }
    return;
  }

  const [r, c, value] = reminder;
  const idx = merge[convertNum(r, c)];
  sell[idx] = value;
}

function mergeSell(reminder) {
  const [x1, y1, x2, y2] = reminder;
  const num1 = merge[convertNum(x1, y1)];
  const num2 = merge[convertNum(x2, y2)];
  let value = sell[num2];
  if (sell[num1] !== 'EMPTY') {
    value = sell[num1];
  }

  for (let i = 1; i < 2501; i++) {
    if (merge[i] === num2) {
      merge[i] = num1;
      sell[i] = 'EMPTY';
    }
  }
  sell[num1] = value;
}

function unmergeSell(reminder) {
  const [r, c] = reminder;
  const num = convertNum(r, c);
  const idx = merge[num];
  const value = sell[idx];

  for (let i = 1; i < 2501; i++) {
    if (merge[i] === idx) {
      merge[i] = i;
      sell[i] = 'EMPTY';
    }
  }
  sell[num] = value;
}

function printSell(reminder) {
  const [r, c] = reminder;
  let idx = merge[convertNum(r, c)];
  answer.push(sell[idx]);
}

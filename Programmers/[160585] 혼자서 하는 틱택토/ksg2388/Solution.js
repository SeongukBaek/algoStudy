function solution(board) {
  let answer = -1;
  let oCount = 0;
  let xCount = 0;

  board.forEach((line) => {
    line.split('').forEach((e) => {
      if (e === 'O') {
        oCount += 1;
        return;
      }
      if (e === 'X') {
        xCount += 1;
      }
    });
  });

  // O의 개수가 X와 같거나 1개 더 많지 않은 경우
  let diff = Math.abs(oCount - xCount);

  if (diff > 1) {
    return 0;
  }

  if (xCount > oCount) {
    return 0;
  }

  // 한 줄이 완성될 수 없는 경우
  if (oCount < 3 || xCount < 3) {
    return 1;
  }

  // 한 줄이 완성되었는데 게임이 종료되지 않은 경우
  if (isGameEnd(board, oCount, xCount)) {
    return 0;
  }

  return 1;
}

function isGameEnd(board, oCount, xCount) {
  let oEnd = false;
  let xEnd = false;
  // 선공이 게임 종료 조건 만족했는지 검사
  // 가로 검사
  for (let i = 0; i < 3; i++) {
    if (board[i] === 'OOO') {
      oEnd = true;
      break;
    }
  }
  // 대각 검사
  if (board[0].charAt(0) + board[1].charAt(1) + board[2].charAt(2) === 'OOO') {
    oEnd = true;
  }
  if (board[0].charAt(2) + board[1].charAt(1) + board[2].charAt(0) === 'OOO') {
    oEnd = true;
  }
  // 세로 검사
  for (let i = 0; i < 3; i++) {
    if (
      board[0].charAt(i) + board[1].charAt(i) + board[2].charAt(i) ===
      'OOO'
    ) {
      oEnd = true;
      break;
    }
  }

  // 후공이 게임 종료 조건 만족했는지 검사
  // 가로 검사
  for (let i = 0; i < 3; i++) {
    if (board[i] === 'XXX') {
      xEnd = true;
      break;
    }
  }
  // 대각 검사
  if (board[0].charAt(0) + board[1].charAt(1) + board[2].charAt(2) === 'XXX') {
    xEnd = true;
  }
  if (board[0].charAt(2) + board[1].charAt(1) + board[2].charAt(0) === 'XXX') {
    xEnd = true;
  }
  // 세로 검사
  for (let i = 0; i < 3; i++) {
    if (
      board[0].charAt(i) + board[1].charAt(i) + board[2].charAt(i) ===
      'XXX'
    ) {
      xEnd = true;
      break;
    }
  }
  // 둘 다 이긴 경우
  if (xEnd && oEnd) {
    return true;
  }
  // 선공만 이긴 경우
  if (oEnd && oCount <= xCount) {
    return true;
  }
  // 후공만 이긴 경우
  if (xEnd && oCount != xCount) {
    return true;
  }
  return false;
}

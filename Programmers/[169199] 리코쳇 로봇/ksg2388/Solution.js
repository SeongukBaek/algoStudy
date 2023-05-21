let startX = 0;
let startY = 0;
let h = 0;
let w = 0;
let dx = [-1, 0, 0, 1];
let dy = [0, -1, 1, 0];
let count = 0;

function solution(board) {
  board = board.map((line) => line.split(''));
  h = board.length;
  w = board[0].length;

  const visited = Array.from({ length: h }, () => Array(w).fill(false));
  findStartPoint(board);

  findOptimalRoot(board, visited);

  return count;
}

function findStartPoint(board) {
  for (let i = 0; i < h; i++) {
    for (let j = 0; j < w; j++) {
      if (board[i][j] === 'R') {
        startX = i;
        startY = j;
        board[i][j] = '.';
        return;
      }
    }
  }
}

function findOptimalRoot(board, visited) {
  const queue = [];
  queue.push([startX, startY]);
  visited[startX][startY] = true;

  while (queue.length > 0) {
    let qSize = queue.length;
    let isMove = false;
    count++;
    for (let i = 0; i < qSize; i++) {
      let [curX, curY] = queue.shift();

      // 4방향으로 이동
      for (let i = 0; i < 4; i++) {
        let nx = curX + dx[i];
        let ny = curY + dy[i];
        // 이동할 수 없는 경우 무시
        if (isMapOut(nx, ny) || board[nx][ny] === 'D') {
          continue;
        }
        // 벽에 부딛힐 때까지 이동
        while (true) {
          if (
            isMapOut(nx + dx[i], ny + dy[i]) ||
            board[nx + dx[i]][ny + dy[i]] === 'D'
          ) {
            break;
          }
          nx += dx[i];
          ny += dy[i];
        }
        // 방문한 적이 있는 경우 무시
        if (visited[nx][ny]) {
          continue;
        }
        if (board[nx][ny] === 'G') {
          return;
        }
        isMove = true;
        visited[nx][ny] = true;
        queue.push([nx, ny]);
      }
    }
    // 움직일 수 있는 경우가 없는 경우 종료
    if (!isMove) {
      count = -1;
      return;
    }
  }
}

function isMapOut(x, y) {
  return x < 0 || y < 0 || x >= h || y >= w;
}

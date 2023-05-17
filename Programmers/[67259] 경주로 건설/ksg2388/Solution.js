const dx = [-1, 0, 0, 1];
const dy = [0, -1, 1, 0];
let size = 0;

function solution(board) {
  var answer = 0;
  size = board.length;
  const visited = Array(size)
    .fill()
    .map(() =>
      Array(size)
        .fill()
        .map(() => Array(4).fill(Infinity))
    );
  findMinFee(board, visited);

  return Math.min(...visited[size - 1][size - 1]);
}

function findMinFee(board, visited) {
  const queue = [];
  // 처음 시작 시 좌우 벽 확인
  if (board[0][1] === 0) {
    queue.push({ x: 0, y: 1, dir: 2, price: 100 });
    visited[0][1][2] = 100;
  }
  if (board[1][0] === 0) {
    queue.push({ x: 1, y: 0, dir: 3, price: 100 });
    visited[1][0][3] = 100;
  }

  while (queue.length) {
    let qSize = queue.length;
    for (let i = 0; i < qSize; i++) {
      let { x, y, dir, price } = queue.pop();
      // 갈 수 있는 방향 확인
      for (let j = 0; j < 4; j++) {
        let nx = x + dx[j];
        let ny = y + dy[j];

        // 맵밖이거나 벽인 경우 무시
        if (isMapOut(nx, ny) || board[nx][ny] === 1) {
          continue;
        }
        // 방향 그대로 갈 수 있는 경우
        if (eqlDir(dir, j)) {
          if (visited[nx][ny][j] >= price + 100) {
            queue.push({ x: nx, y: ny, dir: j, price: price + 100 });
            visited[nx][ny][j] = price + 100;
            continue;
          }
        }
        // 방향을 틀어야하는 경우
        if (visited[nx][ny][j] >= price + 600) {
          queue.push({ x: nx, y: ny, dir: j, price: price + 600 });
          visited[nx][ny][j] = price + 600;
        }
      }
    }
  }
}

function isMapOut(x, y) {
  return x < 0 || y < 0 || x >= size || y >= size;
}

function eqlDir(dir1, dir2) {
  if (dir1 === 0 || dir1 === 3) {
    if (dir2 === 0 || dir2 === 3) {
      return true;
    }
  }
  if (dir1 === 1 || dir1 === 2) {
    if (dir2 === 1 || dir2 === 2) {
      return true;
    }
  }
  return false;
}

const dx = [1, 0, 0, -1];
const dy = [0, -1, 1, 0];
let width = 0;
let height = 0;

function solution(maps) {
  maps = maps.map((line) => line.split(''));
  let answer = [];
  width = maps.length;
  height = maps[0].length;

  for (let i = 0; i < width; i++) {
    for (let j = 0; j < height; j++) {
      // 바다가 아닌 경우
      if (maps[i][j] !== 'X') {
        answer.push(findIsLand(maps, i, j));
      }
    }
  }

  if (answer.length) {
    answer.sort((a, b) => a - b);
  } else {
    answer.push(-1);
  }

  return answer;
}

function findIsLand(map, x, y) {
  const queue = [];
  let count = +map[x][y];
  map[x][y] = 'X';
  queue.push([x, y]);

  while (queue.length) {
    const [cx, cy] = queue.shift();

    for (let i = 0; i < 4; i++) {
      const nx = cx + dx[i];
      const ny = cy + dy[i];

      // 주변에 무인도가 없는 경우 무시
      if (isMapOut(nx, ny) || map[nx][ny] === 'X') continue;
      // 무인도가 있는 경우 큐에 추가
      count += +map[nx][ny];
      map[nx][ny] = 'X';
      queue.push([nx, ny]);
    }
  }
  return count;
}

function isMapOut(x, y) {
  return x < 0 || y < 0 || x >= width || y >= height;
}

function solution(dirs) {
  let cur = [0, 0];
  let coordinates = {
    x: 0,
    y: 0,
  };

  let result = dirs.split('').reduce((acc, dir) => {
    let [x, y] = cur;

    if (dir === 'R') {
      if (y < 5) {
        acc.add(`${x},${y + 0.5}`);
        y += 1;
      }
    }
    if (dir === 'U') {
      if (x < 5) {
        acc.add(`${x + 0.5},${y}`);
        x += 1;
      }
    }
    if (dir === 'D') {
      if (x > -5) {
        acc.add(`${x - 0.5},${y}`);
        x -= 1;
      }
    }
    if (dir === 'L') {
      if (y > -5) {
        acc.add(`${x},${y - 0.5}`);
        y -= 1;
      }
    }
    cur = [x, y];

    return acc;
  }, new Set()).size;

  return result;
}

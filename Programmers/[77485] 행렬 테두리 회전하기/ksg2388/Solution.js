function solution(rows, columns, queries) {
  let answer = [];
  let num = 1;
  const map = [...Array(rows)].map((v, r) =>
    [...Array(columns)].map((v, c) => r * columns + (c + 1))
  );

  queries.forEach((query, idx) => {
    answer.push(
      rotate(map, query[0] - 1, query[1] - 1, query[2] - 1, query[3] - 1)
    );
  });

  return answer;
}

function rotate(map, x1, y1, x2, y2) {
  let temp = map[x2][y2];
  let list = [];
  list.push(temp);

  // 반시계로 돌면서 값 전달
  // 위 -> 아래
  for (let i = x2; i > x1; i--) {
    list.push(map[i][y2]);
    map[i][y2] = map[i - 1][y2];
  }
  // 왼쪽 -> 오른쪽
  for (let i = y2; i > y1; i--) {
    list.push(map[x1][i]);
    map[x1][i] = map[x1][i - 1];
  }
  // 아래 -> 위
  for (let i = x1; i < x2; i++) {
    list.push(map[i][y1]);
    map[i][y1] = map[i + 1][y1];
  }
  // 오른쪽 -> 왼쪽
  for (let i = y1; i < y2 - 1; i++) {
    list.push(map[x2][i]);
    map[x2][i] = map[x2][i + 1];
  }
  list.push(map[x2][y2 - 1]);
  map[x2][y2 - 1] = temp;

  return Math.min(...list);
}

# [49994] 방문 길이

## :pushpin: **Algorithm**

그래프 탐색

## :round_pushpin: **Logic**

```javascript
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
```

- 주어진 방향으로 이동하면서 방문한 선분에 정보를 표시하기 위해서 0.5칸 이동한 정보를 Set에 저장한다.

## :black_nib: **Review**

- 선분의 방문 정보를 표현해야하는데 그걸 어떻게 표현할지가 중요한 문제였다.

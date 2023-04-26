# [13549] 숨바꼭질 3

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```javascript
// 순간 이동
if (p * 2 <= 100000 && !visited[p * 2]) {
  position.unshift([p * 2, dist]);
  visited[p * 2] = true;
}

// 이동
if (p + 1 <= 100000 && !visited[p + 1]) {
  position.push([p + 1, dist + 1]);
  visited[p + 1] = true;
}
if (p - 1 >= 0 && !visited[p - 1]) {
  position.push([p - 1, dist + 1]);
  visited[p - 1] = true;
}
```

- 순간이동을 하는 경우는 시간이 들지 않기 때문에 배열의 가장 앞에 삽입해준다.
- 이동을 하는 경우는 현재 시간 + 1 값을 한 후 배열에 넣어준다.

## :black_nib: **Review**

- 순간이동 처리를 어떻게 할 것인지가 중요한 문제였다.
- BFS를 이용하여 간단하게 해결할 수 있었다.

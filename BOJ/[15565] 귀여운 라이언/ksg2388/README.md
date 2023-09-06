# [15565] 귀여운 라이언

## :pushpin: **Algorithm**

투포인터, 슬라이딩 윈도우

## :round_pushpin: **Logic**

```javascript
function findMinLength(start, end) {
  // K개 이상을 만족하는 집합이 없는 경우
  if (end === -1) {
    return -1;
  }

  // 다음 구간 탐색
  while (end < list.length) {
    if (list[start] === 1) {
      count--;
    }
    start += 1;
    end += 1;
    if (list[end] === 1) count++;
    // 구간 집합 안에 K개의 라이언 인형이 있는 경우
    if (count === K) {
      // 범위 재조정
      if (list[start] === 2) {
        while (list[start] !== 1 && start < end) {
          start++;
        }
      }
      minLength = end - start + 1;
    }
  }

  return minLength;
}
```

- start와 end를 한칸씩 뒤로 밀면서 라이언의 개수를 확인한다.
- 라이언의 개수가 k개가 되는 경우 start가 라이언이 아니라면 start가 라이언이 될때까지 범위를 좁힌다.
- 이후 end가 끝에 도달하기전까지 이 과정을 반복한다.

## :black_nib: **Review**

- 슬라이딩 윈도우와 투 포인터를 같이 사용해 해결할 수 있는 문제였다.

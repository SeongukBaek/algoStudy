# [68646] 풍선 터트리기

## :pushpin: **Algorithm**

구현, 메모이제이션

## :round_pushpin: **Logic**

```javascript
for (let i = 1; i < a.length; i++) {
  leftMin[i] = Math.min(leftMin[i - 1], a[i]);
  rightMin[a.length - i - 1] = Math.min(
    rightMin[a.length - i],
    a[a.length - i]
  );
}

a.forEach((balloon, index) => {
  if (balloon <= leftMin[index] || balloon <= rightMin[index]) answer++;
});
```

- 현재 인덱스에서 왼쪽에서의 가장 최소값과 오른쪽에서의 가장 최소값을 저장하는 배열을 만들어 저장해둔다.
- 모든 배열을 돌면서 왼쪽과 오른쪽 중 하나라도 자신보다 큰 값이 있는 경우는 `answer`을 1증가 시킨다.

## :black_nib: **Review**

- 방법은 쉽게 떠올렸지만 메모이제이션을 사용하는 법이 바로 떠오르지 않아 시간초과가 났었다.
- 간단한 코드 몇줄로 시간이 엄청나게 줄어든다는건 신기하다...

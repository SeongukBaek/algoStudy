# [11279] 최대 힙

## :pushpin: **Algorithm**

힙

## :round_pushpin: **Logic**

```javascript
const h_push = (num) => {
  heap[++idx] = num;
  for (let i = idx; i > 1; i = Math.floor(i / 2)) {
    // 부모 노드가 자신보다 작은 경우 swap
    if (heap[Math.floor(i / 2)] < heap[i]) {
      [heap[i], heap[Math.floor(i / 2)]] = [heap[Math.floor(i / 2)], heap[i]];
    } else break;
  }
};
```

- 현재 힙의 가장 마지막 요소에 삽입을 하고 부모 노드와 값을 비교하면서 위치를 찾아준다.

```javascript
const h_pop = () => {
  if (idx === 0) return 0;
  const num = heap[1];
  heap[1] = heap[idx--];

  // 힙 다시 정렬
  for (let i = 1; i * 2 <= idx; ) {
    if (heap[i] > heap[i * 2] && heap[i] > heap[i * 2 + 1]) {
      break;
    }
    // 왼쪽 자식이 더 큰 경우
    else if (heap[i * 2] > heap[i * 2 + 1]) {
      [heap[i], heap[i * 2]] = [heap[i * 2], heap[i]];
      i = i * 2;
    }
    // 오른쪽 자식이 더 큰 경우
    else {
      [heap[i], heap[i * 2 + 1]] = [heap[i * 2 + 1], heap[i]];
      i = i * 2 + 1;
    }
  }
  return num;
};
```

- 현재 힙의 가장 앞에 있는 값을 반환하고, 힙을 다시 정렬한다.

## :black_nib: **Review**

- 자바스크립트에는 힙이 따로 구현되어있지 않아서 직접 메소드들을 만들어야했다.
- 만들어보면서 힙이 어떤 방식으로 동작되는지 다시 공부할 수 있었다.

# [150369] 택배 배달과 수거하기

## :pushpin: **Algorithm**

Stack, 그리디

## :round_pushpin: **Logic**

```javascript
while (dStack.length || pStack.length) {
        const dLength = dStack.length ? dStack[dStack.length - 1] : 0;
        const pLength = pStack.length ? pStack[pStack.length - 1] : 0;
        const length = Math.max(dLength, pLength);
        // 트럭의 용량만큼 택배 배달 및 수거
        for (let i = 0; i < cap; i++) {
            dStack.pop();
            pStack.pop();
        }
        answer += length * 2;
    }
```

- 배달해야할 상자와 수거해야할 상자 중 가장 멀리 있는 상자의 위치를 저장한다.
- 이후 트럭의 용량만큼 상자들을 수거 및 배달한다.
- 택배 배달과 수거가 모두 끝날때까지 반복한다.


## :black_nib: **Review**

- 택배를 어떤 방식으로 수거하고 계산할지 선택하는게 중요한 문제였다.
- 다른 방법으로도 풀 수 있는 문제인지 궁금하다.
# [12913] 땅따먹기

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```javascript
for (let i = 1; i < n; i++) {
        cur = land[i];
        for (let j = 0; j < 4; j++) {
            let max = 0;
            for (let k = 0; k < 4; k++) {
                // 같은 줄을 선택하는 경우는 무시
                if (j === k) {
                    continue;
                }
                if (max < prev[k]) {
                    max = prev[k];
                }
            }
            cur[j] += max;
        }
        // 현재 배열을 이전 배열로 이동
        prev = cur;
    }
```

- 행별로 내려오면서 바로 위와 같은 줄을 선택하는 경우를 제외하고 가장 큰 값을 더해준다.

## :black_nib: **Review**

- 백준에서 풀었던 RGB거리 문제와 비슷한 유형의 문제라서 금방 풀이방법을 생각할 수 있었다.
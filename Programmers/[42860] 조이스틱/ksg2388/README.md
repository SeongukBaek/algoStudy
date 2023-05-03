# [42860] 조이스틱

## :pushpin: **Algorithm**

그리디

## :round_pushpin: **Logic**

```javascript
let minMove = name.length - 1;
    for (let i = 1; i < name.length; i++) {
        if (name[i] === 'A') {
            for (j = i + 1; j < name.length; j++) {
                if (name[j] !== 'A') {
                    break;
                }
            }
            const left = i - 1; // A가 나오지 직전까지
            const right = name.length - j;
            minMove = Math.min(minMove, left > right ? left + right * 2 : left * 2 + right);

            i = j;
        }
    }
```

- A가 뭉쳐있는 지역을 만나면 그 지역을 지나지 않고 모든 위치를 탐색하는 경우가 최소가 되는 경우를 구해준다.

## :black_nib: **Review**

- 그리디 알고리즘이라고 분류되어 있어서 그리디로 풀려고 했지만 계속 실패했다.... 알고보니 문제 테스트케이스가 추가되어 더이상 그리디로 푸는 문제가 아니었다.
- 그래서 풀이 방법을 참고했는데 어려운 문제였다.
# [12938] 최고의 집합

## :pushpin: **Algorithm**

수학

## :round_pushpin: **Logic**

```javascript
let mod = Math.floor(s / n);
let remider = s % n;

for (let i = 0; i < n; i++) {
    answer.push(mod);
}
```

- 입력받은 수의 몫과 나머지를 저장해둔다.
- 이후 `answer` 배열에 몫을 n개 만큼 저장한다.

```javascript
// 나머지들을 1씩 분배
if (remider !== 0) {
for (let i = 0; i < remider; i++) {
    answer[answer.length - 1 - i]++;
}
}
```

- 이후 나머지들을 배열의 뒤쪽부터 1씩 분배해준다.

## :black_nib: **Review**

- 주어진 테스트 케이스와 다른 경우 2가지 정도를 테스트해보니 규칙이 보여서 그대로 구현했더니 정답이었다.
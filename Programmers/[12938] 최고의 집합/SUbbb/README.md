# [12938] 최고의 집합

## :pushpin: **Algorithm**

수학

## :round_pushpin: **Logic**

```java
int start = s / n;
int remain = s % n;

if (start == 0) {
    return new int[] {-1};
}

int[] answer = new int[n];
Arrays.fill(answer, start);

for (int count = 0; count < remain; count++) {
    answer[n - 1 - count]++;
}
```

- 먼저 주어진 숫자를 나눈 몫과 나머지를 기억한다.
- 몫이 0이라면 애초에 자연수 n개로 s를 만들지 못한다는 의미이다.
- 그렇지 않다면 일단 자연수 n개로 이뤄진 최고의 집합을 몫으로 세팅한다. (ex. start = 2, n = 4 -> {2, 2, 2, 2})
- 이후 남은 나머지만큼, 최고의 집합의 맨 뒤 인덱스부터 + 1씩 증가시킨다.

## :black_nib: **Review**

- 처음 아이디어는 중복조합을 사용하려 했다. 숫자 범위가 커서 시간 초과가 발생했고, 숫자가 큰 만큼 최대한 빠르게 해결하려면 주어진 숫자를 나누어야 할 것 같았다.
- n과 s를 나누다보니 규칙이 보였고, 구현은 금방 했다.

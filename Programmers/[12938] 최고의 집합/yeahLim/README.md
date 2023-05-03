# [12938] 최고의 집합

## :pushpin: **Algorithm**

수학

## :round_pushpin: **Logic**

```java
// n > s일때
if (n > s) {
    return new int[] {-1};
}

// n <= s일때
for(int i = 0; i < n; i++) {
    bestSet[i] = s/n;
}
for(int i = 0; i < s % n; i++) {
    bestSet[i]++;
}
```

- 곱들의 합이 최대일 때는 중간 숫자들이 집합일 때이다. 따라서 최대한 균등하게 나누기 위해, n의 개수만큼 s/n을 각각의 원소에 대입한다. 그리고 나누고 나머지인 값들을 원소 하나에 1씩 더해준다.

## :black_nib: **Review**

- 처음에는 중간값들의 곱이 최대값임을 알아차렸지만, 수학적으로 풀지 않고 중복 조합을 이용하여 풀었지만, 시간 초과가 나는 바람에 위와 같이 구현했다.

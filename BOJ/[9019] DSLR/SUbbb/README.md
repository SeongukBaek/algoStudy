# [9019] DSLR

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
private static StringBuilder convertToFourDigit(String string) {
    return new StringBuilder("0".repeat(4 - string.length()) + string);
}
```

- 자바 11 이상부터 가능한 `repeat()` 를 활용해서 앞이 0으로 채워진 4자리 숫자를 반환

```java
private static String findOperations(StringBuilder from, int to)
```

- from에서 4가지 연산을 모두 수행하면서 to를 만드는 최소한의 명령어 나열을 반환

## :black_nib: **Review**

- 단순히 방문 처리를 하면서 목표 숫자를 만들면 되는 문제였는데, 자료구조의 선택이 잘못 되어서 메모리와 그에 따른 시간 사용량이 많아졌다.
- 리뷰보고 어떤 자료구조를 써야 하는지 한 번 봐야겠다.
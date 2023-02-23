# [9935] 문자열 폭발

## :pushpin: **Algorithm**

문자열, 스택

## :round_pushpin: **Logic**

```java
while (index < input.length()) {
    char current = input.charAt(index++);

    // 스택의 크기가 폭발 문자열의 길이 - 1보다 크거나 같은 경우, 폭발 문자열의 마지막 문자와 현재 문자가 같은 경우 문자열로 생성
    if (words.size() >= boomLength - 1 && lastBoomWord == current) {
        if (isSame(words.subList(words.size() - (boomLength - 1), words.size()))) {
            pop(boomLength - 1);
            continue;
        }
    }

    words.push(current);
}
```

- 문자열을 탐색하면서, 스택에 넣는다.
- 스택의 크기가 폭발 문자열의 길이 - 1보다 크거나 같은 경우, 폭발 문자열의 마지막 문자와 현재 탐색하는 문자가 동일한 경우에 둘의 내용을 비교한다.

## :black_nib: **Review**
- 이러한 유형의 문제는 스택을 통한 풀이가 가능하다는 것은 쉽게 파악할 수 있다.
- 다만 문자열에 대한 연산에 따라 메모리 초과가 발생할 수 있어서, 이를 잘 고려한 구현이 필요한 것 같다.
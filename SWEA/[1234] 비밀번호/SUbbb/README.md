# [1234] 비밀번호

## :pushpin: **Algorithm**

스택

## :round_pushpin: **Logic**

```java
private static String removePairs(String word) {
    Stack<Character> words = new Stack<>();
    for (int index = 0; index < word.length(); index++) {
        char current = word.charAt(index);

        if (!words.empty() && words.peek() == current) {
            words.pop();
            continue;
        }
        words.push(current);
    }
    return stackToString(words);
}
```

- 스택을 이용해 쌍을 이루는 문자를 제거한다.

## :black_nib: **Review**
- 이전에도 비슷한 문제를 많이 풀어봤어서 스택을 사용하는 문제임을 바로 알 수 있었다.
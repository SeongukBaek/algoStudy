# [1234] 비밀번호

## :pushpin: **Algorithm**
stack

## :round_pushpin: **Logic**
``` java
for (int i = 0; i < n; i++) {
    if (stack.isEmpty()) {
        stack.add(code.charAt(i));
        continue;
    }
    if (stack.peek() == code.charAt(i)) {
        stack.pop();
        continue;
    }
    stack.add(code.charAt(i));
}
```
- stack이 비어있는 경우 바로 문자를 넣는다.
- stack이 존재하는 경우 stack의 가장 위에있는 값과 새로 들어오는 값을 비교해 같은 경우 stack에 그 값을 넣지 않고 기존 stack의 가장 위에 있는 값을 삭제한다.

## :black_nib: **Review**
- 생각보다 어렵지 않은 문제라 쉽게 풀 수 있었다.
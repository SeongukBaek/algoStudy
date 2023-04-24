# [1918] 후위 표기식

## :pushpin: **Algorithm**

스택

## :round_pushpin: **Logic**

```java
// 알파벳이면
if (Character.isAlphabetic(current)) {
    result.append(current);
}

// 연산자이면
if (current == '*' || current == '/' || current == '+' || current == '-') {
    while (!operators.isEmpty() && isLeftFirst(operators.peek(), current)) {
        result.append(operators.pop());
    }
    operators.push(current);
}

if (current == '(') {
    operators.push(current);
}

if (current == ')') {
    while (!operators.isEmpty() && operators.peek() != '(') {
        result.append(operators.pop());
    }
    // ( 빼기
    operators.pop();
}
```

- 알파벳인 경우는 바로 후위 표기식에 추가한다.
- 연산자인 경우, 스택의 가장 첫 연산자의 우선순위가 현재 연산자의 우선순위보다 크거나 같은 경우, 연산자 스택에서 pop해서 후위 표기식에 추가한다.
- 열린 괄호는 스택에 추가하고, 닫힌 괄호의 경우는 열린 괄호가 나올 때까지 나오는 모든 연산자들을 후위 표기식에 추가한다.

## :black_nib: **Review**

- 알고리즘 수업에서 후위 표기식을 도출할 때는 스택을 사용해야 한다는 것을 배워서 스택을 사용하는 것을 금방 떠올릴 수 있었다.
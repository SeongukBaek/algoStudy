# [1918] 후위 표기식

## :pushpin: **Algorithm**

스택

## :round_pushpin: **Logic**

```java
for(int i = 0; i < infix.length(); i++) {
    char cur = infix.charAt(i);
    // 변수면 바로 씀
    if(isVariable(cur)) {
        sb.append(cur);
        continue;
    }
    
    // 연산자일 경우
    if(cur ==')') {
        popAll(sb, operators);
        continue;
    }
    // 현재 +,-이고 이전 연산자가 *,/일 경우
    if(operators.size() > 0 && isPlusOrMinus(cur) && isMultOrDivide(operators.peek())) {
        sb.append(operators.pop());
    }
    // 현재 연산자와 이전 연산자의 우선순위가 같은 경우
    // +,-일 경우
    if(operators.size() > 0 && isPlusOrMinus(cur) && isPlusOrMinus(operators.peek())) {
        sb.append(operators.pop());
    }
    // *,/일 경우
    if(operators.size() > 0 && isMultOrDivide(cur) && isMultOrDivide(operators.peek())) {
        sb.append(operators.pop());
    }
    operators.push(cur);
}

```

- 여러가지 경우를 나눠서 생각하자
    - 1. 현재 문자가 `)`인 경우 `(`가 나올때까지 스택에서 pop하기
    - 2. 현재 연산자와 같은 우선순위 연산자를 스택에서 pop하기
    - 3. 현재 연산자보다 스택에 높은 우선순위의 연산자가 있으면 pop하기


## :black_nib: **Review**

- 여러가지 경우의 수만 잘 생각하면 쉽게 풀리는 문제였다.
- 하지만 나는 상근오빠의 약간의 도움을 받아 해결했다.
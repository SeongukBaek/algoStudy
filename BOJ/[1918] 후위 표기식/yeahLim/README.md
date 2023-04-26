# [1918] 후위 표기식

## :pushpin: **Algorithm**

스택, 구현

## :round_pushpin: **Logic**

```java
// 괄호 처리
if(ex == '(') {
    stack.add(ex);
    continue;
}
if(ex == ')') {
    while(!stack.isEmpty()) {
        if(stack.peek() == '(') {
            stack.pop();
            break;
        }
        sb.append(stack.pop());
    }
    continue;
}
```

- '('는 그대로 스택에 넣어준다.
- ')'를 만나면, 스택에 있는 '('를 만날 때까지, 스택에 있는 연산자를 하나씩 빼준다.

```java
// 연산자
while(!stack.isEmpty() && priority(stack.peek()) >= priority(ex)) {
    if (stack.peek() == '(') {
        break;
    }
    sb.append(stack.pop());
}
stack.add(ex);
```

- 연산자를 만나면, 현재 연산자보다 스택에 있는 연산자의 우선 순위가 같거나 높은 때까지 하나씩 빼준다.
- 더 이상 없으면, 현재 연산자를 스택에 추가한다.

## :black_nib: **Review**

- 스택의 느낌이 술술 났다. 하지만 조건식을 찾는 것이 매우매우 어려웠다는 점..

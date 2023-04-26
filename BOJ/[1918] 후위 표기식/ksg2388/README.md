# [1918] 후위 표기식

## :pushpin: **Algorithm**

스택

## :round_pushpin: **Logic**

```javascript
if (str === '(') {
  stack.push(str);
  return;
}

if (str === ')') {
  let size = stack.length;
  while (true) {
    let temp = stack.pop();
    if (temp === '(') {
      break;
    }
    answer += temp;
  }
  return;
}
```

- '(' 괄호를 만난 경우에는 바로 값을 스텍에 넣는다.
- ')' 괄호를 만난 경우에는 '('를 만날때 까지 `pop`해준다.

```javascript
if (str === '-' || str === '+') {
  // 스택이 비어있거나 괄호 안에 있는 경우
  if (stack.length === 0 || stack[stack.length - 1] === '(') {
    stack.push(str);
    return;
  }

  let size = stack.length;

  while (size-- > 0) {
    if (stack[size] === '(') {
      break;
    }
    answer += stack.pop();
  }
  stack.push(str);

  return;
}
```

- '+', '-' 연산자를 만난 경우에 stack에 값이 있다면 '(' 기호를 만나기전까지 모두 `pop`해준다.
- 이후 스텍에 `push`해준다.

```javascript
if (str === '*' || str === '/') {
  if (stack.length === 0) {
    stack.push(str);
    return;
  }

  if (stack[stack.length - 1] === '*' || stack[stack.length - 1] === '/') {
    answer += stack.pop();
  }

  stack.push(str);
  return;
}
```

- '_', '/' 연산자를 만난 경우에 stack의 가장 위값이 '_'나 '/'인 경우에는 `pop`을 한 후 `push`해준다.

## :black_nib: **Review**

- 스텍을 이용하면 된다는 것은 알고있었지만 각각의 연산자가 어떻게 처리되는지 생각하는 것이 까다로웠다.
- 이런 문제는 예외 케이스를 찾는 것이 너무 어렵다...

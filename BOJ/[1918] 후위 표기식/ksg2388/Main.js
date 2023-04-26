const input = require('fs')
  .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split('');

const stack = [];
let answer = '';

const changePostfix = (input) => {
  input.forEach((str) => {
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

    answer += str;
  });

  if (stack.length) {
    let size = stack.length;
    while (size-- > 0) {
      answer += stack.pop();
    }
  }
};

changePostfix(input);

console.log(answer);

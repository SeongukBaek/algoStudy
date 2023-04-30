# [1918] 후위 표기식

## :pushpin: **Algorithm**

스택

## :round_pushpin: **Logic**

```java
static String postfix() {
    String answer = null;
    StringBuilder sb = new StringBuilder(); // 정답 문자열
    
    // 문자열 끝까지 돌면서 
    for(int i = 0; i < str.length; i++) {
        
        // 1. 숫자(알파벳)면 그냥 출력
        if('A' <= str[i]) {
            sb.append(str[i]);
            continue;
        }

        // 2. 숫자가 아니면 --> 연산자이면 
        // 여는 괄호이면 스택에 담기
        if('(' == str[i]) {
            oper.push(str[i]); // 스택에 삽입
        }
        
        // 닫는 괄호이면 여는 괄호까지 스택에 있는 거 빼기 
        else if(')' == str[i]) {
            while(!oper.isEmpty() && oper.peek() != '(') {
                sb.append(oper.pop());
            }
            if(!oper.isEmpty()) {					
                oper.pop(); // 여는 괄호 빼주기
            }
        }
        
        // 일반 연산자면
        else { // 연산자 스택이 비지않고 스택에 담긴 연산자의 순위가 더 높거나 같으면 먼저 출력해야하므로 꺼내줌
            while(!oper.isEmpty() && priority(str[i]) <= priority(oper.peek())) {
                sb.append(oper.pop());
            }
            oper.push(str[i]);
        }
    }
    
    while(!oper.isEmpty()) {
        sb.append(oper.pop());
    }
    
    answer = sb.toString();
    return answer;
}
```
- 1. 숫자이면 바로 출력한다(출력을 위해 sb에 추가한다.).
- 2. 괄호 연산자일 경우, '('이면 스택에 넣고 ')'를 만나면 이전에 스택에 있던 연산자를 출력한다(sb에 넣는다).
- 3. 일반 연산자일 경우, 스택에 들어있는 연산자가 있고 스택에 담긴 연산자의 순위가 더 높거나 같으면 출력한다(sb에 넣는다).


## :black_nib: **Review**
- 알파벳일 경우 조건을 고민을 많이 했었는데 이번에 코드리뷰하면서 Character.isAlphabetic()라는 메소드를 알게 되어서 다음에는 이 메소드를 사용해서 알파벳임을 구분하면 좋을 것 같다!

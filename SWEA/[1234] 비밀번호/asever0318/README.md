# [1234] 비밀번호

## :pushpin: **Algorithm**

Stack

## :round_pushpin: **Logic**

```java
            Stack<Integer> stack = new Stack<>();
            int temp = str[1].charAt(0) - '0';
            stack.push(temp); // 가장 첫 번째 숫자 int로 변형해서 저장 

            // 하나씩 잘라서 스택에 넣고 같은 게 나오면 빼기 
            for(int j = 1; j < N; j++) {
                stack.push(str[1].charAt(j) - '0');

                if(str[1].charAt(j) - '0' == temp) {
                    stack.pop();
                    stack.pop();
                    if(!stack.isEmpty()) {
                        temp = stack.peek(); // 가장 상단의 값 temp에 저장 
                    }
                    else {
                        j++;
                        temp = str[1].charAt(j) - '0';
                        stack.push(temp);
                    }
                }
                else {
                    temp = str[1].charAt(j) - '0';
                }
            }
```
- 순서대로 스택에 숫자를 집어넣고 가장 위의 두 숫자가 같으면 pop해주고 다음 top을 temp에 저장해준다. 만약 pop 이후에 스택에 남은 숫자가 없으면 j를 미리 1증가 시켜 그 다음 숫자를 미리 push하고 넘어간다. 


## :black_nib: **Review**
- 수업 때 스택을 배워서 바로 풀이를 생각할 수 있었다. 배운 내용을 복습할 수 있어서 좋았다. 
- 처음에 스택이 비었을 때를 고려하지 못해서 isEmpty로 확인하고 다음 top을 temp로 넘겨주는 과정을 추가했다. 

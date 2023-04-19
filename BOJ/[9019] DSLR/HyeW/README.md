# [9019] DSLR

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
// 모든 명령어를 하나씩 해보며 target 수가 나오면 종료한다.
while(!commands.isEmpty()) {
    Number cur = commands.poll();
    
    for(char order : orders) {
        int result = 0;
        if(order == 'D') {
            result = doDouble(cur.value);
        }else if(order == 'S') {
            result = doMinus(cur.value);
        }else if(order == 'L') {
            result = turnLeft(cur.value);
        }else {
            result = turnRight(cur.value);
        }
        
        if(result == target) {
            return cur.command + order;
        }
        // 한번 만든 수라면 큐에 담지 않는다.
        if(visited.contains(result)) {
            continue;
        }
        visited.add(result);
        commands.add(new Number(result, cur.command+order));
    }
}
```
- 명령어를 하나씩 모두 수행하면서 수가 만들어지는지 확인하는 방법으로 구현했다.


## :black_nib: **Review**

- 모든 경우를 다 확인하며 수가 만들어지는지 아닌지 확인하는 문제였다.
    - 너무 단순한데 더 좋은 방법이 있는지 궁금하다.
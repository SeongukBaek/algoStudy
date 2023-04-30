# [13549] 숨바꼭질 3

## :pushpin: **Algorithm**

BFS 

## :round_pushpin: **Logic**

```java
static int findSister() {
    Deque<Integer> queue = new ArrayDeque<>();
    queue.add(N);
    map[N] = 0;
    
    while(!queue.isEmpty()) {
        int current = queue.poll();
        
        if(current == K) {
            return map[K];
        }
        
        
        // 2*x
        int newIndex = 2*current;
        if(isValid(newIndex)) {
            queue.add(newIndex);
            map[newIndex] = map[current];
        }
        
        // x-1
        newIndex = current-1;
        if(isValid(newIndex)) {
            queue.add(newIndex);
            map[newIndex] = map[current]+1;
        }
        
        // x+1
        newIndex = current+1;
        if(isValid(newIndex)) {
            queue.add(newIndex);
            map[newIndex] = map[current]+1;
        }
    }
    return -1;
}
```
- BFS로 3가지 경우를 모두 탐색해준다.
- 이때, 코드의 순서가 중요한데 가장 빠른 시간을 구하는 문제인데 순간이동하는 경우에 시간이 0이기 때문에 순간이동하는 경우가 무조건 먼저 방문되어야 하므로 가장 먼저 실행되어야 한다.


## :black_nib: **Review**
- addFirst를 이용해서 큐 앞에 넣어주려고 Deque를 사용했었는데 굳이 addFirst를 사용하지 않아도 되는 풀이였다. 2*n할 경우를 가장 먼저 실행해주면 순간이동 하는 경우가 먼저 방문되어서 문제가 해결된다. 단, 순서를 바꾸면 안된다.
- 다시 한 번 풀어봐야 될 것 같다.

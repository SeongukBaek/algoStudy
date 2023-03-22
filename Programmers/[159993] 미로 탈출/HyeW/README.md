# [159993] 미로 탈출

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

1. 시작점과 레버와 BFS를 해서 최단경로를 구하고
2. 레버와 도착점을 BFS해서 최단경로를 구한다.

```java
while(!path.isEmpty()) {
    int parentSize = path.size();
    cnt++;
    while(parentSize-- > 0) {
        Position cur = path.poll();
        
        for(int i = 0; i < 4; i++) {
            int dx = cur.x + dr[i];
            int dy = cur.y + dc[i];
            
            if(!arrayBoundsValidation(dx, dy)) {
                continue;
            }
    
            if(dx == end.x && dy == end.y) {
                return cnt;
            }
            if(map[dx][dy] == 'X' || visited[dx][dy]) {
                continue;
            }
            
            visited[dx][dy] = true;
            path.add(new Position(dx, dy));
        }
    }	
}
```
기본 BFS를 사용했다.<br/>
큐를 돌때마다 부모 노드의 개수를 세아려서 몇 초인지 구했다.

## :black_nib: **Review**

- 로직이 간단한 문제라서 금방 풀었다. 하지만 답을 구하는 과정에서 탈출을 못할 경우에 대해 따로 예외처리 해줘야 해서 중복코드가 생겼다. 이를 깔끔하게 고치는 방법을 생각해 봐야겠다.


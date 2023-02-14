# [7465] 창용 마을 무리의 개수

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

사람 간의 관계를 양방향 매핑으로 List로 만든다.<br/>
그리고 모든 사람을 확인하며 무리가 있는지 검사한다.<br/>
```java

static void checkGroup(int start) {
    Queue<Integer> q = new LinkedList<>();
    q.add(start);
    connected[start] = true;
    
    while(!q.isEmpty()) {
        int cur = q.poll();
        
        for(int nxt : village.get(cur)) {
            
            if(connected[nxt]) {
                continue;
            }
            
            q.add(nxt);
            connected[nxt] = true;
            
        }
    }
}

```
큐에서 하나의 값을 꺼내 그 값과 연결된 사람들을 큐에 넣는다. 이를 반복하는 BFS알고리즘을 사용해 몇 개의 무리가 있는지 구했다.<br/>


## :black_nib: **Review**
- 양방향 연결리스트를 만들어서 연결된 노드를 찾는 문제들을 많이 풀어보아 쉽게 해결할 수 있었다.
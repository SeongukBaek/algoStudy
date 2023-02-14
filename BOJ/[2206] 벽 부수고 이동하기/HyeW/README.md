# [2206] 벽 부수고 이동하기

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

벽이 있는 맵에서 벽을 최대 한 번 부수고 목적지까지의 최단 거리를 구하는 문제이다.<br/>
최단거리 문제이기에 BFS를 사용했다.

```java
static class Node{
    int x;
    int y;
    int cnt;
    boolean isCrashed;
    
    public Node(int x, int y, int cnt, boolean isCrashed) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.isCrashed = isCrashed;
    }	
}
```
BFS를 위해 큐에 들어갈 객체는 `x좌표값, y좌표값, 이동 횟수, 벽 파괴여부`를 멤버로 갖는다. <br/>
<br/>

```java
static int findRoad(int x, int y) {
		
    //시작점과 도착점이 같은 경우
    if(x == N-1 && y == M-1)
    return 1;
    
   //초기화 코드...
    
    while(!path.isEmpty()) {
        Node cur = path.poll();
        
        for(int i = 0; i < 4; i++) {
            
            //종료지점 도착과 범위 이탈에 대한 예외 처리 코드...
            
            if(cur.isCrashed && visited[dx][dy] != 0)
                continue;
            else if(!cur.isCrashed && visited[dx][dy] == 2)
                continue;
            
            //벽을 만났을 경우
            if(map[dx][dy] == '1')
            {
                if(cur.isCrashed) {
                    continue;
                }
                else {
                    Node next = new Node(dx,dy,cur.cnt+1,true);
                    visited[dx][dy] = 1;
                    path.add(next);
                    
                }
            }
            else {
                Node next = new Node(dx,dy,cur.cnt+1,cur.isCrashed);
                if(cur.isCrashed)
                    visited[dx][dy] = 1;
                else
                    visited[dx][dy] = 2;
                path.add(next);
            }
            
        }
    }
    
    return -1;
}
```
`visited`배열은 이차원 int배열로 `0(가지않은 길)`, `1(벽을 부수고 간 길)`, `2(벽을 부수지 않고 간 길)`로 표시한다.<br/>
벽을 부순 길은 벽을 부수지 않고 간 길을 지나 갈 수 없고, 벽을 부수지 않고 지나간 길은 벽을 부순 길을 지나갈 수 있다. 아무도 가지 않은 길은 모두 다 지나갈 수 있다.<br/>
그래서 visited배열에서 큰 수를 가진 노드는 작은 수를 지나칠 수 있고 작은 수를 가진 노드 큰 수를 지나갈 수 없도록 했다.<br/>


## :black_nib: **Review**
- 최단거리를 구하는 문제라서 BFS를 사용했다.
- 벽을 부수고 지나간 길과 벽을 안 부수고 지나간 길을 따로 체크해야 한다는 것을 알고, visited를 표현하는 방법을 찾으면 금방 풀리는 문제였다.
    - 하지만 그 생각을 하는게 어려워서 지나간 길을 어떻게 표현할지에 대해 엄청나게 고민하는 시간을 가졌다.


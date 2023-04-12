# [1167] 트리의 지름

## :pushpin: **Algorithm**

DFS, 트리

## :round_pushpin: **Logic**


```java
visited[0] = true;
getEndPoint(0, 0);
visited[0] = false;

visited[endPoint] = true;
getEndPoint(endPoint, 0);
```

트리의 어느 노드에서든 가장 먼 노드을 찾으면 그 노드는 지름의 끝점이라는 것이 문제를 해결하는 데 가장 중요한 개념이다.<br/>
그래서 임의의 노드(0번 노드)를 DFS 하여 지름의 끝점을 구하고 그 점을 이용해 트리의 지름을 구한다<br/>

```java
private static void getEndPoint(int v, int w) {
    if(w > maxWeight) {
        maxWeight = w;
        endPoint = v;
    }

    for(Node nxt : graph[v]) {
        if(visited[nxt.v]) {
            continue;
        }
        
        visited[nxt.v] = true;
        getEndPoint(nxt.v, w+nxt.w);
        visited[nxt.v] = false;
    }
}
```
모든 노드를 확인하며 가장 긴 선분을 찾는다.<br/>

## :black_nib: **Review**

- 예전에 풀어봤던 문제라서 바로 풀 수 있었다.

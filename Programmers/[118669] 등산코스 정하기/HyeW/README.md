# [118669] 등산코스 정하기

## :pushpin: **Algorithm**

다익스트라

## :round_pushpin: **Logic**

```java
void goSummit(int[] gates, int n){
    Queue<Path> hiking = new ArrayDeque<>();
    Arrays.fill(distance, Integer.MAX_VALUE);

    for(int gate : gates){
        hiking.add(new Path(gate, 0));
        distance[gate] = 0;
    }

    while(!hiking.isEmpty()){
            Path cur = hiking.poll();

            // 현재까지 구한 최단 intensity보다 현재 동선의 cost가 더 클 경우 패스
            if(distance[cur.v] < cur.cost){
                continue;
            }

            for(Path next : mountain[cur.v]){
                // 현재 노드까지의 최소 intensity와 다음 노드의 intensity 중 큰 값을 다음 노드 최소 intentsity로 결정함
                int maxIntensity = Math.max(distance[cur.v], next.cost);
                if(distance[next.v] > maxIntensity){
                    distance[next.v] = maxIntensity;
                    hiking.add(new Path(next.v, maxIntensity));
                }
            }
        }

    //...
    // 최소 intensity를 구하는 코드
    //...
}
```

다익스트라를 사용한다.<br/>
모든 게이트에서 시작해서 모든 노드까지 가는데 걸리는 최소 intensity를 구한다.<br/><br/>

## :black_nib: **Review**

- 처음에 `BFS+PriorityQueue`를 사용해 문제를 해결하려고 했다. 하지만 18~21번 테케가 계속 실패해서 다익스트라로 풀었다.
  - 다익스트라가 활용되는 문제들이 많은 것 같다. 다익스트라 학습을 추가적으로 해야겠다.

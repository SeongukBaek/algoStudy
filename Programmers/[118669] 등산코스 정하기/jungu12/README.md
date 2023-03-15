# [118669] 등산코스 정하기

## :pushpin: **Algorithm**

다익스트라

## :round_pushpin: **Logic**

```java
for(int[] path : paths) {
    //path가 [출입구, 쉼터, 시간], [쉼터, 봉우리, 시간]인 경우
    if(gateSet.contains(path[0]) || summitSet.contains(path[1])) {
        nodes[path[0]].add(new Node(path[1], path[2]));
        continue;
    }
    //path가 [봉우리, 쉼터, 시간], [봉우리, 출입구, 시간]인 경우
    if(summitSet.contains(path[0]) || gateSet.contains(path[1])) {
        nodes[path[1]].add(new Node(path[0], path[2]));
        continue;
    }
    //쉼터에서 쉼터로 이동하는 경우
    nodes[path[0]].add(new Node(path[1], path[2]));
    nodes[path[1]].add(new Node(path[0], path[2]));
}
```

- 출입구나 봉우리와 이어진 쉼터는 단방향 그래프로 만들어 줘야한다.

```java
private int[] findMinIntensity(int n, int[] gates, int[] summits) {
    Queue<Node> nodesToVisit = new ArrayDeque<>();
    int[] intensity = new int[n + 1];

    Arrays.fill(intensity, Integer.MAX_VALUE);

    for(int gate : gates) {
        intensity[gate] = 0;
        nodesToVisit.add(new Node(gate, 0));
    }

    while(!nodesToVisit.isEmpty()) {
        Node current = nodesToVisit.poll();

        //현재 가중치가 기존 가중치보다 큰 경우 스킵
        if(current.w > intensity[current.e]) {
            continue;
        }

        //intensity가 더 작은 경로가 있다면 정보 업데이트
        for(Node next : nodes[current.e]) {
            int curIntensity = Math.max(intensity[current.e], next.w);
            if(intensity[next.e] > curIntensity) {
                intensity[next.e] = curIntensity;
                nodesToVisit.add(new Node(next.e, curIntensity));
            }
        }
    }

    //봉우리를 오름차순으로 정렬
    Arrays.sort(summits);

    //intensity가 가장 낮은 등산경로를 찾음
    for(int summit : summits) {
        if(minIntensity > intensity[summit]) {
            minIntensity = intensity[summit];
            visitedSummit = summit;
        }
    }
    return new int[] {visitedSummit, minIntensity};
}
```

- 문제에서는 '출입구 중 한 곳에서 출발하여 산봉우리 중 한 곳만 방문한 뒤 다시 원래의 출입구로 돌아오는 등산코스를 정한다'라 했지만 출입구에서 봉우리 까지만 방문하면 최소 intensity를 찾을 수 있다.
- 일반적인 다익스트라와 달리 intensity가 더 작은 경로로 업데이트가 가능하다면 해당 노드와 연결된 노드들을 큐에 넣고 정보를 업데이트 해준다.
- 다익스트라로 모든 경로 정보를 업데이트 했다면, 봉우리를 돌면서 intensity가 가장 낮은 등산 경로를 찾아준다.

## :black_nib: **Review**

- 다익스트라 알고리즘을 변형해서 푸는 문제임을 생각하지 못하였다...
- 일반적인 다익스트라와 경로를 업데이트 해줘야 하는 조건이 달라서 까다로운 문제였다..

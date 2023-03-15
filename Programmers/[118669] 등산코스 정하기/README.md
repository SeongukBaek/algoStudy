# [118669] 등산코스 정하기

## :pushpin: **Algorithm**

다익스트라

## :round_pushpin: **Logic**

```java
while(!q.isEmpty()) {
    int edge = q.peek()[0];
    int intensity = q.peek()[1];
    q.poll();

    if(d[edge] < intensity) continue;

    for(Node nd : nodes[edge]) {
        int distance = Math.max(d[edge], nd.intensity);
        if(d[nd.edge] > distance) {
            d[nd.edge] = distance;
            q.add(new int[] {nd.edge, distance});
        }
    }
}

Arrays.sort(summits);
for(int summit : summits) {
    if(minIntensity > d[summit]) {
        minIntensity = d[summit];
        resultNode = summit;
    }
}
```

-

## :black_nib: **Review**

-

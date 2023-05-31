# [2056] 작업

## :pushpin: **Algorithm**

위상 정렬

## :round_pushpin: **Logic**

```java
private static int computeMinTime() {
    int[] result = costs.clone();

    for (int index = 1; index <= N; index++) {
        // indegree가 0인 작업을 큐에 넣음.
        if (indegrees[index] == 0) {
            todo.add(index);
        }
    }

    while (!todo.isEmpty()) {
        int now = todo.poll();

        for (int next : info.get(now)) {
            indegrees[next]--;
            
            result[next] = Math.max(result[next], result[now] + costs[next]);

            // indegree가 0이 된 작업을 큐에 넣음.
            if (indegrees[next] == 0) {
                todo.add(next);
            }
        }
    }

    return Arrays.stream(result).max().getAsInt();
}
```

- indegrees가 0인 작업은 해당 작업이 선행되어야 한다는 것을 의미한다.
- 따라서 이를 먼저 처리해주기 위해 큐에 넣고, 해당 작업을 처리한 후 처리할 수 있는 다른 작업들을 처리하도록 한다.

## :black_nib: **Review**

- 이 문제 또한 전형적인 위상정렬을 사용하는 문제였다.
- 결론적으로 ... indegree가 큰 것부터 처리하려 했는데, 반대로 접근해서 구현이 더 꼬인 문제가 있었던 것 같다.

# [2383] 점심 식사시간

## :pushpin: **Algorithm**

구현, 시뮬레이션, 우선순위 큐

## :round_pushpin: **Logic**

```java
private static void makeSubset(StringBuilder selected, boolean[] isVisited, int start, int count) {
    if (selected.length() == count) {
        // selected -> 계단 1, selected -> 계단 2 다 구하기
        String key = selected.toString();
        stair1times.put(key, goDownStairs(key, 0));
        stair2times.put(key, goDownStairs(key, 1));
        return;
    }

    for (int index = start; index < people.size(); index++) {
        if (isVisited[index]) {
            continue;
        }
        isVisited[index] = true;
        selected.append(index);
        makeSubset(selected, isVisited, index + 1, count);
        selected.deleteCharAt(selected.length() - 1);
        isVisited[index] = false;
    }
}
```

- 계단을 사용하는 모든 방법을 수행해보기 위해, 계단을 사용할 사람들을 조합을 통해 구하고, 해당 사람들이 계단 1을 사용할 경우의 최소 시간과, 계단 2를 사용할 경우의 최소 시간을 저장한다.

```java
private static int goDownStairs(String selected, int type) {
    Queue<Integer> times = new PriorityQueue<>();
    int time[] = new int[100];
    
    int max = 0;
    int[] stair = stairs.get(type);
    int k = stair[2];
    
    // 우선순위 큐에 해당 계단을 사용할 사람들의 도착 시간 저장
    for (int index = 0; index < selected.length(); index++) {
        times.add(getDistance(stair, people.get(selected.charAt(index) - '0')));
    }
    
    while (!times.isEmpty()) {
        int currentPersonStart = times.poll();
        int currentPersonEnd = currentPersonStart + k;
        
        // 누적합과 비슷하게, time은 해당 시간대에 계단 위에 있는 사람 수를 의미
        for (int index = currentPersonStart; index < currentPersonEnd; index++) {
            // 만약 해당 시간에 계단 위에 3명이 있다면, 현재 current 사람은 1분을 대기해야 하므로, currentPersonEnd가 1 증가!
            if (time[index] == 3) {
                currentPersonEnd++;
                continue;
            }
            time[index]++;
        }
        
        if (max < currentPersonEnd) {
            max = currentPersonEnd;
        }
    }
    return max;
}
```

- 우선순위 큐를 사용해서, 해당 계단을 사용하는 사람들이 계단에 도착하는 시간을 저장한다.
- 이후, 큐에서 하나씩 꺼내서, 계단 도착 시간과 계단 탈출 시간을 구한다.
    - 누적합과 비슷하게, time 배열에 해당 시간에 계단을 사용하는 사람 수를 저장한다.
    - 만약 3명이 이미 사용 중이라면, 현재 해당 계단을 사용하려 했던 사람(current)의 계단 탈출 시간이 1 증가하게 된다.

## :black_nib: **Review**
- 조합을 사용해서 모든 경우를 돌아봐야 했고, 맵을 통해 해당 조합의 사람들이 계단을 오르는 최소 시간을 저장하고, 맵을 다 돌면서 최소가 되는 시간을 구한다는 아이디어는 잡을 수 있었다.
- 하지만, 해당 사람들이 계단을 이용할 때의 최소 시간을 구하는 로직을 잡을 수 없어서 ... 풀이를 참고했다.
    - 누적합처럼 표현해서 생각보다 복잡한 시간 계산을 수월하게 할 수 있었던 것 같다.
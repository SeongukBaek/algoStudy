# [181188] 요격 시스템

## :pushpin: **Algorithm**

우선순위 큐

## :round_pushpin: **Logic**

```java
private Queue<int[]> targetLocations = new PriorityQueue<>(new Comparator<int[]>() {
    @Override
    public int compare(int[] o1, int[] o2) {
        return Integer.compare(o1[1], o2[1]);
    }
});
```

- end를 기준으로 오름차순 정렬된 우선순위 큐 선언

```java
while (!targetLocations.isEmpty()) {
    int[] current = targetLocations.poll();

    // 큐의 peek의 start가 current의 end보다 작은 경우 계속 poll
    while (!targetLocations.isEmpty() && targetLocations.peek()[0] < current[1]) {
        targetLocations.poll();
    }

    answer++;
}
```

- 하나의 target의 end를 기준으로 잡아두고 다른 폭격을 확인한다.
- 폭격 미사일의 start가 기준으로 잡아둔 target의 end 안에 있는 경우, 함께 요격할 수 있다.

## :black_nib: **Review**

- 어떤 자료구조를 사용해야 할 지에 대해서 고민해보다가, 큐 ... 아니면 도저히 사용할 수 있을 게 없었다.
- 근데 어떤 기준으로 정렬을 해야할 지 몰랐는데, 주어진 정보 2개 중 하나를 사용하면 되는 거였다...

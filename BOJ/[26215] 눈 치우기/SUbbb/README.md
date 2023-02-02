# [26215] 눈 치우기

## :pushpin: **Algorithm**

그리디, 우선순위 큐

## :round_pushpin: **Logic**

```java
private static List<Integer> cleanTwoHouses() {
    List<Integer> temp = new ArrayList<>();
    for (int index = 0; index < MAX_HOUSE_COUNT; index++) {
        if (snows.isEmpty()) {
            break;
        }
        int snow = snows.poll() - 1;
        if (snow == 0) {
            continue;
        }
        temp.add(snow);
    }
    return temp;
}
```

- 내림차순으로 정렬된 우선순위 큐에서 두 개의 집 앞에 쌓인 눈을 하나씩 제거한다.
- 눈이 0이 되면 큐에 넣지 않는다. 

## :black_nib: **Review**
- 눈을 내림차순으로 계속 정렬하면서, 2 집씩 감소시키는 것이 중요한 문제였고, 이를 위해 우선순위 큐를 사용해야겠다고 생각했다.
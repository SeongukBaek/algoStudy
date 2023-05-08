# [172927] 광물 캐기

## :pushpin: **Algorithm**

그리디

## :round_pushpin: **Logic**

```java
class Group implements Comparable<Group> {
    // 광물
    List<String> mineralList;
    // 해당 광물들을 돌 곡괭이로 채굴할 때 발생하는 피로도 합
    int fatigue;

    Group(List<String> mineralList, int fatigue) {
        this.mineralList = mineralList;
        this.fatigue = fatigue;
    }

    @Override
    public int compareTo(Group group) {
        return group.fatigue - this.fatigue;
    }
}
```

- 한 곡괭이로 채굴할 수 있는 광물 그룹을 저장하는 클래스

```java
private void groupMinerals() {
    int groups = minerals.length / SIZE;
    int remains = minerals.length % SIZE;

    // 곡괭이 수보다 더 많이 그룹을 나누면 안됨
    // 실제로는 채굴할 수 없는 광물 그룹인데 우선순위에 의해 채굴하게 될 수도 있음
    if (groups > allPicks) {
        groups = allPicks;
        remains = 0;
    }

    int index = 0;
    for (int count = 0; count < groups; count++) {
        groupMinerals(index, SIZE);
        index += SIZE;
    }

    // 남은 광물들 그룹으로 묶기
    groupMinerals(index, remains);
}
```

- 광물들을 한 곡괭이로 채굴할 수 있도록 그룹화한다.
- 해당 그룹들의 피로도 합을 구하고 이를 피로도 기준으로 우선순위 큐에 삽입하면, 다이아 곡괭이부터 차례대로 피로도 합이 높은 광물 그룹을 채굴할 수 있다.

## :black_nib: **Review**

- 처음에는 곡괭이 조합을 모두 구한 후, 완전탐색으로 해결하려 했는데, 시간초과가 발생했다. 입력 범위에 따른 시간 복잡도 계산이 부족했다.
- 시간을 줄일 방법이 떠오르지 않아 질문 게시판을 참고했다.
- 그리디 방식으로 광물을 그룹하는 방식은 생각 못했을 것 같다.

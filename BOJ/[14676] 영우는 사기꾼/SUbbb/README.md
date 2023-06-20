# [14676] 영우는 사기꾼 ?

## :pushpin: **Algorithm**

위상 정렬

## :round_pushpin: **Logic**

```java
private static boolean canBuiltBuilding(int building) {
    // 건설할 수 없다 == 현재 건물을 건설하기 위해 바로 이전에 건설해야 하는 건물을 모두 건설하지 않은 경우 == 진입 차수가 0이 아니다!
    if (inDegrees[building] != 0) {
        return false;
    }

    // 건설할 수 있다면 건설
    buildings[building]++;

    // 진입 차수 갱신은 해당 건물이 처음 세워졌을 경우에만 처리하면 되기에 나머지 경우는 제외!
    if (buildings[building] > 1) {
        return true;
    }

    // 해당 건물과 순서상 연결된 건물들의 진입 차수 - 1
    for (int next : buildingInfo.get(building)) {
        if (inDegrees[next] == 0) {
            continue;
        }
        inDegrees[next]--;
    }

    return true;
}
```

- 건물의 건설 가능 여부는 진입 차수로 판단할 수 있다. 진입 차수가 0인 건물은 앞에 선행되어야 하는 건물이 모두 지어졌거나, 없음을 의미한다.
- 건설 후, 다음 건설되어야 하는 건물들의 진입 차수를 1개씩 감소시켜준다. 이때, 현재 건물이 이미 1개라도 있었다면 진입 차수는 갱신하지 않는다.
  
```java
private static boolean canDestroyBuilding(int building) {
    // 파괴할 수 없다 == 건설되지 않은 건물이다.
    if (buildings[building] == 0) {
        return false;
    }

    // 파괴할 수 있다면 파괴
    buildings[building]--;

    // 만약 해당 건물이 0개인 경우만 다음 건물의 진입 차수 갱신
    if (buildings[building] != 0) {
        return true;
    }

    // 다음 건물의 진입 차수 + 1
    for (int next : buildingInfo.get(building)) {
        inDegrees[next]++;
    }

    return true;
}
```

- 파괴 가능 여부는 그저 해당 건물이 지어졌는가만 판단하면 된다.
- 이후, 다음 건설되어야 하는 건물들의 진입 차수를 증가시켜준다. 이때, 현재 건물이 1개라도 있다면 진입 차수는 갱신하지 않아야 한다.

## :black_nib: **Review**

- 위상 정렬을 사용하지 않고, 단순히 현재 건물 이전에 건설되어야 하는 건물 번호만 저장한 후, 해당 건물들이 건설되었는지를 판단해서 문제를 해결하려 했는데, 87%에서 시간 초과가 발생했다.

```java
private static boolean canBuilt(int building) {
    for (int prevNode : buildingInfo.get(building)) {
        if (!canBuild[prevNode]) {
            return false;
        }
    }

    return true;
}
```
- 시간이 오래 걸릴만한 곳이 위 코드밖에 없어서, 해당 건물을 건설할 수 있는지 여부를 좀 더 빠르게 확인할 방법이 필요했다.
- 정답은 ... `int[] inDegrees` 였다. 건물별 진입 차수를 관리함으로써, 이전 건물이 건설되었는지 여부를 확인할 수 있었다!
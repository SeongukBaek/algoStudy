# [152995] 인사고과

## :pushpin: **Algorithm**

정렬

## :round_pushpin: **Logic**

```java
for (int index = 1; index < scores.length; index++) {
    if (sum(scores[index]) > sum) {
        scoreOrder.add(scores[index]);
    }
}
```

- 완호보다 점수 합이 높은 사원들만 리스트에 추가한다.

```java
scoreOrder.sort((o1, o2) -> {
    if (sum(o2) == sum(o1)) {
        return Integer.compare(o2[0], o1[0]);
    }
    return Integer.compare(sum(o2), sum(o1));
});
```

- 점수 합 내림차순 정렬, 점수 합이 같다면 근무 태도 점수를 기준으로 내림차순 정렬한다.

```java
int answer = 1;
for (int index = 0; index < scoreOrder.size() - 1; index++) {
    // 현재 다음의 사원이 인센티브를 받을 수 없는 경우는 패스
    if (!canGetInsentive(index + 1)) {
        continue;
    }

    answer++;
}
```

- 해당 사원이 인센티브를 받을 수 있는지 여부는 해당 사원 앞의 모든 사원들과 비교해야 했다.

## :black_nib: **Review**

- 정렬을 사용해야 하는 건 알아챌 수 있었는데, 둘 중 특정 하나를 기준으로 정렬하면 된다는 건 몰랐다.
- 계속 틀렸었는데, 인센티브를 받을 수 있는지 여부 확인을 계속 정렬된 기준으로 바로 앞의 사원이랑만 비교했었다.

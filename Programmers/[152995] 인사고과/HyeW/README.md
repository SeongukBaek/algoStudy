# [152995] 인사고과

## :pushpin: **Algorithm**

정렬

## :round_pushpin: **Logic**

```java
// 근무 태도 점수 desc, 동료 평가 점수 desc
Arrays.sort(scores, (o1, o2) -> {
    if (o1[0] == o2[0]) {
        return o2[1] - o1[1];
    }
    return o2[0] - o1[0];
});
```
- 근무 태도 점수에 대해서 내림차순으로 정렬하고 그 다음으로 동료 평가 점수를 내림차순으로 정렬한다.

```java
for(int i = 0; i < scores.length; i++){
    // 근무 평가 점수가 달라지면 동료 평가 점수 max값 갱신
    if(i != 0 && scores[i][0] != scores[i-1][0]){
        preMax = curMax;
        curMax = Math.max(curMax, scores[i][1]);
    }
    // 현재 동료 평가 점수가 max값보다 작다면 인센티브를 받지 못한다.
    if(scores[i][1] < preMax){
        if(target[0] == scores[i][0] && target[1] == scores[i][1]){
            return -1;
        }
        continue;
    }
    // 완호 점수 합보다 점수합이 높으면 완호의 등수를 내린다.
    if(target[0] + target[1] < scores[i][0]+scores[i][1]){
        rank++;
    }
}
```

- 근무 평가 점수가 같은 사람들을 한 그룹이라고 생각하고 문제를 해결했다.
- `preMax`는 현재 그룹이 아닌 이전 그룹까지의 동료 평가 점수의 최대값을 저장하고 있다.
  - 동료 평가 점수만 비교해도 되는 건 근무 평가 점수로 내림차순 정렬이 되어 있기 때문에 무조건 다음에 확인하는 근무 평가 점수가 낮기 때문이다.
- `curMax`는 현재 그룹까지의 동료 평가 점수의 최대값을 담고 있다. 
  - 그룹이 바뀌게 되면 `preMax`에 `curMax`값을 넣어준다.
- 그래서 현재 확인하는 사원의 동료 평가 점수가 `preMax`보다 낮다면 인센티브를 못받는다.
- 인센티브를 받을 수 있다면 완호 점수와 비교하여 등수를 구한다.

## :black_nib: **Review**

- 인센티브를 못받는 직원에 대해 어떻게 구해야할지 너무 어려웠다.
- 요격 시스템 문제도 그렇고 문제를 해결하려면 정렬을 잘 활용하는 것도 중요하다는 것을 깨달았다.
# [14676] 영우는 사기꾼 ?

## :pushpin: **Algorithm**

위상 정렬

## :round_pushpin: **Logic**

```java
if (order == 1) {
  // 영향을 주는 건물이 건설되지 않았을 경우
  if (inDegree[num] > 0) {
    System.out.println("Lier!");
    return;
  }
  // 처음 짓는 건물일 경우
  if (builted[num] == 0) {
    for (int nxt : buildings[num]) {
      inDegree[nxt]--;
    }
  }
  builted[num]++;
  continue;
}
// 지어진 건물이 없는 경우
if (builted[num] < 1) {
  System.out.println("Lier!");
  return;
}
builted[num]--;
// 해당 번호 건물이 다 무너졌을 경우
if (builted[num] == 0) {
  for (int nxt : buildings[num]) {
    inDegree[nxt]++;
  }
}
```
- 위상정렬은 정점에 대해 진입차수를 세아려 정렬을 하는 방법으로 여기서 진입차수를 활용해 문제를 해결했다.
- `builted` 배열은 지어진 건물의 개수를 저장한다.
- 건물을 건설할 때 처음 짓는 건물일 경우 영향을 주는 건물들의 **진입차수를 1씩 줄인다**.
    - 그렇기 때문에 진입차수가 0이 아닌 건물은 지을 수 없다고 판단할 수 있다.
- 건물을 파괴할 때 주어진 번호의 건물이 더이상 없다면 영향을 주는 건물들의 **진입차수를 1씩 더해준다**.
    - `builted`를 활용해 없는 건물을 파괴할 때 치트키를 썼다고 판단한다.


## :black_nib: **Review**

- 위상 정렬문제임을 알고 문제를 풀었다.
   - 주어진 번호의 건물을 건설할 수 있는지 확인하는 로직이 바로 생각나지 않아 준구오빠 풀이를 참고했다(땡큐^_^)

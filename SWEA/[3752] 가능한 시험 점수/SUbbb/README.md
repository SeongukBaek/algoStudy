# [3752] 가능한 시험 점수

## :pushpin: **Algorithm**

집합

## :round_pushpin: **Logic**

```java
private static void makeScore() {
    for (int score : scores) {
        Set<Integer> tempScores = new HashSet<>();
        for (int setScore : scoreSet) {
            tempScores.add(setScore + score);
        }
        scoreSet.addAll(tempScores);
    }
}
```

- 점수를 하나씩 이미 구한 점수 SET의 점수에 더해 다시 SET에 추가한다.
- 0, A -> 0, A, B, A + B -> 0, A, B, A + B, C, A + C, B + C, A + B + C

## :black_nib: **Review**
- 처음에는 조합을 사용해 모든 경우를 구하고, 이를 Set에 넣는 방식으로 하려 했다.
- 시간 초과가 났고, 조합은 $O(2^N)$의 복잡도를 가져 주어진 조건에서는 무조건 시간 초과가 날 수밖에 없었다.
- 하지만 모든 경우를 직접 구해보지 않고는 문제를 해결할 수 없어보여서 고민 끝에 접근법을 좀 찾아봤다.
  - 수학적으로 접근해야 식이 보이는 느낌이었다..
# [5215] 햄버거 다이어트

## :pushpin: **Algorithm**

브루트포스

## :round_pushpin: **Logic**

```java
private static void findMaxScore(int index, int score, int calorie) {
    if (calorie > L) {
        return;
    }

    if (index == N) {
        maxScore = Math.max(maxScore, score);
        return;
    }

    findMaxScore(index + 1, score + scores[index], calorie + calories[index]);
    findMaxScore(index + 1, score, calorie);
}
```

- index에 해당하는 음식을 넣거나, 넣지 않는 모든 경우에 대해 계산한다.

## :black_nib: **Review**
- 강의실 배정 문제와 비슷하게 우선순위 큐를 사용하는 방식으로 접근했다가, 반례를 찾지 못해 모든 경우를 탐색할 수 있는 방식으로 변경했다.
- 더 빠르게 풀 수 있도록 구현 시간을 단축시켜야겠다.
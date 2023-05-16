# [152995] 인사고과

## :pushpin: **Algorithm**

정렬, 우선순위 큐

## :round_pushpin: **Logic**

```java
Arrays.sort(scores, (o1, o2) -> { return (o1[0] == o2[0]) ? o1[1] - o2[1] : o2[0] - o1[0]; });  // 근무점수 내림차순, 동료점수 오름차순
```

- 근무 점수는 내림차순, 동료 점수는 오름차순으로 정렬한다.

```java
PriorityQueue<int[]> rank = new PriorityQueue<>((o1, o2) -> { return (o2[0] + o2[1]) - (o1[0] + o1[1]); });
```

- 근무 점수와 동료 점수의 합을 내림차순으로 Priority Queue를 만든다.

```java
int[] maxScore = scores[0]; // 동료점수 기준 최대값

/* 인센티브 받을 수 있는 사람 구하기 */
for (int i = 1; i < scores.length; i++) {
    if (scores[i][1] >= maxScore[1]) {
        rank.offer(scores[i]);
        maxScore = scores[i].clone();
    }
```

- 현재 score의 근무 점수(`score[i][1]`)는 이전 순서들의 근무 점수보다 낮다.
  - 따라서, 동료 점수도 이전 순서들 보다 낮은 경우는 인센티브를 받을 수 없다.
- maxScore는 이전 순서들의 동료 점수 중 가장 큰 경우이다.
  - 따라서, maxScore의 동료점수보다 작은 경우는 패스하고,
  - 높은 경우에는 rank에 넣어주고, maxScore를 업데이트해준다.

## :black_nib: **Review**

- 한쪽은 오름차순, 한쪽은 내림차순을 이용하여 최대값을 통해 비교하는 방법 잊지 말아야겠다!

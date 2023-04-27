# [92342] 양궁대회

## :pushpin: **Algorithm**

백트래킹

## :round_pushpin: **Logic**

```java
// 만약 현재 쏘려는 점수가 0점이고, 쏠 수 있는 화살이 남은 경우
if (now == 10 && remainArrows > 0) {
    // 0점에 다 쏘기
    ryan[now] = remainArrows;
    remainArrows = 0;
}

if (remainArrows == 0) {
    // 점수 계산
    int[] scores = computeSum(ryan);
    int difference = scores[1] - scores[0];

    // 어피치보다 점수가 낮거나 같으면 이길 수 없음
    if (difference <= 0 || maxDifference > difference) {
        // 0점에 화살을 다 쏜 경우를 다시 돌려놓기
        ryan[10] = 0;
        return;
    }

    // 최대 점수 차와 같다면 갱신 가능한지 비교 후 갱신
    if (maxDifference == difference) {
        if (canUpdate(ryan)) {
            answer = ryan.clone();
        }
        ryan[10] = 0;
    }

    // 점수 차가 최대인 경우 갱신
    if (maxDifference < difference) {
        maxDifference = difference;
        answer = ryan.clone();
        ryan[10] = 0;
    }

    return;
}
```

- 재귀를 수행하는데, 전달되는 인자는 다음과 같다.
  - 라이언의 화살 정보
  - 현재 목표하는 점수
  - 남아있는 화살 수
- 남아있는 화살 수가 0개인 경우, 어피치와 라이언의 점수 차를 계산하고, 최대 점수 차인 경우 갱신을 수행한다.
  - 이때 중요한 건 정답을 갱신할 수 있는지를 판단해야 한다는 점이다.

## :black_nib: **Review**

- 너무 많은 시간을 쓴 문제였다. 구현하면서 조건 하나씩을 빠트리고, 가장 큰 문제는 최대 점수 차인 경우와 최대 점수보다 큰 경우를 나누지 않고 구현했다는 점이다 ...
- 최대한 IDE를 안 쓰고 구현하려 했는데 막판에 IDE로 디버깅하지 않았다면 훨씬 더 오래 걸렸을 문제다.

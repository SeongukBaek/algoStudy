# [12913] 땅따먹기

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
for (int row = 1; row < land.length; row++) {
    // 현재 행의 얻을 수 있는 최댓값 갱신을 저장할 배열
    int[] tempScore = new int[4];

    for (int col = 0; col < 4; col++) {
        int current = scores[col];

        for (int scoreIndex = 0; scoreIndex < 4; scoreIndex++) {
            // 현재 열과 같은 열은 패스
            if (scoreIndex == col) {
                continue;
            }

            if (tempScore[scoreIndex] < land[row][scoreIndex] + current) {
                tempScore[scoreIndex] = land[row][scoreIndex] + current;
            }
        }
    }

    // 현재 행에 대한 최댓값 갱신 이후, 점수 배열 갱신
    scores = tempScore.clone();
}
```

- 각 행마다 바로 윗 행을 제외한 열별 점수 최댓값을 갱신한다.

## :black_nib: **Review**

- 정수 삼각형같은 DP 문제임은 바로 알 수 있었는데, 중복되는 숫자가 있는 경우 어떤 인덱스의 숫자를 고르느냐에 따라 정답이 달라질 수 있어서, 이를 어떻게 처리해야 할 지 고민하다가 백트래킹을 써야 하나 싶었다.
- 바로 윗 행을 제외한 최댓값을 계속 더해주는 방식으로 진행하면 되는데, 너무 어렵게 꼬아 생각한 것 같다.

# [9465] 스티커

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
int nothing = 0;
int up = stickers[0][0];
int down = stickers[1][0];

for (int index = 1; index < n; index++) {
    // 현재 열에서 아무 스티커도 뽑지 않는 경우
    // -> 이전 열에서 뽑은 스티커 중 최대 값 선택
    int tempNothing = Math.max(Math.max(nothing, up), down);

    // 현재 열에서 위쪽 스티커를 뽑는 경우
    // -> 이전 열에서 아무것도 뽑지 않거나, 아래쪽 스티커를 뽑은 경우 중 최대에 현재 스티커 더하기
    int tempUp = Math.max(nothing, down) + stickers[0][index];

    // 현재 열에서 아래쪽 스티커를 뽑는 경우
    // -> 이전 열에서 아무것도 뽑지 않거나, 위쪽 스티커를 뽑은 경우 중 최대에 현재 스티커 더하기
    int tempDown = Math.max(nothing, up) + stickers[1][index];

    nothing = tempNothing;
    up = tempUp;
    down = tempDown;
}
```

- 2행 n열까지 배열을 열단위로 탐색한다.
- nothing : 현재 열에서 아무 스티커도 선택하지 않는 경우 뗄 수 있는 스티커의 최댓값
- up : 현재 열에서 위쪽 스티커를 선택하는 경우 뗄 수 있는 스티커의 최댓값
  - 이 경우는 이전 단계에서 아무 스티커도 떼지 않았거나, 아래쪽 스티커를 뗀 경우의 값 중 최댓값과 더해야 한다.
- down : 현재 열에서 아래쪽 스티커를 선택하는 경우 뗄 수 있는 스티커의 최댓값
  - 이 경우는 이전 단계에서 아무 스티커도 떼지 않았거나, 위쪽 스티커를 뗀 경우의 값 중 최댓값과 더해야 한다.

## :black_nib: **Review**
- 각 경우는 나눠 생각하면 금방 풀리는 문제였다!
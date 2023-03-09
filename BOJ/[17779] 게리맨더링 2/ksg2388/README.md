# [17779] 게리맨더링2 - Java

## :pushpin: **Algorithm**

구현, 브루트포스

## :round_pushpin: **Logic**

기준점 (x, y)와 경계의 길이 d1, d2를 정해 다섯 개의 선거구로 나눈 뒤에 가장 큰 인구를 가진 선거구와 가장 작은 인구를 가진 선거구의 차이가 최소가 되는 경우를 찾음.

```java
// 경계선 설정
for (int i = 0; i <= d1; i++) {
    visited[x + i][y - i] = true;
    visited[x + d2 + i][y + d2 - i] = true;
}

for (int i = 0; i <= d2; i++) {
    visited[x + i][y + i] = true;
    visited[x + d1 + i][y - d1 + i] = true;
}
```

- 4방향의 경계선을 먼저 그어둔다.

```java
// 1번구역 인구 계산
for (int i = 0; i < x + d1; i++) {
    for (int j = 0; j <= y; j++) {
        if (visited[i][j]) {
            break;
        }
        ward[0] += map[i][j];
    }
}
// 3번구역 인구 계산
for (int i = x + d1; i < N; i++) {
    for (int j = 0; j < y - d1 + d2; j++) {
        if (visited[i][j]) {
            break;
        }
        ward[2] += map[i][j];
    }
}
```

- 1번 구역과 3번 구역은 왼쪽에서 오른쪽으로 채워나가면서 경계선을 만난 경우 멈춘다.

```java
// 2번구역 인구 계산
for (int i = 0; i <= x + d2; i++) {
    for (int j = N - 1; j >= y + 1; j--) {
        if (visited[i][j]) {
            break;
        }
        ward[1] += map[i][j];
    }
}
// 4번구역 인구 계산
for (int i = x + d2 + 1; i < N; i++) {
    for (int j = N - 1; j >= y - d1 + d2; j--) {
        if (visited[i][j]) {
            break;
        }
        ward[3] += map[i][j];
    }
}
```

- 2번 구역과 4번 구역은 오른쪽에서 왼쪽으로 채워나가면서 경계선을 만난 경우 멈춘다.

## :black_nib: **Review**

- 어떻게하면 편하게 선거구를 나눌 수 있을까 고민을 많이했다.
- 방법이 떠오르지 않아 그냥 문제에 나와있는대로 범위를 설정하여 문제를 해결하였다.

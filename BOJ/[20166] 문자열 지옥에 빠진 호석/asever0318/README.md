# [20166] 문자열 지옥에 빠진 호석

## :pushpin: **Algorithm**

DFS

## :round_pushpin: **Logic**

```java
static void casesCount(int x, int y, String nStr, int cnt) {

    // map에 저장된 신이 좋아하는 문자열일 경우만 카운트 한다
    if(strCount.containsKey(nStr)) {
        strCount.put(nStr, strCount.get(nStr)+1);
    }

    if(cnt == 5) {
        return;
    }

    for(int d = 0; d < 8; d++) {
        int nx = x + dx[d];
        int ny = y + dy[d];

        // 격자를 벗어나면 방향에 따라 환형 다음 위치로 좌표 바꿔주기
        int[] nPos = checkAnnular(nx, ny);
        nx = nPos[0];
        ny = nPos[1];

        casesCount(nx, ny, nStr+map[nx][ny], cnt+1);
    }
}
```

- 신이 좋아하는 문자열을 미리 Map에 value는 0으로 초기화 해놓고 경우의 수를 카운트한다.
- 모든 좌표에서 DFS를 통해 경우의 수를 카운트한다. 신이 좋아하는 문자열의 최대 길이는 5이기 때문에 5가 될때까지만 반복한다.
- 상하좌우+대각선까지 8방향을 탐색하는데, 격자는 환형으로 이루어져 있기 때문에 격자를 벗어나면 다음 좌표로 이동해서 탐색을 진행한다.
- 매번 다음 칸을 갈 때마다 해당 좌표에 있는 문자를 이전 문자열 뒤에 붙여서 해시맵에 이미 저장된 문자열이라면 카운트+1 한다.
- 최종적으로 해시맵에서 신이 좋아하는 문자열의 value를 찾아준다.

## :black_nib: **Review**

- 오랜만에 Map을 사용할 수 있었던 문제였다!
- 재귀를 이용할 땐 항상 기저조건의 순서가 헷갈리는 거 같다.

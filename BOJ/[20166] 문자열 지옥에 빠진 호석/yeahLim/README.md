# [20166] 문자열 지옥에 빠진 호석

## :pushpin: **Algorithm**

DFS, Map

## :round_pushpin: **Logic**

```java
/* DFS : 각 위치의 모든 문자열의 경우 찾기 */
for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
        StringBuilder sb = new StringBuilder();
        sb.append(map[i][j]);
        searchString(i, j, sb);
    }
}
```

- 모든 위치에서 모든 문자열의 경우를 찾아준다.

```java
private static void searchString(int curX, int curY, StringBuilder sb) {
    String str = sb.toString();
    if (stringCnt.containsKey(str)) {
        stringCnt.put(str, stringCnt.get(str) + 1);
    }

    if (sb.length() >= 5) {
        return;
    }

    for (int i = 0; i < dx.length; i++) {
        int nx = curX + dx[i];
        int ny = curY + dy[i];

        nx = nx == -1 ? n - 1 : nx;
        nx = nx == n ? 0 : nx;
        ny = ny == -1 ? m - 1 : ny;
        ny = ny == m ? 0 : ny;

        sb.append(map[nx][ny]);
        searchString(nx, ny, sb);
        sb.deleteCharAt(sb.length() - 1);
    }
}
```

- 조건에 맞는 string인 경우 stringCnt에 값을 하나 올려준다.

## :black_nib: **Review**
- 문제 만드신 분이 굉장히 문학적이시다... '하루 종일 내리는 비에 세상이 출렁이고 구름이 해를 먹어 밤인지 낮인지 모르는 어느 여름 날'이라니... 서정적이라 감동받을 뻔 했다..
- string을 어떻게 dfs로 돌릴 수 있는지 알 수 있었던 좋은 시간이었다.
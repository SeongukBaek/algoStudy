# [17136] 색종이 붙이기

## :pushpin: **Algorithm**

브루트 포스, 백트래킹

## :round_pushpin: **Logic**

```java
private static void pasteColoredPaper(int currentX, int currentY, int[] coloredPapers, int count) {
    // 다 탐색한 경우 정답을 갱신
    if (currentX == 9 && currentY == 10) {
        coloredPaperCount = Math.min(coloredPaperCount, count);
        return;
    }
    // 오른쪽으로 이동하다가 종이밖으로 벗어나면 아래 행으로 이동
    if (currentY == 10) {
        pasteColoredPaper(currentX + 1, 0, coloredPapers, count);
        return;
    }

    // 이미 구한 최소 개수보다 많아지는 경우는 더 이상 탐색하지 않는다.
    if (count >= coloredPaperCount) {
        return;
    }

    // 아직 색종이를 붙이지 않은 1인 칸이라면
    if (board[currentX][currentY] == 1) {
        // 큰 색종이부터 붙여보기
        for (int coloredPaper = 4; coloredPaper >= 0; coloredPaper--) {
            // 해당 색종이가 없거나, 현재 색종이를 붙일 수 없는 경우는 패스
            if (coloredPapers[coloredPaper] == 0 || !canPasteColoredPaper(coloredPaper, currentX, currentY)) {
                continue;
            }

            attachColoredPaper(currentX, currentY, coloredPaper);
            coloredPapers[coloredPaper]--;
            pasteColoredPaper(currentX, currentY + 1, coloredPapers, count + 1);
            detachColoredPaper(currentX, currentY, coloredPaper);
            coloredPapers[coloredPaper]++;
        }
        return;
    }
    pasteColoredPaper(currentX, currentY + 1, coloredPapers, count);
}
```

- 색종이를 붙일 수 있는 영역인 경우, 가장 큰 색종이부터 붙일 수 있다면 붙여보고, 다음 1을 찾아 반복한다.
- 이때, 주어진 색종이로 다 붙일 수 없는 경우, 백트래킹으로 다른 색종이를 붙여보면서 최소의 개수를 찾는다.
- 만약 최소 개수를 저장하는 변수가 갱신되지 않았다면 이는 색종이로 다 붙일 수 없음을 의미한다.

## :black_nib: **Review**
- 결국 모든 경우를 해보는 수밖에 없었고, 이를 boolean[][]으로 표현해서 해결할 수 있을 것 같았다.
- 하지만 경우는 3가지였다. 색종이를 안 붙여야 하는 곳, 붙일 수 있는 곳, 이미 붙인 곳.
- 정답 코드를 보고도 이해가 되지 않아서 한참을 붙들고 있었다..
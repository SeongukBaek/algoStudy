# [17136] 색종이 붙이기

## :pushpin: **Algorithm**

브루트 포스, 백트래킹

## :round_pushpin: **Logic**

```java
private static void getMinPaperCnt(int space, int cnt, int[][] paper, int[] paperCnt) {

  // 모든 칸에 색종이를 붙였을 경우 종료
  if (space == 0) {
    minCnt = Math.min(minCnt, cnt);
    return;
  }

  if (cnt >= minCnt) {
    return;
  }

  for (int i = 0; i < PAPER_SIZE; i++) {
    for (int j = 0; j < PAPER_SIZE; j++) {
      if (paper[i][j] != 1) {
        continue;
      }
      for (int size = COLORPAPER_TYPE; size > 0; size--) {
        // size크기의 색종이를 다 사용한 경우
        if (paperCnt[size - 1] >= 5) {
          continue;
        }

        int[][] copyPaper = attachColorPaper(i, j, paper, size);
        // 색종이를 붙이지 못하는 경우
        if (copyPaper == null) {
          continue;
        }

        paperCnt[size - 1]++;
        getMinPaperCnt(space - (size * size), cnt + 1, copyPaper, paperCnt);
        paperCnt[size - 1]--;
      }
      //모든 크기를 확인하고 나면 그 전 단계로 돌아가기
      return;
    }
  }
}
```

탐색 좌표가 1이라면 한변의 길이가 5인 색종이부터 붙이고 다음 단계로 들어간다.<br/>
그리고 space 변수를 따로 두어 모든 1을 색종이로 덮었는지 확인한다.<br/>

## :black_nib: **Review**

- 로직을 생각하는 것부터 어려웠던 문제였다.
  - 백트래킹으로 모든 경우를 확인하면서 가지치기를 하는 부분이 제일 힘들었다. 특히 현재 좌표에서 모든 크기의 종이가 들어갈 수 있는지 확인을 다 한다면 다음 좌표가 아닌 이전 단계로 돌아가야 한다는 것을 이해하는데 오랜 시간이 걸렸다.

# [17136] 색종이 붙이기

## :pushpin: **Algorithm**

브루트 포스, 백트래킹

## :round_pushpin: **Logic**


- 백트래킹으로 모든 보드에서 색종이를 붙일 수 있는 공간을 찾는다.

```java
// 색종이를 붙일 수 있는 공간일 경우
if(board[x][y] == 1) {
    for(int i=4; i>=0; i--) {
        if(paper[i] == 0 || !searchSize(x, y, i)) continue;
        attachPaper(x, y, i, 0); // 색종이 붙이기
        paper[i]--;
        searchBoard(x, y+1, total+1);
        attachPaper(x, y, i, 1); // 색종이 떼기
        paper[i]++;
    }
}
```

- 색종이를 붙일 수 있는 공간이라면, searchSize()를 통해 어떤 사이즈의 색종이를 붙일 수 있는지 확인한다.
    - 5x5부터 1x1까지의 색종이를 하나씩 대입한다.
- 사이즈를 찾고, 그 사이즈의 색종이가 남아 있다면, 색종이를 붙인다.
- 위를 반복해서, 색종이의 최소 개수를 구한다.

## :black_nib: **Review**
- 백트래킹과 dfs의 근본적인 차이점을 알 수 있었다. dfs는 그래프 기반의 알고리즘이고 백트래킹은 그래프와는 무관하다.
- 배열을 복사하지않고, 색종이를 붙이고 떼는 방법을 사용하는 것이 더 효율이 좋은 것 같다.
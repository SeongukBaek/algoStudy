# [17406] 배열 돌리기 4

## :pushpin: **Algorithm**

완전탐색, 구현

## :round_pushpin: **Logic**

배열 회전 순서는 순열 알고리즘으로 구했다.

```java
static void order(int d, int[][] array, boolean[] visited) {
		
    if(d == K) {
        checkMin(array);
        return;
    }
    
    for(int i = 0; i < K; i++) {
        if(visited[i]) continue;
        visited[i] = true;
        order(d+1, rotateArr(rotations[i].r, rotations[i].c, rotations[i].s, array), visited);
        visited[i] = false;
    }
    
}
```
배열 회전 순서가 결정되면 바로 회전을 하고 그 배열을 인자로 넘겨준다.

```java
for(int i = 0; i < s; i++) {
    int sx = x + i;
    int sy = y + i;
    int ex = r + s - i;
    int ey = c + s - i;
    
    for(int j = 1; j < 2*(s-i)+1; j++) {
        temp[sx][sy+j] = array[sx][sy+j-1]; //상
        temp[sx+j][ey] = array[sx+j-1][ey]; //좌
        temp[ex][ey-j] = array[ex][ey-j+1]; //하
        temp[ex-j][sy] = array[ex-j+1][sy]; //우
    }  
}
```
배열 회전은 index값을 사용해 4변을 각각 회전시켰다.

## :black_nib: **Review**

- 회전 연산 개수가 최대 6개이기 때문에 다른 방법을 생각안하고 완전탐색 방법으로 구현했다.
- 배열 돌리기는 단순 구현 문제로 배열을 어떤 방법으로 돌릴지만 고민했던 문제였다.
    - 원본 array를 회전시키면 다시 원상복구 시켜야 하기에 temp array를 따로 만들어 회전한 배열을 저장시켰다. 
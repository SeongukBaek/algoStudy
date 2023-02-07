# [14500] 테트로미노

## :pushpin: **Algorithm**

완전탐색

## :round_pushpin: **Logic**

테트로미노를 놓았을 때 놓인 칸에 쓰여 있는 수들의 합 중 최대 값을 구하는 문제이다.<br/>
<br/>
모든 블록이 나타낼 수 있는 방법을 모든 좌표에서 시행한다.
```java
boolean[][] visited = new boolean[r][c];
for(int i = 0; i < r; i++) {
    for(int j = 0; j < c; j++)
    {	
        visited[i][j] = true;
        dfs(i,j,0,arr[i][j], visited);
        visited[i][j] = false;
        another(i,j);
    }
}

```
<br/>

`ㅜ`모양을 제외하고 한 지점에서 모든 블럭을 놓고 회전, 대칭을 했을 경우 사용되는 좌표가 dfs로 4깊이만큼 사방탐색한 결과랑 같기 때문에 dfs로 깊이가 4인 탐색을 한다.<br/>
하지만 처음 dfs를 부를 때 하나의 좌표를 탐색했기에 종료조건은 `d==3`으로 해준다.
```java

static void dfs(int x, int y, int d, int sum, boolean[][] visited) {
		
    if(d==3) {
            max = Math.max(max, sum);
            return;
    }
    
    for(int i = 0; i <4; i++) {
        int dx = x + dr[i];
        int dy = y + dc[i];
        
        if(dx < 0 || dy < 0 || dx >= r || dy >= c)
            continue;
        if(visited[dx][dy])
            continue;
        
        visited[dx][dy] =true;
        dfs(dx, dy, d+1, sum+arr[dx][dy], visited);
        visited[dx][dy] =false;
    }
    
}

```
<br/>
`ㅜ`모양 블럭은 한번에 dfs로 탐색할 수 없어서 따로 처리를 해준다.<br/>
`ㅜ`모양 블럭의 회전, 대칭한 좌표값을 구하여 칸의 숫자 합을 구했다.

```java

static void another(int x, int y) {
    if(x-1 >= 0 && y+2 < c)
        max = Math.max(arr[x][y] + arr[x][y+1] + arr[x][y+2] + arr[x-1][y+1],max);
    
    if(x-2 >= 0 && y-1 >= 0)
        max = Math.max(arr[x][y] + arr[x-1][y] + arr[x-2][y] + arr[x-1][y-1],max);
    
    if(x+1 < r && y-2 >= 0)
        max = Math.max(arr[x][y] + arr[x][y-1] + arr[x][y-2] + arr[x+1][y-1],max);
    
    if(x+2 < r && y+1  < c)
        max = Math.max(arr[x][y] + arr[x+1][y] + arr[x+2][y] + arr[x+1][y+1],max);
}

```

위에서 부터 `ㅗ`,`ㅓ`,`ㅜ`,`ㅏ` 모양 순을 좌표를 나타냈다.

## :black_nib: **Review**
- 여기서 힌트는 블럭 4개로 만들 수 있는 모든 블럭이 있는 것이었다. 그래서 dfs로 한번에 갈 수 없는 `ㅜ`모양을 제외하고 한 지점에서 4만큼 갔을 경우를 dfs로 길이 4만큼 사방탐색한 결과랑 같는 것을 알게 되었다.
    - 처음에 모든 블럭의 좌표를 구해서 풀어야 하는 문제인지 고민을 했던 문제이다.
- visited배열 처리가 까다로운 문제였다. dfs함수에서 dfs종료후 false처리를 해주지 않으면 다른 모양의 블럭이 그 칸을 포함하여 블럭을 놓지 못하기때문에 false를 해줘야 한다.
    - 내가 풀어본 사방탐색 문제들은 갔던 길은 다시 안가는 성질의 문제들이 많아서 false로 다시 설정할 이유가 없었다. 그래서 이 문제를 익숙한 대로 풀려고하여 헷갈렸던 것같다.
    - 깊이가 정해진 탐색이라서 false로 값을 풀어줬을 경우 오버플로우가 날 걱정을 안해도 된다.

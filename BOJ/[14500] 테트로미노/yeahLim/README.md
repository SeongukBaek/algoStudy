# [14500] 테트로미노

## :pushpin: **Algorithm**

완전탐색

## :round_pushpin: **Logic**

- ㅡ, ㄱ, ㅁ, _|- 는 dfs를 이용해 구함
```java
 static void dfs(int i, int j, int depth, int total) {

        if (depth == 4){
            if(total > result)
                result = total;
            return;
        }

        for(int k=0; k<4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if ((0 <= x && x < n && 0 <= y && y < m) && visited[x][y] == 0) {
                visited[x][y] = 1;
                dfs(x, y, depth+1, total+paper[x][y]);
                visited[x][y] = 0;
            }
        }
    }

```
- combi()는 ㅗ,ㅜ,ㅏ,ㅓ는 '+'모양의 가운데를 제외한 4부분에서 3부분을 선택해서 조합한 값들을 구함
```java
static void combi(int i, int j, int depth, int total) {
        if(depth == 3){
            if(total > result)
                result = total;
            return ;
        }

        for(int k=0; k<4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if((0 <= x && x < n && 0 <= y && y < m) && v[x][y] == 0) {
                v[x][y] = 1;
                combi(i, j, depth+1, total+paper[x][y]);
                v[x][y] = 0;
            }
        }
```





## :black_nib: **Review**
- 2차원 배열 생성 시, 2차원 배열의 객체 생성에 주의해야겠다
  - 생성 안할 시 null pointer Exception 발생

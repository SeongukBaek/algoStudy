# [2931] 가스관

## :pushpin: **Algorithm**

구현, DFS

## :round_pushpin: **Logic**

```java
if(pipe == 'M') {
    for(int i=0; i<4; i++) {
        int nx = dx[i] + x;
        int ny = dy[i] + y;
        
        if(nx<0 || nx>=r || ny<0 || ny>=c) continue;
        
        if(map[nx][ny] != '.') {            	
            checkPipe(nx, ny);
        }
    }
}
```

- 문제에서 모스크바에 인접한 블록은 하나로 주어지기 때문에, 모스크바에서 시작해, 사방을 탐색해 파이프 블록을 찾는다.

```java
// |일때 상,하 체크
else if(pipe == '|') {
    checkPipe(x-1, y);
    checkPipe(x+1, y);
}
```
- 이어서 각 파이프 모양의 조건마다 아직 방문하지 않은 연결되는 파이프 블록을 찾는다.

```java
// |일때 상,하 체크
else if(pipe == '.') {
    addPipe(x, y);
}
```
- 이어서 연결되어야 하는 곳에 파이프가 없다면 addPipe()를 해준다.

```java
if(cnnted[0] && cnnted[1] && cnnted[2] && cnnted[3]) { // '상,하,좌,우'에서 연결이 필요할때
    pipe = '+';
} else if(cnnted[0] && cnnted[1]) { // '상,하'에서 연결이 필요할때
    pipe = '|';
} else if(cnnted[2] && cnnted[3]) { // '좌,우'에서 연결이 필요할때
    pipe = '-';
} else if(cnnted[1] && cnnted[3]) { // '하,우'에서 연결이 필요할때
    pipe = '1';
} else if(cnnted[0] && cnnted[3]) { // '상,우'에서 연결이 필요할때
    pipe = '2';
} else if(cnnted[0] && cnnted[2]) { // '상,좌'에서 연결이 필요할때
    pipe = '3';
} else if(cnnted[1] && cnnted[2]) { // '하,좌'에서 연결이 필요할때
    pipe = '4';
}
```
- 파이프가 필요한 좌표를 기준으로 사방을 탐색해 연결이 필요한 곳에 true값을 주어서 필요한 파이프의 모양을 찾는다.

## :black_nib: **Review**
- 4방을 탐색하는 dfs로 구현하는 것이 아니라, 각 파이프의 모양에 따라 4방중에 원하는 곳만 탐색하게 풀었다. 가지치기식 dfs로 푸니까 좀 더 효율이 좋은 것 같고 재밌었다. 
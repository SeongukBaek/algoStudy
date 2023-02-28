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

- 스장

```java
else if(pipe == '.') {
    addPipe(x, y);
}
```

- 수정
## :black_nib: **Review**
- 수정
# [49994] 방문 길이

## :pushpin: **Algorithm**

그래프 탐색

## :round_pushpin: **Logic**

```java
 for(int i = 0; i < dirs.length(); i++){
    char order = dirs.charAt(i);
    int dir = 0;
    
    if(order == 'D'){
        dir = 1;
    }else if(order == 'R'){
        dir = 2;
    }else if(order == 'L'){
        dir = 3;
    }
    
    int dx = curX + dr[dir];
    int dy = curY + dc[dir];
    
    if(isMapOut(dx, dy)){
        continue;
    }
    
    if(!map[dx][dy][dir]){
        map[dx][dy][dir] = true;
        // 반대 방향도 방문처리를 해준다.
        map[curX][curY][getOppositeDir(dir)] = true;
        answer++;
    }
    // 이동하기
    curX = dx;
    curY = dy;
}
```

- 배열을 3차원으로 만들어 [x좌표][y좌표][방향]으로 사용한다.
- 이동하면서 방문하지 않은 경로이면 답에 +1을 해준다.
- 이때 1->2 와 2->1 의 경로는 같기 때문에 반대 방향도 방문처리를 해준다.

## :black_nib: **Review**

- 처음 문제를 보았을 때 쉽다고 생각하고 바로 풀었는데 바로 틀렸다. 반대 방향에 대해서 방문처리를 해줘야 했다... 다음엔 차근차근 생각하면서 문제를 풀어야 겟다.
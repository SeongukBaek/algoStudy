# [14502] 연구소

## :pushpin: **Algorithm**

BFS, 브루트포스

## :round_pushpin: **Logic**

```java
static void buildWall(int d, int start) {
    	
    if( d == WALLCOUNT ) {
        spreadVirus();
        return;
    }
    
    for(int i = start; i < wallSpace.size(); i++) {
        int x = wallSpace.get(i).x;
        int y = wallSpace.get(i).y;
        
        if(lab[x][y] != 0) {
            continue;
        }
        
        lab[x][y] = 1;
        buildWall(d+1, i+1);
        lab[x][y] = 0;
    }
}
```
조합을 사용하여 벽을 3개 세운다.

```java
 while(!viruses.isEmpty()) {
    Node cur = viruses.poll();
    
    for(int i = 0; i < 4; i++) {
        int dx = cur.x + dr[i];
        int dy = cur.y + dc[i];
        
        if(!arrayBoundsValidation(dx, dy)) {
            continue;
        }
        if(tempLab[dx][dy] == 1 || tempLab[dx][dy] == 2) {
            continue;
        }
        
        viruses.add(new Node(dx, dy));
        tempLab[dx][dy] = 1;
        tempCleanSpace--;
    }
}

max = Math.max(max, tempCleanSpace);
```

그리고 bfs를 사용하여 바이러스를 퍼트리는 동시에 남은 안전영역을 계산했다.


## :black_nib: **Review**
- 저번에 풀어 본 문제라서 브루트포스로 풀어야 한다는 것을 알고 있어 쉽게 풀었다.
- 맨 처음 문제를 접했을 때 브루트포스로 풀면 시간초과가 날 것이라는 이상한 생각을 가지고 있어서 브루트포스로 풀어야 한다는 생각을 하지 못했다. 하지만 배열크기의 최대가 3*8 = 24 이고 벽을 세우는 모든 경우의 수는 24C3 = 2,024 밖에 되지 않는다. 이 문제를 통해서 브루트포스에 대한 걱정을 줄이게 되었다.
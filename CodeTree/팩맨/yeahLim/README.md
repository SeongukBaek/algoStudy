# [삼성 SW 역량테스트] 팩맨

## :pushpin: **Algorithm**

구현, 시뮬레이션, DFS

## :round_pushpin: **Logic**

```java
private static void makePackManRoutes(int[] route, int count) {
  if (count == 3) {
    packManRoutes.add(route.clone());
    return;
  }

  for (int move = 0; move < 8; move += 2) {
    route[count] = move;
    makePackManRoutes(route, count + 1);
  }
}
```

- 중복 순열을 이용해 우선순위를 기반으로 한 모든 경로를 저장한다.

```java
private static void playPacman() {
    // 1. 몬스터 복제 시도
    copyMonsters();
    // 2. 몬스터 이동
    moveMonsters();
    // 3. 팩맨 이동
    monsterCount = -1;
    visited = new boolean[4][4];
    visited[pacman[0]][pacman[1]] = true;
    movePacman(0, 0, new int[3][2], pacman[0], pacman[1]);
    // 몬스터 먹어치우기
    killMonsters();
    // 4. 몬스터 시체 소멸
    perishCorpses();
    // 5. 몬스터 복제 완성
    hatchMonsters();
}
```

- 한 턴에 주어진 순서대로 로직을 실행한다.

```java
/* 3. 팩맨 이동 : DFS */
private static void movePacman(int depth, int tmpMonsterCount, int[][] tmpPath, int x, int y) {
    if(depth == 3) {
        // 몬스터의 수가 더 많을 때
        if(tmpMonsterCount > monsterCount) {
            for(int i=0; i<3; i++) {
                path[i] = tmpPath[i].clone();
            }
            monsterCount = tmpMonsterCount;
            pacman[0] = path[2][0];
            pacman[1] = path[2][1];
        }
        return;
    }

    for(int i=0; i<8; i+=2) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if(nx<0 || nx>=4 || ny<0 || ny>=4) continue;
        tmpPath[depth][0] = nx;
        tmpPath[depth][1] = ny;
        // 이미 방문한 경우
        if(visited[nx][ny]) {
            movePacman(depth+1, tmpMonsterCount, tmpPath, nx, ny);
        }
        // 아닌 경우
        else {
            visited[nx][ny] = true;
            movePacman(depth+1, tmpMonsterCount+monsters[nx][ny].size(), tmpPath, nx, ny);
            visited[nx][ny] = false;
        }
    }
}
```

- 몬스터가 없을때, 첫 우선순위의 곳으로 이동시키기 위해, monsterCount의 초기값을 -1, tmpMonsterCount의 초기값을 0으로 설정해서, 최초 한 번은 depth==3일 때 실행이 되도록 짰다.
- 팩맨이 이미 방문한 곳을 재방문 할 수도 있기 때문에, `visited[nx][ny]`가 true일때와 false일때 각각 나누어서 처리한다.
  - 첫 방문일 경우 : tmpMonsterCount에 몬스터 수를 더하고, return되면 false처리 한다.
  - 이미 방문한 경우 : tmpMonsterCount에 몬스터 수를 더해주지 않는다.

## :black_nib: **Review**

- 방대한 데이터 양이 제시된 시뮬레이션 문제를 풀 때, 주어진 요건대로만 푸는 것이 아니라, 시간/메모리 초과를 고려해서 문제를 풀어야 한다.
  - 몬스터의 수가 100개 가까이 주어질 수도 있으니, 이 경우 몬스터 하나하나에 대한 자료구조를 만드는 것이 아니라, 4X4배열을 이용해서 자료구조를 만들어야 한다.
  - 몬스터 시체는 시체의 개수는 로직에 필요가 없기 때문에 4X4배열을 이용해서, 시체가 새로 발생할때마다 시체의 남은 turn만 업데이트하는 식으로 진행해야 한다.
- 몬스터 이동하는 함수가 가장 오래 걸렸다. 어렵지 않은 문제인데, 문제에 적혀져있는대로, 몬스터를 이동하는 장소로 옮기고, 현재 위치에서 지우는 방식을 사용했다. 왜 새로운 배열을 만들어서 복사하는 방법이 안 떠올랐는지 모르겠다!! 앞으로는 배열 복사를 잘 떠오를 예정이다!

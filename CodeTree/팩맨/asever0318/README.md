# [삼성 SW 역량테스트] 팩맨

## :pushpin: **Algorithm**

구현, 시뮬레이션

## :round_pushpin: **Logic**

```java
//static List<Monster> monsters; - 이전 코드
static List<Monster>[][] monsters;
```

- - 몬스터를 저장하는 자료구조를 List<Monster>에서 리스트배열 List<Monster>[][]로 변경해주었다.

```java
findcomb(0, 0); // 팩맨 루트 경우의 수 구하기

        // t턴만큼 반복
        for(int i = 0; i < t; i++) {
        	// 1. 몬스터 복제
            copyMonster();

            // 2. 몬스터 이동
            moveMonster();

            // 3. 팩맨 이동
            maxEat = -1;
            findPacmanRoute(); // 3-1. 팩맨이 몬스터를 가장 많이 먹는 루트 찾기
            killMonster(); // 3-2. 루트대로 몬스터 죽이고 시체 처리

            // 4. 시체 소멸
            removeDead();

            // 5. 몬스터 복제 완성
            bornMonster();

            // 6. 시체시간 ++
            timePlus();
        }

        System.out.println(countMonster());
```

- 총 6단계로 처리했다.

1. 몬스터 복제
2. 몬스터 이동
3. (1) 팩맨 루트 찾기 (2) 루트대로 몬스터 죽이기
4. 시체 소멸
5. 몬스터 부화
6. 시체 시간 1 증가

```java
static void findcomb(int n, int index) {
    	if(n == 3) {
    		for(int i = 0; i < 3; i++) {
    			comb[count][i] = pRoute[i];
    		}
    		count++;
    		return;
    	}

    	for(int i = 1; i <= 4; i++) {
    		pRoute[n] = i;
            findcomb(n+1, index+1);
        }
    }
```

- 4가지 방향 중 3가지 뽑는 조합을 통해서 팩맨이 이동할 수 있는 모든 경우의 수를 구한다.
- 팩맨이 이동할 수 있는 모든 경우의 수를 미리 구해놓고, 이후에 팩맨이 이동할 때마다 해당 경우의 수를 확인해서 최적의 루트를 찾는다.

```java
static void moveMonster() {

    List<Monster>[][] copyMap = new ArrayList[5][5];
    int[][] countMap = new int[5][5];

    ...

    while(true) {

        int nx = m.x + dx[nd]; // 다음 이동 위치
        int ny = m.y + dy[nd];

        // 시체 x && 팩맨 x && 격자 벗어나지 x
        if(isVaildIndex(nx, ny) && deadTime[nx][ny] == 0 && !isPacman(nx, ny)) {
            copyMap[nx][ny].add(new Monster(nx, ny, nd));
            countMap[nx][ny]++;
            break;
        }

        // 시계 방향 45도 회전
        nd = rotate(nd);

        // 8방향 다 돌았는데도 움직일 수 없으면
        if(nd == m.d) {
            copyMap[m.x][m.y].add(new Monster(m.x, m.y, m.d));
            countMap[m.x][m.y]++;
            break;
        }
    }
}

// 원본 배열에 저장
for(int i = 1; i < 5; i++) {
    for(int j = 1; j < 5; j++) {
        monsters[i][j].clear();
        monsters[i][j].addAll(copyMap[i][j]);
        mCount[i][j] = countMap[i][j];
    }
}
```

2. 몬스터 이동

```java
// 팩맨 이동할 길 찾기  --> 상좌하우(1234)
static void findPacmanRoute() {

    for(int i = 0; i < 64; i++) {

        int cnt = countEat(comb[i]);
        if(maxEat < cnt) {
            maxEat = cnt;

            // max 갱신 될때마다 해당 루트도 저장해놓기
            for(int j = 0; j < 3; j++) {
                maxRoute[j] = comb[i][j];
            }
        }
    }
}
```

3-1. 팩맨이 이동할 루트 찾기 : 미리 찾아놓은 경우의 수 comb 배열을 돌면서 countEat() 함수를 통해 해당 루트에서 먹을 수 있는 몬스터 수를 구하고, maxEat값이 가장 큰 루트를 찾는다. maxEat 값이 변경 될 때마다 해당 루트도 maxRout 배열에 저장한다.

```java
static void killMonster() {
    // 루트 따라서 길에 몬스터 있으면 죽이기 --> 리스트에서 삭제
    int nx = pacman[0];
    int ny = pacman[1];

    for(int i = 0; i < 3; i++) {
        nx = nx + px[maxRoute[i]];
        ny = ny + py[maxRoute[i]];

        if(i == 2) {
            pacman[0] = nx;
            pacman[1] = ny;
        }

        if(mCount[nx][ny] > 0) { // 몬스터 있으면
            mCount[nx][ny] = 0; // 죽이고
            deadTime[nx][ny] = 1; // 시체 시간 1로 초기화
            // 몬스터 리스트에서 삭제

            monsters[nx][ny].clear();
        }
    }
}
```

3-1. 몬스터 죽이기 : 3-1에서 찾은 maxRoute를 따라서 가는 길에 있는 몬스터를 죽이고 시체 표시를 한다.

```java
/*
if(mCount[nx][ny] > 0) { // 몬스터 있으면
    mCount[nx][ny] = 0; // 죽이고
    deadTime[nx][ny] = 1; // 시체 시간 1로 초기화
    // 몬스터 리스트에서 삭제
    for(int j = 0; j < monsters.size(); j++) {
        Monster m = monsters.get(j);
        if(m.y == ny && m.x == nx) { // 해당 좌표에 있는 몬스터 리스트에서 삭제
            monsters.remove(j);
            j--;
        }
    }
}
*/
```

- 이전 코드에서는 리스트로 몬스터를 관리했기 때문에 제거할 때 리스트 처음부터 돌면서 좌표가 같으면 remove()하는 방식을 사용했는데, 몬스터 자료구조를 리스트배열로 바꾸면서 해당 좌표의 리스트를 clear() 해주는 방법으로 시간을 많이 줄일 수 있었다.

```java
static void removeDead() {
    for(int i = 1; i < 5; i++) {
        for(int j = 1; j < 5; j++) {
            if(deadTime[i][j] >= 3) { // 2턴 지난 시체는 삭제
                deadTime[i][j] -= 3;
            }
        }
    }
}
```

4. 시체 삭제 : 2턴이 지난 시체는 없어져야 되므로, 시체가 생성되고 2턴이 지난 후인 3보다 클 경우 -3 해준다.

```java
 static void bornMonster() {
    for(int i = 0; i < eggs.size(); i++) {
        Monster m = eggs.get(i);
        monsters[m.x][m.y].add(new Monster(m.x, m.y, m.d));
        mCount[m.x][m.y]++;
    }
}
```

5. 몬스터 부화 : eggs 리스트에 있던 알들을 해당 좌표 몬스터 리스트에 추가하고, 맵에도 몬스터 수를 추가해준다.

```java
static void timePlus() {
    for(int i = 1; i < 5; i++) {
        for(int j = 1; j < 5; j++) {
            if(deadTime[i][j] > 0) {
                deadTime[i][j]++;
            }
        }
    }
}
```

- 6. 시체 시간 ++ : deadTime 맵에서 0보다 큰 값을 가지면 시체가 있는 것이고, 한 턴이 끝났으므로 +1 해준다.

## :black_nib: **Review**

- 처음에 팩맨 이동 우선순위를 '상좌하우'가 아니라 '상하좌우'로 잘못 설정해서 시간을 많이 썼다. 문제를 잘 확인하자 제발~
- 현재 코드가 테스트케이스 26번에서 시간초과 나는데 팩맨이 이동할 루트를 dfs로 찾는 방법이나 시체 시간을 관리하는 것을 다른 방법으로 생각해봐야 될 것 같다.
- +) 해결! 몬스터 자료구조를 List에서 List[][]로 변경하는 것으로 시간초과를 해결 할 수 있었다. 몬스터를 삭제할 때에 매번 모든 리스트를 돌면서 좌표가 같은 지 확인하고 같을 경우 삭제를 진행해서 시간이 오래 걸릴 수 밖에 없었다. 얼마전에 삼성SW역량테스트 [2112]보호필름 문제를 풀 때도 시간초과 때문에 고생했는데 이제는 문제 풀 때 시간도 고려해서 풀도록 노력해야겠다..

# [삼성 SW 역량테스트] 팩맨

## :pushpin: **Algorithm**

구현, 시뮬레이션

## :round_pushpin: **Logic**

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

- 팩맨이 이동할 수 있는 모든 경우의 수를 미리 구해놓고, 이후에 팩맨이 이동할 때마다 해당 경우의 수를 확인해서 최적의 루트를 찾는다.

```java
for(int i = 0; i < t; i++) {
        	// 1. ---------------------------------------------
            copyMonster(); // 몬스터 복제

            // 2. ---------------------------------------------
            moveMonster(); // 몬스터 이동

            // 3. ---------------------------------------------
            maxEat = -1;
            findPacmanRoute(); // 팩맨이 몬스터를 가장 많이 먹는 루트 찾기
            killMonster(); // 루트대로 몬스터 죽이고 시체 처리
          /
            // 4. ---------------------------------------------
            removeDead(); // 시체 소멸 --> deadTime이 2이면 삭제

            // 5. ---------------------------------------------
            bornMonster(); // 몬스터 복제 완료

           // 6. ---------------------------------------------
            timePlus(); // 시체 시간 ++

        }
        System.out.println(monsters.size());
    }
```

- 크게 6단계로 처리했다.
- 1. 몬스터 복제
- 2. 몬스터 이동
- 3. 팩맨 루트 찾기 --> 루트대로 몬스터 죽이기
- 4. 시체 소멸
- 5. 몬스터 부화
- 6. 시체 시간 ++

```java
// 1. 몬스터 복제 (알)
    static void copyMonster() {
        eggs = new ArrayList<>();

        for(int i = 0; i < monsters.size(); i++) {
            Monster m = monsters.get(i);
            eggs.add(new Monster(m.x, m.y, m.d));
        }
    }
```

- 1. 몬스터 복제 : 단순하게 eggs 리스트에 monsters 리스트를 복사해주었다.

```java
 // 2. 몬스터 이동
    static void moveMonster() {
        int[][] copyMap = new int[5][5];

        for(int i = 0; i < monsters.size(); i++) {

            Monster m = monsters.get(i);
            int nd = m.d;

            while(true) {

                int nx = m.x + dx[nd]; // 다음 이동 위치
                int ny = m.y + dy[nd];

                // 시체 x && 팩맨 x && 격자 벗어나지 x
                if(isVaildIndex(nx, ny) && deadTime[nx][ny] == 0 && !isPacman(nx, ny)) {
                    // 이동할 수 있으면 이동 --> 좌표 변경
                    monsters.get(i).y = ny;
                    monsters.get(i).x = nx;
                    monsters.get(i).d = nd;
                    copyMap[nx][ny]++; // 맵에 추가

                    break;
                }

                // 시계 방향 45도 회전
                nd = rotate(nd);

                // 8방향 다 돌았는데도 움직일 수 없으면
                if(nd == m.d) {
                    copyMap[m.x][m.y]++; // 맵에 추가
                    break;
                }
            }
        }

        for(int i = 1; i < 5; i++) {
            for(int j = 1; j < 5; j++) {
                mCount[i][j] = copyMap[i][j];
            }
        }
    }
```

- 2. 몬스터 이동

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

- 3-1. 팩맨이 이동할 루트 찾기

```java
// 몬스터 죽이고 시체표시하고 시체시간 표시
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
                for(int j = 0; j < monsters.size(); j++) {
                    Monster m = monsters.get(j);
                    if(m.y == ny && m.x == nx) { // 해당 좌표에 있는 몬스터 리스트에서 삭제
                        monsters.remove(j);
                        j--;
                    }
                }
            }
        }
    }
```

- 3-2. 몬스터 죽이기 : 3-1에서 찾은 루트를 따라서 가는 길에 있는 몬스터를 죽이고 시체 표시를 한다.

```java
// 2턴 지난 시체는 삭제
    static void removeDead() {
        for(int i = 1; i < 5; i++) {
            for(int j = 1; j < 5; j++) {

            	if(deadTime[i][j] >= 3) { // 2턴 지났으면 삭제
                    deadTime[i][j] -= 3;
                }
            }
        }
    }
```

- 4. 시체 삭제 : 2턴이 지난 시체는 없어져야 되므로, 시체 생성 시 1 --> 2 --> 3(2턴 지남)인 3보다 클 경우 -3 해준다.

```java
// 몬스터 부화
    static void bornMonster() {
        for(int i = 0; i < eggs.size(); i++) {
            Monster m = eggs.get(i);
            monsters.add(new Monster(m.x, m.y, m.d)); // 몬스터 리스트에 추가
            mCount[m.x][m.y]++;
        }
    }
```

- 5. 몬스터 부화 : eggs 리스트에 있던 알들을 monsters 리스트에 추가하고, 맵에도 몬스터 수를 추가해준다.

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

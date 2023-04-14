# [삼성 SW 역량테스트] 팩맨

## :pushpin: **Algorithm**

구현, 시뮬레이션, 중복 순열

## :round_pushpin: **Logic**
```java
static List<Integer>[][] monsters;
```
몬스터는 방향정보를 담는 리스트의 이차원 배열로 선언했다.<br/>
한 칸에 여러마리의 몬스터가 들어갈 수 있기때문에 리스트로 만들었다.<br/>

```java
private static void gameStart(int t) {
    for (int time = 0; time < t; time++) {
        // 몬스터 복제
        createMonsterEggs();
        // 몬스터 이동
        moveMonsters();
        // 팩맨 이동
        visited = new int[MAP_SIZE][MAP_SIZE];
        movePacman();
        // 시체 소멸
        removeMonsters();
        // 알에서 몬스터 생성
        createMonsters();
    }
}
```
위와 같은 순서대로 문제를 진행했다.<br/>

```java
private static void createMonsterEggs()
```
몬스터 알 리스트를 따로 만들어 현재 몬스터 좌표를 저장해준다.<br/>

```java
private static void moveMonsters()
```
현재 몬스터 방향으로 이동하나 시체와 팩맨이 있는 경우 다음 방향으로 이동한다. <br/>

```java
private static void movePacman()
```
팩맨은 방문했던 자리를 다시 방문할 수 있으며 이동하면서 몬스터를 잡는다.

```java
private static void getPacmanStep(int d, int r, int c, int monstersCnt, int[] order) {
    if (d == 3) {
        if (maxMonster >= monstersCnt) {
            return;
        }

        // 잡은 몬스터수가 크다면
        pacManOrder = order.clone();
        maxMonster = monstersCnt;
        return;
    }

    for (int i = 0; i < pacmanMove.length; i++) {
        int dx = r + pacmanMove[i][0];
        int dy = c + pacmanMove[i][1];

        if (isMapOut(dx, dy)) {
            continue;
        }
        
        int catchMonsterCnt = 0;
        // 방문하지 않은 칸이면
        if(visited[dx][dy] == 0) {
            // 몬스터를 잡는다
            catchMonsterCnt = monsters[dx][dy].size();
        }
        
        order[d] = i;
        visited[dx][dy] += 1;
        getPacmanStep(d + 1, dx, dy, monstersCnt + catchMonsterCnt, order);
        visited[dx][dy] -= 1;
    }
}
```
이때 팩맨이 움직일 수 있는 모든 경우를 확인하고 잡은 몬스터 수가 최대인 경우 경로를 저장한다.

```java
private static void removeMonsters()
```
모든 칸을 확인하며 시체가 있는 칸의 시간을 하나 빼준다.

```java
private static void createMonsters()
```
몬스터 알 리스트에 담아둔 좌표를 몬스터 배열에 넣어 몬스터를 증가시킨다.<br/>
그리고 알 리스트는 clear한다.

## :black_nib: **Review**
- 팩맨 visited처리가 골치아팠다. 
    - 방문처리가 되어야지 팩맨이 몬스터를 잡은 자리인지 확인할 수 있다.
    - 그리고 이후 DFS처리하고 이전 단계로 돌아갈 때 false처리가 안되도록 int로 방문처리를 해주었다.
- 몬스터 저장 방법, 시체 처리 방법으로 실행시간을 줄일 수 있다.
    - 몬스터를 3차원 배열로 (int[x좌표][y좌표][방향] = 몬스터 수) 저장하면 실행시간이 엄청 줄어든다! 하지만 다시 리팩토링하긴 귀찮아서 수정은 하지않았다.
- 이번 문제는 함수 하나하나를 테스트하기 어려웠던 문제였다. 테스트케이스 자체를 만들기가 어려워서,, 이런 문제는 어떻게 점검하면서 풀 수 있을지 고민해봐야갰다.
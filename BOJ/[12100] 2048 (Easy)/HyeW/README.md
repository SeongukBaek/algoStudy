# [12100] 2048 (Easy)

## :pushpin: **Algorithm**

구현, 백트래킹, 중복 순열

## :round_pushpin: **Logic**

```java
static void moveBlocks(int r, int[] order) {
    if (r == MOVECOUNT) {
        int[][] temp = copy();

        for (int i = 0; i < MOVECOUNT; i++) {
            switch (order[i]) {
            case 0:
                moveUp(temp);
                break;
            case 1:
                moveDown(temp);
                break;
            case 2:
                moveLeft(temp);
                break;
            case 3:
                moveRight(temp);
                break;
            }
        }
        return;
    }

    // 0: 상 , 1: 하, 2: 좌, 3: 우
    for (int i = 0; i < MOVETYPE; i++) {
        order[r] = i;
        moveBlocks(r+1,order);
    }
}
```

중복 조합을 사용해 5번 이동할 방향을 정한다.

```java
static void moveUp(int[][] board) {
    boolean[] isUnioned = new boolean[N];

    for (int i = 1; i < N; i++) {
        for (int j = 0; j < N; j++) {

            if (board[i][j] == 0) {
                continue;
            }

            int curX = i;
            while (curX > 0 && board[curX - 1][j] == 0) {
                curX--;
            }

            moveOneBlock(curX, j, i, j, board);
            if (curX == 0) {
                continue;
            }

            if (!isUnioned[j] && board[curX-1][j] == board[curX][j]) {
                isUnioned[j] = true;
                unionBlock(curX-1, j, curX, j, board);
            }else {
                isUnioned[j] = false;
            }
        }
    }
}
```

위로 이동할 때의 함수이다.<br/>
1. 위에 빈칸이 있다면 숫자가 나올 때까지 해당 블록을 이동한다.
2. 위에 블록과 합칠 수 있는지 확인하고 그에 따른 동작을 한다.<br/>
    현재 블록이 합쳐지게 된다면 isUnioned를 true로 바꿔준다. 다음 블록이 합쳐진 블록을 만났을 때 isUnioned가 true이면 합쳐지지 못하고 그 자리에 있게 된다. 그리고 현재 블록은 다음 블록과 합쳐질 수 있으니 isUnioned를 false로 바꿔준다.
<br/>


## :black_nib: **Review**
- 배열 복사를 하지않고 원본 배열에 변형이 생겨 답을 제대로 도출하지 못했다. 원본 배열 변형에 신경쓰며 배열 복사를 잘하자!

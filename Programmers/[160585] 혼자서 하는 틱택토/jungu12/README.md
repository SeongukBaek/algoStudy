# [160585] 혼자서 하는 틱택토

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

- 규칙을 어긴 경우는 아래와 같다.
- 1.  O의 개수가 X의 개수, X의 개수 + 1 와 같지 않은 경우
- 2.  O, X둘다 게임을 승리한 경우
- 3.  O만 승리하였는데 O의 개수가 X의 개수 + 1이 아닌 경우
- 4.  X만 승리하였는데 X의 개수가 O의 개수와 같지 않은 경우

```java
void countWin() {
    //가로에서 같은 표시가 만들어졌는지 Check
    for (int i = 0; i < 3; i++) {
        char pattern = board[i].charAt(0);

        if (pattern != board[i].charAt(1) || pattern != board[i].charAt(2)) {
            continue;
        }

        if (pattern == 'O') {
            oWin = true;
            continue;
        }

        if (pattern == 'X') {
            xWin = true;
        }
    }

    //세로에서 같은 표시가 만들어졌는지 Check
    for (int i = 0; i < 3; i++) {
        char pattern = board[0].charAt(i);

        if (pattern != board[1].charAt(i) || pattern != board[2].charAt(i)) {
            continue;
        }

        if (pattern == 'O') {
            oWin = true;
            continue;
        }

        if (pattern == 'X') {
            xWin = true;
        }
    }

    //대각선에서 같은 표시가 만들어졌는지 Check
    char pattern = board[0].charAt(0);
    if (pattern == 'O' && board[1].charAt(1) == pattern && board[2].charAt(2) == pattern) {
        oWin = true;
    }
    if (pattern == 'X' && board[1].charAt(1) == pattern && board[2].charAt(2) == pattern) {
        xWin = true;
    }

    pattern = board[0].charAt(2);
    if (pattern == 'O' && board[1].charAt(1) == pattern && board[2].charAt(0) == pattern) {
        oWin = true;
    }
    if (pattern == 'X' && board[1].charAt(1) == pattern && board[2].charAt(0) == pattern) {
        xWin = true;
    }
}
```

- 배열의 모든 row, column, 대각선을 순회하며 게임을 승리한 사람이 있는지 확인한다.

```java
if (oCnt != xCnt && oCnt != xCnt + 1) {
    return 0;
}

//선공만 이긴 경우 조건에 부합하는지 check
if (oWin && !xWin && oCnt != xCnt + 1) {
    return 0;
}

//후공만 이긴 경우 조건에 부합하는지 check
if (!oWin && xWin && oCnt != xCnt) {
    return 0;
}

//둘 다 이기는 경우
if (oWin && xWin) {
    return 0;
}

return 1;
```

- 맨 위에 적은 조건에 해당한다면(실수한 경우) 0을 반환한다.
- 조건들에 걸리지 않는다면 1을 반환한다.

## :black_nib: **Review**

- 어렵지 않은 구현 문제였으나....
- O를 0으로 적은 부분을 못찾아서 푸는데 오래 걸렸다.

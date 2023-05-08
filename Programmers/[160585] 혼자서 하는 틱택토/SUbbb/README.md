# [160585] 혼자서 하는 틱택토

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
// 아직 아무것도 안 한 상태
if (o == 0 && x == 0) {
    return 1;
}

// o보다 x개수가 많다면 o 표시를 빼먹고 x를 표시한 것
// o 개수와 x개수가 1개 차이가 아니라면 x를 표시하지 않은 것
if (o < x || o - x > 1) {
    return 0;
}

// 둘 다 이미 종료되었는데 진행하는 경우 확인
boolean oEnd = checkEnd('O');
boolean xEnd = checkEnd('X');

// 동시에 이길 수 없다.
if (oEnd && xEnd) {
    return 0;
}

// o 개수가 더 많은 경우, x가 이미 끝나있을 수 없다.
if (o > x && xEnd) {
    return 0;
}

// 두 개 개수가 같은 경우, o가 이미 끝나있을 수 없다.
if (o == x && oEnd) {
    return 0;
}
```

- 확인해야 할 조건들
- o와 x 개수 모두 0개이면, 가능하다.
- o보다 x 개수가 많거나, 두 개의 차이가 1보다 크다면 불가능하다.
- o와 x는 동시에 이기는 경우가 나올 수 없다.
- o의 개수가 더 많은데, x는 이길 수 없다.
- 두 개수가 동일한데 o가 이미 이길 수 없다.

```java
private boolean checkEnd(char word) {
    for (int x = 0; x < 3; x++) {
        for (int y = 0; y < 3; y++) {
            if (board[x].charAt(y) == word) {
                if (x == 0 && checkVertical(word, y)) {
                    return true;
                }
                if (y == 0 && checkHorizontal(word, x)) {
                    return true;
                }
                if ((x == 0 && (y == 0 || y == 2)) && checkDiagonal(word, y)) {
                    return true;
                }
            }
        }
    }

    return false;
}
```

- 문자를 발견한 위치가 첫번째 행이거나, 첫번째 열 또는 마지막 열인 경우만 빙고 확인을 하면 된다.

## :black_nib: **Review**

- 주어진 문제의 조건은 크게 2개였는데, 이 조건들로부터 파생되는 조건 처리가 까다로웠다.
- 문제 자체는 입력 사이즈도 작아서 금방 풀 수 있을줄 알았는데 마구잡이식으로 구현하려다가 꼬여버려 시간이 오래 걸렸다.

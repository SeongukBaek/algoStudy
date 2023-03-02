# [12100] 2048 (Easy)

## :pushpin: **Algorithm**

구현, 중복 순열, 백트래킹

## :round_pushpin: **Logic**

```java
/* 중복 순열로 게임 5회 돌리기 */
    static void play2048(int count) {

        if(count == 5) {
            setMaxNum();
            return;
        }

        int[][] cpBd = cloneArray(board); // 원래 배열 복사

        for(int i=0; i<4; i++) {

            if(i == 0) moveUp();
            if(i == 1) moveDown();
            if(i == 2) moveLeft();
            if(i == 3) moveRight();

            play2048(count+1);

            board = cloneArray(cpBd); // 원래 배열로 복원
        }

    }
```

- 백트래킹(중복순열)으로 5회 이동할 수 있는 경우를 모두 구한다.

```java
for(int j=0; j<n; j++) {
    int idx = 0;
    int num = 0; // 숫자 임시 저장
    for(int i=0; i<n; i++) {
        
        // 블록이 존재하면
        if(board[i][j] != 0) {
            // 그 전의 숫자와 같다면
            if(num == board[i][j]) {
                board[idx-1][j] = num * 2;
                num = 0;
                board[i][j] = 0;
            }
            // 다를 경우
            else {
                num = board[i][j];
                board[i][j] = 0;
                board[idx++][j] = num;
            }
        }
    }
}
```

- 현 위치에 블록이 존재하고
    - 임시 숫자와 현 위치의 숫자가 다를 때, 현 위치의 숫자를 임시 숫자에 저장하고, 임시 숫자를 idx 위치에 저장한다.
    - 임시 숫자와 현 위치의 숫자가 같을 때, 임시 숫자를 곱합 값을 idx-1위치에 저장하고 , 임시 숫자를 0으로 초기화한다.

## :black_nib: **Review**
- 학창시절에 2048게임을 아주 즐겨했던 사람으로서, 처음에는 나름 내가 게임에서 롱런할 수 있는 로직(모퉁이로 숫자를 밀어넣는 방식)으로 문제를 구현을 하다가, 예외의 경우가 많다는 것을 떠오르며 완전탐색으로 방법을 틀었다.
- 블록들을 한 쪽 끝으로 몰아줘야했기 때문에, 처음에는 임시 숫자들을 list에 넣고, 한 줄을 다 돌린 후, 첫 인덱스부터 차례로 list의 값들을 다시 넣어주고, 남은 곳에는 0으로 채워주는 방식을 사용했다. 하지만 너무 비효율적이었기 때문에, 구선생님을 통해 새로운 방법을 이용했다.
    - 임시 변수와 인덱스 포인터를 이용하자!!!
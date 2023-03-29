# [삼성 SW 역량테스트] 꼬리잡기놀이

## :pushpin: **Algorithm**

구현, 시뮬레이션, 덱, BFS

## :round_pushpin: **Logic**

```java
/* 앞으로 한 칸 이동한다. */
private static void moveFront(int team) {
    Person cur;
    // 몸통과 꼬리 이동
    // 앞 팀원의 자리로 이동하면 된다.
    for (int p = teams[team].size() - 1; p > 0; p--) {
        cur = teams[team].get(p);
        // 꼬리일때
        if (p == teams[team].size() - 1) {
            board[cur.x][cur.y].value = -1;
        }

        cur.x = teams[team].get(p - 1).x;
        cur.y = teams[team].get(p - 1).y;
        board[cur.x][cur.y].value = cur.num;
    }

    // 머리가 한 칸 앞으로 이동
    // 머리는 앞 번호가 있었던 자리로 이동하는 것이 아니라 
    // 트랙을 따라 이동해야 해서 사방탐색을 통해 트랙을 찾고 이동한다.
    cur = teams[team].get(0);
    for (int i = 0; i < 4; i++) {
        int dx = cur.x + dr[i];
        int dy = cur.y + dc[i];

        if (!arrayBoundsValidation(dx, dy)) {
            continue;
        }

        // 트랙일 때 이동
        if (board[dx][dy].value == -1) {
            board[dx][dy].value = 1;
            cur.x = dx;
            cur.y = dy;
            break;
        }
    }
}
```
- 팀원은 리스트에 차례대로 정렬되어있다.
- 몸통과 꼬리는 앞 팀원의 자리로 이동하면 된다.
- 머리는 앞 번호가 있었던 자리로 이동하는 것이 아니라 트랙을 따라 이동해야 해서 사방탐색을 통해 트랙을 찾고 이동한다.


```java
private static void catchBall(int round) {
    //시작점이 1, n, 2n, 3n인지 찾기
    int nRound = (round % (n*4))/n;
    //한 변에서 몇번째 줄인지 찾기
    int lineOrder = (round % (n*4))%n;
    Block catcher = null;
    
    int dx = 0;
    int dy = 0;
    int ballDir = nRound;
    //시작점이 1일때
    if(nRound == 0) {
        dx = lineOrder;
        dy = 0;
    }
    //시작점이 n일때
    if(nRound == 1) {
        //..
    }
    //시작점이 2n일때
    if(nRound == 2) {
        //..
    }
    //시작점이 3n일때
    if(nRound == 3) {
        //..
    }

    //공던지기
    for(int i = 0; i < n; i++) {
        if(board[dx][dy].value > 0) {
            catcher = board[dx][dy];
            //공 잡은 팀 머리와 꼬리 바꾸기
            reverse(board[dx][dy].team);
            break;
        }
        
        dx = dx+dr[ballDir];
        dy = dy+dc[ballDir];
    }

    //점수 계산하기
    if(catcher == null) {
        return;
    }
    scores[catcher.team] += (int)Math.pow(catcher.value,2);
}
```
- `nRound = (round % (n*4))/n`으로 어느 변에서 공을 던져야 하는지 찾는다.
- `lineOrder = (round % (n*4))%n`으로 한 변에서 몇 번째 줄에 공을 던져야 하는지 찾는다.
- `nRound`와 `lineOrder`를 가지고 공을 던질 줄을 찾는다.
- 만약 사람이 공을 잡았다면 해당 팀의 머리와 꼬리를 바꾸고 점수를 계산한다.

## :black_nib: **Review**
- 처음에 팀원 저장을 어떻게 할지 엄청나게 고민하고 수정을 많이 했던 문제였다.
    - board에도 팀원 정보가 있어야 하고 팀 마다 또 팀원 정보를 따로 들고 있어야 했고 또 각 팀원이 자신이 몇 번째인지 들고 있도록 하기 위해 애를 썼다.
    - 주어진 정보를 생각한대로 저장하니 이후 공던지고 머리와 꼬리를 바꾸는 것은 수월하게 진행되었다.
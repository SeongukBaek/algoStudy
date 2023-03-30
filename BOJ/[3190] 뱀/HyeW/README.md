# [3190] 뱀

## :pushpin: **Algorithm**

구현, 시뮬레이션, 양방향연결리스트

## :round_pushpin: **Logic**
```java
//사과 저장
for(int i = 0; i < K; i++) {
    st = new StringTokenizer(br.readLine());
    board[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] 
            = 1;
}
//이동할 시간과 방향 저장
int L = Integer.parseInt(br.readLine());
turns = new HashMap<>();
for(int i = 0; i < L; i++) {
    st = new StringTokenizer(br.readLine());
    turns.put(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
}
```
사과는 이차원배열에 저장하고 이동할 시간과 방향은 HashMap으로 저장한다.<br/>

```java
while(true) {
    time++;
    Body cur = snake.peekFirst();

    int dx = cur.x + dr[curDir];
    int dy = cur.y + dc[curDir];
    
    //벽이나 자기 몸에 닿으면 게임 종료
    if(!arrayBoundsValidation(dx, dy) || board[dx][dy] == 2) {
        break;
    }
    
    snake.addFirst(new Body(dx, dy));
    //사과를 안먹었을 때 뱀의 꼬리를 줄임
    if(board[dx][dy] == 0) {
        Body tail = snake.pollLast();
        board[tail.x][tail.y] = 0;
    }
    board[dx][dy] = 2;
    
    //현재 시간에 회전여부 체크
    if(turns.containsKey(time)) {
        curDir = changDir(curDir, turns.get(time));
    }
}
```
- 현재 방향으로 한 칸 나아갈 칸을 확인한다.<br/>
    - 벽이나 몸에 닿는다면 게임을 종료한다.<br/>
- 앞으로 한 칸 머리를 늘린다.<br/>
- 사과를 안먹었다면 꼬리를 줄인다.<br/>
- 다음 방향을 확인한다.<br/>

## :black_nib: **Review**
- 뱀을 `LinkedList`로 구현할지 `ArrayDeque`로 구현할지 고민을 살짝 했었다. 
    - 뱀의 꼬리를 삭제하고 머리를 추가하는 과정이 있어 메모리 변동이 심하니까 `LinkedList`로 구현하는 것이 더 좋지않을까라고 생각했다.
    - 하지만 배열사이즈도 작고 리스트 삽입, 삭제가 최대 2만번이 넘지 않을거 같아 의미가 있는지 모르겠다. 백준에 구현체를 다르게 해서 돌려보니 `LinkedList`가 메모리는 조금더 많이 쓰지만 시간은 덜 걸렸었다.
    - 그리고 `LinkedList`는 반복중에 현재 요소를 제거하는 것이 효율적이고 `ArrayDeque`는 양쪽 끝에서 추가, 제거가 효율적이다. ([출처](https://tecoble.techcourse.co.kr/post/2021-05-10-stack-vs-deque/))


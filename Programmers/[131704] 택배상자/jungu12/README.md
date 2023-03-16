# [131704] 택배상자

## :pushpin: **Algorithm**

구현, 스택

## :round_pushpin: **Logic**

```java
public int solution(int[] order) {
    int answer = 0;
    int mainConveyerBox = 1;
    int idx = 0;
    Stack<Integer> assistConveyerBelt = new Stack<>();
    while(answer != order.length) {
        //메인 컨베이어벨트의 박스 번호가 order와 같은 경우
        if (order[idx] == mainConveyerBox) {
            mainConveyerBox++;
            idx++;
            answer++;
            continue;
        }
        //보조 컨베이어벨트의 박스번호가 order와 같은 경우
        if(!assistConveyerBelt.isEmpty() && assistConveyerBelt.peek() == order[idx]) {
            assistConveyerBelt.pop();
            idx++;
            answer++;
            continue;
        }
        //더 이상 진행이 불가능한 경우
        if(mainConveyerBox == order.length) {
            break;
        }
        //진행 가능하다면 보조 컨베이어 벨트로 박스를 옮김
        assistConveyerBelt.push(mainConveyerBox++);
    }
    return answer;
}
```

- 아래와 같은 순서로 구현하였다.
- 1.  컨테이너 벨트의 맨 앞에 놓인 상자가 트럭에 실어야 하는 순서라면 실는다.
- 2.  보조 컨테이너의 맨 앞에 놓인 상자가 트럭에 실어야 하는 순서라면 실는다.
- 3.  컨테이너 벨트에서 확인한 상자가 마지막 상자라면 종료해준다.
- 4.  3이 아니라면 컨테이너 벨트에서 보조 컨테이너 벨트로 옮겨 준다.

## :black_nib: **Review**

- 로직에서 어떤 조건이 먼저 수행 되어야 하는가를 잘 생각하여 구현 해야하는 문제였다.

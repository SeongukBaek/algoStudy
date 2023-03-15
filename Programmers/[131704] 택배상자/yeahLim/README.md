# [131704] 택배상자

## :pushpin: **Algorithm**

스택, 큐

## :round_pushpin: **Logic**

```java
for(int od : order) {
    while(true) {

        // 최근에 실은 택배의 순서보다 작을 경우
        if (od < loadedCourier) {
            if(container.peek() == od) { 
                loadedCourier = container.pop(); // 보조 컨테이너에서 택배를 싣는다 
                answer++;
                break;
            } 
            else return answer;
        } 

        // 최근에 실은 택배의 순서보다 큰 경우
        else if(!belt.isEmpty()) {
            if(belt.peek() == od) {
                loadedCourier = belt.poll(); // 벨트 컨테이너에서 택배를 싣는다
                answer++;
                break;
            } 
            else container.add(belt.poll()); // 벨트 컨테이너 -> 보조 컨테이너로 옮긴다
        }
    }
}
```

- order는 택배의 순서가 들어있는 배열이다
- 현재 실어야하는 택배가 직전에 실었던 택배보다 작으면, 보조 컨테이너에 마지막으로 들어간 택배를 확인한다.
    - 현재 순서에 맞는 택배면, break하고 다음 택배를 찾는다
    - 아니면, 더 이상 실을 수 있는 택배가 없기에 함수 종료!
- 현재 실어야하는 택배가 직전에 실었던 택배보다 크면, 벨트 컨테이너에서 택배를 찾는다.
    - 벨트 컨테이너의 첫번째 택배가 순서에 맞는 택배면, break하고 다음 택배를 찾는다
    - 없으면, 벨트 컨테이너에서 한 개의 택배를 보조 컨테이너로 옮긴다.


## :black_nib: **Review**

- 문제에 나와있는 동작 그대로 구현했다. 근데 뭔가 어려웠다. 이번주 머리가 잘 안돌아간다. 빙글빙글..

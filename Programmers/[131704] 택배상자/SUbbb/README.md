# [131704] 택배상자

## :pushpin: **Algorithm**

스택의 개념을 활용

## :round_pushpin: **Logic**

```java
int assistantVeltTop = 0;
for (int index = 0; index < order.length;) {
    int box = order[index] - 1;

    // 만약 현재 실으려는 박스의 번호보다 보조 벨트에 있는 박스의 번호가 더 크다면 더 이상 싣지 못함
    if (assistantVeltTop > box) {
        break;
    }

    // 실으려는 박스와 보조 벨트에 있는 박스의 번호가 같다면 싣기
    if (assistantVeltTop == box) {
        hasLoaded[assistantVeltTop] = true;
        answer++;

        // 아직 싣지 않은 박스의 번호로 assistantVeltTop 수정
        while (assistantVeltTop > 0 && hasLoaded[assistantVeltTop]) {
            assistantVeltTop--;
        }

        index++;
        continue;
    }

    // 해당 박스 이전까지의 박스를 보조 벨트에 싣기
    while (assistantVeltTop < box) {
        assistantVeltTop++;
    }
}
```

- assistantVeltTop에 스택의 peek()와 같은 역할을 한다. 가장 마지막에 보조 컨테이너 벨트에 보관한 상자의 번호를 의미한다.
- 실을 수 없는 경우는 현재 택배 기사님이 알려준 번호 앞까지의 상자를 보조 컨테이너 벨트에 보관하고 마지막 번호를 저장한다.
- 실을 수 있다면, 해당 박스에 대해 boolean[] 값을 저장하고, 아직 싣지 않은 박스의 번호를 찾아 assistantVeltTop에 저장한다.

## :black_nib: **Review**

- 문제에 대놓고 스택을 활용하라는 듯, 가장 마지막에 보관한 상자부터 꺼내야 된다고 해서 스택을 활용하려 했다.
- 하지만 구현을 하다보니 굳이 스택을 쓰지 않고 인덱스 번호만으로도 구현이 가능할 것 같았다.
- 시간은 조금 더 걸리지만 메모리 측면에서는 조금 효율을 보였다.

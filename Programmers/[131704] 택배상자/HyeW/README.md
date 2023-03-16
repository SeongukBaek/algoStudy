# [131704] 택배상자

## :pushpin: **Algorithm**

스택

## :round_pushpin: **Logic**

```java
//기존 컨테이너 벨트 확인
for(int main = 1; main <= order.length; main++){

    if(curParcel == main){
        curParcel = order[++truckParcel];
        continue;
    }

    //보조 컨테이너 벨트 확인
    //보조 컨테이너에 상자가 없을 경우
    if(assistantBelt.isEmpty()){
      assistantBelt.offer(main);
      continue;
    }

    //보조 컨테이너에 현재 순서의 상자가 있는 경우
    if(assistantBelt.peekLast() == curParcel){
        main--;
        curParcel = order[++truckParcel];
        assistantBelt.pollLast();
        continue;
    }

    assistantBelt.offer(main);
}
```

컨테이너 벨트에서 택배상자가 트럭에 실을 순서와 맞다면 다음 택배를 확인하고
순서와 맞지 않다면 보조 컨테이너 벨트에 보관한다.<br/>

```java
while(!assistantBelt.isEmpty()){
  curParcel = order[truckParcel];

    if(assistantBelt.peekLast() != curParcel){
        break;
    }

    assistantBelt.pollLast();
    truckParcel++;
}
```

메인 컨테이너 벨트에서 택배가 없다면 보조 컨테이너 벨트를 확인한다.<br/>
보조 컨테이너 벨트에서 꺼낸 택배가 트럭에 실을 순서와 맞지 않다면 바로 종료해준다.<br/>

## :black_nib: **Review**

- 문제에서 보조 컨테이너 벨트는 입구외에 막혀있다라는 말을 보고 스택을 사용해야 한다고 생각했다.
  - `Stack` 클래스를 사용하지 않고 Vetor인터페이스보다 효율적인 `Deque`를 사용해서 스택을 구현했다.

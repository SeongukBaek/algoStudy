# [2527] 직사각형

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
Rectangle left = first;
Rectangle right = second;
if(second.y1 < first.y1) {
  left = second;
  right = first;
}

Rectangle top = first;
Rectangle bottom = second;
if(second.x2 < first.x2) {
  top = second;
  bottom = first;
}
```

우선 어느 사각형이 왼쪽에 있는지 위쪽에 있는지 구분하는 절차를 거친다.<br/>

```java
//선분
if((left.y2 == right.y1 && bottom.x2 - top.x1 <= (firstHeight+secondHeight))
    ||(top.x2 == bottom.x1 && right.y2 - left.y1 <= (firstWidth+secondWidth)) ) {
  System.out.println("b");
  return;
}

//꼭짓점
if(isSameSpot(first.x1, first.y1, second.x2, second.y2) || isSameSpot(first.x2, first.y1,second.x1, second.y2)
    || isSameSpot(first.x2, first.y2, second.x1, second.y1) || isSameSpot(first.x1, first.y2, second.x2, second.y1)) {
  System.out.println("c");
  return;
}

//면
if(left.y2 < right.y1 || top.x2 < bottom.x1) {
  System.out.println("d");
  return;
}

System.out.println("a");
```

선분이 만나는지 구하기 위해서, 두 사각형의 사이가 거리가 0이어야 한다는 조건을 만족시키기 위해 두 사각형의 변의 합을 이용하여 조건을 만족시켰다.<br/>

## :black_nib: **Review**

- 처음엔 배열을 만들어서 사각형을 색칠하는 방법으로 두 사각형이 겹치는지 확인하려고 했다. 하지만 선분만 접하는지, 꼭짓점이 접하는지는 결국에 좌표로 접근해야해서 면이 접하는 것도 좌표로 구하게 되었다.

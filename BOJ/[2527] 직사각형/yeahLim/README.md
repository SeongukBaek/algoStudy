# [2527] 직사각형

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
// 공통 부분 : 없음
if(p2 < x1 || x2 > p1 || q2 < y1 || y2 > q1) {
    System.out.println("d");
}

// 공통 부분 : 점
else if((x1 == p2 && y1 == q2) || (x2 == p1 && y2 == q1) || (x2 == p1 && y1 == q2) || (x1 == p2 && y2 == q1)) {
    System.out.println("c");
}

// 공통 부분 : 선분
else if(x1 == p2 || x2 == p1 || y1 == q2 || y2 == q1) {
    System.out.println("b");
}

// 공통 부분 : 직사각형
else System.out.println("a");
```

- 겹치는 부분이 없다면, d
- 모서리 중에 하나가 겹치면 c
- 선분이 겹치면 b
- 이외의 경우는 a

## :black_nib: **Review**

- 단순 구현문제였지만, 좌표 문제를 볼때는 꼼꼼하게 봐야겠다. 좌표하나를 잘못 적어서 시간이 좀 걸렸다.

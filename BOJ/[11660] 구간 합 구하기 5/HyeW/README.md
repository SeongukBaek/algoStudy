# [11660] 구간 합 구하기 5

## :pushpin: **Algorithm**

DP, 누적 합

## :round_pushpin: **Logic**

```java
for (int i = 1; i <= N; i++) {
    st = new StringTokenizer(br.readLine());
    for (int j = 1; j <= N; j++) {
        sumArr[i][j] = Integer.parseInt(st.nextToken()) 
                + sumArr[i-1][j] + sumArr[i][j-1] - sumArr[i-1][j-1];
    }
}
```
`(i, j)`좌표의 값을 받을 때 왼쪽값`(i, j-1)`, 위쪽값`(i-1, j)`을 더하고 중복되는 값인 `(i-1, j-1)`을 빼고 저장한다.<br/>
이렇게 누적합을 구한다면 `(i, j)`좌표엔 `(0,0)`부터 `(i, j)`까지 놓인 값들의 합을 구할 수 있다.
<br/><br/>

```java
static int getSection(Node start, Node end) {
    int sum = 0;

    sum = sumArr[end.x][end.y] 
            - sumArr[start.x-1][end.y] 
            - sumArr[end.x][start.y-1] 
            + sumArr[start.x-1][start.y-1]; 

    return sum;
}
```
`(x1, y1)`부터 `(x2, y2)`까지 합을 구하기 위해선 `(x2, y2)`의 값에서 필요없는 값을 빼고 중복해서 빼준 값을 더하면 된다.<br/>


## :black_nib: **Review**
-  구간 합을 처음 풀어보았는데 구간 합의 개념을 알아 갈 수 있는 문제였다.
- 그리고 이번 문제를 풀면서 중첩클래스와 외부 클래스 중 어느 것을 쓰는 것이 좋은지 알아보았다.
    - 중첩 클래스를 사용하면 **1. 두 클래스의 멤버를 서로 쉽게 사용가능 하고 2. 외부에는 불필요한 관계 클래스를 감춤으로써 코드 복잡성을 줄일** 수 있다.
    - 알고리즘을 풀 땐 상관없을 것 같은데 우선 `Node클래스`가 한 곳에서만 쓰이기도 하고 한번에 관리하기 위해 중첩클래스를 사용했다.
    - [출처 블로그](https://brownbears.tistory.com/526)
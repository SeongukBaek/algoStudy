# [150367] 표현 가능한 이진트리

## :pushpin: **Algorithm**

DFS, 트리

## :round_pushpin: **Logic**

```java
//해당 수의 2진법 길이 계산
int len = (int)Math.floor(Math.log(num) / Math.log(2)) + 1;
//해당 수의 포화 이진트리 길이 계산
int exp = 1;
int treeLen = 0;
while(true) {
    treeLen = (int)Math.pow(2, exp++) - 1;
    if (treeLen >= len) break;
}
```

- 해당 숫자의 2진법 길이를 계산하여 그 크기를 담을 수 있을 만큼의 treeLen을 구한다.

```java
target = new boolean[treeLen];
int i = treeLen - 1;
while(true) {
    long div = num / 2;
    long mod = num % 2;
    num = div;
    target[i--] = (mod == 1);
    if (div == 1) {
        target[i] = true;
        break;
    } else if (div == 0)
        break;
}
solve(0, treeLen - 1, false);
res[ind] = result;
```

- 트리 배열의 가장 뒤부터 순서대로 해당 숫자를 2진수로 변환한 값을 맨 뒤 숫자부터 넣는다.
- 이후 DFS를 이용하여 포화이진트리로 표현할 수 있는지 확인한다.

```java
//Root 부터 탐색
public void solve(int s, int e, boolean isEnd) {
    int mid = (s + e) / 2;
    boolean cur = target[mid];
    //중간에 0이 나왔는데 현재 노드가 1이라면
    if (isEnd && cur) {
        result = 0;
        return;
    }
    //마지막 노드가 아닐 경우, 계속 진행
    if (s != e) {
        solve(s, mid-1, !cur);
        solve(mid+1, e, !cur);
    }
}
```

- 트리를 탐색하다가 부모노드가 0인데 자식노드가 1인 경우 result에 0을 넣고 탐색을 종료한다.
- 끝까지 아무 문제가 없는 경우는 그 숫자를 포화 이진트리로 표현할 수 있는 경우이다.

## :black_nib: **Review**

- 포화 이진트리에 대한 개념이 중요했다. 포화 이진트리의 수는 2 $^h$ - 1 개인 것을 이용하여 푸는 문제였다.
- 포화 이진트리만 만들면 판별하는 방법은 어렵지 않은 문제였다.

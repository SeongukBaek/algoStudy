# [13549] 숨바꼭질 3

## :pushpin: **Algorithm**

BFS

## :round_pushpin: **Logic**

```java
int cur = positions.poll();
// X-1 이동
int back = cur - 1;
// X+1 이동
int go = cur + 1;

if (back > 0) {
    if(warp(back, visited, positions)) {
        return time;
    }
}

if (go <= 100000) {
    if(warp(go, visited, positions)) {
        return time;
    }
}
```

- 뒤로 한 칸, 앞으로 한 칸 이동하고 순간 이동할 수 있는 칸에 대해서 처리한다.

## :black_nib: **Review**

- 좌표의 최대값을 정하는데 어려웠다.
    - 십만이상까지 가서 뒤로 갔을때 더 적은 시간이 걸리지 않을까라고 생각했는데 그런 경우가 없었다. 그래서 최대값을 십만으로 잡았다.

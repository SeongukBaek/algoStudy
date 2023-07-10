# [68646] 풍선 터트리기

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
// i일때 왼쪽 원소의 최소값 저장
for (int index = 1; index < length - 1; index++) {
    if (leftMin > a[index]) {
        leftMin = a[index];
    }
    lefts[index] = leftMin;
}
```

- 각 풍선을 기준으로 왼쪽에 있는 최소 풍선 번호와 오른쪽에 있는 최소 풍선 번호를 구한다.

```java
int answer = 2;
for (int index = 1; index < length - 1; index++) {
    // 해당 풍선이 자신을 기준으로 왼쪽에 있는 최소 번호 풍선과, 오른쪽에 있는 최소 번호 풍선보다 크다면, 최후까지 남을 수 있다.
    if (a[index] > lefts[index] && a[index] > rights[index]) {
        continue;
    }
    answer++;
}
```

- 현재 풍선이 왼쪽에 있는 최소 풍선 번호보다 크고, 오른쪽에 있는 최소 풍선 보다 크다면 최후까지 남을 수 있는 풍선이다.

## :black_nib: **Review**

- 왼쪽, 오른쪽을 나누어서 최솟값들을 모두 구해놓는 아이디어는 도저히 생각해내지 못할 것 같다.

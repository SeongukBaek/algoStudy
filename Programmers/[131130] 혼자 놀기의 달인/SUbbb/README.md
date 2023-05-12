# [131130] 혼자 놀기의 달인

## :pushpin: **Algorithm**

DFS

## :round_pushpin: **Logic**

```java
// 구한 depth의 개수가 2보다 크면 제일 앞 2개의 곱 반환
if (depths.size() >= 2) {
    return depths.get(0) * depths.get(1);
}

// 1개면, 무조건 2번 상자 그룹의 상자 수는 0개이므로 0 반환
return 0;
```

- DFS 수행 후 구한 depth의 개수에 따라 반환값이 달라진다.

## :black_nib: **Review**

- 생각보다 쉬운 DFS 문제였다.
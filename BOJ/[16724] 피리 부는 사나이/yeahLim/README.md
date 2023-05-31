# [16724] 피리 부는 사나이

## :pushpin: **Algorithm**

union-find

## :round_pushpin: **Logic**

```java
for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
        // 현재좌표, 다음좌표 구하기
        char current = map[i][j];
        int ni = i;
        int nj = j;
        if (current == 'U') {
            ni -= 1;
        }
        else if (current == 'D') {
            ni += 1;
        }
        else if (current == 'L') {
            nj -= 1;
        }
        else {
            nj += 1;
        }
        // 좌표 1차원화
        int curIdx = i*m + j;
        int nxtIdx = ni*m + nj;
        // 유니온 파인드
        union(curIdx, nxtIdx);
    }
}
```
- 현재 좌표와 다음 좌표를 구한다.
- 2차원 좌표를 1차원으로 바꾼다.
- union-find를 이용해서 같은 구역을 찾는다.


## :black_nib: **Review**

- 처음에 BFS로 푸는 와중에 이 문제는 union-find라는 것을 알아차렸다. 코드를 엎고 다시 작성했다... 이제 유니온 파인드 어떻게 하는지 좀 알 것 같기도 하다.
- 이렇게 섹션을 나누는 문제는 union-find 문제일수도 있다는 점 명심하자
# [15565] 귀여운 라이언

## :pushpin: **Algorithm**

두 포인터, 슬라이딩 윈도우

## :round_pushpin: **Logic**

```java
while (end < N) {
    if (dolls[end++] == 1) {
        ryan++;
    }

    while (ryan == K) {
        // 정답 갱신
        minSet = Math.min(minSet, end - start);

        // start 옮기기
        if (dolls[start++] == 1) {
            ryan--;
        }
    }
}
```

- 라이언 개수를 계속 추적하면서, start와 end 인덱스를 변경한다.

## :black_nib: **Review**
- 두 포인터 문제는 항상 구현하다가 한 번 꼬이면 시간이 많이 걸리는 것 같다.
- 그래서 처음에는 더럽게 풀었다가 ... 조금씩 수정했다.
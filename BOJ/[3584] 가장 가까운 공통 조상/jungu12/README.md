# [15565] 귀여운 라이언

## :pushpin: **Algorithm**

투 포인터

## :round_pushpin: **Logic**

```java
private static int findMinSize() {
    int min = Integer.MAX_VALUE;
    int end = -1;
    int count = 0;
    for (int start = 0; start < N; start++) {
        while (end < N - 1 && count < K) {
            end++;
            if (dolls[end] == 1) {
                count++;
            }
        }

        if(count == K) {
            min = Math.min(min, end - start + 1);
        }

        if(dolls[start] == 1) {
            count--;
        }
    }

    if (min == Integer.MAX_VALUE) {
        return -1;
    }
    return min;
}
```

- N이 최대 10의 6승이 될 수 있으니, O(n)의 시간으로 풀어줘야한다 -> 투 포인터!
- 현재 투 포인터 내에 K개 만큼의 라이언이 없다면 계속 end++
- K개가 되었다면 start++하며 다음 라이언 위치까지 이동

## :black_nib: **Review**

- 투 포인터의 개념만 서 있다면 풀 수 있는 문제였으나, 푸는데 시간이 다소 걸렸다..

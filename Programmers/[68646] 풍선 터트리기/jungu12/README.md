# [68646] 풍선 터트리기

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
public int solution(int[] a) {
    if (a.length == 1) {
        return 1;
    }
    int answer = 2;
    int[] leftMin = new int[a.length];
    int[] rightMin = new int[a.length];

    leftMin[0] = a[0];
    rightMin[a.length - 1] = a[a.length - 1];

    for (int j = 1; j < a.length - 1; j++) {
        leftMin[j] = Math.min(leftMin[j - 1], a[j - 1]);
    }

    for (int j = a.length - 2; j > 0; j--) {
        rightMin[j] = Math.min(rightMin[j + 1], a[j + 1]);
    }

    for (int i = 1; i < a.length  - 1; i++) {
        if(leftMin[i] < a[i] && rightMin[i] < a[i]) {
            continue;
        }
        answer++;
    }
    return answer;
}
```

- 인접한 두 풍선 중 큰 풍선을 터트려야 하며, 작은 풍선은 딱 1번 터트릴 수 있다.
- 해당 풍선을 기준으로 왼쪽 그룹, 오른쪽 그룹으로 나눈 후, 각 그룹의 최소값 보다 기준이 된 풍선이 크지 않다면 마지막에 생존 가능한 풍선이다.
- 이에 따라 로직을 작성하였다.

## :black_nib: **Review**

- 한 풍선을 기준으로 좌 우 그룹으로 나누어야 한다는 아이디어를 생각하기 쉽지 않은 문제였다.

# [161988] 연속 펄스 부분 수열의 합

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
void makeArr() {
    for (int i = 0; i < sequence.length; i++) {
        if (i % 2 == 0) {
            seq1[i] = sequence[i];
            seq2[i] = sequence[i] * -1;
        }

        if (i % 2 == 1) {
            seq1[i] = sequence[i] * -1;
            seq2[i] = sequence[i];
        }
    }
}
```

- sequence 배열에 두가지의 펄스 수열을 곱하여 두개의 새로운 배열을 만든다.

```java
long makeDp() {
    dp1[0] = seq1[0];
    dp2[0] = seq2[0];
    for (int i = 1; i < sequence.length; i++) {
        //DP[K] = max(DP[K-1] + K번째 원소, K번째 원소)
        dp1[i] = Math.max(dp1[i - 1] + seq1[i], seq1[i]);
        dp2[i] = Math.max(dp2[i - 1] + seq2[i], seq2[i]);
    }

    return Math.max(Arrays.stream(dp1).max().getAsLong(), Arrays.stream(dp2).max().getAsLong());
}
```

- DP[K] = max(DP[K-1] + K번째 원소, K번째 원소) 라는 점화식을 세울 수 있다.
- 전자를 고른다면 그 전까지의 부분집합에 현재 원소를 추가한다는 의미다.
- 후자를 고른다면 현재 원소부터 새로운 부분집합을 만든다는 의미다.

## :black_nib: **Review**

- 문제 제목과 설명에서 어려운 단어가 많이 나와서 겁을 먹었다.
- 하지만 크게 어려운 로직이 없었던 DP문제였다.

# [42584] 주식가격

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
public int[] solution(int[] prices) {
    int[] answer = new int[prices.length];

    for (int i = 0; i < prices.length; i++) {
        for (int j = i + 1; j < prices.length; j++) {
            answer[i]++;
            if (prices[i] > prices[j])
                break;

        }
    }

    return answer;
}
```

- 2중 for문을 돌면서 해당 주식이 언제까지 상승 하는지 계산한다.

## :black_nib: **Review**

- stack으로 풀라고 만든 문제임을 알았으나 잘 되지 않아서 단순 구현으로 해결하였다.
- 단순 구현으로 풀 때 시간 복잡도는 O(n^2)이다. 따라서 최악의 경우 시간 초과가 날 수 도 있으나, 위 문제는 그런 최악의 경우를 만드는 것이 불가능한 문제이다.

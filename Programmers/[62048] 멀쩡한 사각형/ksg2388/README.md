# [62048] 멀쩡한 사각형

## :pushpin: **Algorithm**

수학

## :round_pushpin: **Logic**

```java
(long) w * h - (((w / num) + (h / num) - 1) * num);

```

- 가로와 세로 길이의 최대 공약수 만큼 공통적으로 나타나는 모양의 사각형의 개수를 전체 사각형의 개수에서 빼준다.

```java
public int gcd(int a, int b) {
    int temp;
    while (b > 0) {
        temp = a % b;
        a = b;
        b = temp;
    }
    return a;
}
```

- 유클리드 호제법을 이용하여 최대 공약수를 구한다.

## :black_nib: **Review**

- 가로, 세로의 최대 길이가 1억 이하의 자연수였기때문에 특정한 공식을 찾아야겠다는 생각을 했다.
- 하지만 아무리 봐도 방법이 떠오르지 않아 결국 답을 보게되었다...

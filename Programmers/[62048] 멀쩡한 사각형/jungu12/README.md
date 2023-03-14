# [62048] 멀쩡한 사각형

## :pushpin: **Algorithm**

## :round_pushpin: **Logic**

```java
public static long solution(int w, int h) {
    long answer = 1;
    long gcd = calGcd(w, h);
    answer = ((long) w * h) - (((w / gcd) + (h / gcd) - 1) * gcd);
    return answer;
}

public static long calGcd(int w, int h) {
    if (h == 0) {
        return w;
    }
    return calGcd(h, w % h);
}
```

- 선이 왼쪽 위 꼭짓점와 오른쪽 아래 꼭짓점을 지나는 가장 작은 직사각형으로 나누어 패턴을 찾는다.
- 위 직사각형의 높이를 h, 길이를 w라고 한다.
- 이 직사각형에서 사용할 수 없는 직사각형의 개수는 w + h - 1이다.
- 유클리드 호제법으로 최대공약수를 구해주었다.

## :black_nib: **Review**

- 입력의 범위가 1억 이하의 자연수라서, 특정 패턴을 찾아야 하는 문제임은 알았다.
- 하지만 찾지 못하여 답을 참고 하였다...

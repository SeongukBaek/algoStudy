# [11726]2xn 타일링

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
int prevPrevNum = 1;
		int prevNum = 1;
		int curNum = 1;

		for(int i = 2; i <= N ; i++) {
			prevPrevNum = prevNum;
			prevNum = curNum;
			curNum = (prevPrevNum + prevNum) % 10007;
		}

		System.out.println(curNum);
```

- n(n > 2)번째 방법의 수는 n-1번째의 방법들에 세로 1x2 타일을 붙이는 경우 + n-2번째의 방법들에 2x1타일을 두개 붙이는 경우이다.
- 즉, 피보나치 수열을 구현하면 문제를 풀 수 있다!

## :black_nib: **Review**

- 비교적 쉬운 DP문제였으나, 값들을 10007로 나누어서 저장해야 하는 것을 바로 생각하지 못하였다.

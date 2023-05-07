# [12938] 최고의 집합

## :pushpin: **Algorithm**

수학

## :round_pushpin: **Logic**

```java
class Solution {
    static int[] solution(int n, int s) {

		if(n > s) {
			return new int[] {-1};
		}
		return getAnswer(n, s);
	}

	static int[] getAnswer(int n, int s) {
		int[] ans = new int[n];

		// 1. n/s한 값을 집합의 원소로 나눠갖는다.
		for(int i = 0; i < n; i++) {
			ans[i] = s/n;
		}

		// 2. n/s한 나머지를 1씩 나눠갖는다.
		for(int i = n-1, j = 0; i < 0 || j < s%n; i--, j++) {
			ans[i]++;
		}

		return ans;
	}
}
```

- s가 n보다 작은 경우는 나누어도 1보다 작기 때문에 자연수 집합으로 이루어진 최고의 집합을 만들 수 없다. 따라서 s가 n보다 작을 때는 -1이 담긴 배열을 반환한다.
- 최고의 집합은 s를 n으로 나눈 몫 n개를 원소로 가지고 s를 n으로 나눈 나머지를 원소들이 1씩 나눠가지면 된다.
- 곱이 가장 큰 집합은 편차가 작은 집합이기 때문에 해당 로직이 가능하다.

## :black_nib: **Review**

- 직접 예제를 써보면서 푸니까 빨리 해결할 수 있었다.

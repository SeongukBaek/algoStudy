# [42860] 조이스틱

## :pushpin: **Algorithm**

.

## :round_pushpin: **Logic**

```java
 static int solution(String name) {
	char[] str = name.toCharArray();
	int sum = 0;

	// 1. 각 문자의 최소 조작 횟수 구하기
	for(int i = 0; i < str.length; i++) {
		if(str[i] == 'A') {
			continue;
		}
		sum += Math.min(Math.abs(str[i]-'A'), Math.abs('Z'-str[i]+1));
	}

	// 2. 각 문자로 이동하는 최소 횟수
	int move = str.length-1; // 최대 이동 횟수는 str-1번

	for(int i = 0; i < str.length-1; i++) {
		// 만약 다음 문자가 'A'이면 연속된 A길이 구하기
		if(str[i+1] == 'A') {
			int j = i+1;
			while(j < str.length && str[j] == 'A') {
				j++;
			}

			move = Math.min(move, (i*2+(str.length-j)));
			move = Math.min(move, (str.length-j)*2+i);
		}
	}

	return sum + move;
}
```

- 1. 각 문자의 최소 조작 횟수 : 각 문자에서 A까지의 이동횟수와 Z까지의 이동횟수를 비교하여 더 작은 값을 선택한다.
- 2. 각 문자로 이동하는 최소 횟수 : 각 문자에서 A까지의 거리와 연속된 A의 길이를 구하여 앞쪽에서 A까지 갔다가 되돌아와서 끝으로 가서 연속된 A 끝까지 가는 방법, 끝쪽으로 가서 연속된 A까지 갔다가 앞쪽으로 되돌아와서 연속된 A의 시작점까지 가는 방법 2가지 중 더 작은 값을 선택한다.
- 1에서 구한 값과 2에서 구한 값을 더하면 조이스틱 조작 횟수의 최소값을 구할 수 있다.

## :black_nib: **Review**

- 1. 각 문자의 최소 조작 횟수와 2. 각 문자로 이동하는 최소 횟수를 구해서 최종 최소값을 구하는 건 생각했는데 2번 구현하는 게 어려워서 답을 참고했다. 연속된 A를 찾아서 해결하는 건 계속봐도 생각 못했을 거 같다..

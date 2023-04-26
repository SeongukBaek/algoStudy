# [1918] 후위 표기식

## :pushpin: **Algorithm**

자료구조, Stack

## :round_pushpin: **Logic**

```java
public static void main(String[] args) throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String str = br.readLine();
	StringBuilder sb = new StringBuilder();
	Stack<Character> stack = new Stack<>();

	for (int i = 0; i < str.length(); i++) {
		char c = str.charAt(i);
		if (c >= 'A' && c <= 'Z') {
			sb.append(c);
			continue;
		}
		if (c == '(') {
			stack.add(c);
			continue;
		}
		if (c == ')') {
			while (stack.peek() != '(') {
				sb.append(stack.pop());
			}
			stack.pop();
			continue;
		}
		while (!stack.isEmpty() && (prior(stack.peek()) >= prior(c))) {
			sb.append(stack.pop());
		}
		stack.add(c);

	}
	while (!stack.isEmpty()) {
		sb.append(stack.pop());
	}
	System.out.println(sb);
}

private static int prior(char c) {
	if (c == '*' || c == '/') {
		return 1;
	}
	if (c == '+' ||  c == '-') {
		return 0;
	}
	return -1;
}
```

- 중위 표기식을 후위 표기식으로 바꾸는 로직은 아래와 같다
- A ~ Z라면 바로 출력한다.
- '('는 스택에 넣는다.
- ')'면 '('가 나올 때 까지 stack에서 빼서 출력한다.
- 사칙연산 문자라면 우선순위가 높은 연산자가 나올 때 까지 스택에서 빼서 출력 후, 현재 보고 있는 문자를 넣는다.
- 모든 문자를 다 보았는데 stack에 남은 문자가 있다면 다 빼주어 출력한다.

## :black_nib: **Review**

- 후위 표기식 변환 규칙을 언젠가 들어본적 있지만 기억이 나지 않았다..
- 그래서 검색해서 찾아보고 구현하였다.

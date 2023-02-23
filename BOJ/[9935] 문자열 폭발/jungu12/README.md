# [9935] 문자열 폭발
## :pushpin: **Algorithm**

자료구조, 스택

## :round_pushpin: **Logic**

```java
for (int i = 0; i < inputStr.length(); i++) {
	strStack.push(inputStr.charAt(i));
	if (strStack.size() >= bombStr.length()) {
		for (int j = 0; j <= bombStr.length(); j++) {
			if (j == bombStr.length()) {
				removeBomb();
				break;
			}
			if (strStack.get(strStack.size() - bombStr.length() + j) != bombStr.charAt(j)) {
				break;
			}
		}
	}
}
  ```
```java
static void removeBomb() {
	for (int i = 0; i < bombStr.length(); i++) {
		strStack.pop();
	}
}
```
   - 스택에 문자열을 하나씩 push하다 폭탄 문자열 길이 이상만큼 차면 문자열을 서로 비교한다.
   - 제거가 가능하다면 removeBomb()해준다.
  
  
## :black_nib: **Review**
 - 스택에 어떤 메소드가 있는지 찾아보게 해준 문제였다.



  
  	

  

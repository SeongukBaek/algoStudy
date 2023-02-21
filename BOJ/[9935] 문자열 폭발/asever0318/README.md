# [9935] 문자열 폭발

## :pushpin: **Algorithm**

Stack

## :round_pushpin: **Logic**
```java
        for(int i = 0; i < str.length(); i++) {
			stack.push(str.charAt(i));
			
			if(str.charAt(i) == bomb.charAt(endindex) && stack.size() >= bomb.length()) { // bomb의 마지막 문자와 같으면 
				check = true;
				int start = stack.size() - bomb.length(); // stack에서 탐색 시작할 위치 
				
				for(int j = 0; j < endindex; j++) {
					if(stack.get(start+j) != bomb.charAt(j)) { // 틀린문자가 있으면 false
						check = false;
						break;
					}
				}
				
				if(check == true) { // 폭발 문자열과 일치하면 
					for(int a = 0; a < bomb.length(); a++) {
						stack.pop();
					}
				}
			}
		}
```
- 스택에 문자를 하나씩 넣으면서 만약 폭발 문자열의 끝문자와 같으면 폭발 문자열만큼 앞문자들을 검사해준다. check 변수를 통해 검사 결과를 저장하고 만약 true이면 폭발 문자열 길이만큼 스택에서 제거해준다.

## :black_nib: **Review**
- print로 출력을 했었는데 시간초과가 나서 StringBuilder로 받아서 출력해주었다. StringBuilder를 열심히 활용해야겠다. 

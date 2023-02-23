# [16472] 고냥이

## :pushpin: **Algorithm**

투 포인터

## :round_pushpin: **Logic**

```java
static void findMaxLen() {
	for (int start = 0; start < catStr.length() - maxLen; start++) {
		Set<Character> typeNum = new HashSet<>();
		int end = start;
		while (true) {
			typeNum.add(catStr.charAt(end));
			if(typeNum.size() > N) {
				end --;
				break;
			}
			end++;
			if (end == catStr.length()) {
				end--;
				break;
			}
		}
		int len = end - start + 1;
		if (len > maxLen) {
			maxLen = len;
		}
	}
}
  ```
   - Set에 새로운 문자를 check할 때 마다 add를 해주어 사용된 알파벳 종류를 관리한다.
   - 알파벳 최대 종류를 넘거나, 문자열을 끝까지 다 확인했다면, 이전까지의 maxLen과 비교하여 값을 갱신해준다.
  
  
## :black_nib: **Review**
 - 투 포인터를 사용하여 어렵지 않게 구현 할 수 있는 문제였다.


  
  	

  

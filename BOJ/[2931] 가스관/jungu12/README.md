# [1759] 암호 만들기

## :pushpin: **Algorithm**

브루트포스, 백트래킹

## :round_pushpin: **Logic**

```java
static void rec_func(int idx, int last) {
		if (idx == len) {
			int count = 0;
			for (int i = 0; i < len; i++) {
				if (vowel.contains(pw[i])) {
					count++;
				}
			}
			if (count >= 1 && len - count >= 2) {
				for (String s : pw) {
					System.out.print(s);
				}
				System.out.println();
				
			}
			return;
		}
		for (int i = last; i <= N -(len - idx); i++) {
			pw[idx] = input[i];
			rec_func(idx + 1, i + 1);
			pw[idx] = null;
		}
	}
  ```
  
   - 재귀 함수 인자로 idx(현재 비밀번호가 몇자리 까지 만들어 졌는지), last(방금 비밀번호에 추가 된 문자가 몇번째 순서인지)를 넘겨준다
   - 비밀번호 생성 for문의 시작 종료 조건으로 활용하여, 반복 횟수를 줄였다.
  
  
## :black_nib: **Review**
 - 자음군 모음군을 따로 관리하지 않았는데 따로 관리한다면 시간을 줄일 수 있기도 하였다.
 - 완성된 pw 배열을 list로 관리하여 출력하려고 하였으나, list에 add 할 때 값이 아닌 주소 값을 넣는 시행착오를 겪었다.
 - 사실 list로 관리할 필요 없이 비밀번호 배열이 조건을 만족한다면 바로 출력하면 되어 필요 없는 고민을 하였다.
  
  	

  

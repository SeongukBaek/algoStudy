# [16472] 고냥이

## :pushpin: **Algorithm**

투포인터

## :round_pushpin: **Logic**
 
```java
public static void catsMessage() {
		
		int left = 0, right = 0;
		check[clist[0] - 'a']++;
		int charcnt = 1; // 인식할 수 있는 문자수 
		int count = 0;

		while(right < clist.length-1) {
			
			// 등록된 문자가 N개보다 적으면
			right++;
			
			check[clist[right] - 'a']++;
			
			// 만약에 처음 등록된 거면 char개수 +1
			if(check[clist[right] - 'a'] == 1) {
				charcnt++; 
			}
			
			while(charcnt > N) { // N개가 될때까지 반복 (문자 하나 지울 때까지)
				
				check[clist[left] - 'a']--;
				
				if(check[clist[left] - 'a'] == 0) {
					charcnt--;
				}
				
				left++;
			}
			
			count = right - left + 1; // 인덱스 0부터니까 +1
			if(max < count) {
				max = count;
			}
		}
	}
```
- 투포인터(left, right)를 두고 문자열을 옮기면서 right가 마지막 문자를 탐색할 때까지 반복문을 돌렸다.
- 나온 알파벳 문자의 수를 체크할 check 배열을 두고 right를 증가시키면서 문자열을 탐색한다. 이때 해당 문자에 해당하는 check 배열을 증가시키고 만약 check 배열이 1이면 처음 나온 문자이기 때문에 charcnt를 증가시킨다. 만약 charcnt가 N이상이 되면 N개가 될 때까지 등록된 문자를 지워주고 지울 때마다 left도 증가시켜준다.

## :black_nib: **Review**
- 처음에 이중for문을 통해서 구현했는데 시간초과가 나왔다. 빠르게 구현할 수 있는 방법을 찾다가 투포인터에 대해서 힌트를 얻게 되었는데 투포인터 문제를 오랜만에 풀어봐서 공부하는데 도움이 된 거 같다!
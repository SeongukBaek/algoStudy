# [16472] 고냥이 - Java

## :pushpin: **Algorithm**

투 포인터

## :round_pushpin: **Logic**

```java
# [16472] 고냥이 - Java

## :pushpin: **Algorithm**

투 포인터

## :round_pushpin: **Logic**

```java
while(right < str.length()) {
			
			
			alphCnt[str.charAt(right) - 'a']++;
			
			if(alphCnt[str.charAt(right) - 'a'] == 1) {
				count++;
			}
			
			while(count > n) {
				alphCnt[str.charAt(left) - 'a']--;
				if(alphCnt[str.charAt(left) - 'a'] == 0) {
					count--;
				}
				left++;
			}
			
			right++;
			
			maxLen = (maxLen < right - left) ? right - left : maxLen;
		}
```

- left는 기준이되는 가장 첫번째 문자열을 가리키고, right는 left의 오른쪽 문자열을 가리킨다.
- 알파벳의 종류가 n보다 큰 경우, while문을 통해 left의 인덱스를 하나씩 증가시키면서, 0이 될때까지 문자를 하나씩 지워준다.

## :black_nib: **Review**
- 처음에는 알파벳의 종류를 세는 배열을 매번 초기화시켜서 시간초과가 났다. 투포인트 방법을 배워서 좋았다.

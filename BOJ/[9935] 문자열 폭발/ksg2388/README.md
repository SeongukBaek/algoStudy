## 사용한 알고리즘

스텍 사용

## 중요 구현 로직 및 설명

스텍을 이용해 순서대로 문자를 받으면서 폭발하는 문자열이 들어오는 경우 그 문자열을 제거해준다.

1. 문자열을 순서대로 스텍에 저장한다.
2. 폭발하는 문자열의 제일 마지막 문자가 들어오는 경우 비교를 시작한다.
3. 비교 후 그 값이 유효하다면 폭발문자의 길이만큼 스텍에서 빼준다.
```java
   // 마지막 문자열이 같으면
			if (temp == boomWord.charAt(interval - 1) && result.size() >= interval) {
				boolean isValidate = true;
				for (int j = 0; j < interval - 1; j++) {
					if (result.get(result.size() - interval + j) != boomWord.charAt(j)) {
						isValidate = false;
					}
				}
				
				if (isValidate) {
					for (int j = 0; j < interval; j++) {
						result.pop();
					}
				}
			}
```
   폭발문자의 마지막 문자와 같은 값이 들어오고 스텍이 충분한 크기라면 유효성 검사를 시작한다. 그 앞의 문자들도 같은 경우 폭발문자의 크기만큼 스텍에서 팝 해준다.

## 풀이 후기

자바에서 문자열을 다루는 것이 쉽지 않다고 느꼈다. 직접 구현해야 하는 부분들이 많은 것 같다.

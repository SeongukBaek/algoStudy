## 사용한 알고리즘

투 포인터를 이용

## 중요 구현 로직 및 설명

투 포인터를 이용해 문자열의 인덱스를 이동하며 최대 길이를 찾음

1. 문자열의 가장 앞의 값의 배열을 1증가시키고 end를 1 증가시킨다.
2. 이미 존재하는 문자가 들어오는 경우는 그 알파벳의 배열 값을 1 증가시킨다.
2. 새로운 문자가 들어오는 경우 count 값을 1 증가시킨다.
3. count 값이 최대 사이즈보다 커지는 경우 start를 1씩 증가시키면서 start가 가리키고 있는 알파벳 배열의 값을 1 내린다.
4. 이 과정에서 알파벳 배열의 값이 0이되는 경우 count를 1 감소시키고 새로운 알파벳의 배열을 1 증가시킨다.
5. 과정을 반복하면서 start와 end사이의 길이가 최대가 되는 경우를 구한다.
``` java
while (end < str.length() - 1) {
	end++;
	char temp = str.charAt(end);
	// 새로 들어온 문자가 기존에 없는 경우
	if (alphabat[temp - 'a'] == 0) {
		count++;
		if (count <= n) {
			alphabat[temp - 'a']++;
		}
	// 알파벳의 종류의 최대 개수를 넘은 경우
        else {
            while (true) {
                alphabat[str.charAt(start) - 'a']--;
                if (alphabat[str.charAt(start) - 'a'] == 0) {
                    start++;
                    break;
                }
                start++;	
            }
            alphabat[temp - 'a']++;
            count--;
        }
	}
	// 새로 들어온 문자가 기존에 존재하는 경우
	else {
		alphabat[temp - 'a']++;
	}
		// 문자열 길이 비교
	result = Math.max(result, end - start + 1);
}
```
## 풀이 후기

투 포인터 알고리즘에 익숙하지 않아서 구현하는데 시간이 오래 걸렸다. 비슷한 유형의 문제를 많이 풀어보면서 익숙해져야겠다.
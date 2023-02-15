# [1234] 비밀번호

## :pushpin: **Algorithm**

스택

## :round_pushpin: **Logic**

```java
int i = 0;
			while (!seq.isEmpty()) {

				if (seq.get(i) == seq.get(i + 1)) {
					seq.remove(i);
					seq.remove(i);
					i = (i == 0) ? i : --i; // 인덱스가 0이 아니면 -1
				} else {
					i++;
				}

				if (i == seq.size() - 1) {
					break;
				}

			}
```

- list의 index를 이용해 i와 i+1가 같다면 두 숫자를 제거해주고, 이후 index의 값을 -1로 설정 후 다시 같은 숫자가 있는 지 탐색한다.
- 만약 두 숫자가 같지 않다면 인데스 값을 i+1 해주고 전부다 돌면 종료한다.

## :black_nib: **Review**
- 단순 구현으로 쉽게 풀었는데, stack으로도 풀어봐야겠다.

# [148653] 마법의 엘리베이터

## :pushpin: **Algorithm**

재귀

## :round_pushpin: **Logic**

```java
public static void useMagicstone(int storey, int cnt) {
		if(storey == 0) {
			if(min > cnt) { // 최소값 갱신
				min = cnt;
			}
			return;
		}

		int num = storey % 10; // 나머지

		// 결론적으로 나머지가 0이 되도록 만들어 줄거임
        if(num >= 5) { // 나머지가 5이상이면 더해서 만들기
        	int plus1 = 10 - num; // +1 할 횟수
        	int temp = storey + plus1; // 현재 숫자에서 plus1만큼 더해주기
        	useMagicstone(temp, cnt+plus1); // +1한 횟수만큼 마법의 돌쓴 거니까 cnt+plus1
        }

        if(0 < num && num <= 5) { // 나머지가 5이하면 나머지만큼 빼서 만들기
        	int temp = storey - num; // 현재 숫자에서 -1을 나머지만큼 해주기
        	useMagicstone(temp, cnt+num); // -1한 횟수만큼 마법의 돈 쓴거니까 cnt+num
        }

        if(num == 0) { // 나머지가 0이면
        	useMagicstone(storey/10, cnt); // 나머지가 0이면 계산할 필요x, 10으로 나눠서 넘겨주기
        }
	}
```

- 숫자를 10으로 나눈 나머지가 5이상일 때, 5이하일 때, 0일 때 세 경우로 나누어서 처리해줬다.
- 나머지가 5이상일 때는 (10-나머지)만큼 +1해서 올림한 후에 처리한다.
- 나머지가 5이하일 때는 나머지만큼 -1해서 내림한 후 처리한다.
- 나머지가 0이면 올림, 내림 할 필요없으므로 10으로 나누어서 넘겨준다.
- 1의 자리만 남을 때까지 반복한다.

## :black_nib: **Review**

- 경우를 나누어서 푸니까 금방 풀 수 있었다.

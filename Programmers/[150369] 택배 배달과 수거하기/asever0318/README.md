# [150369] 택배 배달과 수거하기

## :pushpin: **Algorithm**

스택

## :round_pushpin: **Logic**

```java
long getMinDistance(int cap) {
	long distance = 0; // 편도 이동 거리

	while(!delivery.isEmpty() || !pickup.isEmpty()) { // 배달과 수거를 모두 끝낼 때까지 반복
		int d = 0;
		int p = 0;

		// 배달
		if(!delivery.isEmpty()) {
			d = delivery.peek();
			popBox(delivery ,cap);
		}

		// 수거
		if(!pickup.isEmpty()) {
			p = pickup.peek();
			popBox(pickup, cap);
		}

		distance += Math.max(d, p) * 2; // 둘 중 더 먼 집을 거리로 선택
	}

	return distance;
}
```

- 배달과 수거 스택을 각각 만들고 택배의 수만큼 집의 번호를 스택에 저장한다.
- 물류창고에서 나갈 때는 배달을, 다시 되돌아올 때는 수거를 한다.(각 스택에서 pop())
- 스택의 가장 위에 있는 집을 비교하여 더 먼 집을 이번 차례에 이동할 편도 거리로 정한다.
- 배달 -> 수거 후 왕복 거리를 누적해서 저장한다.
- 배달과 수거를 모두 마칠 때까지 반복한다.

## :black_nib: **Review**

- 풀기는 빨리 풀었는데 이상한데서 시간썼다.ㅎ. IDE 안쓰고 프로그래머스에서 푸는 법 연습해야겠다.ㅎ.

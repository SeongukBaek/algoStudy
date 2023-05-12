# [161988] 연속 펄스 부분 수열의 합

## :pushpin: **Algorithm**

누적합

## :round_pushpin: **Logic**

```java
public long solution(int[] sequence) {
	int size = sequence.length;

	if(size == 1){
		return Math.abs(sequence[0]);
	}

	newSequence = new long[size];
	sum = new long[size+1]; // 누적합이 모두 양수이거나 모두 음수일 경우를 위해서 sum[0] = 0 준비
	// min, max가 둘 다 양수 혹은 음수일 경우는 max에서 min을 빼버리면 오히려 max값에서 min값만큼 더 값이 작아진다
	// 예를들어 max = 7, min = -2라면 max - min = 9가 되어 더 큰 max 값을 구할 수 있는데,
	// 만약 둘 다 양수인 max = 7, min = 2일 경우 max - min = 5로 원래 max값보다 더 작아짐!
	// 따라서 sum[0] = 0으로 두어서 min = 0이 되면 max - min = 7로 max값을 크게 유지할 수 있음!

	getPulseSequence(sequence); // 펄스 수열 구하기
	getSumSequence(); // 누적합 구하기

	return getMaxSum(size);
}
```

- 1. sequence의 사이즈가 1일 경우에는 절댓값을 반환한다.
- 2. getPulseSequence : 펄수 수열(newSequence)을 구한다.
- 3. getSumSequence : 누적합 배열(sum)을 구한다.
- 4. getMaxSum : 부분합의 Max값을 구한다.
- 펄스 수열 2가지를 구했을 때 두 수열은 부호가 반대이다. 따라서 한 가지만 경우만 구해주면 동일한 값을 구할 수 있다.

```java
static void getPulseSequence(int[] sequence){
	for(int i = 0; i < sequence.length; i++){
		if(i%2 == 0){ // 짝수일 때 -1 곱하기
			newSequence[i] = sequence[i] * (-1);
			continue;
		}

		newSequence[i] = sequence[i];
	}
}
```

- 펄스 수열 구하는 함수
- 펄스 수열 2개 중 1가지 경우만 구하면 되기 때문에 임의로 -1부터 시작하는 펄스 함수를 선택(i%2 == 0이면 -1을 곱해주기)

```java
static void getSumSequence(){
	for(int i = 1; i < sum.length; i++){
		sum[i] = sum[i-1] + newSequence[i-1];
	}
}
```

- 누적합 구하는 함수

## :black_nib: **Review**

- 부분 최대값을 구하기 위해서 누적 최대값에서 누적 최소값을 빼주는 건 쉽게 이해했는데, 두 값을 빼주는 과정에서 모두 양수이거나 모두 음수일 때 최대값을 구할 수 없다는 걸 이해하는데 오래걸렸다. 뭔가 이렇게 말고 더 쉽게 이해할 수 있는 방법이 있을 거 같은데 모르겠다 으악

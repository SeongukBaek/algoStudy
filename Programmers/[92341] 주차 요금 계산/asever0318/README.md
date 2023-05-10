# [92341] 주차 요금 계산

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
static class Car{
	String inTime, state;
	int cost, time;

	public Car(String inTime, int cost, String state, int time) {
		super();
		this.inTime = inTime;
		this.cost = cost;
		this.state = state;
		this.time = time;
	}
}

static Map<String, Car> map;
```

- Car class를 만들어 차량객체를 만들었고, Map을 통해 차량번호 중복없이 차량을 관리했다.
- 누적 주차시간을 먼저 구하고, 마지막에 총 주차요금을 구한다.

```java
for(int i = 0; i < records.length; i++) {
	String[] str = records[i].split(" ");

	if(map.containsKey(str[1])) { // 해당 차량번호가 등록 되어있으면
		// 상태 확인하고 요금계산해서 저장
		Car car = map.get(str[1]);

		if(str[2].length() == 3) { // OUT --> 주차요금 계산해서 cost 갱신
			int time = getTime(fees, car.inTime, str[0]);
			car.time += time;
		}

		// IN인 경우 or 주차요금 갱신 끝난 경우 map 값 갱신
		map.replace(str[1], new Car(str[0], car.cost, str[2], car.time));
		continue;
	}

	// 차량 번호가 등록 되어있지 않으면
	map.put(str[1], new Car(str[0], 0, str[2], 0)); // 차량등록
}
```

- records를 돌면서 차량정보를 등록하고, 누적 주차시간을 갱신한다.
- 상태가 OUT인 경우 주차시간을 구해서 time 따로 주차시간을 갱신해주고, 차량이 없을 경우는 map에 등록한다.

```java
for(String key : list) {
	Car car = map.get(key);
	if(car.state.equals("IN")) { // 출차하지 않아서 상태가 IN에 머물러 있는 차량
		int time = getTime(fees, car.inTime, "23:59");
		car.time += time;
	}

	answer[i] = getCost(fees, car.time);
	i++;
}
```

- 위에서 구한 누적 주차시간을 가지고 총 주차요금을 구한다.
- 출차하지 않아서 상태가 IN에 머물로 있는 차량은 23:59를 OUT시간으로 시간을 갱신해서 주차요금을 구해준다.

```java
 // 주차시간 계산
static int getTime(int[] fees, String inTime, String outTime) {

	String[] tempIn = inTime.split(":");
	String[] tempOut = outTime.split(":");

	int in = Integer.parseInt(tempIn[0]) * 60 + Integer.parseInt(tempIn[1]);
	int out = Integer.parseInt(tempOut[0]) * 60 + Integer.parseInt(tempOut[1]);
	return out - in;
}
```

- 00:00부터 IN시각까지의 시간과 00:00부터 OUT까지의 시간을 구해서 OUT-IN을 통해 주차시간을 구했다.

```java
// 주차요금 계산
static int getCost(int[] fees, int time) {
	int totalFee = 0;
	if(time <= fees[0]) { // 주차시간이 기본 시간보다 작거나 같으면
		totalFee = fees[1]; // 기본 요금
	}

	if(time > fees[0]) { // 주차시간이 기본 시간보다 크면
		totalFee = fees[1]; // 기본요금에 초과 시간만큼 단위요금 더하기
		totalFee += Math.ceil((float)(time-fees[0])/fees[2]) * fees[3]; // 단위요금*(주차시간/단위시간)
	}

	return totalFee;
}
```

- 주차시간이 기본시간보다 작을 때와 기본 시간보다 클 때로 나누어 처리했다.

## :black_nib: **Review**

- 처음에 OUT이 들어올 때마다 요금계산을 해줬다가 누적시간인 걸 깨닿고 대폭 수정했다.. 문제를 꼼꼼하게 잘 읽자 :(
- map의 key 값을 기준으로 정렬하는 방법을 배웠다!

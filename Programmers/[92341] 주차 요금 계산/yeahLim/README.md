# [92341] 주차 요금 계산

## :pushpin: **Algorithm**

문자열, 구현

## :round_pushpin: **Logic**

```java
static class Car implements Comparable<Car> {
    int idx;
    int carNum;
    String inTime;
    int parkingTime = 0;
    int parkingFee = 0;
    boolean isOut = false;

    public Car(int idx, int carNum, String time) {
        this.idx = idx;
        this.carNum = carNum;
        this.inTime = time;
    }

    /* 주차 시간 계산 */
    void setParkingTime(String outTime) {
        String[] inTimeInfo = inTime.split(":");
        String[] outTimeInfo = outTime.split(":");
        int fromHour = Integer.parseInt(inTimeInfo[0]);
        int fromMin = Integer.parseInt(inTimeInfo[1]);
        int toHour = Integer.parseInt(outTimeInfo[0]);
        int toMin = Integer.parseInt(outTimeInfo[1]);
        this.parkingTime += (toHour - fromHour) * 60 + toMin - fromMin;
        this.isOut = true;
    }

    @Override
    public int compareTo(Car o) {
        return this.carNum - o.carNum;
    }
}
```

```java
// 차 번호 순으로 정렬
Collections.sort(cars);
```

- Car 객체를 만들어서, 인덱스, 차 번호, 입차 시간, 주차 시간, 주차 비용, 출차 여부 필드를 정의해주었다.
- 또한, `implements Comparable<Car>`를 통해 차번호를 기준으로 정렬할 수 있도록 설정해주었다.

```java
int idx = 0;
int[] carIndex = new int[10000]; // 차 인덱스 확인
Arrays.fill(carIndex, -1);
```

- 차번호를 통해 차의 인덱스를 확인할 수 있는 배열을 만들었다.

```java
/* 주차 요금 계산 */
public static int calculateFee(int parkingTime) {
```

- 누적시간을 파라미터로 받아서, 주차 요금을 계산한다.

## :black_nib: **Review**

- Map으로 풀다가 생각보다 자료구조들이 복잡하여, class 객체를 생성하여 풀었다. class의 편의성에 다시 한번 놀라움을 느꼈다. 그리고 알고리즘 풀때 클래스에 Comparable를 implements한 건 처음이었는데 역시나 편리했다.
- 재미있는 문제였다.

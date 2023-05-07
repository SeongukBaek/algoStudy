# [92341] 주차 요금 계산

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
for(int i = 0; i < records.length; i++) {
    String[] recordIn = records[i].split(" ");
    String[] strInTime = recordIn[0].split(":");

    //입차 정보 찾아서 저장
    if(recordIn[2].equals("IN")) {
        int inTime = Integer.parseInt(strInTime[0]) * 60 + Integer.parseInt(strInTime[1]);
        //출차 기록이 없다면 24:00(1439) 저장됨
        int outTime = 1439;

        //출차 정보 찾기
        for(int j = i + 1; j < records.length; j++) {
            String[] recordOut = records[j].split(" ");
            //입차된 차의 출차 정보 찾아서 저장
            if(recordOut[1].equals(recordIn[1])) {
                String[] strOutTime = recordOut[0].split(":");
                outTime = Integer.parseInt(strOutTime[0]) * 60 + Integer.parseInt(strOutTime[1]);
                break;
            }
        }

        //여러번 입차한 경우
        if(carInfo.containsKey(recordIn[1])) {
            carInfo.put(recordIn[1], carInfo.get(recordIn[1]) + (outTime - inTime));
            continue;
        }

        //처음 입차한 경우
        carInfo.put(recordIn[1], outTime - inTime);

    }
}
```

- records 배열을 순회하면서 입차 기록들만 찾는다.
- 입차 기록을 찾으면 해당 차의 출차 기록을 다시 배열을 순회하며 찾는다.
- 주차 시간을 계산하여 map(carInfo)에다 저장해 주었다.

```java
List<String> carNumSet = new ArrayList<>(carInfo.keySet());
Collections.sort(carNumSet);
//차량별 요금 계산
for(String carNum : carNumSet) {
    int parkingTime = carInfo.get(carNum);
    System.out.println(carNum + " : " + parkingTime);

    //주차 시간이 기본 시간 미만이라면 기본요금 부여
    if(parkingTime < fees[0]) {
        answer[idx] = fees[1];
        idx++;
        continue;
    }

    //주차 시간이 기본 시간 이상이라면 주어진 공식에 따라 기본요금 부여
    answer[idx] = fees[1] + (int)Math.ceil(((double)parkingTime - fees[0]) / fees[2]) * fees[3];
    idx++;
}
```

- 차 번호를 내림차순으로 정렬하기 위해 carInfo의 key들만 따로 정렬하여 List로 만들어 주었다.
- List에서 값을 순차적으로 빼와서 해당 차의 주차 요금을 계산해 준 후, answer 배열에 넣어주었다.

## :black_nib: **Review**

- 어려운 부분은 없었는데 왜 못푼지 모르겠다.
- else 사용을 지양하다가 실수를 자주 하는 것 같다.. 알고리즘을 풀 때는 클린코드 작성이 우선일까, 문제 해결이 우선일까 ?

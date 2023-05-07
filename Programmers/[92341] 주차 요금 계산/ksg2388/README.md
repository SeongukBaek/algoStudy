# [92341] 주차 요금 계산

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```javascript
records.forEach(v => {
      let [time, car, type] = v.split(" ");
      const [hour, minute] = time.split(":");
  
      // 분으로 바꾸기
      time = hour * 60 + Number(minute);
      
      // 처음 조회되는 차량일 시
      if (!cars[car]) {
        cars[car] = { time: 0, car };
      }
      cars[car].type = type;
          
      if (type == "OUT") {
          cars[car].time += time - cars[car].lastInTime;
          return;
      }
      cars[car].lastInTime = time;
    });
```

- 객체에 차 번호, 마지막으로 입차한 시간, 총 주차장을 이용한 시간을 저장한다.
- 그 정보를 이용하여 각 차별 주차요금을 계산한다.

## :black_nib: **Review**

- 어떤 자료구조를 사용할지 고민하다가 간단하게 객체를 이용하여 풀었다.
- 자바스크립트 내부 메서드가 많아서 잘 사용하면 코드를 간단하게 만들 수 있었다.
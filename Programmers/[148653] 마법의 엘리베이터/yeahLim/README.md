# [148653] 마법의 엘리베이터

## :pushpin: **Algorithm**

수학

## :round_pushpin: **Logic**

```java
/* 낮은 자리의 숫자부터 차례로 반올림 */
int roundNumber(int storey) {
    int mod = storey % 10;
    boolean lessThen5 = false;

    // mod가 5인 경우
    if(mod == 5) {
        if(storey % 100 < 50) lessThen5 = true;
    }

    // mod가 0~4일 경우
    if(mod < 5 || lessThen5 == true) {
        answer += mod;
        storey /= 10;
    }

    // mod가 6~9일 경우
    else {
        answer += 10 - mod;
        storey /= 10;
        storey += 1;
    }

    return storey;
}
```

- 일의 자리 숫자부터 차례로 반올림을 해준다.
  - 숫자가 0~4인 경우 : 그 숫자 값 만큼 storey에 더해준다
  - 숫자가 5인 경우
    - 그 다음 자리의 숫자가 0~4인 경우 : 그 숫자 값 만큼 storey에 더해준다
    - 그 다음 자리의 숫자가 5~9 이상인 경우 : 10에서 그 숫자 만큼 빼준 값을 storey에 더해준다.
  - 숫자가 6~9인 경우 : 10에서 그 숫자 만큼 빼준 값을 storey에 더해준다.

## :black_nib: **Review**

- 수학적 개념이 들어가서 규칙찾기가 비교적 수월했다. 다만 수학적 개념 문제가 나올 때, 5인 경우를 주의해줘야 겠다!!!

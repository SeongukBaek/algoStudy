# [1225] 암호생성기

## :pushpin: **Algorithm**

큐

## :round_pushpin: **Logic**

```java
private static String generatePwd() {
    int minus = 1;
    while (true) {
        int number = numbers.poll();
        number -= minus++;
        
        if (minus == 6) {
            minus = 1;
        }
        
        if (number <= 0) {
            number = 0;
            numbers.add(number);
            break;
        }
        
        numbers.add(number);
    }
    
    return queueToString();
}
```

- 큐를 사용해 숫자에 대한 감소 연산을 수행하고 다시 큐에 삽입한다.
- 숫자에 대한 연산 결과가 0 이하인 경우, 큐에 넣고 반복을 종료한다.

## :black_nib: **Review**
- 1단계씩 직접 연산을 수행하지 않고 해결할 방법이 없을까하다가, 방법을 찾지 못했다.
- 직접 연산을 수행하는 방식을 구현하기 위해서는 큐가 가장 적절한 자료구조인 것 같아 사용했다.
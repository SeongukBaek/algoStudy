# [42584] 주식가격

## :pushpin: **Algorithm**

구현, 스텍

## :round_pushpin: **Logic**

```java
for (int i = 0; i < length; i++) {
  // 주식의 가격이 떨어진지 확인
  while (!stack.isEmpty() && stack.peek().price > prices[i]) {
      priceData temp = stack.pop();
      answer[temp.index] = i - temp.index;
  }
  stack.push(new priceData(prices[i], i));
}
```

- 스텍을 이용하여 새로 들어온 주식의 가격이 스텍 가장 위의 가격보다 작은 경우 가격이 떨어지는 경우이므로 스텍에서 `pop`하고 시간을 계산하여 `answer`에 저장한다.
- 이 과정에서 스텍에서 `pop` 후 그 다음값도 작은 경우 계속해서 `pop`해준다.

```java
for (priceData data: stack) {
    answer[data.index] = length - 1 - data.index;
}
```

- 이후 남아있는 주식의 값들을 계산하여 저장해준다.

## :black_nib: **Review**

- 알고리즘 분류가 스텍으로 되어있어서 스텍을 사용하여 문제를 해결하였다.
- 시간을 어떤 방식으로 저장할지가 관건이었다.

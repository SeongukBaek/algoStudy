# [2529] 부등호

## :pushpin: **Algorithm**

브루트포스, 백트래킹

## :round_pushpin: **Logic**

```java
private static void findNumbers(List<Integer> order, boolean[] isVisited) {
    if (order.size() == k + 1) {
        String result = listToString(order);
        if (maxValue == null) {
            maxValue = result;
        }
        minValue = result;
        return;
    }

    for (int n = 9; n >= 0; n--) {
        if (isVisited[n] ||
                !order.isEmpty() &&
                        !isSatisfied(order.get(order.size() - 1), n, inequalitySign.charAt(order.size() - 1))) {
            continue;
        }
        isVisited[n] = true;
        order.add(n);
        findNumbers(order, isVisited);
        order.remove(order.size() - 1);
        isVisited[n] = false;
    }
}
```

- 순열을 만드는 재귀 함수와 비슷한 형태이다.
- 숫자 하나를 선택하여 리스트에 추가하기 전, 해당 숫자를 추가할 수 있는지 여부를 확인한 후 추가한다.

## :black_nib: **Review**
- 처음 접근은 
  - 우선 조건을 만족하는 순열을 생성한 후, 
  - 해당 순열과 부등호식을 가지고 만족하는지 확인한다.
  - 만족하는 경우 최대, 최소값을 갱신한다.
- 위 방식은 통과는 했지만 시간이 너무 오래 걸렸고, 순열을 다 만들고 조건을 확인하기보다 만드는 중간중간에 확인하는 방식으로 변경해서 시간을 줄일 수 있었다.
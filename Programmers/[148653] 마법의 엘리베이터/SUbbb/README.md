# [148653] 마법의 엘리베이터

## :pushpin: **Algorithm**

수학

## :round_pushpin: **Logic**

```java
for (int index = level.length - 1; index >= 0; index--) {
    int current = level[index] - '0';
    
    // 이전 숫자를 반올림한 경우
    if (isRound) {
        current++;
        isRound = false;
    }
    
    // 앞에 숫자가 없는 경우, 현재 숫자만으로 판단
    if (index - 1 < 0) {
        if (current <= 5) {
            answer += current;
            continue;
        }
        answer += (10 - current);
        isRound = true;
        continue;
    }
    
    // 앞의 숫자를 보고 판단해야 하는 경우들
    int preNumber = level[index - 1] - '0';
    
    // 앞의 숫자를 반올림할 수 있는 경우와 없는 경우
    if (preNumber < 5) {
        // 현재 숫자를 반올림하지 않았을때 사용하는 마법의 돌 개수와 현재 숫자를 반올림했을 때 사용하는 마법의 돌 개수 비교
        if (preNumber + current > 10 - current + preNumber + 1) {
            answer += (10 - current);
            isRound = true;
        } else {
            answer += current;
        }
    } else {
        // 앞의 숫자만 반올림하는 경우와 앞의 숫자랑 현재 숫자 모두 반올림하는 경우 비교 
        if (10 - preNumber + current > 10 - current + (10 - (preNumber + 1))) {
            answer += (10 - current);
            isRound = true;
        } else {
            answer += current;
        }
    }
}
```

- 숫자를 1의 자리부터 순차적으로 확인한다.
  - 5 이상인 경우, 반올림이 가능한데, 이때 무조건 반올림을 하는 것이 아니라 이 다음 숫자를 반올림하거나, 하지 않은 경우에 대해 비교한 후, 마법의 돌을 최소로 하는 경우를 선택한다.

## :black_nib: **Review**

- 처음에는 BFS로 0층까지 가는 최단 경로를 구하려 했다. 하지만 범위가 너무 커서 시간초과가 나는 듯 했다.
- 따라서 다른 방법이 없을까 하다가 .. 숫자를 각 자리별로 하나씩 확인하면서 반올림 개념을 더하면 해결이 가능할 것 같았고 몇 번의 시행착오 끝에 로직 완성을 할 수 있었다.
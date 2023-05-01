# [42860] 조이스틱

## :pushpin: **Algorithm**

그리디

## :round_pushpin: **Logic**

```java
for (int index = 0; index < length; index++) {
    answer += convertCost(name.charAt(index)); 

    int next = index + 1;
    // 연속되는 A 갯수 확인
    while (next < length && name.charAt(next) == 'A') {
        next++;
    }

    // 순서대로 가는 것과, 뒤로 돌아가는 것 중 이동수가 적은 것을 선택
    move = Math.min(move, index * 2 + length - next);
    // BBBBAAAAAAAB 와 같이, 처음부터 뒷부분을 먼저 입력하는 것이 더 빠른 경우까지 고려하려면 아래의 코드가 필요
    move = Math.min(move, (length - next) * 2 + index);
}
```

- 제일 첫번째 위치부터 변환을 수행하고, 이후 등장하는 연속된 A의 개수를 구한다.
- 해당 위치에서 앞으로 가는 경우와 뒤로 가는 경우를 나눠 이동하는 최소의 경우를 구한다.

## :black_nib: **Review**

- 그래프 풀이도 고민해보고, 다른 구현 방식도 고려해봤는데 감이 잡히지 않아 정답을 참고했는데, 잘 이해가 되지 않는다.

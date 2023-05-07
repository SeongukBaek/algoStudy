# [17683] 방금그곡

## :pushpin: **Algorithm**

문자열

## :round_pushpin: **Logic**

```java
public int solution(String name) {
    int answer = 0;
    int length = name.length();

    int index;
    int move = length - 1;

    for(int i = 0; i < name.length(); i++){
        answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);

        index = i + 1;
        while(index < length && name.charAt(index) == 'A'){
            index++;
        }

        move = Math.min(move, i * 2 + length - index);

        move = Math.min(move, (length - index) * 2 + i);
    }
    return answer + move;
}
```
- 조이스틱을 상하로 움직이는 경우는 Math.min을 활용하였다.
- 더 적은 이동 횟수를 가지는 방향을 찾아 그 방향으로 이동하며 로직을 수행한다.

## :black_nib: **Review**

- 사실 답을 봐도 잘 이해가 되지 않는다.

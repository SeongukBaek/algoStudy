# [148653] 마법의 엘리베이터

## :pushpin: **Algorithm**

수학

## :round_pushpin: **Logic**

```java
 // 5보다 작은 경우는 그 수만큼 -버튼을 누른다.
if (reminder < 5) {
    answer += reminder;
}
// 5인 경우
else if (reminder == 5) {
    // 앞자리 숫자가 5보다 작은 경우
    if (checkNum < 5) {
        answer += reminder;
    }
    // 앞자리 숫자가 5보다 크거나 같은 경우
    else {
        answer += reminder;
        // 1을 올림해줌.
        division++;
    }
}
// 5보다 큰 경우
else {
    answer += 10 - reminder;
    division++;
}
```

- 숫자를 뒤에서 부터 확인한다.
- 5보다 작은 경우는 그 만큼 answer에 더해주고, 5보다 큰 경우는 10에서 그 숫자를 뺸만큼 answer에 더해준다.
- 그 값이 5인 경우 앞자리 수를 확인하여 앞자리 수가 5보다 작은 경우는 그 만큼 answer에 더해주고, 5보다 크거나 같은 경우는 10에서 그 숫자를 뺸만큼 answer에 더해주고 앞 자리수에 1을 더해준다.

## :black_nib: **Review**

- 처음에는 BFS로 풀려고 시도했지만, 다른 방법이 있을 것 같아 고민해보다가 이 아이디어를 생각해냈다.
- 이런 문제는 틀렸을 경우에 예외를 찾는 것이 쉽지않았다.

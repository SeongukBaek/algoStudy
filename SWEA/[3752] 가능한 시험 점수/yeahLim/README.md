# [3752] 가능한 시험 점수

## :pushpin: **Algorithm**

부분 집합, DP

## :round_pushpin: **Logic**
```java
for (int i = 0; i < n; i++) {
    /* 입력된 점수를 매개변수로 넘겨 점수의 경우의 수 구하기 */
    getScoreCases(Integer.parseInt(st.nextToken()));
}
```
- for문으로 입력된 점수의 값을 하나씩 getScoreCases로 넘겨준다.

```java
static void getScoreCases(int score) {
    Set<Integer> tmp = new HashSet<>();
    tmp.addAll(set);
    for(int s : tmp) {
        set.add(s + score);
    }
}
```

- tmp 집합에 set 집합을 복사하고, 매개변수로 받은 score값과 tmp 집합을 순회한 값을 더해서 set집합에 넣어준다.
  - 0, 0 + a  /  0, a, 0+b, a+b / 0, a, b, 0+c, a+c, b+c, a+b+c ... 
- 그 다음 입력값을 받으면, 새로 추가된 set집합에 위를 반복한다. 

## :black_nib: **Review**
- return값이 없는데 메소드 이름이 get으로 시작되니까 뭔가 아닌 것 같다. 앞으로 set으로 바꿔줘야겠다.

# [2156] 포도주 시식

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
for (int cur = 1; cur < n; cur++) {
    // 현재 와인을 마시지 않을때
    maxWine[cur] = maxWine[cur-1];

    // 연속으로 먹지 않을때
    int pre = (cur - 2 < 0)?0 : maxWine[cur-2];
    maxWine[cur] = Math.max(maxWine[cur], wine[cur] + pre);

    // 두잔을 연속으로 먹을때
    pre = (cur - 3 < 0)?0 : maxWine[cur-3];
    maxWine[cur] = Math.max(maxWine[cur], wine[cur]+wine[cur-1]+pre);
}
```

3가지 경우를 나눠 dp를 사용하는 문제이다
1. 현재 번호의 포도주를 마시시 않았을 때 : **maxWine[cur-1]**
2. 현재 포도주를 마시고 이전 번호의 포도주를 마시지 않았을 때 : **maxWine[cur-2] + wine[cur]**
3. 현재 포도주를 마시고 이전 번호의 포도주 마셨을 때 : **maxWine[cur-3] + wine[cur-1] + wine[cur]**
<br/><br/>
위 3가지 경우 중 가장 큰 값을 maxWine[i]에 저장하면 maxWine[i]는 i번째에서 가장 많이 마시는 와인의 양이 된다.

## :black_nib: **Review**

- i번째 포도주를 마시시 않는 상황도 생각을 했어야 했다. 무조건 i번째를 마신 경우에 대해서만 최대값을 구하는 생각에 사로잡혀서 푸는데 시간이 걸렸다.
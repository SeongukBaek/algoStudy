# [155651] 호텔 대실

## :pushpin: **Algorithm**

그리디

## :round_pushpin: **Logic**

```java
private boolean canAssignPrevRoom(Time time) {
    if (!endTimes.isEmpty()) {
        for (int index = endTimes.size() - 1; index >= 0; index--) {
            if (endTimes.get(index) + 10 <= time.start) {
                endTimes.set(index, time.end);
                return true;
            }
        }
    }

    endTimes.add(time.end);
    return false;
}
```

- 사용한 방들의 퇴실 시각을 저장한 리스트를 뒤에서부터 탐색하면서, 청소가 끝난 방을 사용할 수 있다면 사용하도록 한다.
- 뒤에서부터 탐색하는 이유는, 입실 시각이 늦은 방부터 확인하기 위함이다.

## :black_nib: **Review**

- 백준의 회의실 배정 문제를 풀어봤어서, 똑같은 방식인 줄 알고 정렬 기준을 퇴실 시각으로 잡아서 풀었었다.
- 하지만 아무리 봐도 틀린 부분이 없는데 많은 테케를 틀려서 정렬 기준을 입실 시각으로 바꿨더니 정답이었다.
- 두 개의 차이를 ChatGPT한테 물어봤는데, 입실 시각을 기준으로 정렬해야, 이전 방 사용 퇴실 시각과의 비교를 통해 최소한의 객실을 사용할 수 있다고 했다.
  - 이게 뭔 말인지 이해가 안된다.

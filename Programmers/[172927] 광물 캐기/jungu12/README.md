# [172927] 광물 캐기

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

- 규칙을 어긴 경우는 아래와 같다.
- 1.  O의 개수가 X의 개수, X의 개수 + 1 와 같지 않은 경우
- 2.  O, X둘다 게임을 승리한 경우
- 3.  O만 승리하였는데 O의 개수가 X의 개수 + 1이 아닌 경우
- 4.  X만 승리하였는데 X의 개수가 O의 개수와 같지 않은 경우

```java
void makeMineralGroup() {
    int cnt = 0;
    for(int i = 0 ; i < Math.min(picksCnt, minerals.length / 5 + 1); i++) {
        for (int j = 0; j < 5; j++) {
            if(cnt == minerals.length) {
                return;
            }

            if(minerals[i * 5 + j].equals("diamond")){
                mineralGroups[i][0]++;
            }

            else if(minerals[i * 5 + j].equals("iron")){
                mineralGroups[i][1]++;
            }

            else if(minerals[i * 5 + j].equals("stone")){
                mineralGroups[i][2]++;
            }

            cnt++;
        }
    }
    return;
}
```

- 광물을 5개씩 나누어 저장한다.
- 광물의 개수와 곡갱이의 개수를 고려하여 캐지지 않는 광물들은 그룹에 추가하지 않는다.

```java
Arrays.sort(mineralGroups, (o1, o2) -> {
    if(o1[0] != o2[0]) {
        return o2[0] - o1[0];
    }

    if(o1[1] != o2[1]) {
        return o2[1] - o1[1];
    }

    return o2[2] - o2[2];
});
```

- 광물 그룹을 상위 광물이 많은 순서대로 정렬한다.

```java
void digUpMinerals() {
    for (int i = 0; i < Math.min(picksCnt, minerals.length / 5 + 1); i++) {
        int pick = choosePick();
        System.out.println(calcTired(pick, i));
        picks[pick]--;
        answer += calcTired(pick, i);
    }
}
```

- 위에서 상위 광물이 많은 순서대로 정렬하였음으로, 좋은 곡갱이 부터 순서대로 사용하여 피로도를 계산해준다.

## :black_nib: **Review**

- 그냥 다짜고짜 구현하려다가 시간 초과가 난 사람이 있다는 소문을 듣고 아이디어를 고민해보았다.
- 피로도가 상위 곡갱이를 사용 할 수록 1/5로 줄고 광물 5개가 한개의 그룹이 된다는 것을 보고, 광물이 5개보다 적은 그룹도 상위 광물이 많은 순으로 정렬해도 된다고 판단하였다.

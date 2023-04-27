# [92342] 양궁대회

## :pushpin: **Algorithm**

중복조합, 완전탐색

## :round_pushpin: **Logic**

```java
private void lionshoot(int d, int s, int n, int[] info, int[] arrow){
    if(d == n){
        countScore(info, arrow);
        return;
    }
    
    for(int i = s; i < 11; i++){
        arrow[i]++;
        lionshoot(d+1, i, n, info, arrow);
        arrow[i]--;
    }
}
```

- 중복 조합을 사용해 라이언이 가진 화살로 낼 수 있는 점수를 다 구했다.
- 화살을 다 쏘는 순간 어피치와의 점수 차이를 구한다.


## :black_nib: **Review**

- 제한시간이 10초인 것을 보고 완탐 문제이구나라고 생각했는데 완탐으로 풀기 싫어서 고민을 조금해버렸다.. 앞으론 완탐으로 할 수 있으면 그냥 완탐으로 풀자!
    - 처음에 단순 조합을 생각했는데 같은 점수도 맞출 수 있으니 중복 조합이었다.
- 그리고 문제를 제대로 안읽어서 라이언의 점수를 리턴하는지 알았다.하지만 점수차를 구해야 했다. 문제 잘 읽자!
    - 이거 때문에 조금 헤맸다.
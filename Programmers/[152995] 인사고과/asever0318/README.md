# [152995] 인사고과

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
Arrays.sort(scores, (o1, o2) -> {
    return  o1[0] != o2[0] ? o2[0] - o1[0] : o1[1] - o2[1];
});
```
- 근무태도 점수로 내림차순 정렬하고, 동석차에 대해서는 동료 평가 점수로 오름차순 정렬한다.

```java
 static int getWanHoRank(int[][] scores){
    
    int max = 0; // 동료 평가 점수 max 값
    int cnt = 1; // 완호보다 점수 높은 사람 
    for(int i = 0; i < scores.length; i++){
        
        if(max > scores[i][1]){ // 인센티브 받을 수 없는 직원 패스
            if(whScore[0] == scores[i][0] && whScore[1] == scores[i][1]){ // 인센티브 받을 수 없는 직원이 완호라면 -1 반환 
                return -1;
            }
            continue;
        }
        
        max = Math.max(max, scores[i][1]);
        if(wanHo < scores[i][0] + scores[i][1]){ // 완호보다 점수가 높은 사람이 있으면 cnt++
            cnt++;
        }
    }
    
    return cnt;
}
```
- 근무태도 점수로 내림차순 정렬, 동료평가 점수로 오름차순 정렬했기 때문에 동료평가 점수가 이전에 나온 동료평가 점수의 max보다 작다면 인센티브를 받을 수 없다. 즉, 이미 근무태도 점수가 앞사람들보다 낮거나 같은 상태이기 때문에 동료평가 점수는 반드시 앞사람들의 최고점보다 높아야 인센티브가 보장된다.
- 석차 등수는 동석차의 수만큼 다음 석차를 건너 뛰기때문에 완호보다 점수가 높은 사람을 카운트해주었다.

## :black_nib: **Review**
- 정렬하는 문제는 바로바로 생각나지 않는 것 같다. 모든 사원비교 하다가 시간초과 때문에 혼났다. 이번에 배웠으니 다음엔 더 잘풀 수 있으면 좋겠다.
- 정렬할 때 람다식 알아두면 유용할 것 같다.
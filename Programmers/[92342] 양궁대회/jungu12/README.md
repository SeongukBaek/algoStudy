# [92342] 양궁 대회

## :pushpin: **Algorithm**

백트래킹

## :round_pushpin: **Logic**

```java
public void getOrGiveUpScore(int index, int[] ryanInfo, int left) {
    //10점 ~ 1점 과녁에 화살을 다 쐈는데 화살이 남은 경우, 남은 화살을 다 0점에 쏨
    if(index == 10) {
        ryanInfo[index] = left;
        left = 0;
    }

    //남은 화살이 없는 경우
    if(left == 0) {
        calSum(ryanInfo);

        if(maxSub == ryanSum - apeachSum) {
            for(int i = 10 ;i >= 0; i--) {
                if(answer[i] > ryanInfo[i]) {
                    ryanInfo[10] = 0;
                    return;
                }
                if(answer[i] < ryanInfo[i]) {
                    answer = ryanInfo.clone();
                    ryanInfo[10] = 0;
                    return;
                }
            }
        }

        if(maxSub < ryanSum - apeachSum) {
            maxSub = ryanSum - apeachSum;
            answer = ryanInfo.clone();
        }
        ryanInfo[10] = 0;
        return;
    }

    //현재 점수에 화살을 쏨!
    if(apeachInfo[index] + 1 <= left) {
        ryanInfo[index] = apeachInfo[index] + 1;
        getOrGiveUpScore(index + 1, ryanInfo, left - ryanInfo[index]);
        ryanInfo[index] = 0;
    }

    //현재 점수에 화살 안쏘고 점수 포기!
    getOrGiveUpScore(index + 1, ryanInfo, left);
    }
```

- 과녁의 10점부터 1점까지 해당 과녁의 점수를 얻으려면 apeach의 화살보다 1발 더 쏜다
- 얻지 않으려면 안쏘고 다음 점수로 넘어간다.
- 1점까지 다 쐈는데 남은 화살이 있는 경우 남은 화살을 다 0점에 쏜걸로 처리한다.
- 남은 화살이 없으면 점수 차이를 계산하여 정답을 찾는다.

## :black_nib: **Review**

- 문제를 좀 잘 읽어야겠다....
- 4시간 동안 풀었다...
- 더 정진해야겠다..

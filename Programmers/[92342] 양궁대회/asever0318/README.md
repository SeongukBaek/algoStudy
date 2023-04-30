# [92342] 양궁 대회

## :pushpin: **Algorithm**

중복조합, 백트레킹

## :round_pushpin: **Logic**

```java
static void RyanTurn(int n, int nCnt, int[] info, int index) {
    
    if(n == nCnt) { // n번 다 쐈으면 점수계산 
        int rScore = 0;
        int aScore = 0;
        
        for(int i = 0; i < 11; i++) {
            // 둘 다 0번 맞췄을 때는 둘 다 점수 없기 때문에 패스
            if(Ryan[i] == 0 && info[i] == 0) {
                continue;
            }
            if(Ryan[i] > info[i]) {
                rScore += (10-i);
            }
            else {
                aScore += (10-i);
            }
        }
        
        // 만약 라이언이 이겼으면 
        if(rScore > aScore) {
            int diff = rScore - aScore;
            
            if(maxDiff < diff) {
                copyArr(answer, Ryan); // 정답 갱신 
                maxDiff = diff;
            }
            else if(maxDiff == diff) { // 점수 차가 같으면 가장 낮은 점수를 맞춘 경우가 정답 
                getLowScoreAns();
            }
        }
        return;
    }
    
    for(int i = index; i < 11; i++) {
        // 라이언이 맞춘 화살이 더 많으면 라이언 점수니까 더 쏠 필요x
        if(info[i] < Ryan[i]) {
            continue;
        }
        Ryan[i]++;
        RyanTurn(n, nCnt+1, info, i);
        Ryan[i]--; // 백트레킹 
    }
}
```
- 0~10까지 점수중에 중복을 포함하여 n개의 점수를 뽑는 것이기 때문에 중복조합을 사용했고, 백트레킹을 이용해서 구현했다.
- 라이언이 i 점수를 향해 화살을 쏠 때, 만약 이미 쏜 어피치의 화살보다 라이언이 해당 점수에 쏜 화살이 많으면 이미 라이언의 점수이므로 더 쏠 필요가 없기 때문에 패스한다.
- 만약 n개의 점수를 다 쐈으면 어피치가 쏜 화살(info)와 비교하며 최종 점수 aScore(어피치 점수), rScore(라이언 점수)를 구한다.
- 만약 라이언의 점수가 더 크다면, 점수의 차이를 구한다. 점수의 차이가 더 큰 것을 정답으로 저장한다.
- 만약 점수의 차이가 같다면 가장 낮은 점수를 더 많이 쏜 경우를 정답으로 저장한다. --> getLowScoreArr()

```java
static void getLowScoreAns() {
    // 낮은 점수부터 확인하면서 
    for(int i = 10; i >= 0; i--) {
        
        // 더 낮은 점수를 이전 정답이 먼저(많이) 가지고 있으면 갱신할 필요 x 
        if(answer[i] > Ryan[i]) {
            return;
        }
        
        // 더 낮은 점수를 새 정답이 먼저(많이) 가지고 있으면 갱신 
        if(answer[i] < Ryan[i]) { // 가장 낮은 점수를 더 많이 맞춘 경우 
            copyArr(answer, Ryan);
            return;
        }
    }
}

```
- 낮은 점수부터 확인하면서 만약 어피치가 더 낮은 점수를 쐈다면 갱신할 필요가 없으므로 return, 해당 점수에 같은 횟수의 화살을 쐈다면 의미 없으므로 다음으로 넘어간다. 만약 라이언이 더 낮은 점수를 더 많이 쐈다면 새로운 정답으로 갱신해주고 return한다.

## :black_nib: **Review**

- 겁먹었던 것보다는 쉽게 풀어서 기분 좋았다. 
- 가장 낮은 점수를 더 많이 획득한 것이 정답이라는 조건이 조금 햇갈렸다. 단순히 낮은 점수가 더 많은 경우라고 생각했는데 가장 낮은 점수를 더 많이 가진 것이 정답이었다. 역시 문제 예제까지 꼼꼼히 읽어야된다.. 

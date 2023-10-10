# [15565] 귀여운 라이언

## :pushpin: **Algorithm**

투포인터

## :round_pushpin: **Logic**
```java
static int getRyanCount() {
    [...]
    
    // 첫 요소가 2라면 처음 1이 나오는 위치까지 이동
    if(doll[right] == 2) { 
        while(true) {
            right++;
            if(right >= N || doll[right] == 1) {
                left = right;
                break;
            }
        }
    }
    
    // 1이 나오는 위치부터 진행 
    while(left < N && right < N) {
        count++;
        if(doll[right] == 1) { // 만약 1을 만나면 라이언 추가
            ryan++;
            
            if(ryan == K) { // 만약 라이언이 K개만큼 포함되어 있다면
                min = Math.min(min, count); // min값 갱신 후
                
                while(true) { // left좌표를 다음 1이 나오는 위치로 옮겨주기
                    left++;
                    count--; // 이동하는 만큼 count에서도 제거 
                    if(left >= N || doll[left] == 1) {
                        break;
                    }
                }
                ryan--; // 다음 좌표로 옮겼기 때문에 라이언 수는 -1
            }
        }
        right++;
    }
    
    if(min == Integer.MAX_VALUE) { // min값에 변화가 없으면 K개 이상의 연속된 집합을 못찾은 것 
        min = -1;
    }
    
    return min;
}
```
- 최소 길이를 찾는 것이기 때문에 가장 처음 1이 나오는 위치까지 먼저 이동 후 진행한다.
- 라이언의 개수가 K개가 되면 min값을 갱신하고 left 좌표를 다음 라이언이 있는 곳으로 이동 시킨 후 반복한다.

## :black_nib: **Review**
- 문제를 보고 바로 투포인터로 풀어야겠다고 생각이 들었다. 생각보다 빨리 풀 수 있었다. 쉬운 문제지만 투포인터 문제에 익숙해진 거 같아서 기분이 좋다!
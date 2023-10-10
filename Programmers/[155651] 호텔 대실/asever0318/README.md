# [155651] 호텔 대실

## :pushpin: **Algorithm**

누적합

## :round_pushpin: **Logic**
```java
public int solution(String[][] book_time) {
    room = new int[24*60+10]; // 24시간*60분+10분 -> 가장 늦게 끝나는 시간 
    
    for(int i = 0; i < book_time.length; i++){
        int start = getMinutes(book_time[i][0]);
        int end = getMinutes(book_time[i][1]);
        
        room[start] += 1; // 시작 시간에는 1 저장 
        room[end + 10] += -1; // 끝나는 시간 + 청소시간 10분에는 -1 저장
    }
    
    prefixSum(); // 누적합 
    
    return max;
}
```
- 최대 크기의 1차원 배열을 하나 생성한다.
- 시작 위치에 1을 추가하고 끝 위치에 -1을 추가한다.

```java
static void prefixSum(){
    for(int i = 1; i < 24*60+10; i++){
        room[i] += room[i-1]; // 이전 값을 더하기 
        max = Math.max(max, room[i]); // 최대값 갱신(방의 수)
    }
}
```
- 앞에서부터 뒤로 진행하면서 이전 요소의 값을 더한다.
- 누적합 배열에서 최대값이 필요한 최소의 방의 수가 된다.

## :black_nib: **Review**
- 처음에 우선순위큐로 풀다가 누적합 풀이를 발견해서 누적합으로 풀어봤는데.. 시작을 1, 끝을 -1로 해서 누적합으로 카운트하다니 사람들은 정말 천재다. 좋은 풀이법을 배운 것 같다.
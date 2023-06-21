# [42584] 주식가격

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
public int[] solution(int[] prices) {
   int[] answer = new int[prices.length];

   // 마지막은 무조건 0이니까 prices 길이 -1까지만 탐색하기
   for(int i = 0; i < prices.length-1; i++){
       for(int j = i+1; j < prices.length; j++){
           answer[i]++; // 가격이 떨어지는데 무조건 1초이상 걸리기 때문에 먼저 ++해줌

           if(prices[i] > prices[j]){ // 만약 주가가 내리면 멈추기
               break;
           }
       }
   }

   return answer;
}
```

- 마지막 요소는 반드시 0이 되는 것을 알 수 있기 때문에 마지막 요소 전 요소까지만 탐색한다.
- 현재 주가(i)보다 주가(j)가 하락할 때까지 answer++ 해준다.

## :black_nib: **Review**

- 아무리 생각해도 스택으로 풀 방법이 생각나지 않았다.. 스택을 사용하지 않고 간단하게 풀 수 있는 문제였다. 하지만 스택으로도 다시 풀어보고싶다!

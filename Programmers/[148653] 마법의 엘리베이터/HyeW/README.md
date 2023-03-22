# [148653] 마법의 엘리베이터

## :pushpin: **Algorithm**

수학

## :round_pushpin: **Logic**

```java
// 10 올라갔다가 내려가기
if (curNum > 5) {
    storey += 10;
    magicStone += 10-curNum;
    
}else if (curNum == 5) { 
    // 5일 경우 앞자리 보기 
    int nextNum = (storey/10)%10;
    if(nextNum < 5){
        magicStone += curNum;
    }else{
        storey += 10;
        magicStone += 5;
    }
}else{
    magicStone += curNum;
}
```
일의 자리 수 부터 하나씩 확인한다.<br/>
5보다 작을 경우엔 바로 내려가는 것이 더 적은 마법의 돌을 사용한다.<br/>
5보다 클 경우인 6일 경우 10으로 만들고 10을 한번에 내려가는 게 더 빠르기 때문에 위로 올라간다. <br/>
5일 경우엔 두 경우로 나뉜다. <br/>
- 다음 자리수가 5보다 클 경우 올라가는 것이 더 적은 돌을 사용하고
- 작을 경우엔 내려가는 것이 더 낫다.

## :black_nib: **Review**

- 처음에 0층에서 올라가는 엘리베이터인줄 알고 잘못 풀었었다. 문제를 잘 읽자...

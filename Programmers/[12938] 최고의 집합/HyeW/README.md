# [12938] 최고의 집합

## :pushpin: **Algorithm**

수학

## :round_pushpin: **Logic**

최고의 집합 조건
1. 각 원소의 합이 S가 되는 수의 집합
2. 각 원소의 곱이 최대가 되는 집합

1를 만족하는 상황에서 2를 만족하는 집합은 원소의 차이가 나지않는 수들로 이루어져 있다.<br/>

```java
int avg = s/n;
```
그래서 `전체 값/원소 수`를 구해 원소가 가질 수 있는 최소 값을 구한다.

```java     
if(avg == 0){
    return new int[]{-1};
}
```
만약 평균이 0이 나오면 S를 n개의 수로 나눌 수 없다는 의미로 -1을 리턴한다.

```java
int remainCnt = s%n;
int index = 0;
for(int i = 0; i < n-remainCnt; i++){
    answer[index++] = avg;
}
for(int i = 0; i < remainCnt; i++){
    answer[index++] = avg+1;
}
```
n개의 수가 최소값을 나눠가지고 남은 수를 원소에게 +1씩 해준다.<br/>
그러면 원소들끼리 차이가 최소가 되는 집합을 구할 수 있다.

## :black_nib: **Review**

- 처음에 s/n을 평균이라고 했을 때 평균값을 가지고 하면 될 것같았는데 확신도 없었고 로직을 어떻게 구현할지 고민했다. 하지만 손으로 한번 직접 값을 구해보니 문제와 로직이 정리되면서 금방 풀 수 있었다. 손으로 값을 구하는 작업이 중요하다는 걸 또 다시 한번 깨달았다.
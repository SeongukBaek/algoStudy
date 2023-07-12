# [68646] 풍선 터트리기

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**
```java
for(int i = 1; i < size-1; i++){ // i원소의 왼쪽에 있는 값들 중 최소값을 저장
    if(leftMin > a[i]){
        leftMin = a[i];
    }
    left[i] = leftMin;
}

for(int i = size-2; i > 0; i--){ // (오른쪽에서부터) i원소의 오른쪽에 있는 값들 중 최소값을 저장 
    if(rightMin > a[i]){ // 만약 더 작은 수면 최소값 갱신
        rightMin = a[i];
    }
    right[i] = rightMin; // 최소값 저장 
}
```
- i번째 요소를 기준으로 왼쪽/오른쪽에 있는 값들 중 최소값을 배열에 저장한다.

```java
static int getBalloonCount(int[] a){
    int count = 2; // 가장 왼, 오른쪽은 무조건 남을 수 있다(마지막에 사용하면)
    
    for(int i = 1; i < size-1; i++){
        if(a[i] <= left[i] || a[i] <= right[i]){ // 하나라도 나보다 같거나 크면, 그 수가 삭제되기 때문에 나는 남을 수 있음 
            count++;
        }
    }
    
    return count;
}
```
- 어떤 요소가 마지막에 남으려면 가장 마지막에 작은 수를 제거하는 1번을 사용해야하는데, i번째 요소를 남기려고 했을 때 해당 요소 양쪽에 둘 다 작은 값이 오면 i번째 요소가 제거되므로 양쪽 중 하나라도 해당 요소와 같거나 크면 남을 수 있게 된다.

## :black_nib: **Review**
- 정말 오래 고민했는데 도저히 생각나지 않아서 다른 사람의 답을 참고했다. 한 요소가 마지막까지 남기 위해서는 가장 마지막에 작은 수 제거를 사용해야 된다는 점을 생각해내지 못해서 풀지 못했던 것 같다.
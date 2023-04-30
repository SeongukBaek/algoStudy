# [11279] 최대 힙

## :pushpin: **Algorithm**

최대 힙, 리스트 

## :round_pushpin: **Logic**

```java
public static void insert(int num) {
    
    heap.add(num); // 맨 마지막에 삽입 
    
    int currentPos = heap.size()-1; // 삽입한 값 위치 
    
    while(currentPos > 1) {
        
        int parentsPos = currentPos/2; // 현재 위치 기준 부모 위치
        int parentsNum = heap.get(parentsPos); // 현재 위치 기준 부모 값	
        
        // 부모가 해당 숫자보다 크면 멈춤 
        if(parentsNum > num) {
            break;
        }
        
        // 부모보다 해당 숫자가 크면 교환 
        int temp = parentsNum;
        heap.set(parentsPos, num);
        heap.set(currentPos, temp);
        
        currentPos = parentsPos; // 현재 위치 바꿔주기 
    }
}
```
- 최대 힙에 삽입하는 함수  
- 힙의 가장 마지막에 새로운 수를 삽입하고, 부모 노드와 비교하여 부모 노드보다 크다면 부모 노드와 swap한다.

```java
public static int delete() {
    int max = heap.get(1); // 최대값 저장 
    
    int num = heap.get(heap.size()-1);
    heap.set(1, num); // 가장 끝에 있는 값을 루트로 옮김
    heap.remove(heap.size()-1); // 끝에 있는 값 삭제 
    
    int currentPos = 1; // 현재 숫자 위치 
    
    // 루트에서부터 자리 찾아주기 
    while((currentPos*2) < heap.size()) { // 자식노드가 힙의 범위를 벗어나지 않는다면
        
        int childPos = currentPos*2;
        int childNum = heap.get(childPos);
        
        // 오른쪽 자식이 존재하고 왼쪽 자식보다 크면 오른쪽 자식으로 바꿔주기
        if((childPos+1) < heap.size()) { // 만약 오른쪽 자식이 존재한다면 
            if(childNum < heap.get(childPos+1)) { // 왼쪽 자식과 비교 
                childPos = childPos+1;
                childNum = heap.get(childPos);
            }
        }
        
        if(childNum < num) {
            break;
        }
        
        // 자식이 현재 숫자보다 크면 바꿔주기 
        int temp = childNum;
        heap.set(childPos, num);
        heap.set(currentPos, temp);
        currentPos = childPos; // 자식 위치로 현재 위치 변경 
    }
    
    return max;
}
```
- 최대 힙에서 삭제하는 함수
- 루트 노드에 있는 가장 큰 값을 삭제한다.
- 힙의 가장 마지막에 있는 요소를 루트 노드로 가져온다.
- 자식 노드와 비교하여 자식 노드보다 작다면 자식노드와 swap한다.

## :black_nib: **Review**

- 이전에 최대/최소 힙 구현한 것이 생각나서 리스트로 구현했다. 배웠던 내용을 복기하는 느낌이라 좋았다.

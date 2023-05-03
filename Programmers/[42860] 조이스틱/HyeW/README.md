# [42860] 조이스틱

## :pushpin: **Algorithm**

그리디, DFS, 완전 탐색

## :round_pushpin: **Logic**
```java
int move = Math.min(cur - 'A', 'Z'- cur+1);
```
- 조이스틱 상하이동 최소 횟수를 구한다.

```java
private void getShortestDistance(boolean[] visited, int cur, int done, int distance){
    if(done >= nameLength){
        minDistance = Math.min(distance, minDistance);
        return;
    }
    
    //오른쪽으로 가기
    int right = cur;
    int rightMove = 0;
    while(visited[right]){
        right = (right+1)%nameLength;
        rightMove++;
    }
    visited[right] = true;
    getShortestDistance(visited, right, done+1, distance+rightMove);
    visited[right] = false;
    
    //왼쪽으로 가기
    int left = cur;
    int leftMove = 0;
    while(visited[left]){
        left = left-1 == -1?nameLength-1:left-1;
        leftMove++;
    }
    visited[left] = true;
    getShortestDistance(visited, left, done+1, distance+leftMove);
    visited[left] = false;
}
```
- 왼쪽으로 이동했을 경우, 오른쪽으로 이동했을 경우를 다 구해서 좌우이동 최소값을 구한다.

## :black_nib: **Review**

- 처음에 조이스틱 좌우이동을 그리디로 풀었는데 틀렸다. 그리디로 풀기에 반례가 있을 것 같아 찝찝했는데 반례를 찾지못해 그리디로 풀었다.
    - 다음부턴 찝찝하면 그냥 완탐으로 풀어야 겠다.
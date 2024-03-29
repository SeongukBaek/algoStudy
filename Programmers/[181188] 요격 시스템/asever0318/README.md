# [181188] 요격 시스템

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
 public int solution(int[][] targets) {
    targetList = new ArrayList<>();
    
    for(int i = 0; i < targets.length; i++){
        targetList.add(targets[i]);
    }
    
    Collections.sort(targetList, (o1, o2) -> o1[1] - o2[1]); // 오름차순 정렬 
    
    return shoot();
}
```
- 미사일 끝지점을 기준으로 오름차순 정렬한다.

```java
 static int shoot(){
    int cnt = 1; // 젤 처음 미사일 1
    int e1 = targetList.get(0)[1]; // 가장 처음 폭격 끝 
    
    for(int i = 1; i < targetList.size(); i++){ // 두 번째 폭격부터 
        int s2 = targetList.get(i)[0];
        int e2 = targetList.get(i)[1];
        
        if(s2 >= e1){ // 이전 미사일의 끝지점보다 다음 미사일 시작점이 더 크거나 같으면 새로운 요격 미사일 필요
            cnt++; // 요격 미사일 추가 
            e1 = e2; // 끝 범위 갱신
            continue;
        }
    }
    return cnt;
}
```
- 끝지점을 기준으로 오름차순 정렬했기 때문에, 다음 미사일의 시작지점이 이전 미사일의 끝지점과 겹치지 않으면 새로운 요격 미사일을 추가하고 끝범위를 갱신해준다.

## :black_nib: **Review**
- 뭔가 익숙한 문제같았는데 어떻게 풀지는 바로 생각나지 않았다.. 유유... 아직 멀었나보다 
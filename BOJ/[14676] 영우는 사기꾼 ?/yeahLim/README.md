# [14676] 영우는 사기꾼 ?

## :pushpin: **Algorithm**

위상 정렬

## :round_pushpin: **Logic**

```java
int[] parentsNums = new int[n + 1]; // 부모의 개수
List<Integer>[] relation = new List[n + 1]; // 그래프
int[] buildingCnt = new int[n + 1];
```
- 진입차수는 부모의 개수이다.
- 그리고 관계를 확인할 수 있는 그래프를 구현한다.
- 그리고 이 문제에서는 빌딩이 중복이 될 수 있기 때문에, buildingCnt 배열 변수로 초기화해준다.
  
```java
// 건설
if (cmd == 1) {
    if (parentsNums[building] != 0) {
        System.out.println("Lier!");
        return;
    }
    buildingCnt[building]++;
    if (buildingCnt[building] == 1) {
        for (int child : relation[building]) {
            parentsNums[child]--;
        }
    }
}
```
- 부모의 개수가 0일때만 건설이 가능하다
- 이때, 건설가능한 빌딩의 자식의 차수를 하나씩 줄여준다.

```java
// 파괴
if (cmd == 2) {
    if (buildingCnt[building] == 0) {
        System.out.println("Lier!");
        return;
    }
    buildingCnt[building]--;
    if (buildingCnt[building] == 0) {
        for (int child : relation[building]) {
            parentsNums[child]++;
        }
    }
}
```

- 빌딩의 개수가 0이면 파괴할 수 없다.
- 빌딩의 개수가 0개일 때, 자식의 진입차수를 하나씩 늘려준다.

## :black_nib: **Review**

- 위상 정렬을 처음 사용해보는데, 생각보다 재밌네 ㅎ

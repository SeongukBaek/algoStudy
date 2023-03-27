# [삼성 SW 역량테스트] 코드트리 빵

## :pushpin: **Algorithm**

구현, 시뮬레이션, BFS

## :round_pushpin: **Logic**

```java
int time = 0;
int arrivedPerson = 0;
// 모든 사람이 도착할때까지 반복
while (arrivedPerson < m) {
    // 1. 격자판 위에 있는 모든 사람들 1칸 움직임
    int arriveCnt = move();
    arrivedPerson += arriveCnt;

    // 2. 편의점 도착 -> 다른 사람들 칸을 지날 수 없음
        //이때 map에 변화줘야 함
    for(Person person : people) {
        if(!person.isArrived) {
            continue;
        }
        map[person.mart.x][person.mart.y] = -1;
    }
    
    // 3. 격자 박에 있는 사람은 베이스캠프에 들어감 
    //(t분에 t사람이 베캠에 있을 수 있음 -> 그 이후로 다른 사람 못지나감)
    if(time < m) {
        getCloseCamp(people.get(time));
    }
    time++;
}
```
문제 풀이 순서는 위의 코드 주석과 같다.<br/>

## :black_nib: **Review**

- 문제에서 주어진 순서대로 로직을 세워 해결했다. 사람마다 bfs를 할 큐와 visisted배열이 필요해서 이게 맞나 싶었는데 배열 크기가 그렇게 크지 않아서 메모리 초과나 시간초과가 나지않고 해결이 되었다.
- 그리고 여기서 문제를 잘 읽었어야 했다. 1번 단계에서 편의점에 도착을 하면 바로 그 편의점을 못지나 치는건지 아니면 모든 사람이 동시에 이동하고 그 이후에 편의점을 못지나치는지 헷갈렸는데 후자였었다.
# [16234] 인구 이동

## :pushpin: **Algorithm**

구현, 그래프 이론, 그래프 탐색, BFS, 시뮬레이션

## :round_pushpin: **Logic**

```java
private static int getPopulation(Node start) {
    Deque<Node> countries = new ArrayDeque<>();
    countries.add(start);
    visited[start.x][start.y] = true;

    int totalPopulation = population[start.x][start.y];
    int countryCount = 1;
    
    while (!countries.isEmpty()) {
        Node cur = countries.poll();
        int curPopulation =  population[cur.x][cur.y];
        // 인구 이동할 나라 표시
        population[cur.x][cur.y] = -1;
        
        for (int i = 0; i < 4; i++) {
            int dx = cur.x + dr[i];
            int dy = cur.y + dc[i];

            if (!arrayBoundsValidation(dx, dy)) {
                continue;
            }
            //방문했고 두 나라의 인구 차이가 L과 R사이가 아니라면 패스
            if(visited[dx][dy] || !diffPopulation(curPopulation,  population[dx][dy])) {
                continue;
            }
            
            visited[dx][dy] = true;
            countries.add(new Node(dx, dy));
            totalPopulation += population[dx][dy];
            countryCount++;
        }
    }
    return totalPopulation / countryCount;
}
```
국경을 열 나라를 찾는 BFS이다.<br/>
- 뽑은 나라는 이후에 인구이동을 쉽게하기 위해 -1로 값을 바꾼다.
- 만약 두 나라의 인구 수 차이가 L이상 R이하가 아니라면 그 나라는 보지않는다.
- 국경선을 공유할 나라를 찾으면 총 인구수와 나라수를 증가시켜 평균 인구수를 구한다.
<br/>

```java
private static int move(Node start, int peopleAvg) {
    Deque<Node> countries = new ArrayDeque<>();
    countries.add(start);
    population[start.x][start.y] = peopleAvg;
    
    int cnt = 0;
    while (!countries.isEmpty()) {
        Node cur = countries.poll();
        
        for (int i = 0; i < 4; i++) {
            int dx = cur.x + dr[i];
            int dy = cur.y + dc[i];

            if (!arrayBoundsValidation(dx, dy)) {
                continue;
            }
            
            if(population[dx][dy] != -1) {
                continue;
            }
            
            countries.add(new Node(dx, dy));
            population[dx][dy] = peopleAvg;
            cnt++;
        }
    }
    
    return cnt;
}
```
BFS로 인구 값이 -1인 좌표를 찾아 평균 인구수를 넣어준다. <br/>

## :black_nib: **Review**

- 11달전의 나보다 성능이 2배 안좋은 코드를 짰다.
    - 11달전은 DFS로 문제를 풀었는데 로직은 같은데 효율성에서 차이나는 게,,이번 문제는 DFS가 왜 더 효율이 좋은지 의문이 들었다. 그런데 Chat GPT가 BFS로 푸는게 더 좋다고 했는데..내가 놓치고 있는게 있는지 스터디때 알아봐야 겠다.

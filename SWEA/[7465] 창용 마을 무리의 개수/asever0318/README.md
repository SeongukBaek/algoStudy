# [7465] 창용 마을 무리의 개수

## :pushpin: **Algorithm**

dfs

## :round_pushpin: **Logic**
```java
            for(int a = 0; a <= N; a++) {
                List link = new ArrayList<Integer>();
                linkmap.add((ArrayList<Integer>) link);
            }
             
            for(int j = 0; j < M; j++) {
                str = br.readLine().split(" ");
                p1 = Integer.parseInt(str[0]);
                p2 = Integer.parseInt(str[1]);
                 
                linkmap.get(p1).add(p2);
                linkmap.get(p2).add(p1);
            }
             
            count = 0;
             
            for(int p = 1; p <= N; p++) {
                if(dfs(p)) {
                    count++;
                }
            }
```
- 마을 사람 수만큼 N개의 리스트를 만들고 각 리스트 속에 관계를 등록할 리스트를 만들어주었다. 
- 관계가 있는 두 사람은 서로의 리스트에 각각 등록하였다.
- 마을 사람 1번부터 돌면서 더 이상 관계가 있는 사람이 없을 때까지 dfs 탐색해주었다. 

```java
private static boolean dfs(int n) {

        if(!visited[n]) { // 방문하지 않았으면 넣음 
            visited[n] = true;

            // 해당 번호와 연결된 번호들 돌면서 
            for(int j = 0; j < linkmap.get(n).size(); j++) {
                if(!visited[linkmap.get(n).get(j)]) {
                    dfs(linkmap.get(n).get(j)); 
                }
            }
            return true;
        }
        else {
            return false; // 이미 방문한 노드일 경우 바로 return
        }
    }
```
- 한 사람이라도 연결되어 있으면 무리에 속하기 때문에 무리에 등록되지 않고(방문하지 않았고) 관계 등록된 사람 모두를 돌아볼 때까지 탐색해준다. 

## :black_nib: **Review**
- 처음에 각 연결관계를 어떻게 표현해야될지 고민이 많았는데 리스트를 사용해도 되고 2차원 배열을 사용해도 되고 그렇게 어려운 문제는 아니었던 것 같다. 
- dfs 말고 union-find로 문제를 해결하는 방법도 있던데 이 풀이법도 고민해봐야겠다.

# [7465] 창용 마을 무리의 개수

## :pushpin: **Algorithm**
DFS

## :round_pushpin: **Logic**

```java
for (int i = 0; i < M; i++) {
    st = new StringTokenizer(br.readLine());
    int a = Integer.parseInt(st.nextToken()) - 1;
    int b = Integer.parseInt(st.nextToken()) - 1;
    input.get(a).add(b);
    input.get(b).add(a);
}

static void makeGroup(int num) {
    visited[num] = true;
    for (int person : input.get(num)) {
        if (!visited[person]) {
            makeGroup(person);
        }
    }
}

static int countGroup() {
    int cnt = 0;
    for (int i = 0; i < N; i++) {
        if (!visited[i]) {
            makeGroup(i);
            cnt++;
        }
    }
    return cnt;
}
  ```
   - 입력 받을 때 양방향 그래프로 저장한다.
   - visited[i]가 false면 i 번째 사람과 연결 된 모든 곳을 방문해준다.
  
  
## :black_nib: **Review**
 - List를 초기화 해주지 않아 시간을 많이 뺏겼다. 초기화를 잘해주자.

  
  	

  

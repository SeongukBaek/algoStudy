# [3584] 가장 가까운 공통 조상

## :pushpin: **Algorithm**

DFS

## :round_pushpin: **Logic**

```java
for (int i = 0; i < N - 1; i++) {
    st = new StringTokenizer(br.readLine());
    int parent = Integer.parseInt(st.nextToken());
    int child = Integer.parseInt(st.nextToken());
    graph[child] = parent;
}

st = new StringTokenizer(br.readLine());
A = Integer.parseInt(st.nextToken());
B = Integer.parseInt(st.nextToken());

ancestors.add(A);
makeAncestorSet(A);
if(ancestors.contains(B)) {
    System.out.println(B);
    continue;
}
findCommonAncester(B);
```

```java
private static void findCommonAncester(int cur) {
    if (ancestors.contains(graph[cur])) {
        System.out.println(graph[cur]);
        return;
    }

    findCommonAncester(graph[cur]);
}
```

```java
private static void makeAncestorSet(int cur) {
    if (graph[cur] == 0) {
        return;
    }

    ancestors.add(graph[cur]);
    makeAncestorSet(graph[cur]);
}
```

- 입력 받은 정보를 역방향 그래프로 저장한다.
- A에서 그래프를 순회하며 모든 조상들을 set에 저장한다.
- B에서 그래프를 순회하며 저장한 set에 있는 값에 접근한다면 return 해준다.

## :black_nib: **Review**

- DFS를 두번 돌려 쉽게 풀 수 있는 문제였다.

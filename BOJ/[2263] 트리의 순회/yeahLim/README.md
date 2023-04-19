# [2263] 트리의 순회

## :pushpin: **Algorithm**

트리

## :round_pushpin: **Logic**

```java
/* recursive : 인오더와 포스터오더로 프리오더 구하기 */
private static void getPreOrder(int startI, int endI, int startP, int endP) {
    if(startI >= endI || startP >= endP) return;
    int root = postOrder[endP-1]; // postOrder의 마지막 값 = root
    int index = inOrderIdx[root]; // root값의 index
    System.out.print(root + " ");
    // root를 기준으로
    getPreOrder(startI, index, startP, startP+(index-startI)); // inOrder의 왼쪽 부분
    getPreOrder(index+1, endI, index-startI+startP, endP-1); // inOrder의 오른쪽 부분
}
```

- postOrder의 마지막값이 root값이기 때문에, root값을 기준으로 inOrder를 왼쪽과 오른쪽으로 나눈다.
  - postOrde는 left-right-root 순이기 때문에 마지막값이 root이다.
  - inOrder는 left-root-right 순이기 때문에 루트를 기준으로 왼쪽과 오른쪽으로 나눌 수 있다.
- 위를 모든 노드를 탐색할 때까지 반복한다.
- preOrder는 root-left-right 순이기 때문에, root값을 차례로 출력하면 그것이 preOrder의 순서대로 탐색한 결과이다.

## :black_nib: **Review**

- 트리 문제는 언제나 어렵다~~ 개념을 잘 탑재하고 이후에 다시 한 번 풀어봐야겠다.

# [2263] 트리의 순회

## :pushpin: **Algorithm**

트리

## :round_pushpin: **Logic**

```java
positions = new int[N + 1];
for (int index = 0; index < N; index++) {
    positions[inorder[index]] = index;
}
```

- inorder 배열에서 특정 숫자의 인덱스를 찾기 위해 positions 배열 생성

```java
private static void getPreorder(int inStart, int inEnd, int postStart, int postEnd) {
    // 인덱스 범위가 어긋나는 경우
    if (inStart > inEnd || postStart > postEnd) {
        return;
    }

    // postorder의 마지막 값은 항상 루트
    int root = postorder[postEnd];
    System.out.print(root + " ");

    // root가 inorder의 어느 인덱스에 위치해있는지 찾기
    int rootPosition = positions[root];
    // 왼쪽 자식 노드들의 개수 + 1
    int left = rootPosition - inStart;

    // root 기준 왼쪽 자식 트리 확인
    getPreorder(inStart, rootPosition - 1, postStart, postStart + left - 1);
    // root 기준 오른쪽 자식 트리 확인
    getPreorder(rootPosition + 1, inEnd, postStart + left, postEnd - 1);
}
```

1. postorder에서 맨 뒷 숫자 -> root
2. inorder에서 찾은 맨 뒷 숫자 위치 찾기
3. 해당 위치의 앞은 왼쪽 자식 트리, 뒤는 오른쪽 자식 트리
4. postorder(해당 위치 index - 1)이 L의 루트, postorder(length - 1)이 R의 루트

## :black_nib: **Review**

- Inorder는 LVR, Postorder는 LRV이다. 이를 잘 생각해보면 Preorder인 VLR 형태로 변환할 수 있다라는 아이디어는 예전에 알고리즘 수업 때 들었던 기억이 나서 금방 생각할 수 있었다.
- 근데 구현을 어떻게 해야할지 .. 시간 뺏기기 싫어서 풀이 참고했다 ^^

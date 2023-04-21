# [2263] 트리의 순회

## :pushpin: **Algorithm**

Divide and Conquer

## :round_pushpin: **Logic**

```java
private static void getPreOrder(int inStart, int inEnd, int postStart, int postEnd) {
	if (inStart > inEnd) {
		return;
	}

	int root = postOrder[postEnd];
	sb.append(root + " ");

	int rootIdx = inOrderIdx[root];

	int left = rootIdx - inStart;

	getPreOrder(inStart, rootIdx - 1, postStart, postStart + left - 1);

	getPreOrder(rootIdx + 1, inEnd, postStart + left, postEnd - 1);
}
```

- postorder의 마지막 idx는 root이다.
- inorder root의 idx를 기준으로 좌측은 좌측 자식 노드, 우측은 우측 자식 노드이다.
- 위 로직을 토대로 Node가 하나 남을 때 까지 돌아준다.

## :black_nib: **Review**

- 어렵다...

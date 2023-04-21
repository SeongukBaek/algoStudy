# [2263] 트리의 순회

## :pushpin: **Algorithm**

트리, 분할정복, 재귀

## :round_pushpin: **Logic**

```java
private static void getPreorder(int inStart, int inEnd, int pStart, int pEnd) {
		if(inStart > inEnd || pStart > pEnd) {
			return;
		}

		int inRootIndex = inorderParent[postorder[pEnd]];
		int leftChild = inRootIndex - inStart;
		
		preorder[preIndex++] = postorder[pEnd];
		// 왼쪽 서브 트리
		getPreorder(inStart, inRootIndex -1, pStart, leftChild+pStart-1);
		// 오른쪽 서브 트리
		getPreorder(inRootIndex + 1,inEnd, leftChild+pStart, pEnd - 1);
		
	}
```

- postorder로 루트를 찾고 inorder로 트리의 사이즈를 구한다.

## :black_nib: **Review**

- 로직은 조금만 생각하면 분할 정복으로 할 수 있다는 것을 알 수 있었다.
    - 풀이 방법도 떠올랐는데 문제가 분할 정복을 하면서 전달해야 하는 인자 값을 정하기가 어렵다는 것이다. 계속 고민을 하다가 머리가 복잡해져서 그 부분은 다른 사람의 풀이를 참고하여 해결했다..
    - 하지만 아직도 그렇게 4개의 인자를 줘야하는 이유를 모르겠다.. 트리의 사이즈와 inorder와 postorder의 시작점만 넘겨주면 안되는건가..? 더 공부해 봐야겠다.
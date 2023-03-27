# [150367] 표현 가능한 이진트리

## :pushpin: **Algorithm**

분할 정복, 트리

## :round_pushpin: **Logic**

```java
private boolean canRepresent(long number) {
    StringBuilder toProcessBinary = toFullyBinary(Long.toBinaryString(number));
    int length = toProcessBinary.length();

    // 중간 인덱스 구하고, 이를 기준으로 나눔
    // 중간 인덱스는 해당 트리의 루트 노드를 의미
    int mid = length / 2;
    String left = toProcessBinary.substring(0, mid);
    String right = toProcessBinary.substring(mid + 1);

    // 루트는 0일 수 없기에 바로 반환
    if (toProcessBinary.charAt(mid) == '0') {
        return false;
    }

    return isBinaryTree(left) & isBinaryTree(right);
}
```

- 해당 숫자가 이진트리로 표현가능한지 확인한다.
- 이를 위해서 먼저 숫자를 이진수로 표현하고, 포화 이진트리 문자열로 만들기 위해 문자열의 앞에 0을 추가한다.
- 만약 루트가 0이라면 이는 이진트리로 표현할 수 없는 수이다.
- 그게 아니면 루트를 기준으로 왼쪽 서브 트리, 오른쪽 서브 트리로 나눠 확인한다.

```java
private boolean isBinaryTree(String binary) {
    // 트리가 없다면 true 반환
    if (binary.length() == 0) {
        return true;
    }

    int length = binary.length();

    // 중간 인덱스 = 루트 인덱스 구하기
    int mid = length / 2;
    String left = binary.substring(0, mid);
    String right = binary.substring(mid + 1);

    // 루트가 0이라면 그 왼쪽, 오른쪽 서브 트리 모두 0이어야 한다. 즉 1이 없어야 한다.
    // 더미 노드의 자식 노드에 실제 값이 있는 노드가 올 수 없기 때문
    if (binary.charAt(mid) == '0') {
        return isAllZero(left) & isAllZero(right);
    }

    // 그게 아니라면 양 서브 트리 모두 이진트리인지 확인
    return isBinaryTree(left) & isBinaryTree(right);
}
```

- 서브 트리에서는, 루트가 0이면 모든 자식 노드가 0이어야 하고, 그게 아니라면 재귀 호출을 통해 서브 트리의 서브 트리를 확인하도록 한다.

## :black_nib: **Review**

- 입력 조건이 매우 커서 효율적인 방식을 찾는게 어려웠다. 이걸 어떻게 시간 안에 할 수 있을까..
- 포화 이진트리를 만드는 것이 관건인 문제였다. 아이디어는 비슷하게 찾았는데, 표현할 수 있냐 없냐를 판단하는 조건이 잘못된 것 같아 질문하기를 참고했다.

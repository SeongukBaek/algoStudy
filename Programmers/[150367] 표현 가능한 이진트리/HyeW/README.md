# [150367] 표현 가능한 이진트리

## :pushpin: **Algorithm**

분할 정복, 트리

## :round_pushpin: **Logic**

```java
 //현재 이진트리 높이
int height = (int)Math.ceil(Math.log(nodeCnt+1)/Math.log(2));
//현재 높이일때 포화이진트리의 노드 수
int minNodeCnt = (int)Math.pow(2, height)-1;

//포화이진 트리가 되도록 더미노드를 앞에 붙여줌
StringBuilder sb = new StringBuilder();
while(nodeCnt++ < minNodeCnt ) {
    sb.append("0");
}
binary = sb.toString()+binary;
```
- 필요한 공식
1. 노드의 개수로 트리 높이 구하기(1부터 높이가 시작함) : $log2(n+1)$ 
2. 노드의 높이가 있을 때 포화 이진트리 노드 개수 : $2^h-1$

주어진 이진트리의 높이를 구하고 포화 이진트리가 되기 위해 필요한 노드 수를 구한다.<br/>
그리고 앞에 0을 붙여 포화이진트리를 만든다.

```java
private boolean binaryTreeValidation(String binary) {
    boolean valid = true;
    
    //이진트리 루트 찾기
    int mid = (binary.length()-1)/2;
    char root = binary.charAt(mid);
    
    //루트의 왼쪽 자식트리 
    String left = binary.substring(0,mid);
    //루트의 오른쪽 자식트리
    String right = binary.substring(mid+1,binary.length());
    
    //루트가 0인데 자식이 1이라면 이진트리 충족 못함
    if(root == '0' && (left.charAt((left.length()-1)/2)=='1' || right.charAt((right.length()-1)/2)=='1')){
        return false;
    }
    
    //자식트리의 노드개수가 3이상일 때 자식트리 탐방
    if(left.length() >= 3) {
        valid = binaryTreeValidation(left);
        if(valid) {
            valid = binaryTreeValidation
                (right);
        }
    }
    return valid;
}
```
- 루트를 기준으로 왼쪽 자식 트리, 오른쪽 자식 트리로 나누다.
- 루트가 0인데 자식이 1인 경우 이진트리를 충족시키지 못해 false를 리턴한다.
- 만족한다면 자식 트리를 확인한다.

## :black_nib: **Review**
- 이진트리 노드와 높이를 구하는 수식이 필요해서 진입장벽이 너무 높았다.
- 그리고 리프노드의 개수가 리프노드를 제외한 노드보다 +1 많기때문에 주어진 숫자 하나 당 하나의 포화이진트리가 나온다.
    - 원래 고민했던 부분이 0을 계속 붙인다면 숫자 하나 당 여러개의 포화이진트리가 나오지 않을까란 고민을 했었는데 그 부분은 생각을 안해도 됐었다.
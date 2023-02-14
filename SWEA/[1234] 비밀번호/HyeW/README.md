# [1234] 비밀번호

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

주어진 값을 LinkedList에 저장해서 모든 수를 읽으며 중복되는 숫자를 삭제해주었다.<br/>

```java
static int removeNum(int left, int right) {
    while ((left >= 0) && (right < number.size())) {

        if (number.get(left) != number.get(right)) {
            break;
        }	

        number.remove(left);
        number.remove(left);
        
        left--;
        right--;
    }
    return left+1;
}
```

비교할 값의 index 2개를 받아서 두 수가 같다면 계속 삭제하는 방법으로 문제를 해결했다.<br/>
여기서 left에 있는 값을 삭제하면 삭제한 값의 오른쪽 값들의 index가 1씩 작아지니 left index를 두번 삭제했다.<br/>

## :black_nib: **Review**
- 주어진 수열에서 특정 값을 삭제하는 문제라서 배열의 삭제가 쉬운 연결 리스트를 이용해서 문제를 해결했다.

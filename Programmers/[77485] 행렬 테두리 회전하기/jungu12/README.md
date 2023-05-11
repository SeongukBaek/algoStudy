# [77485] 행렬 테두리 회전하기

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
void rotateArr(int idx) {
    int min = Integer.MAX_VALUE;
    int minRow = queries[idx][0] - 1;
    int minCol = queries[idx][1] - 1;
    int maxRow = queries[idx][2] - 1;
    int maxCol = queries[idx][3] - 1;

    int start = arr[minRow][minCol];
    for (int i = minRow; i < maxRow; i++) {
        arr[i][minCol] = arr[i + 1][minCol];
        min = Math.min(min, arr[i][minCol]);
    }

    for (int i = minCol; i < maxCol; i++) {
        arr[maxRow][i] = arr[maxRow][i + 1];
        min = Math.min(min, arr[maxRow][i]);
    }

    for (int i = maxRow; i > minRow; i--) {
        arr[i][maxCol] = arr[i - 1][maxCol];
        min = Math.min(min, arr[i][maxCol]);
    }

    for (int i = maxCol; i > minCol + 1; i--) {
        arr[minRow][i] = arr[minRow][i - 1];
        min = Math.min(min, arr[minRow][i]);
    }

    arr[minRow][minCol + 1] = start;
    min = Math.min(min, start);

    answer[idx] = min;
}
```

- 배열을 돌리면서 이동 한 숫자 중 최소값을 구한다.
- 가독성을 고려하여 queries 배열 안 값들을 새로운 변수에 할당해주었다 ㅎㅎ

## :black_nib: **Review**

- 단순 구현 문제였다.

# [150366] 표 병합

## :pushpin: **Algorithm**

Union Find

## :round_pushpin: **Logic**

```java
private void merge(int r1, int c1, int r2, int c2) {
        if (r1 == r2 && c1 == c2 || parents[r1][c1] == parents[r2][c2]) {
            return;
        }

        int parent1 = parents[r1][c1];
        int parent2 = parents[r2][c2];
        
        // 인접한 경우와 그렇지 않은 경우를 나눌 필요가 없지 않을까
        // parent1에 해당하는 값이 null이면, parent2에 해당하는 값을 가진다. 
        if (getValue(parent1) == null) {
            updateParents(parent1, parent2);
            values[r1][c1] = null;
        } else {
            updateParents(parent2, parent1);
            values[r2][c2] = null;
        }
    }
```

- merge 시, 각 좌표별 부모 좌표 번호를 가져와서, 해당 부모 좌표의 값이 존재하는지 확인한다.
- 경우에 따라 union 작업을 수행한다.

```java
private void unmerge(int mr, int mc) {
    int parent = parents[mr][mc];
    String value = getValue(parent);

    for (int r = 0; r < SIZE; r++) {
        for (int c = 0; c < SIZE; c++) {
            if (parents[r][c] == parent) {
                parents[r][c] = r * SIZE + c;
                values[r][c] = null;
            }
        }
    }

    values[mr][mc] = value;
}
```

- 좌표에 해당하는 부모 좌표 번호와, 그 부모 좌표의 값을 가져온다.
- 모든 셀을 탐색하면서, 해당 부모 좌표 번호를 부모로 가지는 셀들을 초기화한다.

## :black_nib: **Review**

- 여러 좌표를 하나의 좌표로 합쳐야 하는 로직이 보여서, Union - Find를 사용해야겠다고 생각했다.
  - 각 셀별로 번호를 붙이고, 그 번호를 통해 연결성을 관리하는 방식!
- 또한 구현할 함수들이 많아보여서, 먼저 함수로 분리를 한 후, 하나씩 구현하는 방식으로 해결하니 생각보다 금방 해결됐다.

# [131130] 혼자 놀기의 달인

## :pushpin: **Algorithm**

DFS

## :round_pushpin: **Logic**

```java
void makeBoxGroups() {
    //방문하지 않은 박스를 찾는다.
    for (int i = 0; i < cards.length; i++) {
        //이미 방문한 박스라면 continue
        if(visited[i]) {
            continue;
        }

        //해당 박스를 시작으로 DFS로 새로운 박스 그룹을 만든다.
        boxGroups.add(new ArrayList<>());
        boxGroups.get(boxGroups.size() - 1).add(i);
        visited[i] = true;
        openBox(cards[i] - 1);
    }
}

void openBox(int idx) {
    //이미 방문한 박스라면 continue
    if(visited[idx]) {
        return;
    }

    visited[idx] = true;
    //박스 그룹에 추가
    boxGroups.get(boxGroups.size() - 1).add(idx);
    //다음 박스 방문
    openBox(cards[idx] - 1);
}
```

- 배열의 모든 row, column, 대각선을 순회하며 게임을 승리한 사람이 있는지 확인한다.

```java
int findMaxScore() {
        int max = 0;
        //박스 그룹 중 2개를 골라 가장 곱이 큰 값을 return
        for (int i = 0; i < boxGroups.size() - 1; i++) {
            for (int j = i + 1; j < boxGroups.size(); j++) {
                max = Math.max(max, (boxGroups.get(i).size()) * (boxGroups.get(j).size()));
            }
        }
        return max;
    }
```

- 박스 그룹 중 2개를 골라 가장 곱이 큰 값을 반환 해준다.

## :black_nib: **Review**

- DFS로 간단하게 풀 수 있는 문제였다!

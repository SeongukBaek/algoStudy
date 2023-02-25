# [14502] 연구소 - C++

## :pushpin: **Algorithm**

그래프 이론, 그래프 탐색, BFS, 브루트포스

## :round_pushpin: **Logic**

```c++
void set_boundary(int idx, int wall);
```

- 재귀호출을 통해 벽을 세우고, 3개의 벽을 모두 세운 후 바이러스를 퍼뜨리는 spreadout() 함수와 연구소의 정보를 가지고 있는 map배열을 직전의 상태로 초기화하고 안전구역을 계산하는 map_set() 함수를 호출하는 함수

```c++
int** visited;
```

- 방문 여부를 저장하는 배열, -1로 초기화

```c++
if (map[i][j] == 2)
    two[idx2].first = i, two[idx2++].second = j;
else if (map[i][j] == 0)
    zero[idx0].first = i, zero[idx0++].second = j;
```

- 연구소의 해당 좌표 정보가 0인 경우와 2인 경우에 대해 각각의 배열을 만들어 좌표 저장, 후에 벽을 세우거나 바이러스를 퍼뜨리는 함수에서 사용

```c++
void set_boundary(int idx, int wall) {
	if (wall == 0) {
		spreadout();
		map_set();
	}
	else {
		wall--;
		for (int i = idx; i < idx0; i++) {
			copy_map[zero[i].first][zero[i].second] = 1;
			set_boundary(i + 1, wall);
			copy_map[zero[i].first][zero[i].second] = 0;
		}
	}
}
```

- 벽을 모두 세운 경우, BFS를 통해 2인 좌표로부터 시작하여 바이러스를 퍼뜨리는 함수와 안전구역 계산 후 연구소를 바이러스를 퍼뜨리기 전의 상태로 돌려놓는 함수 호출
- 벽을 아직 세우는 중인 경우, 0인 좌표를 저장한 zero배열에서 순차적으로 하나씩 선택하여 벽을 세우고, set_boundary함수 재귀호출, 재귀호출한 함수가 종료되어 리턴되면 세웠던 벽을 허물어 다시 0으로 바꾸고 다음 0인 좌표 탐색

```c++
if (copy_map[i][j] == 0)
    area++;
else if (copy_map[i][j] == 2) {
    for (int a = 0; a < idx2; a++) {
        if (i == two[a].first && j == two[a].second)
            check++;
    }
    if (check == 0)
        copy_map[i][j] = 0;
    check = 0;
}
```

- 안전구역을 계산하고 2인 좌표 중 원래 연구소에 존재하던 2가 아닌 경우에 대해 다시 0으로 복구하는 함수

## :black_nib: **Review**

- 이번 주차 중 아무래도 가장 난이도가 있었던 문제, 처음엔 어떻게 접근해야 할지 너무 막막했으나 벽을 세우고, 바이러스를 퍼뜨리고, 안전구역을 계산한다는 3 부분으로 나누어 생각
- 세 부분 중 벽을 세우는 부분이 가장 머리를 많이 썼던 것 같다. 브루트 포스 알고리즘을 사용하기 때문에 완전 탐색을 해야한다는 점은 알고 있었지만 무작위로 벽을 선택하여 최대 안전 구역을 도출해야하는지 혹은 벽을 세우는 데 있어 최선의 선택을 하는 방법이 존재하는지를 계속 고민해봤음
- 완전 탐색은 하지만 조건을 두어, 어차피 벽은 0인 좌표에만 세우기 때문에 0인 좌표정보만 저장하는 배열을 생성하여 이 배열만 가지고 모든 경우에 대해 벽을 세워 해결
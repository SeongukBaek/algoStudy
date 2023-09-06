# [3584] 가장 가까운 공통 조상

## :pushpin: **Algorithm**

LCA

## :round_pushpin: **Logic**

```javascript
const visited = Array.from({ length: N + 1 }, () => 0); // root 노드를 찾기 위한 배열
const node = Array.from({ length: N + 1 }, () => []); // 자식 노드의 정보 저장
const parents = Array.from({ length: N + 1 }, () => 0);
const level = Array.from({ length: N + 1 }, () => 0);
```

- root 노드를 찾을때 사용할 배열인 `visited`와 자식 노드와 부모 노드의 정보를 저장할 배열, 각 노드의 레벨을 저장할 배열을 각각 만든다.

```javascript
function setTreeLevel(root, node, level) {
  const start = root;
  let count = 1;
  level[start] = count++;
  const q = [];

  for (let item of node[start]) {
    q.push(item);
  }

  while (q.length) {
    const size = q.length;

    for (let i = 0; i < size; i++) {
      const cur = q.shift();
      level[cur] = count;
      for (let item of node[cur]) {
        q.push(item);
      }
    }
    count++;
  }
}
```

- 두 노드의 높이를 같게 맞춘다.
- 이후 노드의 값이 같아 질때까지 노드의 레벨을 한 칸씩 올린다.

## :black_nib: **Review**

- 어디서 들어본 적이 있는 알고리즘이라서 참고 자료를 보지 않고 비슷하게 구현을 하려고 노력했다.
- 그러다보니 배열을 너무 여러개 사용한 것이 좀 신경쓰여서 추가로 다른 사람들의 풀이를 봐야겠다는 생각이 들었다.

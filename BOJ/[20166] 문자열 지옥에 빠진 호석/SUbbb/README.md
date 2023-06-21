# [20166] 문자열 지옥에 빠진 호석

## :pushpin: **Algorithm**

BFS, Map

## :round_pushpin: **Logic**

```java
keys = new String[K];
for (int index = 0; index < K; index++) {
    String line = br.readLine();
    keys[index] = line;
    wordCount.put(line, 0);
}
```

- 중복되는 문자열이 있더라도, 정답을 순서대로 출력하기 위해서 문자열들을 배열에 저장해둔다.

```java
private static void makeString(int x, int y) {
    Queue<Word> words = new LinkedList<>();
    words.add(new Word(x, y, String.valueOf(map[x][y])));

    while(!words.isEmpty()) {
        Word cur = words.poll();

        wordCount.put(cur.result, wordCount.getOrDefault(cur.result, 0) + 1);

        for (int[] move : moves) {
            int nx = (cur.x + move[0]) % N;
            int ny = (cur.y + move[1]) % M;

            if (nx < 0) {
                nx += N;
            }
            if (ny < 0) {
                ny += M;
            }

            // 이미 최대 길이라면 더 이상 진행 X
            if (cur.result.length() == 5) {
                continue;
            }

            words.add(new Word(nx, ny, cur.result + map[nx][ny]));
        }
    }
}
```

- BFS를 수행하면서, 만들어지는 문자열에 대해서 경우의 수를 모두 구한다.
- 만들어진 문자열의 길이가 최대 길이(5)를 넘기는 경우는 패스한다.

## :black_nib: **Review**
- 처음에는 맵을 확장시켜서, 굳이 인덱스 조정을 안해주게끔 하려 했는데, 오히려 탐색 범위가 늘어나서 시간이 많이 걸릴까봐 해보진 않았다.
- 단순히 맵 쓰고, BFS하면 되는 문제였다!
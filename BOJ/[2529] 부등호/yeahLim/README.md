# [2529] 부등호

## :pushpin: **Algorithm**

브루트포스, 백트래킹

## :round_pushpin: **Logic**

```java
private static List<String> result = new ArrayList<>();
```

- 결과값 담을 arrayList<string> 객체

```java
private static void dfs(String n, int depth) {
        if (depth == k + 1) {
            result.add(n);
            return;
        }
        for (int i = 0; i <= 9; i++) {
            if (depth == 0 || !visited[i] && check(n.charAt(n.length() - 1) - '0', i, signs[depth - 1])) {
                visited[i] = true;
                dfs(n + i, depth + 1);
                visited[i] = false;
            }
        }
    }
```

- 첫번째 숫자와 두번째 숫자와 부등호를 check함수에 넘겨 조건에 부합하고, 0~9까지의 모든 숫자가 다 부합하면 result에 문자열 넣어주고 종료
  - 부등호의 개수가 하나 더 많아지면 종료

## :black_nib: **Review**
- List 타입에 ArrayList 객체 생성
  - List가 ArrayList의 부모 객체이기 때문에 더 자유롭게 사용 가능

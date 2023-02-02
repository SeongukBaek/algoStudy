# [1759] 암호 만들기

## :pushpin: **Algorithm**

브루트포스, 백트래킹

## :round_pushpin: **Logic**

```java
words = Arrays.stream(br.readLine().split(" ")).sorted().collect(Collectors.toList());
```

- 주어진 소문자들을 정렬하여 `List<String>`으로 저장한다.
  - 주어진 조건인 **암호를 이루는 알파벳이 암호에서 증가하는 순서로 배열**을 만족시키기 위해서이다.

```java
private static void makePossiblePasswords(StringBuilder password, boolean[] isVisited, int vowelCnt, int consonantCnt, int now) {
    if (password.length() == L && vowelCnt >= LEAST_VOWEL_COUNT && consonantCnt >= LEAST_CONSONANT_COUNT) {
        print(password);
        return;
    }

    for (int index = now; index < C; index++) {
        if (isVisited[index]) {
            continue;
        }

        String current = words.get(index);
        password.append(current);
        isVisited[index] = true;
        if (vowelSet.contains(current)) {
            makePossiblePasswords(password, isVisited, vowelCnt + 1, consonantCnt, index + 1);
        } else {
            makePossiblePasswords(password, isVisited, vowelCnt, consonantCnt + 1, index + 1);
        }
        isVisited[index] = false;
        password.deleteCharAt(password.length() - 1);
    }
}
```

- 암호를 생성하다가 출력하는 조건은 길이가 L이고, 자음 개수가 최소 2개, 모음 개수가 최소 1개를 만족하는 경우이다.
- 한 문자가 중복으로 암호에 포함될 수 없기에 방문 여부를 확인하면서, 생성된 암호에 들어가는 자음과 모음의 개수를 갱신하면서 재귀호출한다.

## :black_nib: **Review**
- 자음군에서 뽑고, 모음군에서 뽑는 방식으로 조합을 생각했다. 
  - 최소 개수 조건을 채운 이후 구현에 대해 아이디어가 떠오르지 않아서 변경했다.
- 백트래킹 방식으로, 현재 암호의 상태를 체크하면서 가능한 문자를 붙이면서 진행했다.
- 주어진 데이터를 어떤 자료형으로 사용할지와, 암호를 `List<String>`으로 저장할지, `StringBuilder`로 저장할지에 대한 고민을 해본 문제였다.
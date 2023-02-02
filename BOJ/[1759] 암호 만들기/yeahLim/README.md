# [1759] 암호 만들기

## :pushpin: **Algorithm**

브루트포스, 백트래킹

## :round_pushpin: **Logic**

```java
static Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
```

- 모음을 set에 넣어, set 안에 있는 원소와의 일치 여부로 모음/자음 판별

```java
for (int i = 0 ; i < c; i++) {
                if (visited[i]) {
                    if (isVowel(alph[i])) {
                        vowel += 1;
                    } else {
                        consonant +=1;
                    }

                    sb.append(alph[i]);
                }
            }

            if (vowel >= 1 && consonant >= 2) {
                System.out.println(sb);
            }

        } else {
            for (int i = depth; i < c; i++) {
                visited[i] = true;
                dfs(i+1, length+1);
                visited[i] = false;
            }
        }
```

- 먼저 모든 문자열들의 조합을 만든 후, 문자열 내의 모음과 자음의 개수가 조건과 부합하면 출력
  - 비밀번호의 조건 : 모음 1개 이상, 자음 2개 이상

## :black_nib: **Review**
- 처음부터 모음 1개 이상 자음 2개 이상의 문자열들의 조합만으로 조건을 충족한 뒤, 바로 출력하려고 했으나 하다가 막혀서 방향을 틀었다. 
  - 조합, 순열에 대한 공부 필요
